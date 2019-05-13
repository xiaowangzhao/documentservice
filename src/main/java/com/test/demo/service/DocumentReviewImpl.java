package com.test.demo.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.demo.Controller.SubSubmintController;
import com.test.demo.dto.JsonUtil;
import com.test.demo.dto.Result;
import com.test.demo.dto.TeaSubComparator;
import com.test.demo.entity.*;
import com.test.demo.mapper.DocumentReviewMapper;
import com.test.demo.mapper.StuDocumentMapper;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.*;

@Service
public class DocumentReviewImpl implements DocumentReviewService{
    private String url = "http://10.216.82.79:8081/subjectsysargu";
    @Autowired
    private DocumentReviewMapper documentReviewMapper;
    @Autowired
    private StuDocumentMapper stuDocumentMapper;



    /**
     * 从教师里获取subid
     */
    public String getSubidFromTea(TeaSubSum temp) {
        String allotSubid = temp.getSubjects().get(0); //获取第一个subid
        temp.getSubjects().remove(0);  //获取后删除
        temp.setSubsum( temp.getSubsum() - 1); //-1
        return allotSubid;
    }
    @Override
    /**为教师分配盲审论文*/
    public String assignPaperToTeaForReview() throws Exception {
        //检查毕业设计是否已经开始，若已开始则返回
        RestTemplate rest = new RestTemplate();
        Map<String, Object> ifStartGraduateMap = rest.getForObject(url + "/ifstartgraduate", Map.class);
        boolean ifStartGraduate = (Boolean) ifStartGraduateMap.get("data");
        if(!ifStartGraduate) {
            return "毕业设计还未开始，不允许盲审论文!";
        }
        String assignresult="";
        ArrayList<DocumentReview> subReviews = new ArrayList<>();
        Map<String,String> teaSumForReview=new HashMap<>();//参与盲审的教师数量
        try{
            String subdirection = "";
            for(int i = 1; i <= 3; i++) {
                if(i == 1){
                    subdirection = "软件";
                }else if(i == 2) {
                    subdirection = "网络";
                }else {
                    subdirection = "硬件";
                }

                Map<String, Object> map = rest.getForObject(url + "/getsubbydirection?subdirection={subdirection}", JSONObject.class,subdirection);
                System.out.println(map.toString());
                 JsonUtil jsonUtil = new JsonUtil();
                 List<Subject> subjectList = jsonUtil.mapToList(map, Subject.class, "data");

                //测试

                //测试
                ArrayList<TeaSubSum> teaSubSums = new ArrayList<>(); //统计每个教师申报课题数目（符合课题方向i），记录每个老师盲审题目
                List<TeaSubSum> teaSubSumTemp = new ArrayList<>(); //存储课题号，分配时用
                String lastTid =null;
                TeaSubSum teacher = null;
                TeaSubSum temp = null;  //生成中间结果的，需要重新new,防止一个对象引用
                for(Subject subject : subjectList) { //循环将申报了课题方向i的教师放入teaSubSums中
                    String tid = subject.getTid();
                    String subid = String.valueOf(subject.getSubid());
                    if(tid ==null||subid==null) return "教师编号或者课程编号为空！！！";
                    if(tid.equals(lastTid)) {
                        teacher.setSubsum(teacher.getSubsum() + 1);
                        temp.setSubsum(temp.getSubsum() + 1);
                        temp.getSubjects().add(subid);
                    }else {
                        lastTid = tid;
                        teacher = new TeaSubSum();
                        teacher.setTid(tid);
                        temp = new TeaSubSum();
                        temp.setTid(tid);
                        teaSubSums.add(teacher);
                        teaSubSumTemp.add(temp);

                        teacher.setSubsum(1);
                        temp.setSubsum(1);
                        temp.getSubjects().add(subid);
                    }
                }
                //开始分配课题
                int teasum=teaSubSums.size();
                if(teasum == 1) return "只有一个学生申请了课题方向代码为"+String.valueOf(i)+"的课题，无法进行分配！";
                TeaSubComparator comparator = new TeaSubComparator();
                Collections.sort(teaSubSums, comparator);//先分审题目多的老师
                for(int j = 0; j < teasum; j++) {  //对每个老师进行循环分配，取第一个教师
                    TeaSubSum teaSubSum = teaSubSums.get(j);
                    String tid = teaSubSum.getTid();  //教师号
                    int subsum =  teaSubSum.getSubsum(); //课题数

                    Collections.sort(teaSubSumTemp, comparator);  //将temp过程中按仍尚未分配的课题数从小到大排序

                    boolean add = true;
                    while (add && subsum > 0) {
                        add = false;
                        for(int k = teaSubSumTemp.size() -1; k >=0; k--) { //取最后一个教师
                            temp = teaSubSumTemp.get(k);
                            if(temp.getTid().equals(tid)) continue;
                            String allotSubid = getSubidFromTea(temp);
                            DocumentReview review = new DocumentReview();
                            review.setReviewerid(tid);
                            review.setSubid(Long.parseLong(allotSubid));
                            subReviews.add(review);
                            if(temp.getSubsum() == 0) {
                                teaSubSumTemp.remove(k);
                            }
                            add = true;
                            subsum --;
                            if(subsum <=0) break;
                        }
                    }
                }
                //若还有剩余课题，则该剩余课题一定只属于一个教师，其余老师平均分配
                if(teaSubSumTemp.size() > 0) {
                    temp = teaSubSumTemp.get(0);
                    int overplus = temp.getSubsum();
                    int teacurr = -1;
                    while (overplus > 0) {
                        if(teacurr >= teaSubSums.size() - 1) {
                            teacurr = -1;
                        }
                        teacurr++;
                        if(teaSubSums.get(teacurr).getTid().equals(temp.getTid())) continue;
                        String distsubid = getSubidFromTea(temp);
                        DocumentReview subReview = new DocumentReview();
                        subReview.setReviewerid(teaSubSums.get(teacurr).getTid());
                        subReview.setSubid(Long.parseLong(distsubid));
                        subReviews.add(subReview);
                        overplus --;
                    }
                }
            }

            //将分配情况写入数据库
            try{
            //    reviewdocMapper.deleteReviewdoc();
                documentReviewMapper.delectAll();
                int subSums = subReviews.size();
                DocumentReview reviewdoc;
                for(int l = 0; l < subSums; l++) {
                    DocumentReview reviewTemp = subReviews.get(l);
                    String tid = reviewTemp.getReviewerid();
                    teaSumForReview.put(tid, tid);
                    Long subid = reviewTemp.getSubid();
                    reviewdoc = new DocumentReview();
                    reviewdoc.setReviewerid(tid);
                    reviewdoc.setSubid(subid);
                 //   reviewdocMapper.insertReviewdoc(reviewdoc);
                    documentReviewMapper.insert(reviewdoc);
                }
            }catch (Exception e) {
                System.out.println(e + "insertReviewdoc");
            }

        }catch (Exception e) {
            System.out.println(e + " assignSubject");
        }
        assignresult = "已分配的盲审总数为 "+String.valueOf(subReviews.size()+" ,参与盲审的教师数为 " + teaSumForReview.size());
        return assignresult;

    }
    /**设置盲审意见*/
    @Override
    public void setReviewOpinion(DocumentReview reviewpaper)   {
        Date reviewtime = new Date();
        reviewpaper.setReviewtime(new java.sql.Timestamp(reviewtime.getTime()));
        try{
        documentReviewMapper.updateByPrimaryKeySelective(reviewpaper) ;
        }catch (Exception e){
            e.printStackTrace();
        }
        if(reviewpaper.getSubmitflag().equals("1")){
            //设置已盲审标志
            //pstmt1=con.prepareStatement("update tb_subsubmit set paperblindstatus='3' where stuid=(select stuid from tb_stusub where pickflag='1' and subid="+reviewpaper.getSubid()+")");
            //pstmt1.executeUpdate();
            //从tb_stusub中获取学生id  select stuid from tb_stusub where pickflag='1' and subid="+reviewpaper.getSubid()
            //RestTemplate restTemplate = new RestTemplate();
            //String stuid  = restTemplate.getForObject(url,String.class);
            String stuid = "1";
            //将文件提交表中的paperblindstatus更新为3
             stuDocumentMapper.submitDocForTea(stuid,"paperblind","3");

        }
    }
    /**查看盲审意见*/
    @Override
    public DocumentReview getReviewOpinion(Long subid) throws Exception {
       DocumentReview documentReview = documentReviewMapper.selectByPrimaryKey(subid);
        return documentReview;
    }

