package com.test.demo.Controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.demo.entity.DocumentReview;
import com.test.demo.entity.Subject;
import com.test.demo.mapper.DocumentReviewMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.io.ResolverUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service
public class test {
    private DocumentReviewMapper documentReviewMapper;
    public static void main(String[]args){

//        RestTemplate rest = new RestTemplate();
//        String subdirection = "软件";
//       JSONObject jsonObject =  rest.getForObject("http://10.216.82.79:8081/subjectsysargu/getsubbydirection?subdirection={subdirection}", JSONObject.class,subdirection);
//         String jsonvalue = jsonObject.optString("value");
//         List<Subject> subjectList = new ArrayList<>();
//         JSONArray jsonArray = JSONArray.fromObject(jsonvalue);
//         List<Subject> subjectList1 = JSONArray.toList(jsonArray);
//         System.out.println(subjectList1);
       String url="http://10.216.82.79:8081/subjectservice";
       RestTemplate restTemplate = new RestTemplate();
        JSONObject jsonObject=  restTemplate.getForObject(url+"/subjects/{subid}", JSONObject.class,"94");
        String data1 = jsonObject.optString("data");
        jsonObject =JSONObject.fromObject(data1);
        String subject=jsonObject.optString("subject");
        jsonObject = JSONObject.fromObject(subject);
        String subname = jsonObject.optString("subname");
        System.out.println(subname);
      //  test test = new test();
        //ArrayList<DocumentReview> documentReviews = (ArrayList<DocumentReview>) test.documentReviewMapper.selectBytid("001");

        //for (DocumentReview documentReview:documentReviews){
//            JSONObject jsonObject=  restTemplate.getForObject("http://10.216.82.79:8081/subjects/{subid}", JSONObject.class,documentReview.getSubid());
//            String data1 = jsonObject.optString("data");
//            jsonObject =JSONObject.fromObject(data1);
//            String subject=jsonObject.optString("subject");
//            jsonObject = JSONObject.fromObject(subject);
//            String subname = jsonObject.optString("subname");
//            documentReview.setSubname(subname);
    //        System.out.println(documentReview.getReviewerid()+"运行");
    //    }

    }
}
