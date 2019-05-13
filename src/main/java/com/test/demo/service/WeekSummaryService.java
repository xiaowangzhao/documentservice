package com.test.demo.service;

import com.test.demo.entity.WeekSummary;
import com.test.demo.entity.WeekSummaryKey;

public interface WeekSummaryService {
    int deleteByPrimaryKey(WeekSummaryKey key);

    int insert(WeekSummary record);

    int insertSelective(WeekSummary record);



    WeekSummary selectByPrimaryKey(WeekSummaryKey key);

    int countWeekSummaryBystuid(String stuid);

    int updateByPrimaryKeySelective(WeekSummary record);

    int updateByPrimaryKey(WeekSummary record);
    //获取当前是多少周
    int getweeknum();
    //将允许修改的值设定为1
    //将当前周和为允许修改
    //将参数表中允许修改的周数的设为允许修改
    //1为允许，0为不允许
    int updateIsmodifed(String stuid,int weeknum);

   // public void exportProgressTableByStu(String stuid)throws Exception;


    /**
     * 从其他服务中根据老师获取对应的学生的信息学号姓名和课题名
     */
     void generateBlankWeekup();

   // void exportProgressTableByStu(String stuid);
    void dgenerateeBlankWeekup(String stuid);
}
