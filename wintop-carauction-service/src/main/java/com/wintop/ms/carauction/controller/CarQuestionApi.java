package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.PageEntity;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarQuestion;
import com.wintop.ms.carauction.entity.CarQuestionClassify;
import com.wintop.ms.carauction.entity.ListEntity;
import com.wintop.ms.carauction.service.ICarQuestionClassifyService;
import com.wintop.ms.carauction.service.ICarQuestionService;
import com.wintop.ms.carauction.util.utils.CarAutoUtils;
import com.wintop.ms.carauction.util.utils.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangzijuan
 * @Description:常见问题
 * @date 2018-03-06
 */

@RestController
@RequestMapping("/service/question")
public class CarQuestionApi {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CarQuestionApi.class);

    @Resource
    private ICarQuestionService questionService;
    @Autowired
    private ICarQuestionClassifyService classifyService;

    private IdWorker idWorker = new IdWorker(10);
    /**
     * 根据问题分类编码查询该分类下面的问题集
     * @author zhangzijuan
     * @param object
     * @return
     */
    @PostMapping(value = "getQuestionByCode",/*指定请求的实际地址，指定的地址可以是URI Template 模式（后面将会说明）；*/
            consumes="application/json; charset=UTF-8",/*指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;*/
            produces="application/json; charset=UTF-8")/*指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回；*/
    public ServiceResult<List<CarQuestion>> getQuestionByCode(@RequestBody JSONObject object) {
        logger.info("根据编码查询常见问题"+object.getString("code"));
        return this.questionService.getQuestionByCode(object.getString("code"));
    }

    /**
     *获取常见问题分类列表
     */
    @RequestMapping(value = "/selectQuestionClassifyList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<CarQuestionClassify>> selectCarQuestionClassify(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<CarQuestionClassify>> result = new ServiceResult<>();
        Map<String,Object> map = new HashMap<>();
        try {
            int count = classifyService.countByExample(map);
            PageEntity pageEntity = CarAutoUtils.getPageParam(obj);
            map.put("startRowNum",pageEntity.getStartRowNum());
            map.put("endRowNum",pageEntity.getEndRowNum());
            List<CarQuestionClassify> list = classifyService.selectByExample(map);
            ListEntity<CarQuestionClassify> listEntity = new ListEntity<>(list,count);
            result.setResult(listEntity);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取常见问题分类列表失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     *获取所有常见问题分类
     */
    @RequestMapping(value = "/selectAllQuestionClassify",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<List<CarQuestionClassify>> selectAllQuestionClassify() {
        ServiceResult<List<CarQuestionClassify>> result = new ServiceResult<>();
        Map<String,Object> map = new HashMap<>();
        map.put("isOpen","1");
        try {
            List<CarQuestionClassify> list = classifyService.selectByExample(map);
            result.setResult(list);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取所有常见问题分类失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     * 保存常见问题分类
     */
    @RequestMapping(value = "/saveQuestionClassify",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> saveQuestionClassify(@RequestBody JSONObject obj) {
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        Map<String,Object> map = new HashMap<>();
        try {
            CarQuestionClassify classify = JSONObject.toJavaObject(obj,CarQuestionClassify.class);
            if(StringUtils.isEmpty(classify.getIsOpen())){
                classify.setIsOpen("1");
            }
            classify.setId(idWorker.nextId());
            classify.setCreateTime(new Date());
            int count = classifyService.insert(classify);
            map.put("count",count);
            result.setResult(map);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("保存常见问题类型失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     * 修改常见问题分类
     */
    @RequestMapping(value = "/updateQuestionClassify",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> updateQuestionClassify(@RequestBody JSONObject obj) {
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        Map<String,Object> map = new HashMap<>();
        try {
            CarQuestionClassify classify = JSONObject.toJavaObject(obj,CarQuestionClassify.class);
            if(StringUtils.isEmpty(classify.getIsOpen())){
                classify.setIsOpen("1");
            }
            int count = classifyService.updateByIdSelective(classify);
            map.put("count",count);
            result.setResult(map);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("修改常见问题类型失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     * 根据分类获取常见问题列表
     */
    @RequestMapping(value = "/selectQuestionListByClassify",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<List<CarQuestion>> selectQuestionListByClassify(@RequestBody JSONObject obj) {
        logger.info("获取常见问题详情");
        ServiceResult<List<CarQuestion>> result = new ServiceResult<List<CarQuestion>>();
        try {
            Long classifyId = obj.getLong("classifyId");
            List<CarQuestion> list = questionService.selectByClassifyId(classifyId);
            result.setResult(list);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("根据获取常见问题列表失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     * 根据问题Id获取问题详情
     * @author zhangzijuan
     * @param object
     * @return
     */
    @PostMapping(value = "selectQuestionById",/*指定请求的实际地址，指定的地址可以是URI Template 模式（后面将会说明）；*/
            consumes="application/json; charset=UTF-8",/*指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;*/
            produces="application/json; charset=UTF-8")/*指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回；*/
    public ServiceResult<CarQuestion> selectById(@RequestBody JSONObject object) {
        ServiceResult<CarQuestion> result = new ServiceResult<>();
        try {
            CarQuestion question = questionService.selectById(object.getLong("questionId"));
            result.setResult(question);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取常见问题详情失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }


    /**
     * 分页查询问题集
     * @author zhangzijuan
     * @param object
     * @return
     */
    @PostMapping(value = "selectQuestionList",
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<CarQuestion>> selectQuestionList(@RequestBody JSONObject object) {
        ServiceResult<ListEntity<CarQuestion>> result = new ServiceResult<>();
        try {
            Map<String,Object> paramMap = new HashMap<>();
            if(object.get("classifyId")!=null){
                paramMap.put("classifyId",object.getLong("classifyId"));
            }
            if(object.get("questionName")!=null){
                paramMap.put("questionName",object.getLong("questionName"));
            }
            int count = questionService.countByExample(paramMap);
            PageEntity pageEntity = CarAutoUtils.getPageParam(object);
            paramMap.put("startRowNum",pageEntity.getStartRowNum());
            paramMap.put("endRowNum",pageEntity.getEndRowNum());
            List<CarQuestion> questionList = questionService.selectByExample(paramMap);
            ListEntity<CarQuestion> listEntity = new ListEntity<>(questionList,count);
            result.setResult(listEntity);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取常见问题列表失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     * 首页问题查看
     */
    @RequestMapping(value = "/selectIndexQuestion",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<List<CarQuestion>> selectIndexQuestion(@RequestBody JSONObject obj) {
        ServiceResult<List<CarQuestion>> result = new ServiceResult<List<CarQuestion>>();
        try {
            List<CarQuestion> list = questionService.selectIndexQuestion(obj.getInteger("limit"));
            result.setResult(list);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("首页问题查看",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.SUCCESS.getRemark());
        }
        return result;
    }

    /**
     * 保存常见问题
     */
    @RequestMapping(value = "/saveQuestion",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> saveQuestion(@RequestBody JSONObject obj) {
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        Map<String,Object> map = new HashMap<>();
        try {
            CarQuestion question = JSONObject.toJavaObject(obj,CarQuestion.class);
            if(StringUtils.isEmpty(question.getIsOpen())){
                question.setIsOpen("1");
            }
            question.setId(idWorker.nextId());
            question.setCreateTime(new Date());
            int count = questionService.insert(question);
            map.put("count",count);
            result.setResult(map);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("保存常见问题失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     * 修改常见问题
     */
    @RequestMapping(value = "/updateQuestion",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> updateQuestion(@RequestBody JSONObject obj) {
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        Map<String,Object> map = new HashMap<>();
        try {
            CarQuestion question = JSONObject.toJavaObject(obj,CarQuestion.class);
            if(StringUtils.isEmpty(question.getIsOpen())){
                question.setIsOpen("1");
            }
            int count = questionService.updateByIdSelective(question);
            map.put("count",count);
            result.setResult(map);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("修改常见问题失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     * 删除常见问题分类
     */
    @RequestMapping(value = "/deleteQuestionClassify",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> deleteQuestionClassify(@RequestBody JSONObject obj) {
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        Map<String,Object> map = new HashMap<>();
        try {
            int count = classifyService.deleteById(obj.getLong("id"));
            map.put("count",count);
            result.setResult(map);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("删除常见问题分类失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /**
     * 删除常见问题
     */
    @RequestMapping(value = "/deleteQuestion",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> deleteQuestion(@RequestBody JSONObject obj) {
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        Map<String,Object> map = new HashMap<>();
        try {
            int count = questionService.deleteById(obj.getLong("id"));
            map.put("count",count);
            result.setResult(map);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("删除常见问题失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }
}
