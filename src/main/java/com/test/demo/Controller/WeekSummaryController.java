package com.test.demo.Controller;

import com.test.demo.entity.WeekSummary;
 import com.test.demo.dto.Result;
import com.test.demo.entity.WeekSummaryKey;
import com.test.demo.service.WeekSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RequestMapping("/weeksummary")
@RestController
public class WeekSummaryController {
    @Autowired
    private  WeekSummaryService weekSummaryService;



    /**
     * 更新周总结
     * @param weekSummary
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/updateweeksummary" ,method= RequestMethod.POST)
    public Result updateWeeksummary(@RequestBody WeekSummary weekSummary){

       try {
        weekSummaryService.updateByPrimaryKeySelective(weekSummary);
         return Result.success();
        }catch(Exception e){
            return Result.error(202,"更新失败");
        }
    }


    /**学生
     * 返回当前是第几周
     *并对周总结的ismodify属性进行修改
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/weeknum",method = RequestMethod.GET)
    public Result getWeeksummary(@RequestParam("stuid") String stuid){
        int weeksum = weekSummaryService.getweeknum();
        if (weeksum==0){
           return Result.success().add("毕业设计还未开始，无任何周总结");
        }
        else{
            //设置可以修改的周总结
            weekSummaryService.updateIsmodifed(stuid,weeksum);

         return   Result.success().add(weeksum);
        }
    }

    /**
     * 根据id查询学生提交周总结的数量
     *
     * @param stuid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getcountsummary",method = RequestMethod.GET)
    public Result getCountSummaryById(@RequestParam("stuid")String stuid){
        try{
             return Result.success().add(weekSummaryService.countWeekSummaryBystuid(stuid));
            }catch (Exception e){
                    return Result.error(202,"未查询到该学生");
            }
    }
    /**
     * 根据学号和周数获得学生的周总结
     * @param weekSummaryKey
     * @return
     */
    @ResponseBody
    @RequestMapping(value= "/getweeksummary" ,method =RequestMethod.GET)
    public Result getWeekSummary(@RequestBody WeekSummaryKey weekSummaryKey){
        try {
            return Result.success().add(weekSummaryService.selectByPrimaryKey(weekSummaryKey));
        }catch (Exception e){
            return Result.error(202,"查询失败");
        }
    }
    /**
     * 获得指导教师的学生的信息
     */
    @ResponseBody
    @RequestMapping(value = "/getstusubbytid",method  = RequestMethod.GET)
    public Result getStusubBytid(@RequestParam("tid")String tid){
        //调用外来接口
         RestTemplate restTemplate = new RestTemplate();
         String url= "http://10.216.82.79:8083/getstuinfo/{tid}";
         Result result =  restTemplate.getForObject(url,Result.class,tid);
         return result;
    }


    /**
     * 生成空白的周总结表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/generateblankweekup" )
    public Result generateBlankWeekup(){
        try {
            weekSummaryService.generateBlankWeekup();
            return Result.success();
       }catch(Exception e){
            e.printStackTrace();
           return Result.error(202,"未能成功生成空白周总结");
        }
    }
//    /**
//     * 学生登陆时生成空白周总结
//     */
//    @ResponseBody
//    @RequestMapping(value = "/dgenerateblankweekup/{stuid}")
//    public Result dgenerateeBlankWeekup(@PathVariable("stuid")String stuid){
//    try{
//        weekSummaryService.dgenerateeBlankWeekup(stuid);
//        return Result.success();
//    }catch(Exception e){
//        return  Result.error(202,"生成空白周总结失败");
//    }
//
//    }
}
