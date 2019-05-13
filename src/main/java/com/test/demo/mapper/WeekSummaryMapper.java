package com.test.demo.mapper;

import com.test.demo.entity.WeekSummary;
import com.test.demo.entity.WeekSummaryKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WeekSummaryMapper {


    int deleteByPrimaryKey(WeekSummaryKey key);

    int insert(WeekSummary record);

    int insertSelective(WeekSummary record);



    WeekSummary selectByPrimaryKey(WeekSummaryKey key);

    int countWeekSummaryBystuid(String stuid);

    int updateByPrimaryKeySelective(WeekSummary record);

    int updateByPrimaryKey(WeekSummary record);


}