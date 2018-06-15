package com.wintop.ms.carauction.util.utils;

import com.wintop.ms.carauction.core.entity.PageEntity;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhangzijuan
 * @Description:
 * @date 2018-03-15
 */
public class PageUtils {
    /**
     * 获取分页参数
     * @param page,count
     * @return
     */
    public static PageEntity getPageParam(String page, String count){
        PageEntity pageEntity = new PageEntity();
        int page1 = 1;
        int pageSize = 10;
        if(StringUtils.isNotBlank(page)){
            page1 = Integer.parseInt(page);
        }
        if(StringUtils.isNotBlank(count)){
            pageSize = Integer.parseInt(count);
        }
        pageEntity.setStartRowNum((page1-1)*pageSize);
        pageEntity.setEndRowNum(pageSize);
        return pageEntity;
    }
}
