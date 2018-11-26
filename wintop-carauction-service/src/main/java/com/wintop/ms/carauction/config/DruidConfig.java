package com.wintop.ms.carauction.config;
 
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
/**
 * Created by LF on 2017/4/18.
 */
@Configuration
public class DruidConfig {
  @Bean
  public ServletRegistrationBean registrationBean() {
    ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet());    //添加初始化参数：initParams
    servletRegistrationBean.addUrlMappings("/druid/*");
    //白名单：
    servletRegistrationBean.addInitParameter("allow", "");
    //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
    servletRegistrationBean.addInitParameter("deny", "");
    //登录查看信息的账号密码.
    servletRegistrationBean.addInitParameter("loginUsername", "admin");
    servletRegistrationBean.addInitParameter("loginPassword", "123456");
    //是否能够重置数据.
    servletRegistrationBean.addInitParameter("resetEnable", "false");
    return servletRegistrationBean;
  }
 
  @Bean
  public FilterRegistrationBean filterRegistrationBean() {
    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
    //添加过滤规则.
    filterRegistrationBean.addUrlPatterns("/*");
    //添加不需要忽略的格式信息.
    filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
    return filterRegistrationBean;
  }
}