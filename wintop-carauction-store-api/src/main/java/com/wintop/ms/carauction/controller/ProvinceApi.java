package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.ResultStatus;
import com.wintop.ms.carauction.core.model.ResultModel;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/***
 * 地区市接口服务
 */
@RestController
@RequestMapping("province")
public class ProvinceApi {

    private final RestTemplate restTemplate;
    private ResultModel resultModel;

    private static final Logger logger = LoggerFactory.getLogger(ProvinceApi.class);


    /**根据id查询省直辖市*/
    String getProvinceByIdUrl = Constants.ROOT + "/province/getById";
    /**查询全部省直辖市*/
    String getProvinceAllUrl = Constants.ROOT + "/province/getAll";


    /**根据id查询地区城市*/
    String getCityByIdUrl = Constants.ROOT + "/city/getById";
    /**查询全部地区城市*/
    String getCityAllUrl = Constants.ROOT + "/city/getAll";
    /**根据省直辖市查询地区城市*/
    String getCityByProvinceUrl = Constants.ROOT + "/city/getByProvince";


    /**根据id查询区县详情*/
    String getDistrictByIdUrl = Constants.ROOT + "/district/getById";
    /**查询全部区县数据*/
    String getDistrictAllUrl = Constants.ROOT + "/district/getAll";
    /**根据地区市查询区县数据*/
    String getDistrictByCityUrl = Constants.ROOT + "/district/getByCity";



