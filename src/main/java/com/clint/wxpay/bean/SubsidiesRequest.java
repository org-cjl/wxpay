package com.clint.wxpay.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 请求补差API
 *    https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/subsidies/chapter3_1.shtml
 */
@Data
@NoArgsConstructor
public class SubsidiesRequest implements Serializable {
  private static final long serialVersionUID = -1242741645939606441L;
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
   * 字段名：补差金额
   * 变量名：amount
   * 是否必填：是
   * 类型：int
   * 描述：
   *  补差金额，单位为分，只能为整数，不能超过下单时候的最大补差金额。
   * 注意：单笔订单最高补差金额为5000元
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
   *  补差备注描述，查询的时候原样带回。
   * 示例值：测试备注
   * </pre>
   */
  @SerializedName(value = "description")
  private String description;

  /**
   * <pre>
   * 字段名：微信退款单号
   * 变量名：refund_id
   * 是否必填：否
   * 类型：string(64)
   * 描述：
   *  微信退款单号，微信系统退款返回的唯一标识，当补差金额小于下单时候的金额，该字段必填
   * 示例值：3008450740201411110007820472
   * </pre>
   */
  @SerializedName(value = "refund_id")
  private String refundId;

}
