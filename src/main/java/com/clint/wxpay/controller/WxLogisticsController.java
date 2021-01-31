package com.clint.wxpay.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.express.WxMaExpressAccount;
import cn.binarywang.wx.miniapp.bean.express.WxMaExpressDelivery;
import cn.binarywang.wx.miniapp.bean.express.WxMaExpressPath;
import cn.binarywang.wx.miniapp.bean.express.WxMaExpressPrinter;
import cn.binarywang.wx.miniapp.bean.express.request.*;
import cn.binarywang.wx.miniapp.bean.express.result.WxMaExpressOrderInfoResult;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Clint
 * Date: 2021-01-06 15:54
 * Description: 微信物流助手 https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/express/introduction.html
 *  产品介绍:
 *    快递接口是微信官方为小程序提供的免费物流接口。
 *    接入后，你可使用本接口在多家快递公司获取电子面单单号等信息，再通过热敏打印机完成电子面单打印，即可将快件交给快递公司上门揽收。
 *  接入指引:
 *    前往微信公众平台→【物流助手】→【去接入】查看接入流程指引
 */
@RestController
@RequestMapping("1.")
@RequiredArgsConstructor
public class WxLogisticsController {
  private final WxMaService wxMaService;

  /**
   * 绑定、解绑物流账号
   *    https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/express/by-business/logistics.bindAccount.html
   */
  @PostMapping("/bindAccount")
  public void bindAccount(@RequestBody WxMaExpressBindAccountRequest request)
      throws WxErrorException {
    this.wxMaService.getExpressService().bindAccount(request);
  }

  /**
   * 获取所有绑定的物流账号
   *    https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/express/by-business/logistics.getAllAccount.html
   */
  @GetMapping("/getAllAccount")
  public List<WxMaExpressAccount> getAllAccount()
      throws WxErrorException {
    return this.wxMaService.getExpressService().getAllAccount();
  }

  /**
   * 获取支持的快递公司列表
   *    https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/express/by-business/logistics.getAllDelivery.html
   */
  @GetMapping("/getAllDelivery")
  public List<WxMaExpressDelivery> getAllDelivery()
      throws WxErrorException {
    return this.wxMaService.getExpressService().getAllDelivery();
  }

  /**
   * 生成运单
   *    https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/express/by-business/logistics.addOrder.html
   */
  @PostMapping("/addOrder")
  public WxMaExpressOrderInfoResult addOrder(@RequestBody WxMaExpressAddOrderRequest request)
      throws WxErrorException {
    return this.wxMaService.getExpressService().addOrder(request);
  }

  /**
   * 取消运单
   *    https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/express/by-business/logistics.cancelOrder.html
   */
  @DeleteMapping("/cancelOrder")
  public void cancelOrder(@RequestBody WxMaExpressGetOrderRequest request)
      throws WxErrorException {
    this.wxMaService.getExpressService().cancelOrder(request);
  }

  /**
   * 获取运单数据
   *    https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/express/by-business/logistics.getOrder.html
   */
  @PostMapping("/getOrder")
  public WxMaExpressOrderInfoResult getOrder(@RequestBody WxMaExpressGetOrderRequest request)
      throws WxErrorException {
    return this.wxMaService.getExpressService().getOrder(request);
  }

  /**
   * 批量获取运单数据
   *    https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/express/by-business/logistics.batchGetOrder.html
   */
  @PostMapping("/batchGetOrder")
  public List<WxMaExpressOrderInfoResult> batchGetOrder(@RequestBody List<WxMaExpressGetOrderRequest> request)
      throws WxErrorException {
    return this.wxMaService.getExpressService().batchGetOrder(request);
  }

  /**
   * 查询运单轨迹
   *    https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/express/by-business/logistics.getPath.html
   *
   * 运单轨迹更新事件(接入微信小程序消息推送服务)
   *    https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/express/by-business/logistics.onPathUpdate.html
   */
  @PostMapping("/getPath")
  public WxMaExpressPath getPath(@RequestBody WxMaExpressGetOrderRequest request)
      throws WxErrorException {
    return this.wxMaService.getExpressService().getPath(request);
  }

  /**
   * 配置面单打印员，可以设置多个，若需要使用微信打单 PC 软件，才需要调用。
   *    https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/express/by-business/logistics.updatePrinter.html
   */
  @PostMapping("/updatePrinter")
  public void updatePrinter(@RequestBody WxMaExpressPrinterUpdateRequest request)
      throws WxErrorException {
    this.wxMaService.getExpressService().updatePrinter(request);
  }

  /**
   * 获取打印员。若需要使用微信打单 PC 软件，才需要调用
   *    https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/express/by-business/logistics.getPrinter.html
   */
  @GetMapping("/getPrinter")
  public WxMaExpressPrinter getPrinter()
      throws WxErrorException {
    return this.wxMaService.getExpressService().getPrinter();
  }

  /**
   * 获取电子面单余额(单位: 分)。仅在使用加盟类快递公司时，才可以调用。
   *    https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/express/by-business/logistics.getQuota.html
   */
  @PostMapping("/getQuota")
  public Integer getQuota(@RequestBody WxMaExpressBindAccountRequest request)
      throws WxErrorException {
    return this.wxMaService.getExpressService().getQuota(request);
  }

  /**
   * 模拟快递公司更新订单状态, 该接口只能用户测试(沙盒测试)
   *   https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/express/by-business/logistics.testUpdateOrder.html
   */
  @PostMapping("/testUpdateOrder")
  public void testUpdateOrder(@RequestBody WxMaExpressTestUpdateOrderRequest request)
      throws WxErrorException {
    this.wxMaService.getExpressService().testUpdateOrder(request);
  }





}
