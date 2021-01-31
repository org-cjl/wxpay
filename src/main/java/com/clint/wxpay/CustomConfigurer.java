package com.clint.wxpay;

import com.clint.wxpay.util.MdcThreadUtils;
import org.slf4j.MDC;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Clint
 * Date: 2020-09-04 13:56
 * Description:
 */
@Configuration
public class CustomConfigurer {

  @Bean
  public FilterRegistrationBean<Filter> registerTraceFilter() {
    FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
    registration.setFilter(new GenericFilter() {
      @Override
      public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
          MdcThreadUtils.setTraceIdIfAbsent();
          filterChain.doFilter(servletRequest, servletResponse);
        } finally {
          MDC.clear();
        }
      }
    });
    registration.addUrlPatterns("/*");
    registration.setName("traceFilter");
    registration.setOrder(1);  //值越小，Filter越靠前。
    return registration;
  }

}
