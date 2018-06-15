package com.wintop.ms.carauction.service.impl;

import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarQuestion;
import com.wintop.ms.carauction.model.CarQuestionModel;
import com.wintop.ms.carauction.service.ICarQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by 12991 on 2018/3/5.
 */
@Service
public class CarQuestionServiceImpl implements ICarQuestionService {
    @Autowired
    private CarQuestionModel model;
    @Override
    public Integer countByExample(Map<String, Object> map) {
        Integer count = model.countByExample(map);
        return count;
    }

    @Override
    public List<CarQuestion> selectByExample(Map<String, Object> map) {
        List<CarQuestion> carQuestions= model.selectByExample(map);
        return carQuestions;
    }

    @Override
    public CarQuestion selectById(Long id) {
        CarQuestion carQuestion =  model.selectById(id);
        return carQuestion;
    }

    @Override
    public Integer deleteById(Long id) {
        Integer count = model.deleteById(id);
        return count;
    }

    @Override
    public Integer insert(CarQuestion carQuestion) {
        Integer count = model.insert(carQuestion);
        return count;
    }

    @Override
    public Integer updateByIdSelective(CarQuestion carQuestion) {
        Integer count = model.updateByIdSelective(carQuestion);
        return count;
    }

    @Override
    public Integer updateById(CarQuestion carQuestion) {
        Integer count = model.updateById(carQuestion);
        return count;
    }

    /**
     *  根据问题分类查询该分类下面的问题集
     *  @Autor 付陈林
     *  @Time  2018-3-5
     */
    @Override
    public List<CarQuestion> selectByClassifyId(Long classifyId) {
        List<CarQuestion> carQuestionList =model.selectByClassifyId(classifyId);
        return carQuestionList;
    }


    /**
     *  根据问题分类编码查询该分类下面的问题集
     *  @Autor zhangzijuan
     *  @Time  2018-3-6
     */
    @Override
    public ServiceResult<List<CarQuestion>> getQuestionByCode(String code){
        ServiceResult<List<CarQuestion>> result = new ServiceResult<>();
        try {
            List<CarQuestion> carQuestions = model.getQuestionByCode(code);
            if (carQuestions!=null&&carQuestions.size()!=0){
                result.setResult(carQuestions);
              result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            }else {
                result.setError(ResultCode.NO_OBJECT.strValue(),ResultCode.NO_OBJECT.getRemark());
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return  result;
    }

    /**
     * 查询首页展示
     * @return
     */
    @Override
    public List<CarQuestion> selectIndexQuestion(int limit){
        return model.selectIndexQuestion(limit);
    }
}
