package com.wintop.ms.carauction.config;
 
import com.alibaba.druid.support.http.WebStatFilter;
 
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
 
/**
 * Created by LF on 2017/4/18.
 */
@WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*",
    initParams = {
        @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")//忽略资源
    }
)
public class DruidStatFilter extends WebStatFilter {
}