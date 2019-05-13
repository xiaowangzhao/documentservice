package com.test.demo.Controller;

import com.test.demo.dto.Result;
import com.test.demo.service.SubSubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/file")
public class SubSubmintController {

    @Autowired
    private SubSubmitService subSubmitService;


    @RequestMapping("index")
    public String index(){
        return "index";
    }


    /**
     * 上传文件
     * @param stuid
     * @param doctype
     * @param file
     * @return
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(value= "/upload",method = RequestMethod.POST)
    @ResponseBody
    public Result shangchuanwenjian(@RequestParam MultipartFile file,
                                    @RequestParam("stuid")String stuid,
                                    @RequestParam("doctype")String doctype, HttpServletResponse response) {

        if (file.isEmpty()) {
            return Result.error(202, "文件为空，请先导入文件位置");
        } else {
            subSubmitService.updateDoc(stuid,doctype,file);
            return Result.success();
        }
    }

    /**
     * 根据文件类型和学号查询文档的路径和状态
     * @param stuid
     * @param doctype
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getuploadstatus/{stuid}-{doctype}",method = RequestMethod.GET)
    public Result getUploadstatus(@PathVariable("stuid")String stuid ,@PathVariable("doctype") String doctype){
        if(subSubmitService.selectbystuid(stuid)==null){
            return Result.error(202,"该学生不存在");
        }else{
            return Result.success().add(subSubmitService.getUploadstatus(stuid,doctype));
        }

    }

    /**
     * 得到每个学生的文档信息
     * @param stuid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getuploadstatus",method = RequestMethod.GET)
    public Result getUploadstatusbystuid(@RequestParam("stuid")String stuid  ){
        if(subSubmitService.selectbystuid(stuid)==null){
            return Result.error(202,"该学生不存在");
        }else{
            return Result.success().add(subSubmitService.getUploadstatusbystuid(stuid));
        }

    }

    /**
     * 提交归档或提交盲审  修改文档状态
     * paperstatus状态（0未上传1已上传2已归档
     * paperblindstatus 状态（0未上传1已上传2等待盲审3已盲审）
     * translationstatus 同paperstatus状态
     * sourcecodestatus  同paperstatus状态
     * @param stuid
     * @param doctype
     * @param status
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/submitdocfortea",method = RequestMethod.POST)
    public Result submitDocForTea(@RequestParam("stuid")String stuid ,@RequestParam("doctype") String doctype,
                                  @RequestParam("status")String status){
        if (subSubmitService.selectbystuid(stuid) ==null){
            return Result.error(202,"处理失败");
        }else{
        try {
            subSubmitService.submitDocForTea(stuid,doctype,status);

        }catch(Exception e){
                e.printStackTrace();
        }
            return Result.success(); }
    }
    /**
     *
     *
     * 下载文件
     */

    @RequestMapping(value = "download", method = RequestMethod.GET)
    public Result downLoad(HttpServletRequest request, HttpServletResponse response)  {
        System.out.println(request.getParameter("filenamewithallpath"));
        System.out.println( new File(request.getParameter("filenamewithallpath")).exists());
         File file =new File(request.getParameter("filenamewithallpath"));
            if (file .exists())
            {   try{
                System.out.println("运行");
                subSubmitService.FileDownload(request,response);

                }
                catch (Exception e){
                        e.printStackTrace();
                }return Result.success();

            } else {
                return Result.error(202, "文件不存在");
            }
    }
    //按专业查看学生提交文档情况


}
