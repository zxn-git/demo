package com.example.java.simple.swingwindow;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @description:
 * @author: xiaonan.zhang
 * @create: 2018-12-21 15:45
 **/
@Slf4j
public class WindowsData {
    public static String OKEX() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        String body = restTemplate.exchange("https://www.okex.me/v2/market/index/tickerAll", HttpMethod.GET, entity, String.class).getBody();
//        log.info(body);

        JSONArray read = (JSONArray) JSONPath.read(body, "$.data");
        for (int i = 0; i < read.size(); i++) {
            String coinname = String.valueOf(JSONPath.read(body, "$.data[" + i + "].coinName"));
            if ("bch".equals(coinname)) {
                String last = String.valueOf(JSONPath.read(body, "$.data[" + i + "].last"));
                return last;
            }
        }
        return "";

    }


    public static void main(String... star) {
        String okex = WindowsData.OKEX();
        System.out.println(okex);
    }
}


