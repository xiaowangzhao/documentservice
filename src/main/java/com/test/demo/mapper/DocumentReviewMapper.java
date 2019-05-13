package com.test.demo.mapper;

import com.test.demo.entity.DocumentReview;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DocumentReviewMapper {
    List<DocumentReview> selectAll();
    int delectAll();
    int deleteByPrimaryKey(Long subid);

    int insert(DocumentReview record);

    int insertSelective(DocumentReview record);


    DocumentReview selectByPrimaryKey(Long subid);


    int updateByPrimaryKeySelective(DocumentReview record);

    int updateByPrimaryKeyWithBLOBs(DocumentReview record);

    int updateByPrimaryKey(DocumentReview record);

    List<DocumentReview> selectBytid(String tid);
}