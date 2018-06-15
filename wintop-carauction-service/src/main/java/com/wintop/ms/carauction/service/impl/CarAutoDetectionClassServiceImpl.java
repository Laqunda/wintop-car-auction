package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.*;
import com.wintop.ms.carauction.model.CarAutoDetectionClassModel;
import com.wintop.ms.carauction.model.CarAutoDetectionClassOptionsModel;
import com.wintop.ms.carauction.model.CarAutoDetectionDataModel;
import com.wintop.ms.carauction.model.CarAutoDetectionDataPhotoModel;
import com.wintop.ms.carauction.service.ICarAutoDetectionClassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("CarAutoDetectionClassService")
public class CarAutoDetectionClassServiceImpl implements ICarAutoDetectionClassService {
    @Autowired
    private CarAutoDetectionClassModel carAutoDetectionClassModel;
    @Autowired
    private CarAutoDetectionDataModel carAutoDetectionDataModel;
    @Autowired
    private CarAutoDetectionDataPhotoModel carAutoDetectionDataPhotoModel;
    @Autowired
    private CarAutoDetectionClassOptionsModel carAutoDetectionClassOptionsModel;
    private static final Logger logger = LoggerFactory.getLogger(CarAutoDetectionClassServiceImpl.class);

    public ServiceResult<Integer> countByExample(Criteria example) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoDetectionClassModel.countByExample(example);
            result.setResult(count);
            result.setSuccess("0", "成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setError("-1", "异常");
        } finally {
            return result;
        }
    }

    public ServiceResult<CarAutoDetectionClass> selectByPrimaryKey(Long id) {
        ServiceResult<CarAutoDetectionClass> result = new ServiceResult<>();
        try {
            CarAutoDetectionClass carAutoDetectionClass = this.carAutoDetectionClassModel.selectByPrimaryKey(id);
            result.setResult(carAutoDetectionClass);
            result.setSuccess("0", "成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setError("-1", "异常");
        } finally {
            return result;
        }
    }

    public ServiceResult<List<CarAutoDetectionClass>> selectByExample(Criteria example) {
        ServiceResult<List<CarAutoDetectionClass>> result = new ServiceResult<>();
        try {
            List<CarAutoDetectionClass> list = this.carAutoDetectionClassModel.selectByExample(example);
            result.setResult(list);
            result.setSuccess("0", "成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setError("-1", "异常");
        } finally {
            return result;
        }
    }

    @Transactional
    public ServiceResult<Integer> deleteByPrimaryKey(Long id) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoDetectionClassModel.deleteByPrimaryKey(id);
            result.setResult(count);
            result.setSuccess("0", "成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setError("-1", "异常");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        } finally {
            return result;
        }
    }

    @Transactional
    public ServiceResult<Integer> updateByPrimaryKeySelective(CarAutoDetectionClass record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoDetectionClassModel.updateByPrimaryKeySelective(record);
            result.setResult(count);
            result.setSuccess("0", "成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setError("-1", "异常");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        } finally {
            return result;
        }
    }

    @Transactional
    public ServiceResult<Integer> updateByPrimaryKey(CarAutoDetectionClass record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoDetectionClassModel.updateByPrimaryKey(record);
            result.setResult(count);
            result.setSuccess("0", "成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setError("-1", "异常");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        } finally {
            return result;
        }
    }

    @Transactional
    public ServiceResult<Integer> insert(CarAutoDetectionClass record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoDetectionClassModel.insert(record);
            result.setResult(count);
            result.setSuccess("0", "成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setError("-1", "异常");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        } finally {
            return result;
        }
    }

    @Transactional
    public ServiceResult<Integer> insertSelective(CarAutoDetectionClass record) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int count = this.carAutoDetectionClassModel.insertSelective(record);
            result.setResult(count);
            result.setSuccess("0", "成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setError("-1", "异常");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        } finally {
            return result;
        }
    }

    @Override
    public ServiceResult<List<CarAutoDetectionClassMap>> selectByDetectionData(Map map) {
        ServiceResult<List<CarAutoDetectionClassMap>> result = new ServiceResult<>();
        result.setResult(carAutoDetectionClassModel.selectByDetectionDataType(map));
        result.setSuccess("0", "成功");
        return result;
    }


    /***
     * 查询某车的质检报告数据
     * @param autoId
     * @return
     */
    @Override
    public ServiceResult<Map<String, Object>> getAutoDetectionDataClass(Long autoId) {
        ServiceResult<Map<String, Object>> result = new ServiceResult<>();

        Map<String, Object> resultMap = new HashMap<>();
        //查询当前车辆全部检测损坏项数据
        List<CarAutoDetectionData> detectionDataList = carAutoDetectionDataModel.selectByAutoId(autoId);
        List<CarAutoDetectionDataPhoto> detectionDataPhotoList = carAutoDetectionDataPhotoModel.selectByAutoId(autoId);

        //骨架前
        Map mapPrm = new HashMap();
        mapPrm.put("detectionType", "1");
        mapPrm.put("positionType", "1");
        mapPrm.put("autoId", autoId);
        List<CarAutoDetectionClassMap> frontList = carAutoDetectionClassModel.selectByDetectionDataType(mapPrm);
        Map frameworkDataMap = new HashMap();
        frameworkDataMap.put("frontList", frontList);

        //骨架后
        mapPrm.put("positionType", "2");
        List<CarAutoDetectionClassMap> backList = carAutoDetectionClassModel.selectByDetectionDataType(mapPrm);
        frameworkDataMap.put("backList", backList);
        resultMap.put("frameworkData", frameworkDataMap);

        //钣金
        mapPrm.put("detectionType", "2");
        mapPrm.put("positionType", null);
        List<CarAutoDetectionClassMap> metalPlateList = carAutoDetectionClassModel.selectByDetectionDataType(mapPrm);
        resultMap.put("metalPlateList", metalPlateList);

        //外观
        mapPrm.put("detectionType", "3");
        List<CarAutoDetectionClassMap> appearanceList = carAutoDetectionClassModel.selectByDetectionDataType(mapPrm);
        resultMap.put("appearanceList", appearanceList);

        //其他
        List<Map> otherList = new ArrayList<>();

        //泡水/火烧
        mapPrm.put("detectionType", "4");
        List<CarAutoDetectionClassMap> paoshui_List = carAutoDetectionClassModel.selectByDetectionDataType(mapPrm);
        Map map1 = new HashMap();
        map1.put("className", "泡水/火烧");
        map1.put("classList", paoshui_List);
        otherList.add(map1);

        //机械
        mapPrm.put("detectionType", "5");
        List<CarAutoDetectionClassMap> jixie_List = carAutoDetectionClassModel.selectByDetectionDataType(mapPrm);
        map1 = new HashMap();
        map1.put("className", "机械检测");
        map1.put("classList", jixie_List);
        otherList.add(map1);


        //内饰
        mapPrm.put("detectionType", "6");
        List<CarAutoDetectionClassMap> neishi_List = carAutoDetectionClassModel.selectByDetectionDataType(mapPrm);
        map1 = new HashMap();
        map1.put("className", "内饰检测");
        map1.put("classList", neishi_List);
        otherList.add(map1);

        //电器
        mapPrm.put("detectionType", "7");
        List<CarAutoDetectionClassMap> dianqi_List = carAutoDetectionClassModel.selectByDetectionDataType(mapPrm);
        map1 = new HashMap();
        map1.put("className", "电器检测");
        map1.put("classList", dianqi_List);
        otherList.add(map1);

        resultMap.put("otherList", otherList);


        //循环筛选各各损坏项的数据
        for (CarAutoDetectionData detectionData : detectionDataList) {
            //骨架损伤-前
            for (CarAutoDetectionClassMap classMap : frontList) {
                if (detectionData.getClassId().equals(classMap.getClassId())) {
                    classMap.setDamagedType("1");
                    if (classMap.getResultDescribe() != null && !classMap.getResultDescribe().isEmpty()) {
                        //第二项以后逗号隔开
                        classMap.setResultDescribe(classMap.getResultDescribe() + "," + detectionData.getProblemDescription());
                    } else {
                        //第一项无需逗号
                        classMap.setResultDescribe(detectionData.getProblemDescription());
                    }
                }
            }
            //骨架损伤-后
            for (CarAutoDetectionClassMap classMap : backList) {
                if (detectionData.getClassId().equals(classMap.getClassId())) {
                    classMap.setDamagedType("1");
                    if (classMap.getResultDescribe() != null && !classMap.getResultDescribe().isEmpty()) {
                        //第二项以后逗号隔开
                        classMap.setResultDescribe(classMap.getResultDescribe() + "," + detectionData.getProblemDescription());
                    } else {
                        //第一项无需逗号
                        classMap.setResultDescribe(detectionData.getProblemDescription());
                    }
                }
            }
            //钣金修复
            for (CarAutoDetectionClassMap classMap : metalPlateList) {
                if (detectionData.getClassId().equals(classMap.getClassId())) {
                    classMap.setDamagedType("1");
                    if (classMap.getResultDescribe() != null && !classMap.getResultDescribe().isEmpty()) {
                        //第二项以后逗号隔开
                        classMap.setResultDescribe(classMap.getResultDescribe() + "," + detectionData.getProblemDescription());
                    } else {
                        //第一项无需逗号
                        classMap.setResultDescribe(detectionData.getProblemDescription());
                    }
                }
            }
            //外观修复
            for (CarAutoDetectionClassMap classMap : appearanceList) {
                if (detectionData.getClassId().equals(classMap.getClassId())) {
                    classMap.setDamagedType("1");
                    if (classMap.getResultDescribe() != null && !classMap.getResultDescribe().isEmpty()) {
                        //第二项以后逗号隔开
                        classMap.setResultDescribe(classMap.getResultDescribe() + "," + detectionData.getProblemDescription());
                    } else {
                        //第一项无需逗号
                        classMap.setResultDescribe(detectionData.getProblemDescription());
                    }
                }
            }
            //循环其他损坏结果项
            for (Map map : otherList) {
                if (map.get("classList") != null) {
                    //去除结果大类
                    for (CarAutoDetectionClassMap classMap : (List<CarAutoDetectionClassMap>) map.get("classList")) {
                        if (detectionData.getClassId().equals(classMap.getClassId())) {
                            classMap.setDamagedType("1");
                            if (classMap.getResultDescribe() != null && !classMap.getResultDescribe().isEmpty()) {
                                //第二项以后逗号隔开
                                classMap.setResultDescribe(classMap.getResultDescribe() + "," + detectionData.getProblemDescription());
                            } else {
                                //第一项无需逗号
                                classMap.setResultDescribe(detectionData.getProblemDescription());
                            }
                        }
                    }
                }
            }
        }
        //循环筛选去除各自的损坏拍照
        for (CarAutoDetectionDataPhoto autoDetectionDataPhoto : detectionDataPhotoList) {
            //骨架损伤-前
            for (CarAutoDetectionClassMap classMap : frontList) {
                if (autoDetectionDataPhoto.getClassId().equals(classMap.getClassId())) {
                    Map photoMap = new HashMap();
                    photoMap.put("id", autoDetectionDataPhoto.getId());
                    photoMap.put("url", autoDetectionDataPhoto.getPhotoUrl());
                    classMap.getPhotoList().add(photoMap);
                }
            }
            //骨架损伤-后
            for (CarAutoDetectionClassMap classMap : backList) {
                if (autoDetectionDataPhoto.getClassId().equals(classMap.getClassId())) {
                    Map photoMap = new HashMap();
                    photoMap.put("id", autoDetectionDataPhoto.getId());
                    photoMap.put("url", autoDetectionDataPhoto.getPhotoUrl());
                    classMap.getPhotoList().add(photoMap);
                }
            }
            //钣金修复
            for (CarAutoDetectionClassMap classMap : metalPlateList) {
                if (autoDetectionDataPhoto.getClassId().equals(classMap.getClassId())) {
                    Map photoMap = new HashMap();
                    photoMap.put("id", autoDetectionDataPhoto.getId());
                    photoMap.put("url", autoDetectionDataPhoto.getPhotoUrl());
                    classMap.getPhotoList().add(photoMap);
                }
            }
            //外观修复
            for (CarAutoDetectionClassMap classMap : appearanceList) {
                if (autoDetectionDataPhoto.getClassId().equals(classMap.getClassId())) {
                    Map photoMap = new HashMap();
                    photoMap.put("id", autoDetectionDataPhoto.getId());
                    photoMap.put("url", autoDetectionDataPhoto.getPhotoUrl());
                    classMap.getPhotoList().add(photoMap);
                }
            }
            //循环其他损坏结果项
            for (Map map : otherList) {
                if (map.get("classList") != null) {
                    //去除结果大类
                    for (CarAutoDetectionClassMap classMap : (List<CarAutoDetectionClassMap>) map.get("classList")) {
                        if (autoDetectionDataPhoto.getClassId().equals(classMap.getClassId())) {
                            Map photoMap = new HashMap();
                            photoMap.put("id", autoDetectionDataPhoto.getId());
                            photoMap.put("url", autoDetectionDataPhoto.getPhotoUrl());
                            classMap.getPhotoList().add(photoMap);
                        }
                    }
                }
            }
        }
        result.setSuccess("0", "检测报告查询成功");
        result.setResult(resultMap);
        return result;
    }

    @Override
    public List<CarAutoDetectionClass> getAutoDetectionClass(CarAutoDetectionClass carAutoDetectionClass, Long autoId) {
        List<CarAutoDetectionClass> classList = carAutoDetectionClassModel.getAutoDetectionClass(carAutoDetectionClass);
        if (autoId == null) {
            return classList;
        } else {
            ///查询车辆以保存的损坏点信息
            List<CarAutoDetectionData> dataList = carAutoDetectionDataModel.selectByAutoId(autoId);
            List<CarAutoDetectionDataPhoto> photoList = carAutoDetectionDataPhotoModel.selectByAutoId(autoId);
            for (CarAutoDetectionData detectionData : dataList) {
                for (CarAutoDetectionClass detectionClass : classList) {
                    //获取封装损坏点
                    if (detectionClass.getId() == detectionData.getClassId()) {
                        detectionClass.getDataList().add(detectionData);
                        break;
                    }
                }
            }

            for (CarAutoDetectionDataPhoto dataPhoto : photoList) {
                for (CarAutoDetectionClass detectionClass : classList) {
                    //获取封装损坏点图片
                    if (detectionClass.getId() == dataPhoto.getClassId()) {
                        detectionClass.getPhotoList().add(dataPhoto);
                        break;
                    }
                }
            }
            return classList;
        }
    }

    @Override
    public CarAutoDetectionClass getAutoDetectionClassOption(Long classId, Long autoId) {
        CarAutoDetectionClass detectionClass = carAutoDetectionClassModel.selectByPrimaryKey(classId);
        if (detectionClass!=null) {
            ///查询车辆某检测项保存的--照片+损坏点信息
            List<CarAutoDetectionDataPhoto> photoList = carAutoDetectionDataPhotoModel.selectByAutoId(autoId);
            Map map = new HashMap();
            map.put("classId", classId);
            map.put("autoId", autoId);
            List<CarAutoDetectionClassOptions> optionsList = carAutoDetectionClassOptionsModel.selectByAutoId(map);
            detectionClass.setOptionsList(optionsList);
            for (CarAutoDetectionDataPhoto dataPhoto : photoList) {
                //获取封装损坏点图片
                if (detectionClass.getId() == dataPhoto.getClassId()) {
                    detectionClass.getPhotoList().add(dataPhoto);
                }
            }
        }
        return detectionClass;
    }
}