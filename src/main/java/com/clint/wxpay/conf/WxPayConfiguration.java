package com.clint.wxpay.conf;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Clint
 * Date: 2021-01-05 14:17
 * Description: 服务商配置
 */
@Data
@Configuration
@ConfigurationProperties("wx.pay.sp")
public class WxPayConfiguration {

  /**
   * 微信公众号或者小程序等的appid
   */
  private String appId;
  /**
   * 微信支付商户号
   */
  private String mchId;
  /**
   * 微信支付商户密钥
   */
  private String mchKey;

  /**
   * 服务商模式下的子商户公众账号ID(非必填)，直连模式请不要配置
   */
  private String subAppId;
  /**
   * 服务商模式下的子商户号，直连模式请不要配置
   */
  private String subMchId;

  /**
   * apiclient_cert.p12文件的绝对路径，或者如果放在项目中，请以classpath:开头指定
   */
  private String keyPath;

  /**
   * 获取 WxPayService
   */
  @Bean
  @ConditionalOnMissingBean
  public WxPayService getWxPayService() {
    WxPayConfig payConfig = new WxPayConfig();
    payConfig.setAppId(StringUtils.trimToNull(this.appId));
    payConfig.setMchId(StringUtils.trimToNull(this.mchId));
    payConfig.setMchKey(StringUtils.trimToNull(this.mchKey));
    payConfig.setKeyPath(StringUtils.trimToNull(this.keyPath));

    // 服务商模式(设置特约子商户)
    payConfig.setSubAppId(StringUtils.trimToNull(this.subAppId));
    payConfig.setSubMchId(StringUtils.trimToNull(this.subMchId));

    // 可以指定是否使用沙箱环境
    payConfig.setUseSandboxEnv(false);

    WxPayService wxPayService = new WxPayServiceImpl();
    wxPayService.setConfig(payConfig);
    return wxPayService;
  }
}
