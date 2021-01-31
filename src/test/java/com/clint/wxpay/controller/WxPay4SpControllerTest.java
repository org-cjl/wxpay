package com.clint.wxpay.controller;

import com.clint.wxpay.AbstractMock;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Clint
 * Date: 2021-01-08 15:58
 * Description:
 */
public class WxPay4SpControllerTest extends AbstractMock {

  @Test
  public void queryOrder() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/wx/pay/sp/queryOrder")
        .param("transactionId", "1008450740201411110005820873")
        // .param("outTradeNo", "WB00000000000000")
        .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andDo(log());
  }

  @Test
  public void closeOrder() throws Exception {
    String outTradeNo = "WB00000000000000";
    mockMvc.perform(MockMvcRequestBuilders.delete("/wx/pay/sp/closeOrder/" + outTradeNo)
        .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andDo(log());
  }

  @Test
  public void unifiedOrder() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/wx/pay/sp/createOrder")
        .content("{\n" +
            // "    \"appid\":\"wx2421b1c4370ec43b\",\n" +
            "    \"attach\":\"支付测试\",\n" +
            "    \"body\":\"JSAPI支付测试\",\n" +
            // "    \"mch_id\":\"10000100\",\n" +
            "    \"detail\":\"{ \"goods_detail\":[ { \"goods_id\":\"iphone6s_16G\", \"wxpay_goods_id\":\"1001\", \"goods_name\":\"iPhone6s 16G\", \"quantity\":1, \"price\":528800, \"goods_category\":\"123456\", \"body\":\"苹果手机\" }, { \"goods_id\":\"iphone6s_32G\", \"wxpay_goods_id\":\"1002\", \"goods_name\":\"iPhone6s 32G\", \"quantity\":1, \"price\":608800, \"goods_category\":\"123789\", \"body\":\"苹果手机\" } ] }\",\n" +
            // "    \"nonce_str\":\"1add1a30ac87aa2db72f57a2375d8fec\",\n" +
            "    \"notify_url\":\"http://wxpay.wxutil.com/pub_v2/pay/notify.v2.php\",\n" +
            "    \"openid\":\"oUpF8uMuAJO_M2pxb1Q9zNjWeS6o\",\n" +
            "    \"out_trade_no\":\"1415659990\",\n" +
            "    \"spbill_create_ip\":\"14.23.150.211\",\n" +
            "    \"total_fee\":\"1\",\n" +
            "    \"trade_type\":\"JSAPI\",\n" +
            // "    \"sign\":\"0CB01533B8C1EF103065174F50BCA001\"\n" +
            "}")
        .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andDo(log());
  }

  @Test
  public void refund() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/wx/pay/sp/refund")
        .content("{\n" +
            // "    \"appid\":\"wx2421b1c4370ec43b\",\n" +
            // "    \"mch_id\":\"10000100\",\n" +
            // "    \"nonce_str\":\"6cefdb308e1e2e8aabd48cf79e546a02\",\n" +
            "    \"out_refund_no\":\"1415701182\",\n" +
            "    \"out_trade_no\":\"1415757673\",\n" +
            "    \"refund_fee\":\"1\",\n" +
            "    \"total_fee\":\"1\",\n" +
            "    \"transaction_id\":\"4006252001201705123297353072\",\n" +
            // "    \"sign\":\"FE56DD4AA85C0EECA82C35595A69E153\"\n" +
            "}")
        .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andDo(log());
  }

  @Test
  public void refundQuery() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/wx/pay/sp/refundQuery")
        // .param("transactionId", "1008450740201411110005820873")
        .param("outTradeNo", "WB00000000000000")
        // .param("outRefundNo", "")
        // .param("refundId", "")
        .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andDo(log());
  }
}