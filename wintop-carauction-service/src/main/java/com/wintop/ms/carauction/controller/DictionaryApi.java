package com.wintop.ms.carauction.controller;

import com.wintop.ms.carauction.core.config.DictionaryConstants;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.WtDictionary;
import com.wintop.ms.carauction.service.IDictionaryService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liangtingsen on 2018/3/5.
 * 字典数据api
 */
@RestController
@RequestMapping("dictionary")
public class DictionaryApi {
    @Autowired
    private IDictionaryService iDictionaryService;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(DictionaryApi.class);

    /***
     * 根据编号查询字典项
     * @param code
     * @return
     */
    @RequestMapping(value = "/getByCode",
            method= RequestMethod.POST)
    public ServiceResult<WtDictionary> getByCode(@RequestBody String code) {
        logger.info("根据编号查询字典项");
        Map map = new HashMap();
        map.put("code",code);
        return iDictionaryService.selectByCode(map);
    }

    /***
     * 根据编号查询字典项
     * @param map
     * @return
     */
    @RequestMapping(value = "/getByPCode",
            method= RequestMethod.POST)
    public ServiceResult<WtDictionary> findByPCode(@RequestBody Map map) {
        logger.info("根据编号查询子集字典集合");
        return iDictionaryService.selectByPCode(map);
    }

    @ApiOperation(value = "查询车辆基本信息所用到的字典基础数据",notes = " " +
            "           //车型 AUTO_CHX_\n" +
            "           //车辆类型 AUTO_CLLX_\n" +
            "            //排放标准 AUTO_PFBZ_\n" +
            "            //进气方式 AUTO_JQFS_\n" +
            "            //供油系统 AUTO_GYXT_\n" +
            "            //变速箱形式 AUTO_BSX_\n" +
            "            //驱动形式 AUTO_QDXS_\n" +
            "            //车身颜色 AUTO_YS_\n" +
            "            //车辆性质 AUTO_CLXZ_\n" +
            "            //使用性质 AUTO_SYXZ_")
    @PostMapping(value = "findForAutoBaseInfo")
    public ServiceResult<Map> findForAutoBaseInfo(){
        logger.info("查询车辆基本信息所用到的字典基础数据");
        ServiceResult<Map> result = new ServiceResult<>();
        try {

            Map parm = new HashMap();
            parm.put("status","1");
            parm.put("codeArr", DictionaryConstants.AUTO_BASE_INFO_PCODE_ARR);
            List<WtDictionary> dictionaryList = iDictionaryService.selectByCodeArr(parm);
            List<WtDictionary> sonList = iDictionaryService.selectByPCodeArr(parm);

            List<WtDictionary> AUTO_CHX_ = new ArrayList<>();
            List<WtDictionary> AUTO_PFBZ_ = new ArrayList<>();
            List<WtDictionary> AUTO_JQFS_ = new ArrayList<>();
            List<WtDictionary> AUTO_GYXT_ = new ArrayList<>();
            List<WtDictionary> AUTO_BSX_ = new ArrayList<>();
            List<WtDictionary> AUTO_QDXS_ = new ArrayList<>();
            List<WtDictionary> AUTO_YS_ = new ArrayList<>();
            List<WtDictionary> AUTO_CLXZ_ = new ArrayList<>();
            List<WtDictionary> AUTO_SYXZ_ = new ArrayList<>();
            //重新 拆封字典数据返回
            for (WtDictionary son:sonList){
                for (WtDictionary p:dictionaryList){
                    //取出封装车辆车型
                    if (DictionaryConstants.AUTO_CLLX_.equals(p.getCode()) && son.getpId()==p.getId()){
                        AUTO_CHX_.add(son);
                        continue;
                    }
                    //排放标准 AUTO_PFBZ_
                    if (DictionaryConstants.AUTO_PFBZ_.equals(p.getCode()) && son.getpId()==p.getId()){
                        AUTO_PFBZ_.add(son);
                        continue;
                    }
                    //进气方式 AUTO_JQFS_
                    if (DictionaryConstants.AUTO_JQFS_.equals(p.getCode()) && son.getpId()==p.getId()){
                        AUTO_JQFS_.add(son);
                        continue;
                    }
                    //供油系统 AUTO_GYXT_
                    if (DictionaryConstants.AUTO_GYXT_.equals(p.getCode()) && son.getpId()==p.getId()){
                        AUTO_GYXT_.add(son);
                        continue;
                    }
                    //变速箱形式 AUTO_BSX_
                    if (DictionaryConstants.AUTO_BSX_.equals(p.getCode()) && son.getpId()==p.getId()){
                        AUTO_BSX_.add(son);
                        continue;
                    }
                    //驱动形式 AUTO_QDXS_
                    if (DictionaryConstants.AUTO_QDXS_.equals(p.getCode()) && son.getpId()==p.getId()){
                        AUTO_QDXS_.add(son);
                        continue;
                    }
                    //车身颜色 AUTO_YS_
                    if (DictionaryConstants.AUTO_YS_.equals(p.getCode()) && son.getpId()==p.getId()){
                        AUTO_YS_.add(son);
                        continue;
                    }
                    //车辆性质 AUTO_CLXZ_
                    if (DictionaryConstants.AUTO_CLXZ_.equals(p.getCode()) && son.getpId()==p.getId()){
                        AUTO_CLXZ_.add(son);
                        continue;
                    }
                    //使用性质 AUTO_SYXZ_
                    if (DictionaryConstants.AUTO_SYXZ_.equals(p.getCode()) && son.getpId()==p.getId()){
                        AUTO_SYXZ_.add(son);
                        continue;
                    }
                }
            }
            Map map = new HashMap();
            map.put(DictionaryConstants.AUTO_CHX_,AUTO_CHX_);
            map.put(DictionaryConstants.AUTO_PFBZ_,AUTO_PFBZ_);
            map.put(DictionaryConstants.AUTO_JQFS_,AUTO_JQFS_);
            map.put(DictionaryConstants.AUTO_GYXT_,AUTO_GYXT_);
            map.put(DictionaryConstants.AUTO_BSX_,AUTO_BSX_);
            map.put(DictionaryConstants.AUTO_QDXS_,AUTO_QDXS_);
            map.put(DictionaryConstants.AUTO_YS_,AUTO_YS_);
            map.put(DictionaryConstants.AUTO_CLXZ_,AUTO_CLXZ_);
            map.put(DictionaryConstants.AUTO_SYXZ_,AUTO_SYXZ_);
            result.setResult(map);
            result.setSuccess("0","查询成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getLocalizedMessage());
            result.setError("-1","业务处理异常");
        }finally {
            return result;
        }


    }
}