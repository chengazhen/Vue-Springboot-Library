package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.slf4j.Logger;

@RestController
@RequestMapping("/proxy")
public class HttpRequestProxy {

    @GetMapping("/claude")
    public String makeRequest() {
//        Integer visitCount = getVisitCount();
        int visitCount = 54;

        String url = "https://sharedchat.fun/GetClaudeOAuth?id=" + visitCount + "&token=123qazwsx&type=0";

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, "*/*");
        headers.set(HttpHeaders.ACCEPT_LANGUAGE, "zh-CN,zh;q=0.9");
        headers.set(HttpHeaders.CACHE_CONTROL, "no-cache");
        headers.set("pragma", "no-cache");
        headers.set("priority", "u=1, i");
        headers.set("sec-ch-ua", "\"Not)A;Brand\";v=\"99\", \"Google Chrome\";v=\"127\", \"Chromium\";v=\"127\"");
        headers.set("sec-ch-ua-mobile", "?0");
        headers.set("sec-ch-ua-platform", "\"macOS\"");
        headers.set("sec-fetch-dest", "empty");
        headers.set("sec-fetch-mode", "cors");
        headers.set("sec-fetch-site", "cross-site");
        headers.set(HttpHeaders.REFERER, "https://kelaode.ai/");
        headers.set("Referrer-Policy", "strict-origin-when-cross-origin");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class
        );

        return response.getBody();
    }

    private Integer getVisitCount() {
        // 随机生成 0 - 50 之间的整数
        return (int) (Math.random() * 50);
    }
}