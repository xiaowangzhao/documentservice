package com.test.demo.service;


import com.test.demo.dto.Result;
import com.test.demo.entity.WeekSummary;
import com.test.demo.entity.WeekSummaryKey;
import com.test.demo.mapper.WeekSummaryMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WeeksummaryServiceImpl implements WeekSummaryService{
    @Autowired
    private WeekSummaryMapper weekSummaryMapper;
    private String url = "http://localhost:8081/subjectsysargu";

    @Override
    public int deleteByPrimaryKey(WeekSummaryKey key) {

        return 0;
    }

    @Override
    public int insert(WeekSummary record) {
        return 0;
    }

    @Override
    public int insertSelective(WeekSummary record) {
        weekSummaryMapper.insertSelective(record);
        return 0;
    }

    @Override
    public WeekSummary selectByPrimaryKey(WeekSummaryKey key) {
        WeekSummary weekSummary = weekSummaryMapper.selectByPrimaryKey(key);
        return weekSummary;
    }

    @Override
    public int countWeekSummaryBystuid(String stuid) {
        return weekSummaryMapper.countWeekSummaryBystuid(stuid);
    }

    @Override
    public int updateByPrimaryKeySelective(WeekSummary record) {
        weekSummaryMapper.updateByPrimaryKeySelective(record);
        return 0;
    }

    @Override
    public int updateByPrimaryKey(WeekSummary record) {
        return 0;
    }

    /**
     * 获取当前周是多少周
     *
     * @return
     */
    @Override
    public int getweeknum() {
        //从其它服务中获取毕业设计的总周数
        RestTemplate restTemplate = new RestTemplate();
        String url2= "http://10.216.82.79:8081/subjectsysargu/getSysargubyname?systemname=graduateweeknum";
      //  Result resultweeknum =restTemplate.getForObject(url2, Result.class);
     //   System.out.println(resultweeknum.getData());
     //   String graduateweeknumstr = (String) resultweeknum.getData();
         JSONObject jsonObject2 =restTemplate.getForObject(url2,JSONObject.class);
         String data1 = jsonObject2.optString("data");
        jsonObject2 =JSONObject.fromObject(data1);
        String graduateweeknumstr=jsonObject2.optString("arguvalue");
        int graduateweeknum=Integer.valueOf(graduateweeknumstr);
        //从其它服务中获取毕业设计的开始时间
        String url= "http://10.216.82.79:8081/subjectsysargu/getSysargubyname?systemname=startdate";
        //Result resultweekstarttime =restTemplate.getForObject(url,Result.class,1);
        //String startdate  = (String) resultweekstarttime.getData();
        JSONObject jsonObject =restTemplate.getForObject(url,JSONObject.class,1);
        String data= jsonObject.optString("data");
        jsonObject = JSONObject.fromObject(data);
        String startdate = jsonObject.optString("arguvalue");
        Date currentdate=new Date();
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        Date gradstartdate= null;
        try {
            gradstartdate = format.parse(startdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long day=(currentdate.getTime()-gradstartdate.getTime())/(24*60*60*1000);//相隔天数
        long weeknum=0;
        if(day>=0) weeknum=day/7+1;//周次
        if(weeknum>graduateweeknum) weeknum=graduateweeknum;

        return (int)weeknum;
    }

    /**
     *  将当前周和上一周设为允许修改
     *     1为允许，0为不允许
     * @return
     */
    @Override
    public int updateIsmodifed(String stuid,int weeknum) {
        RestTemplate restTemplate = new RestTemplate();
        String url2= "http://10.216.82.79:8083/getstarttime/{id}";
        Result resultgra =restTemplate.getForObject(url2,Result.class,2);
        String graduateweeknumstr  = (String) resultgra.getData();

//        String graduateweeknum  =restTemplate.getForObject("url",String.class,graduateweeknum);
//        JSONObject jsonObject =restTemplate.getForObject(url2,JSONObject.class,2);
//        String data = jsonObject.optString("data");
//        jsonObject =JSONObject.fromObject(data);
//        String graduateweeknumstr=jsonObject.optString("value");
//        String graduateweeknumstr="20";//毕业设计周数

        int graduateweeknum=Integer.valueOf(graduateweeknumstr);
        for (int i =1;i<=graduateweeknum;i++){
            weekSummaryMapper.updateByPrimaryKeySelective(new WeekSummary(stuid,i, (short) 0));
        }
       weekSummaryMapper.updateByPrimaryKeySelective(new WeekSummary(stuid,weeknum, (short) 1));
        //从参数表中获取modifiedweekorders
        //String modifiedweekorders = "modifiedweekorders";
        //RestTemplate restTemplate = new RestTemplate();
        //String url= "http:// */{modifiedweekorders}";
        //String modifiedweekorders  =restTemplate.getForObject(url,String.class,modifiedweekorders);
        //String url= "http://10.216.82.79:8083/getstarttime/{id}";
        //String graduateweeknum  =restTemplate.getForObject("url",String.class,graduateweeknum);
        //JSONObject jsonObject2 =restTemplate.getForObject(url,JSONObject.class,3);
        //String data1 = jsonObject2.optString("data");
        //jsonObject2 =JSONObject.fromObject(data1);
        //String modifiedweekorders=jsonObject2.optString("value");
        //String modifiedweekorders="0,1";
        String url= "http://10.216.82.79:8083/getstarttime/{id}";
        Result resultweekstarttime =restTemplate.getForObject(url,Result.class,3);
        String modifiedweekorders  = (String) resultweekstarttime.getData();
        String[] weekorderarr=modifiedweekorders.split(",");
        for(int i=0;i<weekorderarr.length;i++){
            int temp=Integer.valueOf(weekorderarr[i]);
            //将参数表中获取的周数允许修改标志设置为1
            weekSummaryMapper.updateByPrimaryKeySelective(new WeekSummary(stuid,temp,(short)1));
        }

        return 0;
    }


    public void generateBlankWeekup() {
        List<String> stuid =  new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://10.216.82.79:8083/getAll";
        Result stuinfo = restTemplate.getForObject(url, Result.class);
        JSONArray stuinfoo = JSONArray.fromObject(stuinfo.getData());
        for (int i = 0; i < stuinfoo.size(); i++) {//循环json数组
            JSONObject ob = (JSONObject) stuinfoo.get(i);//得到json对象
            String stuid1 = ob.getString("id");//name这里是列名称，获取json对象中列名为name的值
            stuid.add(stuid1);
        }

            //获得毕业设计的总周数
            String url2 = "http://10.216.82.79:8083/getstarttime/{id}";
            Result resultweeknum = restTemplate.getForObject(url2, Result.class, 2);
            String graduateweeknumstr = (String) resultweeknum.getData();
            int graduateweeknum = Integer.valueOf(graduateweeknumstr);

            for (int i = 0; i < stuid.size(); i++) {
                for (int j = 1; j <= graduateweeknum; j++)
                    if(weekSummaryMapper.selectByPrimaryKey(new WeekSummaryKey(stuid.get(i),j))!=null){
                    insertSelective(new WeekSummary(stuid.get(i), j));
                    }

            }

        }


    @Override
    public void dgenerateeBlankWeekup(String stuid) {
        RestTemplate restTemplate = new RestTemplate();
        String url2 = "http://10.216.82.79:8083/getstarttime/{id}";
        Result resultweeknum = restTemplate.getForObject(url2, Result.class, 2);
        String graduateweeknumstr = (String) resultweeknum.getData();
        int graduateweeknum = Integer.valueOf(graduateweeknumstr);
            for (int j = 1; j <= graduateweeknum; j++)
                insertSelective(new WeekSummary(stuid, j));
        }






}
