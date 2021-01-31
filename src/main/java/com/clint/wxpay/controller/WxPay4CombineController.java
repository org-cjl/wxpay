package com.clint.wxpay.controller;

import com.clint.wxpay.bean.*;
import com.github.binarywang.wxpay.bean.ecommerce.*;
import com.github.binarywang.wxpay.bean.ecommerce.enums.TradeTypeEnum;
import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.v3.util.RsaCryptoUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * Created by Clint
 * Date: 2021-01-06 15:54
 * Description: 微信合单支付 https://pay.weixin.qq.com/wiki/doc/apiv3/open/pay/chapter2_9_1.shtml
 */
@RestController
@RequestMapping("/wx/pay/combine")
@RequiredArgsConstructor
public class WxPay4CombineController {
  private static final Gson GSON = new GsonBuilder().create();
  private final WxPayService wxPayService;

  /**
   * JSAPI、小程序场景合单下单
   *    settleInfo: 可设置补差金额subsidy_amount(分账profit_sharing必须为true)  在分账之前调用补差价接口
   *    https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter5_1_4.shtml
   * @param request
   * @return
   * @throws WxPayException
   */
  @PostMapping("/jsapi")
  public TransactionsResult combine(@RequestBody CombineTransactionsRequest request)
      throws WxPayException {
    return wxPayService.getEcommerceService().combine(TradeTypeEnum.JSAPI, request);
  }

  /**
   * 合单查询订单
   *    https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter5_1_11.shtml
   * @param combineOutTradeNo
   * @return
   * @throws WxPayException
   */
  @GetMapping("/{combineOutTradeNo}")
  public CombineTransactionsResult queryCombineTransactions(@PathVariable String combineOutTradeNo)
      throws WxPayException {
    return wxPayService.getEcommerceService().queryCombineTransactions(combineOutTradeNo);
  }

  /**
   * 合单关闭订单
   *    https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter5_1_12.shtml
   * @param request
   * @return
   * @throws WxPayException
   */
  @DeleteMapping("/close")
  public void closeCombineTransactions(@RequestBody CombineTransactionsCloseRequest request)
      throws WxPayException {
    String url = String.format("%s/v3/combine-transactions/out-trade-no/%s/close", wxPayService.getPayBaseUrl(), request.getCombineOutTradeNo());

    wxPayService.postV3(url, GSON.toJson(request));
  }

  /**
   * 微信合单支付结果通知
   *    https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter5_1_13.shtml
   * @param xmlData
   * @return
   * @throws WxPayException
   */
  @PostMapping("/notify/combine")
  public String parseCombineNotifyResult(@RequestBody String xmlData,
                                         @RequestHeader SignatureHeader header)
      throws WxPayException {
    final CombineTransactionsNotifyResult notifyResult = wxPayService.getEcommerceService().parseCombineNotifyResult(xmlData, header);
    // TODO 根据自己业务场景需要构造返回对象
    return WxPayNotifyResponse.success("成功");
  }


  /**
   * 请求补差
   *  服务商下单的时候带上补差标识，微信订单支付成功并结算完成后，发起分账前，调用该口进行补差。
   *    https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/subsidies/chapter3_1.shtml
   * @param request
   * @return
   * @throws WxPayException
   */
  @PostMapping("/subsidies")
  public SubsidiesResult subsidies(@RequestBody SubsidiesRequest request)
      throws WxPayException {
    String url = String.format("%s/v3/ecommerce/subsidies/create", wxPayService.getPayBaseUrl());

    String result = wxPayService.postV3(url, GSON.toJson(request));
    return GSON.fromJson(result, SubsidiesResult.class);
  }

  /**
   * 请求补差回退
   *  订单发送退款的时候，可以对补贴成功的补差单发起回退。
   *    https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/subsidies/chapter3_2.shtml
   * @param request
   * @return
   * @throws WxPayException
   */
  @PostMapping("/subsidiesReturn")
  public SubsidiesReturnResult subsidiesReturn(@RequestBody SubsidiesReturnRequest request)
      throws WxPayException {
    String url = String.format("%s/v3/ecommerce/subsidies/return", wxPayService.getPayBaseUrl());

    String result = wxPayService.postV3(url, GSON.toJson(request));
    return GSON.fromJson(result, SubsidiesReturnResult.class);
  }

  /**
   * 取消补差
   *  对带有补差标识的订单，如果不需要补差，可在发起分账前，可调用这个接口进行取消补差。
   *    https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/subsidies/chapter3_3.shtml
   * @param request
   * @return
   * @throws WxPayException
   */
  @PostMapping("/subsidiesCancel")
  public SubsidiesCancelResult subsidiesCancel(@RequestBody SubsidiesCancelRequest request)
      throws WxPayException {
    String url = String.format("%s/v3/ecommerce/subsidies/cancel", wxPayService.getPayBaseUrl());

    String result = wxPayService.postV3(url, GSON.toJson(request));
    return GSON.fromJson(result, SubsidiesCancelResult.class);
  }


}
