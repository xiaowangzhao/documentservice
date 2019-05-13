package com.test.demo.service;


import com.test.demo.dto.FileUtil;
import com.test.demo.dto.Result;
import com.test.demo.entity.StuDocument;
import com.test.demo.mapper.StuDocumentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;

@Service
public class SubSubmitServiceImpl implements SubSubmitService {
    @Autowired
    private StuDocumentMapper stuDocumentMapper;



    //上传文件时更新数据库
    @Override
    public void updateDoc(String stuid,String doctype,MultipartFile file) {
            //获得公共文件路径

        //RestTemplate restTemplate = new RestTemplate();
        //String url2= "http://10.216.82.79:8083/getstarttime/{id}";
        //Result resultgra =restTemplate.getForObject(url2,Result.class,4);
       // String commonfilepath  = (String) resultgra.getData();
        String commonfilepath  ="D:/graduate2016/";
            //得到学生信息
       // String url = "http://10.216.82.79:8083/getAll/{stuid}";
       // Result resultstuinfo = restTemplate.getForObject(url,Result.class,stuid);
        //解析出学生的姓名，学号和班级
        String stuidname = "小李";

        String classname = "软件162";

        //根据doctype确定文件路径
        String filedir="";
        String newfilename=stuid;
        if(doctype.equals("paperblind")){//盲审论文
            filedir=commonfilepath+"paperblind";
            System.out.println("是盲审论文"+filedir);
        }else{
            filedir=commonfilepath+classname+"-"+stuid+"-"+stuidname;
            if(doctype.equals("paper")||doctype.equals("translation")){
                filedir=filedir+"/毕业设计说明书及译文";
                if(doctype.equals("paper")){
                    newfilename="说明书(论文)";
                }else{
                    newfilename="译文";
                }
            }else{
                filedir=filedir+"/程序代码";
                newfilename="程序代码";
					 /*try{//20150618修改，subname改由服务器端取出.若由浏览器传递，则包含特殊字符的课题名将会导致参数错误。
						 SubjectBpo subbpo=new SubjectBpo();
						 newfilename=subbpo.getSubjectByStuPicked(stuid).getSubname();
					 }catch(Exception e){
						 errmsg=e.getMessage();
					 }*/
                //newfilename=new String(request.getParameter("subname").getBytes("ISO-8859-1"),"utf-8");

            }
        }
       // 接收上传文件
        try{
    System.out.println("接受上传文件"+file.isEmpty()+file.getName());

            //首先得到文件
            if(!file.isEmpty()){
                FileUtil.MkdirWithoutIfExisted(filedir);
                String fileName = file.getOriginalFilename();
                String fileext=fileName.substring(fileName.lastIndexOf(".")+1);
                newfilename=newfilename+"."+fileext;
                File dest = new File(filedir ,newfilename);
                file.transferTo(dest);
                System.out.println(newfilename);
            }else{
                throw new Exception("上传文件表单属性应为enctype='multipart/form-data'");
            }
           // 再修改数据库
            if(stuDocumentMapper.selectByPrimaryKey(stuid)==null){
                stuDocumentMapper.insertnew(stuid, doctype,filedir+"/"+newfilename);
            }else {
                stuDocumentMapper.updateDoc(stuid, doctype, filedir + "/" + newfilename);
            }
        }catch(Exception e){

        }

    }
    /**查询文档上传状态及路径 根据学号和文档类型*/
    @Override
    public Map getUploadstatus(String stuid, String doctype) {
         Map map=  stuDocumentMapper.getUploadstatus(stuid,doctype);
         return  map;
    }
    /**提交归档或提交盲审  修改文档状态*/
    @Override
    public void submitDocForTea(String stuid, String doctype, String status) {
            stuDocumentMapper.submitDocForTea(stuid,doctype,status);
    }



   /**上传文档时修改数据库，doctype分别对应paper、paperblind等列名,状态改为“已上传（1）”*/
    public void updateDoc(String stuid,String weizhi ,String doctype){
        stuDocumentMapper.updateDoc(stuid,doctype,weizhi);
}
    /**查询文档上传状态及路径 根据课题号
     * @return*/
    @Override
    public Map getUploadstatusBySubid(String subid, String doctype) {
        //根据课题号获得学生id，根据学生id查询文档上传状态
        RestTemplate restTemplate = new RestTemplate();
      //  String url= "http://10.216.82.79:8083/getstuinfo/{tid}";
      //  Result result =  restTemplate.getForObject(url,Result.class,subid);
        String stuid = "1";
        Map map= this.getUploadstatus(stuid, doctype);
        return map;
    }
    public StuDocument getUploadstatusbystuid(String stuid){
        return stuDocumentMapper.selectByPrimaryKey(stuid);

    }



    /**
     * 下载文件
     * @param request
     * @param response
     * @throws UnsupportedEncodingException
     */
    @Override
    public void FileDownload(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        //得到全路径的文件名
        String filenamewithallpath="";
        String tempfilepath="";
        if(request.getParameter("filenamewithallpath")!=null){
            filenamewithallpath=new String(request.getParameter("filenamewithallpath"));//带有全路径的文件名
        }
        if(request.getParameter("tempfilename")!=null){
            filenamewithallpath=tempfilepath+request.getParameter("tempfilename");//带有全路径的文件名
        }
        System.out.println(filenamewithallpath+"实现中");

            File file = new File( filenamewithallpath);

            if (file.exists()) {
              response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + new String(file.getName().getBytes("UTF-8"),"iso-8859-1"));  // 设置文件名
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
//查询学生
    public StuDocument selectbystuid(String stuid){
       StuDocument stuDocument =  stuDocumentMapper.selectByPrimaryKey(stuid);
        return stuDocument;
    }


    //按专业查看学生提交文档情况
    @Override
    public List getStuDocsBySpec(String specid, String classname, String sname) {
        return null;
    }


}



