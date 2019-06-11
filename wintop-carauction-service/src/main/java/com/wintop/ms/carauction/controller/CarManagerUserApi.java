package com.wintop.ms.carauction.controller;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.PageEntity;
import com.wintop.ms.carauction.core.entity.ServiceResult;
import com.wintop.ms.carauction.entity.CarManagerUser;
import com.wintop.ms.carauction.entity.CommonNameVo;
import com.wintop.ms.carauction.entity.ListEntity;
import com.wintop.ms.carauction.entity.WtAppUser;
import com.wintop.ms.carauction.service.ICarManagerUserService;
import com.wintop.ms.carauction.service.ICarStoreService;
import com.wintop.ms.carauction.service.IWtAppUserService;
import com.wintop.ms.carauction.util.utils.CarAutoUtils;
import com.wintop.ms.carauction.util.utils.IdWorker;
import io.swagger.models.auth.In;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 客户端用户使用接口类
 * 如：登陆
 */
@RestController
@RequestMapping("/service/managerUser")
public class CarManagerUserApi {

    private static final Logger logger = LoggerFactory.getLogger(CarManagerUserApi.class);

    @Autowired
    private ICarManagerUserService managerUserService;
    @Autowired
    private ICarStoreService iCarStoreService;

    private IdWorker idWorker = new IdWorker(10);