    public ProvinceApi(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    /***
     * 根据id查询省直辖市信息
     * @param provinceId
     * @return
     */
    @RequestMapping(value = "/getProvinceById",method = RequestMethod.POST)
    @ApiOperation(value = "根据地区市id查询详情")
    public ResponseEntity<ResultModel> getProvinceById(@RequestParam Long provinceId){
        logger.info("根据地区市id查询详情");
        Map map = new HashMap();
        map.put("provinceId",provinceId);
        ResponseEntity<JSONObject> resultResponseEntity = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(getProvinceByIdUrl))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        JSONObject serviceResult = resultResponseEntity.getBody();
        ResultModel resultModel = null;
        //1、先判断返回网络状态码是否成功
        if(resultResponseEntity.getStatusCode()== HttpStatus.OK){
            //2、判断业务处理结果是否成功
            if (serviceResult.get("success").toString()=="true") {
                JSONObject object = serviceResult.getJSONObject("result");
                if (object!=null){//已存在
                    resultModel = ResultModel.ok(object);
                }else {//不存在
                    resultModel = new ResultModel(true, ResultStatus.OBJECT_NOT_EXIST);
                }
                return new ResponseEntity<>(resultModel, HttpStatus.OK);
            }else {//异常情况
                resultModel = new ResultModel(true,ResultStatus.ERROR);
                return new ResponseEntity<>(resultModel,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else {
            resultModel = new ResultModel(false,ResultStatus.ERROR);
            return new ResponseEntity<>(resultModel,resultResponseEntity.getStatusCode());
        }
    }

    /***
     * 查询全部省直辖市数据
     * @return
     */
    @RequestMapping(value = "/getProvinceAll",method = RequestMethod.POST)
    @ApiOperation(value = "查询全部省直辖市数据")
    public ResponseEntity<ResultModel> getProvinceAll(){
        logger.info("查询全部省直辖市数据");
        ResponseEntity<JSONObject> resultResponseEntity = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(getProvinceAllUrl))
                        .contentType(MediaType.APPLICATION_JSON).build(),JSONObject.class);
        JSONObject serviceResult = resultResponseEntity.getBody();
        ResultModel resultModel = null;
        //1、先判断返回网络状态码是否成功
        if(resultResponseEntity.getStatusCode()== HttpStatus.OK){
            //2、判断业务处理结果是否成功
            if (serviceResult.get("success").toString()=="true") {
                JSONArray object = serviceResult.getJSONArray("result");
                if (object!=null){//已存在
                    resultModel = ResultModel.ok(object);
                }else {//不存在
                    resultModel = new ResultModel(true,ResultStatus.OBJECT_NOT_EXIST);
                }
                return new ResponseEntity<>(resultModel, HttpStatus.OK);
            }else {//异常情况
                resultModel = new ResultModel(true,ResultStatus.ERROR);
                return new ResponseEntity<>(resultModel,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else {
            resultModel = new ResultModel(false,ResultStatus.ERROR);
            return new ResponseEntity<>(resultModel,resultResponseEntity.getStatusCode());
        }
    }
//======================================================*/



    /***
     * 根据地区市id查询详情
     * @param cityId
     * @return
     */
    @RequestMapping(value = "/getCityById",method = RequestMethod.POST)
    @ApiOperation(value = "根据地区市id查询详情")
    public ResponseEntity<ResultModel> getCityById(@RequestParam Long cityId){
        logger.info("根据地区市id查询详情");
        Map map = new HashMap();
        map.put("cityId",cityId);
        ResponseEntity<JSONObject> resultResponseEntity = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(getCityByIdUrl))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        JSONObject serviceResult = resultResponseEntity.getBody();
        ResultModel resultModel = null;
        //1、先判断返回网络状态码是否成功
        if(resultResponseEntity.getStatusCode()== HttpStatus.OK){
            //2、判断业务处理结果是否成功
            if (serviceResult.get("success").toString()=="true") {
                JSONObject object = serviceResult.getJSONObject("result");
                if (object!=null){//已存在
                    resultModel = ResultModel.ok(object);
                }else {//不存在
                    resultModel = new ResultModel(true,ResultStatus.OBJECT_NOT_EXIST);
                }
                return new ResponseEntity<>(resultModel, HttpStatus.OK);
            }else {//异常情况
                resultModel = new ResultModel(true,ResultStatus.ERROR);
                return new ResponseEntity<>(resultModel,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else {
            resultModel = new ResultModel(false,ResultStatus.ERROR);
            return new ResponseEntity<>(resultModel,resultResponseEntity.getStatusCode());
        }
    }

    /***
     * 查询全部地区市数据
     * @return
     */
    @RequestMapping(value = "/getCityAll",method = RequestMethod.POST)
    @ApiOperation(value = "查询全部地区市数据")
    public ResponseEntity<ResultModel> getCityAll(){
        logger.info("查询全部地区市数据");
        ResponseEntity<JSONObject> resultResponseEntity = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(getCityAllUrl))
                        .contentType(MediaType.APPLICATION_JSON).build(),JSONObject.class);
        JSONObject serviceResult = resultResponseEntity.getBody();
        ResultModel resultModel = null;
        //1、先判断返回网络状态码是否成功
        if(resultResponseEntity.getStatusCode()== HttpStatus.OK){
            //2、判断业务处理结果是否成功
            if (serviceResult.get("success").toString()=="true") {
                JSONArray object = serviceResult.getJSONArray("result");
                if (object!=null){//已存在
                    resultModel = ResultModel.ok(object);
                }else {//不存在
                    resultModel = new ResultModel(true,ResultStatus.OBJECT_NOT_EXIST);
                }
                return new ResponseEntity<>(resultModel, HttpStatus.OK);
            }else {//异常情况
                resultModel = new ResultModel(true,ResultStatus.ERROR);
                return new ResponseEntity<>(resultModel,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else {
            resultModel = new ResultModel(false,ResultStatus.ERROR);
            return new ResponseEntity<>(resultModel,resultResponseEntity.getStatusCode());
        }
    }

    /***
     * 根据省直辖市查询地区信息
     * @return
     */
    @RequestMapping(value = "/getCityByProvince",method = RequestMethod.POST)
    @ApiOperation(value = "查询全部地区市数据")
    public ResponseEntity<ResultModel> getCityByProvince(@RequestParam Long provinceId){
        logger.info("查询全部地区市数据");
        Map map = new HashMap();
        map.put("provinceId",provinceId);
        ResponseEntity<JSONObject> resultResponseEntity = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(getCityByProvinceUrl))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        JSONObject serviceResult = resultResponseEntity.getBody();
        ResultModel resultModel = null;
        //1、先判断返回网络状态码是否成功
        if(resultResponseEntity.getStatusCode()== HttpStatus.OK){
            //2、判断业务处理结果是否成功
            if (serviceResult.get("success").toString()=="true") {
                JSONArray object = serviceResult.getJSONArray("result");
                if (object!=null){//已存在
                    resultModel = ResultModel.ok(object);
                }else {//不存在
                    resultModel = new ResultModel(true,ResultStatus.OBJECT_NOT_EXIST);
                }
                return new ResponseEntity<>(resultModel, HttpStatus.OK);
            }else {//异常情况
                resultModel = new ResultModel(true,ResultStatus.ERROR);
                return new ResponseEntity<>(resultModel,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else {
            resultModel = new ResultModel(false,ResultStatus.ERROR);
            return new ResponseEntity<>(resultModel,resultResponseEntity.getStatusCode());
        }
    }

    //////////=-=-=----------------=-=-=-=-=-=-=-=-=-=--================================/
    /***
     * 根据区县id查询详情
     * @param districtId
     * @return
     */
    @RequestMapping(value = "/getDistrictById",method = RequestMethod.POST)
    @ApiOperation(value = "根据地区市id查询详情")
    public ResponseEntity<ResultModel> getDistrictById(@RequestParam Long districtId){
        logger.info("根据地区市id查询详情");
        Map map = new HashMap();
        map.put("districtId",districtId);
        ResponseEntity<JSONObject> resultResponseEntity = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(getDistrictByIdUrl))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        JSONObject serviceResult = resultResponseEntity.getBody();
        ResultModel resultModel = null;
        //1、先判断返回网络状态码是否成功
        if(resultResponseEntity.getStatusCode()== HttpStatus.OK){
            //2、判断业务处理结果是否成功
            if (serviceResult.get("success").toString()=="true") {
                JSONObject object = serviceResult.getJSONObject("result");
                if (object!=null){//已存在
                    resultModel = ResultModel.ok(object);
                }else {//不存在
                    resultModel = new ResultModel(true,ResultStatus.OBJECT_NOT_EXIST);
                }
                return new ResponseEntity<>(resultModel, HttpStatus.OK);
            }else {//异常情况
                resultModel = new ResultModel(true,ResultStatus.ERROR);
                return new ResponseEntity<>(resultModel,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else {
            resultModel = new ResultModel(false,ResultStatus.ERROR);
            return new ResponseEntity<>(resultModel,resultResponseEntity.getStatusCode());
        }
    }

    /***
     * 查询全部区县数据
     * @return
     */
    @RequestMapping(value = "/getDistrictAll",method = RequestMethod.POST)
    @ApiOperation(value = "查询全部区县数据")
    public ResponseEntity<ResultModel> getDistrictAll(){
        logger.info("查询全部区县数据");
        ResponseEntity<JSONObject> resultResponseEntity = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(getDistrictAllUrl))
                        .contentType(MediaType.APPLICATION_JSON).build(),JSONObject.class);
        JSONObject serviceResult = resultResponseEntity.getBody();
        ResultModel resultModel = null;
        //1、先判断返回网络状态码是否成功
        if(resultResponseEntity.getStatusCode()== HttpStatus.OK){
            //2、判断业务处理结果是否成功
            if (serviceResult.get("success").toString()=="true") {
                JSONArray object = serviceResult.getJSONArray("result");
                if (object!=null){//已存在
                    resultModel = ResultModel.ok(object);
                }else {//不存在
                    resultModel = new ResultModel(true,ResultStatus.OBJECT_NOT_EXIST);
                }
                return new ResponseEntity<>(resultModel, HttpStatus.OK);
            }else {//异常情况
                resultModel = new ResultModel(true,ResultStatus.ERROR);
                return new ResponseEntity<>(resultModel,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else {
            resultModel = new ResultModel(false,ResultStatus.ERROR);
            return new ResponseEntity<>(resultModel,resultResponseEntity.getStatusCode());
        }
    }

    /***
     * 根据地区市查询区县信息
     * @return
     */
    @RequestMapping(value = "/getDistrictByCity",method = RequestMethod.POST)
    @ApiOperation(value = "根据地区市查询区县信息")
    public ResponseEntity<ResultModel> getDistrictByCity(@RequestParam Long cityId){
        logger.info("根据地区市查询区县信息");
        Map map = new HashMap();
        map.put("cityId",cityId);
        ResponseEntity<JSONObject> resultResponseEntity = this.restTemplate.exchange(
                RequestEntity
                        .post(URI.create(getDistrictByCityUrl))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(map),JSONObject.class);
        JSONObject serviceResult = resultResponseEntity.getBody();
        ResultModel resultModel = null;
        //1、先判断返回网络状态码是否成功
        if(resultResponseEntity.getStatusCode()== HttpStatus.OK){
            //2、判断业务处理结果是否成功
            if (serviceResult.get("success").toString()=="true") {
                JSONArray object = serviceResult.getJSONArray("result");
                if (object!=null){//已存在
                    resultModel = ResultModel.ok(object);
                }else {//不存在
                    resultModel = new ResultModel(true,ResultStatus.OBJECT_NOT_EXIST);
                }
                return new ResponseEntity<>(resultModel, HttpStatus.OK);
            }else {//异常情况
                resultModel = new ResultModel(true,ResultStatus.ERROR);
                return new ResponseEntity<>(resultModel,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else {
            resultModel = new ResultModel(false,ResultStatus.ERROR);
            return new ResponseEntity<>(resultModel,resultResponseEntity.getStatusCode());
        }
    }

}
