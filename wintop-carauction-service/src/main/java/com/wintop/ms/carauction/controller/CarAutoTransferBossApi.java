package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.config.TransferStatusEnum;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAutoInfoDetail;
import com.wintop.ms.carauction.entity.CarAutoTransfer;
import com.wintop.ms.carauction.service.ICarAutoInfoDetailService;
import com.wintop.ms.carauction.service.ICarAutoTransferService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangzijuan
 * @Description:PC端过户手续
 * @date 2018-03-26
 */
@RestController
@RequestMapping("/service/transferBoss")
public class CarAutoTransferBossApi {
    private static final Logger logger = LoggerFactory.getLogger(CarAutoTransferBossApi.class);
    @Autowired
    private ICarAutoTransferService transferService;

    @Autowired
    private ICarAutoInfoDetailService autoInfoDetailService;
    /***
     * 确认过户
     * @param obj
     * @return
     */
    @ApiOperation(value = "确认过户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "transferId",value = "过户id",required = true,dataType = "long"),
            @ApiImplicitParam(name = "status",value = "状态",required = true,dataType = "string"),
            @ApiImplicitParam(name = "managerId",value = "登陆人id",required = true,dataType = "string"),
            @ApiImplicitParam(name = "moveToWhere",value = "迁往何地 1本市外迁均可，2只能外迁，3只能本市",required = true,dataType = "string"),
            @ApiImplicitParam(name = "moveAddress",value = "迁往具体地址",required = true,dataType = "string"),
            @ApiImplicitParam(name = "fileType",value = "附件类型 1 图片 2附件",required = false,dataType = "string"),
            @ApiImplicitParam(name = "fileUrl",value = "附件路径",required = false,dataType = "string"),
            @ApiImplicitParam(name = "handleTime",value = "办理时间 格式：2018-02-26 12:00:00",required = true,dataType = "string"),
            @ApiImplicitParam(name = "remark",value = "备注",required = false,dataType = "string"),
    })
    @PostMapping(value = "/confirmTransfer",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> confirmTransfer(@RequestBody JSONObject obj){
        logger.info("确认过户");
        ServiceResult<Map<String,Object>>result=new ServiceResult<>();
        try{
            CarAutoTransfer transfer=transferService.selectByPrimaryKey(obj.getLong("transferId")).getResult();
            //查看过户信息，过户状态为 ：1过户事宜确定中  进行确认过户
            boolean flag=transfer!=null && TransferStatusEnum.TRANSFER_HANLDING.value().equals(transfer.getStatus()) && !transfer.getStatus().equals(obj.getString("status"));
            if(flag){
                Integer i=transferService.submitTransfer(obj);
                if (i>0){
                    result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
                }else if (i==0){
                    result.setError(ResultCode.FAIL.strValue(),ResultCode.FAIL.getRemark());
                }else {
                    result.setError(ResultCode.NO_PARAM.strValue(),ResultCode.NO_PARAM.getRemark());
                }
            }else {
                result.setError(ResultCode.FORBID_CONFIRM.strValue(),ResultCode.FORBID_CONFIRM.getRemark());
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /***
     * 确认出牌
     * @param obj
     * @return
     */
    @ApiOperation(value = "确认出牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "transferId",value = "过户id",required = true,dataType = "long"),
            @ApiImplicitParam(name = "status",value = "状态",required = true,dataType = "string"),
            @ApiImplicitParam(name = "managerId",value = "登陆人id",required = true,dataType = "string"),
            @ApiImplicitParam(name = "fileType",value = "附件类型 1 图片 2附件 1 图片 2附件",required = false,dataType = "string"),
            @ApiImplicitParam(name = "fileUrl",value = "附件路径",required = false,dataType = "string"),
            @ApiImplicitParam(name = "handleTime",value = "办理时间",required = true,dataType = "string"),
            @ApiImplicitParam(name = "remark",value = "备注",required = false,dataType = "string"),
    })
    @PostMapping(value = "/outLicensePlate",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> outLicensePlate(@RequestBody JSONObject obj){
        logger.info("确认出牌");
        ServiceResult<Map<String,Object>>result=new ServiceResult<>();
        try{
            CarAutoTransfer transfer=transferService.selectByPrimaryKey(obj.getLong("transferId")).getResult();
            //查看过户信息，过户状态为 ：2出牌确认中  确认出牌
            boolean flag=transfer!=null && TransferStatusEnum.PLATE_HANLDING.value().equals(transfer.getStatus()) && !transfer.getStatus().equals(obj.getString("status"));
            if(flag){
                //如果是外迁 move_to_where=2  确认出牌后状态变为 3交档确认中
                if("2".equals(transfer.getMoveToWhere())){
                    obj.put("status",TransferStatusEnum.HANDOVER_FILE.value());
                    //如果是本市 move_to_where=3  确认出牌后状态变为 5手续上传中
                }else if("3".equals(transfer.getMoveToWhere())){
                    obj.put("status",TransferStatusEnum.PROCEDURE_UPLOADING.value());
                }else{
                    result.setError(ResultCode.FAIL.strValue(),ResultCode.FAIL.getRemark());
                    logger.info("不能确认本市还是外迁");
                    return result;
                }
                Integer i=transferService.submitTransfer(obj);
                if (i>0){
                    result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
                }else if (i==0){
                    result.setError(ResultCode.FAIL.strValue(),ResultCode.FAIL.getRemark());
                }else {
                    result.setError(ResultCode.NO_PARAM.strValue(),ResultCode.NO_PARAM.getRemark());
                }
            }else {
                result.setError(ResultCode.FORBID_CONFIRM.strValue(),ResultCode.FORBID_CONFIRM.getRemark());
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /***
     * 确认交档
     * @param obj
     * @return
     */
    @ApiOperation(value = "确认交档")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "transferId",value = "过户id",required = true,dataType = "long"),
            @ApiImplicitParam(name = "status",value = "状态",required = true,dataType = "string"),
            @ApiImplicitParam(name = "managerId",value = "登陆人id",required = true,dataType = "string"),
            @ApiImplicitParam(name = "fileType",value = "附件类型 1 图片 2附件 1 图片 2附件",required = false,dataType = "string"),
            @ApiImplicitParam(name = "fileUrl",value = "附件路径",required = false,dataType = "string"),
            @ApiImplicitParam(name = "handleTime",value = "办理时间",required = true,dataType = "string"),
            @ApiImplicitParam(name = "remark",value = "备注",required = false,dataType = "string"),
    })
    @PostMapping(value = "/submitFiles",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> submitFiles(@RequestBody JSONObject obj){
        logger.info("确认交档");
        ServiceResult<Map<String,Object>>result=new ServiceResult<>();
        try{
            CarAutoTransfer transfer=transferService.selectByPrimaryKey(obj.getLong("transferId")).getResult();
            //查看过户信息，过户状态为 ：3交档确认中  确认交档
            boolean flag=transfer!=null && TransferStatusEnum.HANDOVER_FILE.value().equals(transfer.getStatus()) && !transfer.getStatus().equals(obj.getString("status"));
            if(flag){
                Integer i=transferService.submitTransfer(obj);
                if (i>0){
                    result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
                }else if (i==0){
                    result.setError(ResultCode.FAIL.strValue(),ResultCode.FAIL.getRemark());
                }else {
                    result.setError(ResultCode.NO_PARAM.strValue(),ResultCode.NO_PARAM.getRemark());
                }
            }else {
                result.setError(ResultCode.FORBID_CONFIRM.strValue(),ResultCode.FORBID_CONFIRM.getRemark());
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /***
     * 确认提档
     * @param obj
     * @return
     */
    @ApiOperation(value = "确认提档")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "transferId",value = "过户id",required = true,dataType = "long"),
            @ApiImplicitParam(name = "status",value = "状态",required = true,dataType = "string"),
            @ApiImplicitParam(name = "managerId",value = "登陆人id",required = true,dataType = "string"),
            @ApiImplicitParam(name = "fileType",value = "附件类型 1 图片 2附件 1 图片 2附件",required = false,dataType = "string"),
            @ApiImplicitParam(name = "fileUrl",value = "附件路径",required = false,dataType = "string"),
            @ApiImplicitParam(name = "handleTime",value = "办理时间",required = true,dataType = "string"),
            @ApiImplicitParam(name = "remark",value = "备注",required = false,dataType = "string"),
    })
    @PostMapping(value = "/takeFiles",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> takeFiles(@RequestBody JSONObject obj){
        logger.info("确认提档");
        ServiceResult<Map<String,Object>>result=new ServiceResult<>();
        try{
            CarAutoTransfer transfer=transferService.selectByPrimaryKey(obj.getLong("transferId")).getResult();
            //查看过户信息，过户状态为 ：4提档确认中  确认提档
            boolean flag=transfer!=null && TransferStatusEnum.EXTRACT_FILE.value().equals(transfer.getStatus()) && !transfer.getStatus().equals(obj.getString("status"));
            if(flag){
                Integer i=transferService.submitTransfer(obj);
                if (i>0){
                    result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
                }else if (i==0){
                    result.setError(ResultCode.FAIL.strValue(),ResultCode.FAIL.getRemark());
                }else {
                    result.setError(ResultCode.NO_PARAM.strValue(),ResultCode.NO_PARAM.getRemark());
                }
            }else {
                result.setError(ResultCode.FORBID_CONFIRM.strValue(),ResultCode.FORBID_CONFIRM.getRemark());
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /***
     * 上传手续
     * @param obj
     * @return
     */
    @ApiOperation(value = "上传手续")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "transferId",value = "过户id",required = true,dataType = "long"),
            @ApiImplicitParam(name = "status",value = "状态",required = true,dataType = "string"),
            @ApiImplicitParam(name = "managerId",value = "登陆人id",required = true,dataType = "string"),
            @ApiImplicitParam(name = "fileType",value = "附件类型 1 图片 2附件 1 图片 2附件",required = false,dataType = "string"),
            @ApiImplicitParam(name = "fileUrl",value = "附件路径",required = false,dataType = "string"),
            @ApiImplicitParam(name = "remark",value = "备注",required = false,dataType = "string"),
    })
    @PostMapping(value = "/uploadProcedures",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> uploadProcedures(@RequestBody JSONObject obj){
        logger.info("上传手续");
        ServiceResult<Map<String,Object>>result=new ServiceResult<>();
        try{
            obj.put("handleTime",new Date());
            CarAutoTransfer transfer=transferService.selectByPrimaryKey(obj.getLong("transferId")).getResult();
            //查看过户信息，过户状态为 ：5手续上传中 或者 7 手续回传不通过   上传手续
            boolean flag=transfer!=null && (TransferStatusEnum.PROCEDURE_UPLOADING.value().equals(transfer.getStatus()) || TransferStatusEnum.PROCEDURE_NO_PASS.value().equals(transfer.getStatus())) && !transfer.getStatus().equals(obj.getString("status"));
            if(flag){
                Integer i=transferService.submitTransfer(obj);
                if (i>0){
                    result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
                }else if (i==0){
                    result.setError(ResultCode.FAIL.strValue(),ResultCode.FAIL.getRemark());
                }else {
                    result.setError(ResultCode.NO_PARAM.strValue(),ResultCode.NO_PARAM.getRemark());
                }
            }else {
                result.setError(ResultCode.FORBID_CONFIRM.strValue(),ResultCode.FORBID_CONFIRM.getRemark());
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }
    @ApiImplicitParam(name = "transferId",value = "过户id",required = true,dataType = "long")
    @ApiOperation(value = "查看审批信息")
    @PostMapping(value = "/getApproveTransfer",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<CarAutoTransfer> getApproveTransfer(@RequestBody JSONObject obj){
        ServiceResult<CarAutoTransfer> result=transferService.selectByPrimaryKey(obj.getLong("transferId"));
       if(result.getSuccess() && result.getResult()!=null && result.getResult().getAuthManager()==null){
            result.setResult(null);
            result.setError(ResultCode.NO_OBJECT.strValue(),ResultCode.NO_OBJECT.getRemark());
       }
        return result;
    }

    @ApiImplicitParam(name = "transferId",value = "过户id",required = true,dataType = "long")
    @ApiOperation(value = "确认过户时查看过户信息")
    @PostMapping(value = "/getTransferById",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> getTransferById(@RequestBody JSONObject obj){
            ServiceResult<Map<String,Object>> result=new ServiceResult<>();
            Map<String,Object> map=new HashMap<>();
            CarAutoTransfer transfer=transferService.selectByPrimaryKey(obj.getLong("transferId")).getResult();
            if (transfer!=null){
                map.put("transferId",transfer.getId());
                map.put("moveToWhere",transfer.getMoveToWhere());
                CarAutoInfoDetail infoDetail=autoInfoDetailService.selectDetailByCarId(transfer.getAutoId());
                if (infoDetail!=null){
                    map.put("vehicleAttributionCityCn",infoDetail.getVehicleAttributionCityCn());
                    map.put("vehicleAttributionCity",infoDetail.getVehicleAttributionCity());
                    map.put("vehicleAttributionProvince",infoDetail.getVehicleAttributionProvince());
                    map.put("vehicleAttributionProvinceCn",infoDetail.getVehicleAttributionProvinceCn());
                }
                result.setResult(map);
                result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            }else {
                result.setError(ResultCode.NO_OBJECT.strValue(),ResultCode.NO_OBJECT.getRemark());
            }
        return result;
    }
}