    /**
     * 得到教师盲审论文的信息
     * @param tid
     * @return
     * @throws Exception
     */
    @Override
    public List<DocumentReview> getPapersReviewedByTid(String tid) throws Exception {
        RestTemplate rest = new RestTemplate();
        Map<String, Object> ifStartGraduateMap = rest.getForObject(url + "/ifstartgraduate", Map.class);
        boolean ifStartGraduate = (Boolean) ifStartGraduateMap.get("data");
        if(!ifStartGraduate) {
           throw new Exception( "毕业设计还未开始，不允许盲审论文!");
        }
        //调用课题服务获取课题
        RestTemplate restTemplate = new RestTemplate();



       ArrayList<DocumentReview> documentReviews = (ArrayList<DocumentReview>) documentReviewMapper.selectBytid(tid);
       for (DocumentReview documentReview:documentReviews){
            JSONObject jsonObject=  restTemplate.getForObject("http://10.216.82.79:8081/subjectservice/subjects/{subid}", JSONObject.class,documentReview.getSubid());
           String data1 = jsonObject.optString("data");
           jsonObject =JSONObject.fromObject(data1);
           String subject=jsonObject.optString("subject");
          jsonObject = JSONObject.fromObject(subject);
           String subname = jsonObject.optString("subname");
           documentReview.setSubname(subname);
           System.out.println(documentReview);
        }
        return  documentReviews;
    }
    /**按 学生 查询论文评阅情况*/
    @Override
    public List<ReviewPaperBaseInfoBean> getPaperReviewInfos(String specid, String classname, String sname) throws Exception {
        List<ReviewPaperBaseInfoBean> ret= new ArrayList<ReviewPaperBaseInfoBean>();
        RestTemplate restTemplate = new RestTemplate();
        String canshu = specid+"-"+classname+"-"+sname;
        List<StudentBean> students = restTemplate.getForObject(url,List.class,canshu);
        for(Iterator<StudentBean> it=students.iterator();it.hasNext();){
            ReviewPaperBaseInfoBean temp= new ReviewPaperBaseInfoBean();
            StudentBean student=it.next();
            String stuid=student.getStuid();
            temp.setClassname(student.getClassname());
            temp.setStuid(stuid);
            temp.setSname(student.getSname());

            Subject  subject=restTemplate.getForObject(url,Subject.class,stuid);
            Long  subid=subject.getSubid();
            temp.setSubid(subid);
            temp.setSubname(subject.getSubname());

            String tid=subject.getTid();
            String othertid=subject.getAsstid();
           Teacher teacher = restTemplate.getForObject(url,Teacher.class,tid);
            String tutornames = teacher.getTname();
            Teacher otherteacher = restTemplate.getForObject(url,Teacher.class,othertid);
                String othertname = otherteacher.getTname();
            if(!othertname.equals("")){
                tutornames=tutornames+"/"+othertname;
            }
            temp.setTutornames(tutornames);
            DocumentReview reviewpaper=this.getReviewOpinion(subid);
            String reviewerid=reviewpaper.getReviewerid();
            Teacher reviewTeacher = restTemplate.getForObject(url,Teacher.class,reviewerid);
            temp.setReviewername(reviewTeacher.getTname());
            temp.setSubmitflag(reviewpaper.getSubmitflag());
            ret.add(temp);
        }

        return ret;
    }

    //	pstmt0=con.prepareStatement("update tb_subsubmit set paperblindstatus='2' where stuid=(select stuid from tb_stusub where pickflag='1' and subid="+subid+")");
    //			pstmt1=con.prepareStatement("update tb_reviewpaper set submitflag='0' where subid="+subid);//设置提交标志为保存状态

    /**撤销评阅*/
    @Override
    public void cancelPaperReview(Long subid) throws Exception {
        //从学生选题表中获取学生stuid
        RestTemplate restTemplate = new RestTemplate();
        String stuid  = restTemplate.getForObject(url,String.class,subid);
        stuid = "1";
        stuDocumentMapper.submitDocForTea(stuid,"paperblind","2");
        documentReviewMapper.updateByPrimaryKeySelective(new DocumentReview(subid,"0"));
    }
}
