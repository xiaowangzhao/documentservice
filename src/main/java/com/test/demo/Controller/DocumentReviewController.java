package com.test.demo.Controller;

import com.test.demo.dto.Result;
import com.test.demo.entity.DocumentReview;
import com.test.demo.entity.ReviewPaperBaseInfoBean;
import com.test.demo.service.DocumentReviewService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/documentreview")
@Slf4j
@CrossOrigin
public class DocumentReviewController {

    /**
     * 为教师分配盲审论文
     */
    @Autowired
    private DocumentReviewService documentReviewService;
    @RequestMapping("/assignpapertoteaforreview")
    public Result assignPaperToTeaForReview(){
        try{
            String resulttemp=documentReviewService.assignPaperToTeaForReview();
            return Result.success().add(resulttemp);
        }catch(Exception e){
            e.printStackTrace();
            return Result.error(202,"处理失败");
    }}

    /**
     * 设置盲审意见
     * @param documentReview
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/setreviewopinion", method= RequestMethod.POST)
    public Result setReviewOpinion(@RequestBody DocumentReview documentReview)   {
        System.out.println(documentReview.toString());
        try {
            documentReviewService.setReviewOpinion(documentReview);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(202,"操作失败");
        }
    }

    /***
     * 查看盲审意见
     * @param subid
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getreviewopinion",method = RequestMethod.GET)
    public Result getReviewOpinion(@RequestParam String subid){
        Long sub = Long.parseLong(subid);
        try{
       DocumentReview documentReview =documentReviewService.getReviewOpinion(sub);
        return Result.success().add(documentReview);
        }catch (Exception e){
            return Result.error(202,"处理失败");
        }
    }

    /**
     * 得到教师盲审的论文的信息
     * @param tid
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/getpapersreviewedbytid" ,method = RequestMethod.GET)
    public Result getPapersReviewedByTid(@RequestParam String tid){
        try{
           List<DocumentReview> documentReviews =  documentReviewService.getPapersReviewedByTid(tid);
           if (documentReviews==null) return Result.error(200,"盲审的论文数目为0");
            return Result.success().add(documentReviews);
        }catch(Exception e){
            return  Result.error(202,e.toString());
        }
    }

    /**
     * 按 学生 查询 详细的盲审信息
     * @param specid
     * @param classname
     * @param sname
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getPaperReviewInfos/{specid}-{classname}-{sname}",method = RequestMethod.GET)
    public Result getPaperReviewInfos(@PathVariable String specid,
                                      @PathVariable String classname,@PathVariable String sname){
        try{
      List   <ReviewPaperBaseInfoBean> reviewPaperBaseInfoBeans =documentReviewService.getPaperReviewInfos(specid,classname,sname);
            return Result.success().add(reviewPaperBaseInfoBeans);
        }catch (Exception e){
            return Result.error(202,"处理失败");
        }
    }

    /**
     * 撤销评阅
     * @param subid
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/cancelPaperReview",method = RequestMethod.POST)
    public Result cancelPaperReview(@RequestParam String subid){
        Long subidlong = Long.parseLong(subid);
        try{
        documentReviewService.cancelPaperReview(subidlong);
         return Result.success();
        }catch(Exception e){
            return Result.error(202,"处理失败");
        }
    }
/****
 *
 *
 * //查询教师指导论文数目
 */


}
