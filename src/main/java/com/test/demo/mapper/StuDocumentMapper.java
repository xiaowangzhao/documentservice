package com.test.demo.mapper;

import com.test.demo.entity.StuDocument;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface StuDocumentMapper {


    int deleteByPrimaryKey(String stuid);

    int insert(StuDocument record);

    int insertSelective(StuDocument record);


    StuDocument selectByPrimaryKey(String stuid);


    int updateByPrimaryKeySelective(StuDocument record);

    int updateByPrimaryKey(StuDocument record);
    /**上传文档时修改数据库，doctype分别对应paper、paperblind等列名,状态改为“已上传（1）”*/
    int updateDoc(@Param("stuid") String stuid,@Param("doctype") String doctype,@Param("file") String file);

    int insertnew(@Param("stuid") String stuid,@Param("doctype") String doctype,@Param("file") String file);

    Map getUploadstatus(@Param("stuid") String stuid,@Param("doctype") String doctype );


    /**提交归档或提交盲审  修改文档状态*/
    int  submitDocForTea(@Param("stuid") String stuid,@Param("doctype") String doctype,@Param("status") String status);
    /** 得到 学生 文档 状态*/
    List<StuDocument> getStuDocsBySpec(String specid,String classname,String sname);
}