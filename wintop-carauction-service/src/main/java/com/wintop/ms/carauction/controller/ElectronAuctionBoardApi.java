package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.PageEntity;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.ListEntity;
import com.wintop.ms.carauction.entity.TblAuctionBoard;
import com.wintop.ms.carauction.entity.TblBaseStation;
import com.wintop.ms.carauction.service.TblAuctionBoardService;
import com.wintop.ms.carauction.service.TblBaseStationService;
import com.wintop.ms.carauction.util.utils.CarAutoUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/service/electronAuctionBoard")
public class ElectronAuctionBoardApi {
    @Autowired
    private TblAuctionBoardService tblAuctionBoardService;
    @Autowired
    private TblBaseStationService tblBaseStationService;

    private static final Logger logger = LoggerFactory.getLogger(ElectronAuctionBoardApi.class);

    /***
     * 查询所有拍牌
     * @return
     */
    @RequestMapping(value = "/selectAuctionBoardList",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<TblAuctionBoard>> selectAuctionBoardList(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<TblAuctionBoard>> result = new ServiceResult<>();
        try {
            Map<String,Object> paramMap = new HashMap<>();
            PageEntity pageEntity= CarAutoUtils.getPageParam(obj);
            paramMap.put("startRowNum",pageEntity.getStartRowNum());
            paramMap.put("endRowNum",pageEntity.getEndRowNum());
            if(StringUtils.isNotBlank(obj.getString("boardName"))){
                paramMap.put("boardName",obj.get("boardName"));
            }
            if(StringUtils.isNotBlank(obj.getString("stationRealId"))){
                paramMap.put("stationRealId",obj.get("stationRealId"));
            }
            List<TblAuctionBoard> boardList = tblAuctionBoardService.selectByExample(paramMap);
            int count = tblAuctionBoardService.countByExample(paramMap);
            ListEntity<TblAuctionBoard> listEntity = new ListEntity<>();
            listEntity.setList(boardList);
            listEntity.setCount(count);
            result.setResult(listEntity);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            logger.info("查询拍牌接口失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     * 保存拍牌
     * @param obj
     * @return
     */
    @RequestMapping(value = "/saveAuctionBoard",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<TblAuctionBoard> saveAuctionBoard(@RequestBody JSONObject obj) {
        ServiceResult<TblAuctionBoard> result = new ServiceResult<>();
        try {
            TblAuctionBoard auctionBoard = JSONObject.toJavaObject(obj,TblAuctionBoard.class);
            String[] stationRealIds = auctionBoard.getStationRealIds().split(",");
            for(String stationRealId:stationRealIds){
                //,,时间切割车牌只能有一个
                TblAuctionBoard auctionBoard0 = tblAuctionBoardService.selectCuttingSignByBsId(stationRealId,auctionBoard.getCuttingSign());
                if(auctionBoard0 != null){
                    result.setResult(auctionBoard);
                    result.setError(ResultCode.DUPLICATE_ADD.strValue(),"调价器只能有一个");
                    return result;
                }
            }
            //物理ID不能重复
            TblAuctionBoard auctionBoard1 = tblAuctionBoardService.selectByRealId(auctionBoard.getBoardRealId());
            if(auctionBoard1!=null){
                result.setResult(auctionBoard);
                result.setError(ResultCode.DUPLICATE_ADD.strValue(),ResultCode.DUPLICATE_ADD.getRemark());
                return result;
            }else{
                /*TblBaseStation baseStation = tblBaseStationService.selectByRealCode(auctionBoard.getStationRealCode());
                if(baseStation==null){
                    result.setResult(auctionBoard);
                    result.setSuccess(ResultCode.NO_OBJECT.strValue(),"基站不存在");
                    return result;
                }else{
                    auctionBoard.setBsId(baseStation.getId());
                    tblAuctionBoardService.insert(auctionBoard);
                    result.setResult(auctionBoard);
                    result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
                    return result;
                }*/
                tblAuctionBoardService.insert(auctionBoard);
                result.setResult(auctionBoard);
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
                return result;
            }
        }catch (Exception e){
            logger.info("保存拍牌失败",e);
            result.setResult(null);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
            return result;
        }
    }

    /**
     * 更新拍牌
     * @param obj
     * @return
     */
    @RequestMapping(value = "/updateAuctionBoard",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<TblAuctionBoard> updateAuctionBoard(@RequestBody JSONObject obj) {
        ServiceResult<TblAuctionBoard> result = new ServiceResult<>();
        try {
            TblAuctionBoard auctionBoard = JSONObject.toJavaObject(obj,TblAuctionBoard.class);
            //,,时间切割车牌只能有一个
            String[] stationRealIds = auctionBoard.getStationRealIds().split(",");
            for(String stationRealId:stationRealIds){
                //,,时间切割车牌只能有一个
                TblAuctionBoard auctionBoard0 = tblAuctionBoardService.selectCuttingSignByBsId(stationRealId,auctionBoard.getCuttingSign());
                if(auctionBoard0 != null){
                    result.setResult(auctionBoard);
                    result.setError(ResultCode.DUPLICATE_ADD.strValue(),"调价器只能有一个");
                    return result;
                }
            }
            //更新操作
            TblAuctionBoard auctionBoard1 = tblAuctionBoardService.selectByRealId(auctionBoard.getBoardRealId());
            if(auctionBoard1!=null && auctionBoard.getId().compareTo(auctionBoard1.getId())!=0){
                result.setResult(auctionBoard);
                result.setError(ResultCode.DUPLICATE_ADD.strValue(),ResultCode.DUPLICATE_ADD.getRemark());
                return result;
            }else{
                /*TblBaseStation baseStation = tblBaseStationService.selectByRealCode(auctionBoard.getStationRealCode());
                if(baseStation==null){
                    result.setResult(auctionBoard);
                    result.setSuccess(ResultCode.NO_OBJECT.strValue(),"基站不存在");
                    return result;
                }else{
                    auctionBoard.setBsId(baseStation.getId());
                    tblAuctionBoardService.updateByPrimaryKeySelective(auctionBoard);
                    result.setResult(auctionBoard);
                    result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
                    return result;
                }*/
                tblAuctionBoardService.updateByPrimaryKeySelective(auctionBoard);
                result.setResult(auctionBoard);
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
                return result;
            }
        }catch (Exception e){
            logger.info("更新拍牌失败",e);
            result.setResult(null);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
            return result;
        }
    }

    /**
     * 删除拍牌
     * @param obj
     * @return
     */
    @RequestMapping(value = "/deleteAuctionBoard",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<TblAuctionBoard> deleteAuctionBoard(@RequestBody JSONObject obj) {
        ServiceResult<TblAuctionBoard> result = new ServiceResult<>();
        try {
            TblAuctionBoard AuctionBoard = JSONObject.toJavaObject(obj,TblAuctionBoard.class);
            //删除操作
            tblAuctionBoardService.updateDeleteFlag(AuctionBoard);
            result.setResult(AuctionBoard);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            return result;
        }catch (Exception e){
            logger.info("删除拍牌失败",e);
            result.setResult(null);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
            return result;
        }
    }

    /**
     * 查询拍牌
     * @param obj
     * @return
     */
    @RequestMapping(value = "/selectAuctionBoard",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<TblAuctionBoard> selectAuctionBoard(@RequestBody JSONObject obj) {
        ServiceResult<TblAuctionBoard> result = new ServiceResult<>();
        try {
            TblAuctionBoard AuctionBoard = tblAuctionBoardService.selectByPrimaryKey(obj.getLong("id"));
            result.setResult(AuctionBoard);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            return result;
        }catch (Exception e){
            logger.info("查询拍牌失败",e);
            result.setResult(null);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
            return result;
        }
    }

}
