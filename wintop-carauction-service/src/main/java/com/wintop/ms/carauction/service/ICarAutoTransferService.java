package com.wintop.ms.carauction.service;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarAutoTransfer;
import com.wintop.ms.carauction.entity.Criteria;
import java.util.List;
import java.util.Map;

public interface ICarAutoTransferService {

    ServiceResult<Integer> countByExample(Criteria example);

    ServiceResult<CarAutoTransfer> selectByPrimaryKey(Long id);

    ServiceResult<List<CarAutoTransfer>> selectByExample(Criteria example);

    ServiceResult<Integer> deleteByPrimaryKey(Long id);

    ServiceResult<Integer> updateByPrimaryKeySelective(CarAutoTransfer record);

    ServiceResult<Integer> updateByPrimaryKey(CarAutoTransfer record);

    ServiceResult<Integer> insert(CarAutoTransfer record);
    /*根据auto_id和order_id查询有关数据*/
    ServiceResult<CarAutoTransfer> selectByOrderId(Long orderId, Long autoId);
    ServiceResult<Integer> insertSelective(CarAutoTransfer record);

    /*交接完成*/
    ServiceResult<Integer> insertHandOver(CarAutoTransfer record);
    /*上传手续*/
    ServiceResult<Integer> insertUpload(CarAutoTransfer record);

    /*交挡*/
    ServiceResult<Integer> insertToStop(CarAutoTransfer record);

    /*出牌*/
    ServiceResult<Integer> insertPlay(CarAutoTransfer record);

    /*提挡*/
    ServiceResult<Integer> insertLiftBlock(CarAutoTransfer record);

    /*确定过户事宜*/
    ServiceResult<Integer> insertDetermine(CarAutoTransfer record);

    /*根据条件查询过户流程信息*/
    ServiceResult<List<CarAutoTransfer>> selectByParam(Map<String,Object> map);
    ServiceResult<Integer> selectCount(Map<String,Object> map);
    /**
     *@Author:zhangzijuan
     *上传手续、交挡、出牌、提挡、确定过户事宜
     *@date 2018/3/26
     *@param:
     */
    Integer submitTransfer(JSONObject object);
/**
 * 确认手续交接
 *@Author:zhangzijuan
 *@date 2018/4/18
 *@param:
 */
    Integer saveTransfer(JSONObject object);

    CarAutoTransfer selectByOrderIdAndAutoId(Long orderId, Long autoId);
}