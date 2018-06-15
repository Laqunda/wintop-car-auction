package com.wintop.ms.carauction.util.utils;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.model.ResultModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiUtil {

    public static final String OBJECT = "OBJECT";
    public static final String LIST = "LIST";

    /**
     * api返回结果
     * @param response
     * @return
     */
    public static ResultModel getResultModel(ResponseEntity<JSONObject> response,String type){
        ResultModel resultModel;
        if(response.getStatusCode()== HttpStatus.OK){
            JSONObject obj = response.getBody();
            boolean success = obj.getBoolean("success");
            if(success){
                Object result;
                if(OBJECT.equals(type)){
                    result = obj.getJSONObject("result");
                }else{
                    result = obj.getJSONArray("result");
                }
                resultModel = new ResultModel(true, ResultCode.SUCCESS.value(),obj.getString("message"),result);
            }else{
                int resultCode = Integer.valueOf(obj.getString("code"));
                int code = 300;
                if(resultCode>0){
                    code = resultCode;
                }
                resultModel = new ResultModel(false,code,obj.getString("message"),null);
            }
        }else{
            resultModel = new ResultModel(false,ResultCode.NET_EXCEPTION.value(),ResultCode.NET_EXCEPTION.getRemark(),null);
        }
        return resultModel;
    }

    /**
     * api返回结果
     * @param response
     * @param resultModel
     * @return
     */
    public static ResponseEntity<ResultModel> getResponseEntity(ResponseEntity<JSONObject> response, ResultModel resultModel,String type){
        if(response.getStatusCode()== HttpStatus.OK){
            JSONObject obj = response.getBody();
            boolean success = obj.getBoolean("success");
            if(success){
                Object result;
                if(OBJECT.equals(type)){
                    result = obj.getJSONObject("result");
                }else{
                    result = obj.getJSONArray("result");
                }
                resultModel = new ResultModel(success,100,obj.getString("message"),result);
            }else{
                int code = 300;
                if (obj.getString("code")!=null && obj.getString("code")!="") {
                    int resultCode = Integer.valueOf(obj.getString("code"));
                    if (resultCode > 0) {
                        code = resultCode;
                    }
                }
                resultModel = new ResultModel(success,code,obj.getString("message"),null);
            }
            return new ResponseEntity<>(resultModel, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(resultModel, response.getStatusCode());
        }
    }

}
