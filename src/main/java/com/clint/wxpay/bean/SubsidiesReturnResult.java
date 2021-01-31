package com.clint.wxpay.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 请求补差回退API
 *    https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/subsidies/chapter3_2.shtml
 */
@Data
@NoArgsConstructor
public class SubsidiesReturnResult implements Serializable {
  private static final long serialVersionUID = -3186851559004865784L;

  /**
   * <pre>
   * 字段名：二级商户号
   * 变量名：sub_mchid
   * 是否必填：是
   * 类型：string(32)
   * 描述：
   *  补差的电商平台二级商户，填写微信支付分配的商户号。
   * 示例值：1900000109
   * </pre>
   */
  @SerializedName(value = "sub_mchid")
  private String subMchid;

  /**
   * <pre>
   * 字段名：微信订单号
   * 变量名：transaction_id
   * 是否必填：是
   * 类型：string(64)
   * 描述：
   *  微信支付订单号。
   * 示例值： 4208450740201411110007820472
   * </pre>
   */
  @SerializedName(value = "transaction_id")
  private String transactionId;

  /**
   * <pre>
   * 字段名：微信补差回退单号
   * 变量名：subsidy_refund_id
   * 是否必填：是
   * 类型：string(64)
   * 描述：
   *  微信补差回退单号，微信补差回退系统返回的唯一标识。
   * 示例值： 3008450740201411110007820472
   * </pre>
   */
  @SerializedName(value = "subsidy_refund_id")
  private String subsidyRefundId;

  /**
   * <pre>
   * 字段名：微信退款单号
   * 变量名：refund_id
   * 是否必填：是
   * 类型：string(64)
   * 描述：
   *  微信退款单号，微信系统退款返回的唯一标识。
   * 示例值： 3008450740201411110007820472
   * </pre>
   */
  @SerializedName(value = "refund_id")
  private String refundId;

  /**
   * <pre>
   * 字段名：商户补差回退单号
   * 变量名：out_order_no
   * 是否必填：是
   * 类型：string(64)
   * 描述：
   *  商户系统内部的补差回退单号，在商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一补差回退单号多次请求等同一次。
   * 示例值：P20150806125346
   * </pre>
   */
  @SerializedName(value = "out_order_no")
  private String outOrderNo;

  /**
   * <pre>
   * 字段名：补差回退金额
   * 变量名：amount
   * 是否必填：是
   * 类型：int
   * 描述：
   *  补差回退金额
   * 示例值：10
   * </pre>
   */
  @SerializedName(value = "amount")
  private Integer amount;

  /**
   * <pre>
   * 字段名：补差描述
   * 变量名：description
   * 是否必填：是
   * 类型：string(80)
   * 描述：
   *  补差描述
   * 示例值：满减补差活动
   * </pre>
   */
  @SerializedName(value = "description")
  private String description;

  /**
   * <pre>
   * 字段名：补差回退结果
   * 变量名：result
   * 是否必填：是
   * 类型：string(16)
   * 描述：
   *  补差回退结果，枚举值：
   *    SUCCESS：成功
   *    FAIL：失败
   * 示例值：SUCCESS
   * </pre>
   */
  @SerializedName(value = "result")
  private String result;

  /**
   * <pre>
   * 字段名：补差回退完成时间
   * 变量名：success_time
   * 是否必填：是
   * 类型：string(32)
   * 描述：
   *  补差回退完成时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss.sss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss.sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日13点29分35秒。
   * 示例值：2015-05-20T13:29:35.120+08:00
   * </pre>
   */
  @SerializedName(value = "success_time")
  private Date successTime;

}
