package com.test.demo.service;

import com.test.demo.entity.StuDocument;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public interface SubSubmitService {

    void updateDoc(String stuid,String doctype,MultipartFile file);
    //获得每个文档的上传状态
     Map getUploadstatus(String stuid, String doctype);

    /**提交归档或提交盲审  修改文档状态*/
   void submitDocForTea(String stuid, String doctype, String status);

    public StuDocument selectbystuid(String stuid);

    /**上传文档时修改数据库，doctype分别对应paper、paperblind等列名,状态改为“已上传（1）”*/
    void updateDoc(String stuid,String weizhi,String doctype);
    StuDocument getUploadstatusbystuid(String stuid);

    /**查询文档上传状态及路径 根据课题号
     * @return*/
    Map getUploadstatusBySubid(String subid, String doctype);


    //按专业查看学生提交文档情况
    List getStuDocsBySpec(String specid, String classname, String sname);
    //下载文件
    void FileDownload(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException;
}
