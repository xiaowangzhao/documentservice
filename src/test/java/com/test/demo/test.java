package com.test.demo;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class test extends DemoApplicationTests {

    @Test
    public void testgetsubject() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> map = restTemplate.getForObject("http://192.168.137.1:8081/subjectsysargu/getallsubject", Map.class);
        System.out.println(map);
    }
}
