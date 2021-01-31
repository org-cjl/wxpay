package com.clint.wxpay.controller;

import com.github.binarywang.wxpay.bean.profitsharing.*;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Clint
 * Date: 2021-01-06 15:54
 * Description: 微信支付(服务商模式) https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pay/transactions/chapter1_2.shtml
 */
@RestController
@RequestMapping("/wx/pay/profitSharing")
@RequiredArgsConstructor
public class WxPay4ProfitSharingController {
  private final WxPayService wxPayService;

  /**
   * 服务商代子商户发起添加分账接收方请求，后续可通过发起分账请求将结算后的钱分到该分账接收方。
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/api/allocation_sl.php?chapter=25_3&index=4
   * 接口链接：https://api.mch.weixin.qq.com/pay/profitsharingaddreceiver
   * @param request
   * @return
   * @throws WxPayException
   */
  @PostMapping("/addReceiver")
  public ProfitSharingReceiverResult addReceiver(@RequestBody ProfitSharingReceiverRequest request)
      throws WxPayException {
    return wxPayService.getProfitSharingService().addReceiver(request);
  }

  /**
   * 服务商代子商户发起删除分账接收方请求，删除后不支持将结算后的钱分到该分账接收方。
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/api/allocation_sl.php?chapter=25_4&index=5
   * 接口链接：https://api.mch.weixin.qq.com/pay/profitsharingremovereceiver
   * @param request
   * @return
   * @throws WxPayException
   */
  @PostMapping("/removeReceiver")
  public ProfitSharingReceiverResult removeReceiver(@RequestBody ProfitSharingReceiverRequest request)
      throws WxPayException {
    return wxPayService.getProfitSharingService().removeReceiver(request);
  }

  /**
   * 单次分账请求按照传入的分账接收方账号和资金进行分账，同时会将订单剩余的待分账金额解冻给特约商户。故操作成功后，订单不能再进行分账，也不能进行分账完结。
   * 接口频率：30QPS
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/api/allocation_sl.php?chapter=25_1&index=1
   * 接口链接：https://api.mch.weixin.qq.com/secapi/pay/profitsharing
   * @param request
   * @return
   * @throws WxPayException
   */
  @PostMapping("/profitSharing")
  public ProfitSharingResult profitSharing(@RequestBody ProfitSharingRequest request)
      throws WxPayException {
    return wxPayService.getProfitSharingService().profitSharing(request);
  }

  /* TODO 分账动账通知 https://pay.weixin.qq.com/wiki/doc/api/allocation_sl.php?chapter=25_9&index=11
       分账或分账回退成功后，微信会把相关变动结果发送给分账接收方（只支持商户）。
       对后台通知交互时，如果微信收到应答不是成功或超时，微信认为通知失败，微信会通过一定的策略定期重新发起通知，尽可能提高通知的成功率，但微信不保证通知最终能成功。
   */

  /**
   * 仅对订单进行退款时，如果订单已经分账，可以先调用此接口将指定的金额从分账接收方（仅限商户类型的分账接收方）回退给特约商户，然后再退款。
   * 回退以原分账请求为依据，可以对分给分账接收方的金额进行多次回退，只要满足累计回退不超过该请求中分给接收方的金额。
   * 此接口采用同步处理模式，即在接收到商户请求后，会实时返回处理结果。
   * 此功能需要接收方在商户平台-交易中心-分账-分账接收设置下，开启同意分账回退后，才能使用。
   * 接口频率：30QPS
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/api/allocation_sl.php?chapter=25_7&index=7
   * 接口链接：https://api.mch.weixin.qq.com/secapi/pay/profitsharingreturn
   * @param request
   * @return
   * @throws WxPayException
   */
  @PostMapping("/profitSharingReturn")
  public ProfitSharingReturnResult profitSharingReturn(@RequestBody ProfitSharingReturnRequest request)
      throws WxPayException {
    return wxPayService.getProfitSharingService().profitSharingReturn(request);
  }

  /**
   * 商户需要核实回退结果，可调用此接口查询回退结果。
   * 如果分账回退接口返回状态为处理中，可调用此接口查询回退结果。
   * 接口频率：30QPS
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/api/allocation_sl.php?chapter=25_8&index=8
   * 接口链接：https://api.mch.weixin.qq.com/pay/profitsharingreturnquery
   * @param request
   * @return
   * @throws WxPayException
   */
  @PostMapping("/profitSharingReturnQuery")
  public ProfitSharingReturnResult profitSharingReturnQuery(@RequestBody ProfitSharingReturnQueryRequest request)
      throws WxPayException {
    return wxPayService.getProfitSharingService().profitSharingReturnQuery(request);
  }
}
