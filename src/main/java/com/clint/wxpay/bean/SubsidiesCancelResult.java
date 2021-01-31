package com.clint.wxpay.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 取消补差API
 *    https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/subsidies/chapter3_3.shtml
 */
@Data
@NoArgsConstructor
public class SubsidiesCancelResult implements Serializable {
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
   * 字段名：取消补差结果
   * 变量名：result
   * 是否必填：是
   * 类型：string(16)
   * 描述：
   *  取消补差结果，枚举值：
   *    SUCCESS：成功
   *    FAIL：失败
   * 示例值：SUCCESS
   * </pre>
   */
  @SerializedName(value = "result")
  private String result;

  /**
   * <pre>
   * 字段名：取消补差描述
   * 变量名：description
   * 是否必填：是
   * 类型：string(80)
   * 描述：
   *  取消补差描述
   * 示例值：订单退款
   * </pre>
   */
  @SerializedName(value = "description")
  private String description;

}
