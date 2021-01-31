package com.clint.wxpay.conf;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Clint
 * Date: 2021-01-05 14:17
 * Description: 小程序配置
 */
@Data
@Configuration
@ConfigurationProperties("wax.ma")
public class WxMaConfiguration {

  /**
   * 微信小程序的appid
   */
  private String appId;

  /**
   * 微信小程序的Secret
   */
  private String secret;

  /**
   * 微信小程序消息服务器配置的token
   */
  private String token;

  /**
   * 微信小程序消息服务器配置的EncodingAESKey
   */
  private String aesKey;

  /**
   * 获取 WxMaService
   */
  @Bean
  @ConditionalOnMissingBean
  public WxMaService getWxMaService() {
    WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
    config.setAppid(StringUtils.trimToNull(this.appId));
    config.setSecret(StringUtils.trimToNull(this.secret));
    config.setToken(StringUtils.trimToNull(this.token));
    config.setAesKey(StringUtils.trimToNull(this.aesKey));

    WxMaService wxMaService = new WxMaServiceImpl();
    wxMaService.setWxMaConfig(config);
    return wxMaService;
  }
}
