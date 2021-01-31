package com.clint.wxpay.controller;

import com.clint.wxpay.AbstractMock;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Clint
 * Date: 2021-01-08 16:24
 * Description:
 */
public class WxPay4ProfitSharingControllerTest extends AbstractMock {

  @Test
  public void addReceiver() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/wx/pay/profitSharing/addReceiver")
        .content("{\n" +
            // "    \"mch_id\":\"10000100\",\n" +
            "    \"sub_mch_id\":\"1415701182\",\n" +
            // "    \"appid\":\"wx2421b1c4370ec43b\",\n" +
            "    \"sub_appid\":\"wx2203b1494370e08cm\",\n" +
            // "    \"nonce_str\":\"6cefdb308e1e2e8aabd48cf79e546a02\",\n" +
            // "    \"sign\":\"ABC6DD4AA85C0EECA82C35595A69EFGH\",\n" +
            // "    \"sign_type\":\"HMAC-SHA256\",\n" +
            "    \"receiver\":\"{\n" +
            "       \"type\": \"MERCHANT_ID\",\n" +
            "       \"account\": \"190001001\",\n" +
            "       \"name\": \"示例商户全称\",\n" +
            "       \"relation_type\": \"STORE_OWNER\"\n" +
            "    }\"\n" +
            "}")
        .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andDo(log());
  }

  @Test
  public void removeReceiver() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/wx/pay/profitSharing/removeReceiver")
        .content("{\n" +
            // "    \"mch_id\":\"10000100\",\n" +
            "    \"sub_mch_id\":\"1415701182\",\n" +
            // "    \"appid\":\"wx2421b1c4370ec43b\",\n" +
            "    \"sub_appid\":\"wx2203b1494370e08cm\",\n" +
            // "    \"nonce_str\":\"6cefdb308e1e2e8aabd48cf79e546a02\",\n" +
            // "    \"sign\":\"ABC6DD4AA85C0EECA82C35595A69EFGH\",\n" +
            // "    \"sign_type\":\"HMAC-SHA256\",\n" +
            "    \"receiver\":\"{\n" +
            "       \"type\": \"MERCHANT_ID\",\n" +
            "       \"account\": \"190001001\",\n" +
            "       \"name\": \"示例商户全称\"\n" +
            "    }\"\n" +
            "}")
        .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andDo(log());
  }

  @Test
  public void profitSharing() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/wx/pay/profitSharing/profitSharing")
        .content("{\n" +
            // "    \"appid\":\"wx2421b1c4370ec43b\",\n" +
            // "    \"mch_id\":\"10000100\",\n" +
            "    \"sub_appid\":\"wx2203b1494370e08cm\",\n" +
            "    \"sub_mch_id\":\"1415701182\",\n" +
            // "    \"nonce_str\":\"6cefdb308e1e2e8aabd48cf79e546a02\",\n" +
            "    \"out_order_no\":\"P20150806125346\",\n" +
            "    \"transaction_id\":\"4006252001201705123297353072\",\n" +
            // "    \"sign\":\"FE56DD4AA85C0EECA82C35595A69E153\",\n" +
            // "    \"sign_type\":\"HMAC-SHA256\",\n" +
            "    \"receivers\":\"[\n" +
            "        {\n" +
            "             \"type\": \"MERCHANT_ID\",\n" +
            "             \"account\":\"190001001\",\n" +
            "             \"amount\":100,\n" +
            "             \"description\": \"分到商户\"\n" +
            "        },\n" +
            "        {\n" +
            "             \"type\": \"PERSONAL_OPENID\",\n" +
            "             \"account\":\"86693952\",\n" +
            "             \"amount\":888,\n" +
            "             \"description\": \"分到个人\"\n" +
            "        }\n" +
            "    ]\"\n" +
            "}")
        .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andDo(log());
  }

  @Test
  public void profitSharingReturn() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/wx/pay/profitSharing/profitSharingReturn")
        .content("{\n" +
            // "    \"appid\":\"wx2421b1c4370ec43b\",\n" +
            // "    \"mch_id\":\"10000100\",\n" +
            "    \"sub_appid\":\"wx2203b1494370e08cm\",\n" +
            "    \"sub_mch_id\":\"1415701182\",\n" +
            // "    \"nonce_str\":\"6cefdb308e1e2e8aabd48cf79e546a02\",\n" +
            // "    \"sign_type\":\"HMAC-SHA256\",\n" +
            // "    \"sign\":\"FE56DD4AA85C0EECA82C35595A69E153\",\n" +
            "    \"out_order_no\":\"P20150806125346\",\n" +
            "    \"out_return_no\":\"R20190516001\",\n" +
            "    \"return_account_type\":\"MERCHANT_ID\",\n" +
            "    \"return_account\":\"86693852\",\n" +
            "    \"return_amount\":\"800\",\n" +
            "    \"description\":\"用户退款\"\n" +
            "}")
        .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andDo(log());
  }

  @Test
  public void profitSharingReturnQuery() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/wx/pay/profitSharing/profitSharingReturnQuery")
        .content("{\n" +
            // "    \"appid\":\"wx2421b1c4370ec43b\",\n" +
            // "    \"mch_id\":\"10000100\",\n" +
            "    \"sub_mch_id\":\"1415701182\",\n" +
            // "    \"nonce_str\":\"6cefdb308e1e2e8aabd48cf79e546a02\",\n" +
            // "    \"sign\":\"FE56DD4AA85C0EECA82C35595A69E153\",\n" +
            // "    \"sign_type\":\"HMAC-SHA256\",\n" +
            "    \"out_order_no\":\"P20190806125346\",\n" +
            "    \"out_return_no\":\"R20190806125346\"\n" +
            "}")
        .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andDo(log());
  }

}