package com.wintop.ms.carauction.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagerRolePages {
    private static ManagerRolePages managerRolePages = new ManagerRolePages();
    private static HashMap<Long,List<String>> requestPageMap = new HashMap<>();
    public void putData(Long userId,List<String> pages){
        requestPageMap.put(userId,pages);
    }
    public List<String> getAllRequestPages(Long userId){
        List<String> pageUrls = requestPageMap.get(userId);
        if(pageUrls==null){
            return new ArrayList<>();
        }
        return pageUrls;
    }
    public static ManagerRolePages getInstance(){
        if(managerRolePages==null){
            managerRolePages = new ManagerRolePages();
        }
        if(requestPageMap==null){
            requestPageMap = new HashMap<>();
        }
        return managerRolePages;
    }
    private ManagerRolePages(){}
}
