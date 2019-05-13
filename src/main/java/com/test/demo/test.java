package com.test.demo;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
import com.test.demo.entity.WeekSummary;
import com.test.demo.entity.WeekSummaryKey;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

public class test {
    public static void main(String []args){
       // RestTemplate restTemplate = new RestTemplate();
        List list = Collections.singletonList(new int[]{1, 2, 3, 4, 5, 6});

 //   System.out.println( restTemplate.getForObject("http://10.216.82.79:8081/stuid/getcountsummary/{stuid}",String.class,1));
    }
}