    /***
     * 后台用户登录
     * @param obj
     * @return
     */
    @RequestMapping(value = "/userLogin",
            method=RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<CarManagerUser> userLogin(@RequestBody JSONObject obj) {
        ServiceResult<CarManagerUser> result = new ServiceResult<>();
        try{
            String userPassword = obj.getString("userPassword");
            String userKey = obj.getString("userKey");
            CarManagerUser managerUser = managerUserService.selectByUserId(userKey);
            if(managerUser == null){
                result.setError(ResultCode.NO_MANAGER_USER.strValue(),ResultCode.NO_MANAGER_USER.getRemark());
                return result;
            }
            if(!managerUser.getUserPassword().equals(userPassword.toUpperCase())){
                result.setError(ResultCode.NO_MATCH_PASSWORD.strValue(),ResultCode.NO_MATCH_PASSWORD.getRemark());
                return result;
            }
            if("2".equals(managerUser.getUserStatus())){
                result.setError(ResultCode.NO_ALLOW_LOGIN.strValue(),ResultCode.NO_ALLOW_LOGIN.getRemark());
                return result;
            }
            if("3".equals(managerUser.getUserStatus())){
                result.setError(ResultCode.KEY_YET_DELETE.strValue(),ResultCode.KEY_YET_DELETE.getRemark());
                return result;
            }
            /*List<Long> storeIds = managerUserService.queryStoreScope(managerUser.getRoleTypeId(),managerUser.getDepartmentId());
            for(Long storeId:storeIds){
                System.out.println(storeId.longValue());
            }*/
            managerUser.setUserPassword(null);
            result.setSuccess(ResultCode.SUCCESS.strValue(),"登录成功");
            result.setResult(managerUser);
        } catch (Exception e){
            e.printStackTrace();
            logger.info("登录失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),"登录失败");
        }
        return result;
    }

    /***
     * 根据id查询用户详情
     * APP端
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getById",
            method=RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<CarManagerUser> getById(@RequestBody Long userId) {
        ServiceResult<CarManagerUser> result = new ServiceResult<>();
        try{
            CarManagerUser managerUser = managerUserService.selectByPrimaryKey(userId,false);
            if(managerUser == null){
                result.setError(ResultCode.NO_USER.strValue(),ResultCode.NO_USER.getRemark());
                return result;
            }
            managerUser.setUserPassword(null);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            result.setResult(managerUser);
        } catch (Exception e){
            e.printStackTrace();
            logger.info("查询用户失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /***
     * 修改用户密码
     * @param obj
     * @return
     */
    @RequestMapping(value = "/updateUserPassword",
            method=RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Integer>> updateUserPassword(@RequestBody JSONObject obj) {
        ServiceResult<Map<String,Integer>> result = new ServiceResult<>();
        try{
            String oldPassword = obj.getString("oldPassword");
            String newPassword = obj.getString("newPassword");
            Long id = obj.getLong("id");
            CarManagerUser oldUser = managerUserService.selectByPrimaryKey(id,false);
            if(oldUser == null){
                result.setError(ResultCode.NO_USER.strValue(),ResultCode.NO_USER.getRemark());
                return result;
            }
            if(!oldUser.getUserPassword().equals(oldPassword.toUpperCase())){
                result.setError(ResultCode.PASSWORD_NOT_MATCH.strValue(),ResultCode.PASSWORD_NOT_MATCH.getRemark());
                return result;
            }
            CarManagerUser managerUser = new CarManagerUser();
            managerUser.setId(id);
            managerUser.setUserPassword(newPassword.toUpperCase());
            int count = managerUserService.updateByPrimaryKey(managerUser);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            Map<String,Integer> map = new HashMap<>();
            map.put("count",count);
            result.setResult(map);
        } catch (Exception e){
            e.printStackTrace();
            logger.info("修改失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /***
     * 修改头像
     * @param object
     * @return
     */
    @PostMapping("updateHeadImg")
    public ServiceResult updateHeadImg(@RequestBody JSONObject object){
        ServiceResult result = new ServiceResult();
        try {
            Long userId = Long.parseLong(object.get("id").toString());
            String userPhoto = object.getString("userPhoto");
            if (managerUserService.updateUserPhoto(userId,userPhoto)>0) {
                result.setSuccess(true);
            }else {
                result.setError("头像修改失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setError("修改失败");
        }finally {
            return result;
        }
    }

    /***
     * 新增用户
     * @param obj
     * @return
     */
    @RequestMapping(value = "/saveManagerUser",
            method=RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Integer>> saveManagerUser(@RequestBody JSONObject obj) {
        ServiceResult<Map<String,Integer>> result = new ServiceResult<>();
        try{
            CarManagerUser user = JSONObject.toJavaObject(obj,CarManagerUser.class);
            if(managerUserService.selectByUserId(user.getUserKey())!=null){
                result.setError(ResultCode.KEY_EXIST.strValue(),ResultCode.KEY_EXIST.getRemark());
                return result;
            };
            user.setId(idWorker.nextId());
            if(StringUtils.isBlank(user.getUserPassword())){
                if(StringUtils.isNotBlank(user.getUserPhone())){
                    user.setUserPassword(DigestUtils.md5Hex(user.getUserPhone().substring(user.getUserPhone().length()-6)));
                }else{
                    user.setUserPassword(DigestUtils.md5Hex("123456"));
                }
            }
            user.setUserPassword(user.getUserPassword().toUpperCase());
            user.setUserStatus("1");
            user.setCreateTime(new Date());
            int count = managerUserService.insert(user);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            Map<String,Integer> map = new HashMap<>();
            map.put("count",count);
            result.setResult(map);
        } catch (Exception e){
            e.printStackTrace();
            logger.info("新增失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /***
     * 修改用户
     * @param obj
     * @return
     */
    @RequestMapping(value = "/updateManagerUser",
            method=RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Integer>> updateManagerUser(@RequestBody JSONObject obj) {
        ServiceResult<Map<String,Integer>> result = new ServiceResult<>();
        try{
            CarManagerUser user = JSONObject.toJavaObject(obj,CarManagerUser.class);
            user.setModifyTime(new Date());
            int count = managerUserService.updateByPrimaryKey(user);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            Map<String,Integer> map = new HashMap<>();
            map.put("count",count);
            result.setResult(map);
        } catch (Exception e){
            e.printStackTrace();
            logger.info("修改用户失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /***
     * 查询某个子部门下的所有有效用户
     * @return
     */
    @RequestMapping(value = "/selectAllManagerUser",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<List<CommonNameVo>> selectAllManagerUser(@RequestBody JSONObject obj) {
        ServiceResult<List<CommonNameVo>> result = new ServiceResult<>();
        try {
            List<CommonNameVo> userList = managerUserService.selectAllManagerUser(obj.getLong("departmentId"));
            result.setResult(userList);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询所有有效用户失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /***
     * 删除用户
     * @param obj
     * @return
     */
    @RequestMapping(value = "/deleteManagerUser",
            method=RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Integer>> deleteManagerUser(@RequestBody JSONObject obj) {
        ServiceResult<Map<String,Integer>> result = new ServiceResult<>();
        try{
            CarManagerUser user = new CarManagerUser();
            user.setId(obj.getLong("id"));
            user.setDelTime(new Date());
            user.setDelPerson(obj.getLong("delPerson"));
            user.setUserStatus("3");
            int count = managerUserService.updateByPrimaryKey(user);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            Map<String,Integer> map = new HashMap<>();
            map.put("count",count);
            result.setResult(map);
        } catch (Exception e){
            e.printStackTrace();
            logger.info("删除用户失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /***
     * 根据id查询用户详情
     * BOSS端
     * @param obj
     * @return
     */
    @RequestMapping(value = "/selectManagerUser",
            method=RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<CarManagerUser> selectManagerUser(@RequestBody JSONObject obj) {
        ServiceResult<CarManagerUser> result = new ServiceResult<>();
        try{
            CarManagerUser managerUser = managerUserService.selectByPrimaryKey(obj.getLong("id"),true);
            if(managerUser == null){
                result.setError(ResultCode.NO_USER.strValue(),ResultCode.NO_USER.getRemark());
                return result;
            }
            managerUser.setUserPassword(null);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            result.setResult(managerUser);
        } catch (Exception e){
            e.printStackTrace();
            logger.info("查询用户失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /***
     * 重置密码
     * @param obj
     * @return
     */
    @RequestMapping(value = "/resetUserPassword",
            method=RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<Map<String,Object>> resetUserPassword(@RequestBody JSONObject obj) {
        ServiceResult<Map<String,Object>> result = new ServiceResult<>();
        try{
            CarManagerUser user = new CarManagerUser();
            //String newPwd = CarAutoUtils.getRandomCode();
            String newPwd = "123456";
            user.setUserPassword(DigestUtils.md5Hex(newPwd).toUpperCase());
            user.setId(obj.getLong("userId"));
            int count = managerUserService.updateByPrimaryKey(user);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
            Map<String,Object> map = new HashMap<>();
            map.put("count",count);
            map.put("newPassword",newPwd);
            result.setResult(map);
        } catch (Exception e){
            e.printStackTrace();
            logger.info("重置密码失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /***
     * 查询所有用户列表
     * @return
     */
    @RequestMapping(value = "/selectManagerUserList",
            method= RequestMethod.POST,
            consumes="application/json; charset=UTF-8",
            produces="application/json; charset=UTF-8")
    public ServiceResult<ListEntity<CarManagerUser>> selectManagerUserList(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<CarManagerUser>> result = new ServiceResult<>();
        try {
            Map<String,Object> map = new HashMap<>();
            if(obj.get("searchName")!=null){
                map.put("searchName",obj.getString("searchName"));
            }
            if(obj.get("roleTypeId")!=null){
                map.put("roleTypeId",obj.getLong("roleTypeId"));
            }
            if(obj.get("roleId")!=null){
                map.put("roleId",obj.getLong("roleId"));
            }
            if(obj.get("name")!=null){
                Map<String,Object> storeMap = new HashMap<>();
                storeMap.put("name",obj.get("storeName"));
                Long storeId = iCarStoreService.idByExample(storeMap);
                map.put("department_id",storeId);
            }
            int count = managerUserService.countByExample(map);
            PageEntity pageEntity = CarAutoUtils.getPageParam(obj);
            map.put("startRowNum",pageEntity.getStartRowNum());
            map.put("endRowNum",pageEntity.getEndRowNum());
            List<CarManagerUser> userList = managerUserService.selectByExample(map);
            ListEntity<CarManagerUser> listEntity = new ListEntity<>(userList,count);
            result.setResult(listEntity);
            result.setSuccess(ResultCode.SUCCESS.strValue(),ResultCode.SUCCESS.getRemark());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询所有用户列表失败",e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(),ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

    /***
     * 查询所有用户列表
     * @return
     */
    @RequestMapping(value = "/selectAssessUserList",
            method = RequestMethod.POST,
            consumes = "application/json; charset=UTF-8",
            produces = "application/json; charset=UTF-8")
    public ServiceResult<ListEntity<CarManagerUser>> selectAssessUserList(@RequestBody JSONObject obj) {
        ServiceResult<ListEntity<CarManagerUser>> result = new ServiceResult<>();
        Map<String, Object> map = new HashMap<>();
        try {
            CarManagerUser managerUser = managerUserService.selectByPrimaryKey(obj.getLong("userId"), true);
            if (managerUser != null) {
                //平台用户查看所有，其它只查看自己店铺
                if (1 != managerUser.getRoleTypeId()) {
                    /*其他用户*/
                    map.put("departmentId", managerUser.getDepartmentId());
                    map.put("roleTypeId", managerUser.getRoleTypeId());
                    map.put("roleId", managerUser.getRoleId());
                }
            }
            int count = managerUserService.countByExample(map);
            List<CarManagerUser> userList = managerUserService.selectByExample(map);
            ListEntity<CarManagerUser> listEntity = new ListEntity<>(userList, count);
            result.setResult(listEntity);
            result.setSuccess(ResultCode.SUCCESS.strValue(), ResultCode.SUCCESS.getRemark());

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("查询所有用户列表失败", e);
            result.setError(ResultCode.BUSS_EXCEPTION.strValue(), ResultCode.BUSS_EXCEPTION.getRemark());
        }
        return result;
    }

}