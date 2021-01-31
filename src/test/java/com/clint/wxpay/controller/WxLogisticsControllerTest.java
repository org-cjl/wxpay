package com.clint.wxpay.controller;

import com.clint.wxpay.AbstractMock;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Clint
 * Date: 2021-01-12 16:50
 * Description:
 */
public class WxLogisticsControllerTest extends AbstractMock {

  @Test
  public void bindAccount() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/wx/ma/logistics/bindAccount")
        .content("{\n" +
            "  \"type\": \"bind\",\n" +
            "  \"biz_id\": \"123456\",\n" +
            "  \"delivery_id\": \"YUNDA\",\n" +
            "  \"password\": \"123456789123456789\"\n" +
            "}\n"))
        .andExpect(status().isOk())
        .andDo(log());
  }

  @Test
  public void getAllAccount() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/wx/ma/logistics/getAllAccount")
        .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andDo(log());
  }

  @Test
  public void getAllDelivery() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/wx/ma/logistics/getAllDelivery")
        .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andDo(log());
  }

  @Test
  public void addOrder() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/wx/ma/logistics/addOrder")
        .content("{\n" +
            "  \"add_source\": 0,\n" +
            "  \"order_id\": \"01234567890123456789\",\n" +
            "  \"openid\": \"oABC123456\",\n" +
            "  \"delivery_id\": \"SF\",\n" +
            "  \"biz_id\": \"xyz\",\n" +
            "  \"custom_remark\": \"易碎物品\",\n" +
            "  \"sender\": {\n" +
            "    \"name\": \"张三\",\n" +
            "    \"tel\": \"020-88888888\",\n" +
            "    \"mobile\": \"18666666666\",\n" +
            "    \"company\": \"公司名\",\n" +
            "    \"post_code\": \"123456\",\n" +
            "    \"country\": \"中国\",\n" +
            "    \"province\": \"广东省\",\n" +
            "    \"city\": \"广州市\",\n" +
            "    \"area\": \"海珠区\",\n" +
            "    \"address\": \"XX路XX号XX大厦XX栋XX\"\n" +
            "  },\n" +
            "  \"receiver\": {\n" +
            "    \"name\": \"王小蒙\",\n" +
            "    \"tel\": \"020-77777777\",\n" +
            "    \"mobile\": \"18610000000\",\n" +
            "    \"company\": \"公司名\",\n" +
            "    \"post_code\": \"654321\",\n" +
            "    \"country\": \"中国\",\n" +
            "    \"province\": \"广东省\",\n" +
            "    \"city\": \"广州市\",\n" +
            "    \"area\": \"天河区\",\n" +
            "    \"address\": \"XX路XX号XX大厦XX栋XX\"\n" +
            "  },\n" +
            "  \"shop\": {\n" +
            "    \"wxa_path\": \"/index/index?from=waybill&id=01234567890123456789\",\n" +
            "    \"img_url\": \"https://mmbiz.qpic.cn/mmbiz_png/OiaFLUqewuIDNQnTiaCInIG8ibdosYHhQHPbXJUrqYSNIcBL60vo4LIjlcoNG1QPkeH5GWWEB41Ny895CokeAah8A/640\",\n" +
            "    \"goods_name\": \"微信气泡狗抱枕&微信气泡狗钥匙扣\",\n" +
            "    \"goods_count\": 2\n" +
            "  },\n" +
            "  \"cargo\": {\n" +
            "    \"count\": 2,\n" +
            "    \"weight\": 5.5,\n" +
            "    \"space_x\": 30.5,\n" +
            "    \"space_y\": 20,\n" +
            "    \"space_z\": 20,\n" +
            "    \"detail_list\": [\n" +
            "      {\n" +
            "        \"name\": \"微信气泡狗抱枕\",\n" +
            "        \"count\": 1\n" +
            "      },\n" +
            "      {\n" +
            "        \"name\": \"微信气泡狗钥匙扣\",\n" +
            "        \"count\": 1\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  \"insured\": {\n" +
            "    \"use_insured\": 1,\n" +
            "    \"insured_value\": 10000\n" +
            "  },\n" +
            "  \"service\": {\n" +
            "    \"service_type\": 0,\n" +
            "    \"service_name\": \"标准快递\"\n" +
            "  }\n" +
            "}")
        .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andDo(log());
  }

  @Test
  public void cancelOrder() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.delete("/wx/ma/logistics/cancelOrder")
        .content("{\n" +
            "  \"order_id\": \"01234567890123456789\",\n" +
            "  \"openid\": \"oABC123456\",\n" +
            "  \"delivery_id\": \"SF\",\n" +
            "  \"waybill_id\": \"123456789\"\n" +
            "}\n")
        .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andDo(log());
  }

  @Test
  public void getOrder() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/wx/ma/logistics/getOrder")
        .content("{\n" +
            "  \"order_id\": \"01234567890123456789\",\n" +
            "  \"openid\": \"oABC123456\",\n" +
            "  \"delivery_id\": \"SF\",\n" +
            "  \"waybill_id\": \"123456789\"\n" +
            "}\n")
        .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andDo(log());
  }

  @Test
  public void batchGetOrder() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/wx/ma/logistics/batchGetOrder")
        .content("{\n" +
            "   \"order_list\": [\n" +
            "       {\n" +
            "          \"order_id\": \"01234567890123456789\",\n" +
            "          \"delivery_id\": \"SF\",\n" +
            "          \"waybill_id\": \"123456789\"\n" +
            "       },\n" +
            "       {\n" +
            "          \"order_id\": \"01234567890123456789\",\n" +
            "          \"delivery_id\": \"SF\",\n" +
            "          \"waybill_id\": \"123456789\"\n" +
            "       }\n" +
            "   ]\n" +
            "}\n")
        .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andDo(log());
  }

  @Test
  public void getPath() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/wx/ma/logistics/getPath")
        .content("{\n" +
            "  \"order_id\": \"01234567890123456789\",\n" +
            "  \"openid\": \"oABC123456\",\n" +
            "  \"delivery_id\": \"SF\",\n" +
            "  \"waybill_id\": \"123456789\"\n" +
            "}\n")
        .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andDo(log());
  }

  @Test
  public void testUpdateOrder() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/wx/ma/logistics/testUpdateOrder")
        .content("{\n" +
            "  \"biz_id\": \"test_biz_id\",\n" +
            "  \"order_id\": \"xxxxxxxxxxxx\",\n" +
            "  \"delivery_id\": \"TEST\",\n" +
            "  \"waybill_id\": \"xxxxxxxxxx\",\n" +
            "  \"action_time\": 123456789,\n" +
            "  \"action_type\": 100001,\n" +
            "  \"action_msg\": \"揽件阶段\"\n" +
            "}\n")
        .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andDo(log());
  }
}