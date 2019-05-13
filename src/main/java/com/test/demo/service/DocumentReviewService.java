package com.test.demo.service;

import com.test.demo.entity.DocumentReview;
import com.test.demo.entity.ReviewPaperBaseInfoBean;

import java.util.List;

public interface DocumentReviewService {
    /**为教师分配盲审论文*/
    public String assignPaperToTeaForReview() throws Exception;
    /**设置盲审意见*/
    public void setReviewOpinion(DocumentReview reviewpaper)throws Exception;

    /**查看盲审意见*/
    public DocumentReview getReviewOpinion(Long subid)throws Exception;

    //查询教师指导论文数目
  //  public List<SubNumByTeaBean> getAllpapernum() throws Exception;

    //得到教师盲审的论文信息
    public List<DocumentReview> getPapersReviewedByTid(String tid) throws Exception;


    /**按 学生 查询论文评阅情况*/
    public List<ReviewPaperBaseInfoBean> getPaperReviewInfos(String specid, String classname, String sname) throws Exception;



    /**撤销评阅*/
    public void cancelPaperReview(Long subid)throws Exception;
}
