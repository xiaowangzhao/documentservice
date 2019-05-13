package com.test.demo.Controller;

import com.test.demo.dto.FileUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@RequestMapping("/upload2")
public class Fileupandload {
    @ResponseBody
@RequestMapping("/testhuoqu")
    public void testhuoqu(){
//        String url = "http://10.216.82.79:8081/subjectsysargu";
//        String subdirection= "软件";
        RestTemplate restTemplate = new RestTemplate();
//        JSONObject jsonObject =rest.getForObject(url + "/getsubbydirection?subdirection={subdirection}", JSONObject.class,subdirection);
//        System.out.println(jsonObject);

        String url= "http://10.216.82.79:8081/subjectsysargu/getSysargubyname?systemname=startdate";
        //Result resultweekstarttime =restTemplate.getForObject(url,Result.class,1);
        //String startdate  = (String) resultweekstarttime.getData();
        JSONObject jsonObject =restTemplate.getForObject(url,JSONObject.class,1);
        String data= jsonObject.optString("data");
        jsonObject = JSONObject.fromObject(data);
        String startdate = jsonObject.optString("arguvalue");
        System.out.println(startdate);
    }

    @ResponseBody
    @RequestMapping(value = "/shangchuan",method = RequestMethod.POST)
    public void upload(@RequestParam MultipartFile file){
        String filepath  ="D:/graduate2016/";
        // 接收上传文件
        try{
            System.out.println("接受上传文件"+file.isEmpty()+file.getName());

            //首先得到文件
            if(!file.isEmpty()){

                String filename = file.getOriginalFilename();
                String fileext=filename.substring(filename.lastIndexOf(".")+1);
                 filename=filename+"."+fileext;
                File dest = new File(filepath ,filename);
                file.transferTo(dest);

            }else{
                throw new Exception("上传文件表单属性应为enctype='multipart/form-data'");
            }
            // 再修改数据库


        }catch(Exception e){

        }
    }
    @ResponseBody
    @RequestMapping(value ="/xiazai",method = RequestMethod.GET)
    public void download(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
        //得到全路径的文件名
        String filenamewithallpath="";
        String tempfilepath="";
        if(request.getParameter("filenamewithallpath")!=null){
            filenamewithallpath=new String(request.getParameter("filenamewithallpath").getBytes("ISO-8859-1"),"utf-8");//带有全路径的文件名
        }


        //设置文件路径

        File file = new File( filenamewithallpath);

        if (file.exists()) {
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=" + new String(file.getName().getBytes("UTF-8"),"iso-8859-1"));  // 设置文件名,解决中文名乱码的问题
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                System.out.println("success");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
