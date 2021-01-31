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
 * Date: 2021-01-13 17:02
 * Description:
 */
public class WxPay4CombineControllerTest extends AbstractMock {

  @Test
  public void combine() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/wx/pay/combine/jsapi")
        .content("{\n" +
            "    \"combine_out_trade_no\":\"1217752501201407033233368018\",\n" +
            "    \"combine_mchid\":\"1230000109\",\n" +
            "    \"combine_appid\":\"wxd678efh567hg6787\",\n" +
            "    \"scene_info\":{\n" +
            "        \"device_id\":\"POS1:1\",\n" +
            "        \"payer_client_ip\":\"14.17.22.32\"\n" +
            "    },\n" +
            "    \"sub_orders\":[\n" +
            "        {\n" +
            "            \"mchid\":\"1230000109\",\n" +
            "            \"attach\":\"深圳分店\",\n" +
            "            \"amount\":{\n" +
            "                \"total_amount\":10,\n" +
            "                \"currency\":\"CNY\"\n" +
            "            },\n" +
            "            \"out_trade_no\":\"20150806125346\",\n" +
            "            \"sub_mchid\":\"1900000109\",\n" +
            "            \"description\":\"腾讯充值中心-QQ会员充值\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"combine_payer_info\":{\n" +
            "        \"openid\":\"oUpF8uMuAJO_M2pxb1Q9zNjWeS6o\"\n" +
            "    },\n" +
            "    \"time_start\":\"2018-06-08T10:34:56+08:00\",\n" +
            "    \"time_expire\":\"2018-06-08T10:34:56+08:00\",\n" +
            "    \"notify_url\":\"https://yourapp.com/notify\"\n" +
            "}\n")
        .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andDo(log());
  }

  @Test
  public void queryCombineTransactions() throws Exception {
    String combineOutTradeNo = "1217752501201407033233368018";
    mockMvc.perform(MockMvcRequestBuilders.get("/wx/pay/combine/" + combineOutTradeNo)
        .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andDo(log());
  }

  @Test
  public void closeCombineTransactions() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.delete("/wx/pay/combine/close")
        .content("{\n" +
            "    \"combine_appid \":\"wxd678efh567hg6787\",\n" +
            "    \"combine_out_trade_no\":\"P20150806125346\",\n" +
            "    \"sub_orders\":[\n" +
            "        {\n" +
            "            \"mchid\":\"1900000109\",\n" +
            "            \"out_trade_no\":\"20150806125346\",\n" +
            "            \"sub_mchid\":\"1230000109\",\n" +
            "            \"description\":\"腾讯充值中心-QQ会员充值\"\n" +
            "        }\n" +
            "    ]\n" +
            "}")
        .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andDo(log());
  }

  @Test
  public void subsidies() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.delete("/wx/pay/combine/subsidies")
        .content("{\n" +
            "  \"sub_mchid\": \"1900000109\",\n" +
            "  \"transaction_id\": \"4208450740201411110007820472\",\n" +
            "  \"amount\": 10,\n" +
            "  \"description\": \"测试备注\",\n" +
            "  \"refund_id\": \"3008450740201411110007820472\"\n" +
            "}")
        .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andDo(log());
  }

  @Test
  public void subsidiesReturn() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.delete("/wx/pay/combine/subsidiesReturn")
        .content("{\n" +
            "  \"sub_mchid\": \"1900000109\",\n" +
            "  \"out_order_no\": \"P20150806125346\",\n" +
            "  \"transaction_id\": \"4208450740201411110007820472\",\n" +
            "  \"refund_id\": \"3008450740201411110007820472\",\n" +
            "  \"amount\": 10,\n" +
            "  \"description\": \"测试备注\"\n" +
            "}")
        .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andDo(log());
  }@Test
  public void subsidiesCancel() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.delete("/wx/pay/combine/subsidiesCancel")
        .content("{\n" +
            "  \"sub_mchid\": \"1900013401\",\n" +
            "  \"transaction_id\": \"4208450740201411110007820472\",\n" +
            "  \"description\": \"订单退款\"\n" +
            "}\n")
        .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andDo(log());
  }

}