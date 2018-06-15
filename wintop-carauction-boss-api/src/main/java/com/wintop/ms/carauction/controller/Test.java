package com.wintop.ms.carauction.controller;

import com.wintop.ms.carauction.core.config.ManagerRole;
import com.wintop.ms.carauction.core.entity.AppUser;
import com.wintop.ms.carauction.core.entity.CarManagerUser;
import com.wintop.ms.carauction.util.utils.RedisAppUserManager;
import com.wintop.ms.carauction.util.utils.RedisStoreUserManager;
import com.wintop.ms.carauction.util.utils.RedisTokenManager;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

@RestController
@RequestMapping("test")
public class Test {

    @Autowired
    private RedisTokenManager tokenManager;
    @Autowired
    private RedisAppUserManager appUserManager;

    @Autowired
    private RedisStoreUserManager storeUserManager;

    @PostMapping("deleteCusAppUserToken")
    public void deleteCusAppUserToken(String userId){
        //删除买家端用户token=====冻结或退会使用
        tokenManager.deleteCusAppUserToken("用户id");
    }
    @PostMapping("deleteStoreUserToken")
    public void deleteStoreUserToken(String userId){
        //删除卖家端用户token======冻结管理账户使用
        tokenManager.deleteStoreUserToken("用户id");
    }



    @PostMapping("cleanAppUser")
    public void cleanAppUser(String userId){
        //清理redis用户信息=========冻结退会使用
        appUserManager.cleanAppUser("用户id");
        appUserManager.saveUser(new AppUser());
    }
    @PostMapping("cleanStoreUser")
    public void cleanStoreUser(String userId){
        //清理卖家redis信息=========冻结管理账户使用
        storeUserManager.cleanStoreUser("用户id");
        storeUserManager.saveUser(new CarManagerUser());
    }

    /**
     *
     *        appUserManager.updateUserStatus("用户id","状态");=============买家状态变更使用
     *        storeUserManager.updateStoreStatus("用户id","状态");==============卖家状态变更使用
     *
     *
     *        tokenManager.deleteCusAppUserToken("用户id");
     *        appUserManager.cleanAppUser("用户id");          ====清理买家用户信息
     *
     *        tokenManager.deleteStoreUserToken("用户id");
     *        storeUserManager.cleanStoreUser("用户id");          ====清理卖家用户信息
     *
     *
     *        appUserManager.saveUser(new AppUser());        =========== 修改买家用户信息
     *        storeUserManager.saveUser(new CarManagerUser());===========修改卖家用户信息
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     * */

    public static void main1(String[] args) throws Exception {
        File file = new File("D:\\IdeaProjects\\wintop-car-auction\\wintop-carauction-boss-api\\src\\main\\java\\com\\wintop\\ms\\carauction\\controller");
        File[] files = file.listFiles();
        int i=0;
        List<String> urls = new ArrayList<>();
        for(File file1:files){
            if(file1.getName().contains("CarProvinceApi") || file1.getName().contains("CarShareFriend")
                    || file1.getName().contains("Test") || file1.getName().contains("InitApi")){
                continue;
            }
            FileReader reader = new FileReader(file1);
            BufferedReader br = new BufferedReader(reader);
            String str;
            while ((str = br.readLine()) != null) {
                if((str.contains("@PostMapping") || str.contains("@RequestMapping")) && !str.contains("value")){
                    int begin = str.indexOf("\"");
                    int end = str.indexOf("\"",begin+1);
                    System.out.println("-------"+str.substring(begin+1,end));
                }
                if((str.contains("@PostMapping") || str.contains("@RequestMapping")) && str.contains("value")){
                    int begin = str.indexOf("\"");
                    int end = str.indexOf("\"",begin+1);
                    String url = str.substring(begin+1,end).replace("/","");
                    System.out.println(url);
                    i++;
                    urls.add(url);
                }
            }
            br.close();
            reader.close();
        }
        FileReader reader = new FileReader("D:\\柠檬好车\\访问路径配置04250.txt");
        BufferedReader br = new BufferedReader(reader);
        String str;
        List<String> url0s = new ArrayList<>();
        while ((str = br.readLine()) != null) {
            url0s.add(str);
        }
        br.close();
        reader.close();
        System.out.println(i);
        for(String url:urls){
            boolean flag = true;
            for(String url0:url0s){
                if(url0.contains(url)){
                    flag=false;
                    break;
                }
            }
            if(flag){
                System.out.println(url);
            }
        }

    }

    public static void main(String[] args){
        Map<String,Object> map = new HashMap<>();
        map.put("key1","null");
        System.out.println(map.get("key1")==null);
        System.out.println(map.get("key2")==null);
    }
}
