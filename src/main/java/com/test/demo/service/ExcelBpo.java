//package com.test.demo.service;
//
//import jxl.Workbook;
//import jxl.format.Alignment;
//import jxl.format.Border;
//import jxl.format.BorderLineStyle;
//import jxl.format.VerticalAlignment;
//import jxl.write.WritableCellFormat;
//import jxl.write.WritableFont;
//import jxl.write.WritableSheet;
//import jxl.write.WritableWorkbook;
//
//import java.awt.*;
//import java.io.File;
//import java.util.Iterator;
//
//public class ExcelBpo {
//    private void progessTableContent(WritableWorkbook workbook,int sheetnum,StudentBean student,SubjectBean subject)throws Exception{
//        WritableSheet sheet = workbook.createSheet(subject.getSubid()+"进程表", sheetnum);
//        /**设置各种字体*/
//        WritableFont wfont12=new WritableFont(WritableFont.createFont("宋体"),12);//设置单元格字体(宋体、小四号、加粗）
//
//        WritableFont wfont14bold=new WritableFont(WritableFont.createFont("宋体"),14);//设置单元格字体(宋体、四号、加粗)
//        wfont14bold.setBoldStyle(WritableFont.BOLD);
//
//        WritableFont wfont11=new WritableFont(WritableFont.createFont("宋体"),11);//设置单元格字体(宋体、四号)
//        /**共6列，设置每列列宽*/
//        sheet.setColumnView( 0,10 );//设置列宽
//        sheet.setColumnView( 1,14 );//设置列宽
//        sheet.setColumnView( 2,14 );//设置列宽
//        sheet.setColumnView( 3,16 );//设置列宽
//        sheet.setColumnView( 4,14 );//设置列宽
//        sheet.setColumnView( 5,18 );//设置列宽
//        /**标题1*/
//        sheet.mergeCells(0, 0, 5, 0);//(col,row)
//        //设置第1行，行高为600.填写标题1
//        sheet.setRowView(0, 800);
//        Label label=new Label(0,0,"山东建筑大学毕业设计（论文）工作进程表",this.setCellFormatWithoutBorder(Alignment.CENTRE, wfont14bold));
//        sheet.addCell(label);
//        /**标题2-4*/
//        //第2行
//        sheet.setRowView(1, 400);
//        sheet.addCell(new Label(0,1,"班级",this.setCellFormatWithThinLine(Alignment.CENTRE, wfont12)));
//        sheet.addCell(new Label(1,1,student.getClassname(),this.setCellFormatWithThinLine(Alignment.CENTRE, wfont12)));
//        sheet.addCell(new Label(2,1,"学生姓名",this.setCellFormatWithThinLine(Alignment.CENTRE, wfont12)));
//        sheet.addCell(new Label(3,1,student.getSname(),this.setCellFormatWithThinLine(Alignment.CENTRE, wfont12)));
//        sheet.addCell(new Label(4,1,"指导教师",this.setCellFormatWithThinLine(Alignment.CENTRE, wfont12)));
//        String tutorname=subject.getTutor().getTname();
//        if(subject.getOthertutor()!=null){
//            tutorname=tutorname+" "+subject.getOthertutor().getTname();
//        }
//        sheet.addCell(new Label(5,1,tutorname,this.setCellFormatWithThinLine(Alignment.CENTRE, wfont12)));
//        //第3行
//        sheet.setRowView(2, 400);
//        sheet.mergeCells(0, 2, 1, 2);//(col,row)合并前两列
//        sheet.addCell(new Label(0,2,"设计（论文）题目",this.setCellFormatWithThinLine(Alignment.CENTRE, wfont12)));
//        sheet.mergeCells(2, 2, 5, 2);//(col,row)合并前两列
//        sheet.addCell(new Label(2,2,subject.getSubname(),this.setCellFormatWithThinLine(Alignment.CENTRE, wfont12)));
//        //第4行
//        sheet.setRowView(3, 400);
//        sheet.mergeCells(0, 3, 1, 3);//(col,row)合并前两列
//        sheet.mergeCells(2, 3, 3, 3);
//        sheet.mergeCells(4, 3, 5, 3);
//        sheet.addCell(new Label(0,3,"时间（按两周填写）",this.setCellFormatWithThinLine(Alignment.CENTRE, wfont12)));
//        sheet.addCell(new Label(2,3,"应完成的工作内容",this.setCellFormatWithThinLine(Alignment.CENTRE, wfont12)));
//        sheet.addCell(new Label(4,3,"检查情况",this.setCellFormatWithThinLine(Alignment.CENTRE, wfont12)));
//        /**内容(循环)5-10*/
//        List<ProgressBean> progresses=subject.getProgress();
//        int initrownum=4;//第5行开始
//        int progsize=progresses.size();//进程数
//        SysarguBpo sysargubpo=new SysarguBpo();
//        for(int i=0;i<progsize;i++){
//            ProgressBean progress=progresses.get(i);
//            int rowheighttemp=this.evaluateRowHeight(11, progress.getContent());
//            if(this.evaluateRowHeight(11, progress.getCheckopinion())+3*400>rowheighttemp) rowheighttemp=this.evaluateRowHeight(11, progress.getCheckopinion())+3*400;
//            int currentrownum=initrownum+i;
//            sheet.setRowView(currentrownum, rowheighttemp);
//            sheet.mergeCells(0, currentrownum, 1, currentrownum);//(col,row)合并前两列
//            sheet.mergeCells(2, currentrownum, 3, currentrownum);
//            sheet.mergeCells(4, currentrownum, 5, currentrownum);
//
//            sheet.addCell(new Label(0,currentrownum,progress.getStartenddate(),this.setCellFormatWithThinLine(Alignment.CENTRE, wfont12)));
//            sheet.addCell(new Label(2,currentrownum,progress.getContent(),this.setCellFormatWithThinLine(Alignment.LEFT, wfont12)));
//            sheet.addCell(new Label(4,currentrownum,progress.getCheckopinion()+"\n          "+tutorname+"\n                "+sysargubpo.getFillinDate(Integer.valueOf(progress.getInorder())),this.setCellFormatWithThinLine(Alignment.LEFT, wfont12)));
//        }
//        /**尾部11（教务处制）*/
//        int tailrownum=initrownum+progsize;
//        sheet.setRowView(tailrownum, 300);
//        sheet.mergeCells(0, tailrownum, 5, tailrownum);
//        sheet.addCell(new Label(0,tailrownum,"教务处制",this.setCellFormatWithoutBorder(Alignment.RIGHT, wfont11)));
//        /**尾部12（注）*/
//        sheet.setRowView(tailrownum+1, 600);
//        sheet.mergeCells(0, tailrownum+1, 5, tailrownum+1);
//        sheet.addCell(new Label(0,tailrownum+1,"注：“应完成的工作内容”、“检查情况”两栏应详细填写检查监督情况，并有指导教师签字及检查日期。",this.setCellFormatWithoutBorder(Alignment.LEFT, wfont11)));
//    }
//    /**设置单元格格式（带细边线）*/
//    private WritableCellFormat setCellFormatWithThinLine(Alignment alignment,WritableFont wfont) throws Exception{
//        WritableCellFormat wccontentcentre=new WritableCellFormat(wfont);
//        wccontentcentre.setAlignment(alignment);//内容对齐方式
//        wccontentcentre.setVerticalAlignment(VerticalAlignment.CENTRE);
//        wccontentcentre.setBorder(Border.BOTTOM, BorderLineStyle.THIN);//边框为细线,默认为none
//        wccontentcentre.setBorder(Border.TOP, BorderLineStyle.THIN);//边框为细线
//        wccontentcentre.setBorder(Border.LEFT, BorderLineStyle.THIN);
//        wccontentcentre.setBorder(Border.RIGHT, BorderLineStyle.THIN);
//        wccontentcentre.setWrap(true);//支持自动换行
//
//        return wccontentcentre;
//    }
//    private WritableCellFormat setCellFormatWithoutButtom(Alignment alignment,WritableFont wfont) throws Exception{
//        WritableCellFormat wccontentcentre=new WritableCellFormat(wfont);
//        wccontentcentre.setAlignment(alignment);//内容对齐方式
//        wccontentcentre.setVerticalAlignment(VerticalAlignment.CENTRE);
//        wccontentcentre.setBorder(Border.BOTTOM, BorderLineStyle.NONE);//边框为细线,默认为none
//        wccontentcentre.setBorder(Border.TOP, BorderLineStyle.THIN);//边框为细线
//        wccontentcentre.setBorder(Border.LEFT, BorderLineStyle.THIN);
//        wccontentcentre.setBorder(Border.RIGHT, BorderLineStyle.THIN);
//        wccontentcentre.setWrap(true);//支持自动换行
//
//        return wccontentcentre;
//    }
//    private WritableCellFormat setCellFormatWithoutTop(Alignment alignment,WritableFont wfont) throws Exception{
//        WritableCellFormat wccontentcentre=new WritableCellFormat(wfont);
//        wccontentcentre.setAlignment(alignment);//内容对齐方式
//        wccontentcentre.setVerticalAlignment(VerticalAlignment.CENTRE);
//        wccontentcentre.setBorder(Border.BOTTOM, BorderLineStyle.THIN);//边框为细线,默认为none
//        wccontentcentre.setBorder(Border.TOP, BorderLineStyle.NONE);//边框为细线
//        wccontentcentre.setBorder(Border.LEFT, BorderLineStyle.THIN);
//        wccontentcentre.setBorder(Border.RIGHT, BorderLineStyle.THIN);
//        wccontentcentre.setWrap(true);//支持自动换行
//
//        return wccontentcentre;
//    }
//    private WritableCellFormat setCellFormatWithoutTopButtom(Alignment alignment,WritableFont wfont) throws Exception{
//        WritableCellFormat wccontentcentre=new WritableCellFormat(wfont);
//        wccontentcentre.setAlignment(alignment);//内容对齐方式
//        wccontentcentre.setVerticalAlignment(VerticalAlignment.CENTRE);
//        wccontentcentre.setBorder(Border.BOTTOM, BorderLineStyle.NONE);//边框为细线,默认为none
//        wccontentcentre.setBorder(Border.TOP, BorderLineStyle.NONE);//边框为细线
//        wccontentcentre.setBorder(Border.LEFT, BorderLineStyle.THIN);
//        wccontentcentre.setBorder(Border.RIGHT, BorderLineStyle.THIN);
//        wccontentcentre.setWrap(true);//支持自动换行
//
//        return wccontentcentre;
//    }
//    private WritableCellFormat setCellFormatWithoutBorder(Alignment alignment, WritableFont wfont) throws Exception{
//        WritableCellFormat wccontentcentre=new WritableCellFormat(wfont);
//        wccontentcentre.setAlignment(alignment);//内容对齐方式
//        wccontentcentre.setVerticalAlignment(VerticalAlignment.CENTRE);
//        wccontentcentre.setWrap(true);//支持自动换行
//
//        return wccontentcentre;
//    }
//    /**估算行高
//     * countperrow-每行字数。任务书大约每行为40个。
//     * text-显示的文本
//     * */
//    public int evaluateRowHeight(int countperrow, String text)throws Exception{
//        int rowcounts=0;//行数
//        String[] textarr=text.split("\n");
//        for(int i=0;i<textarr.length;i++){
//            int rowtemp=textarr[i].length()/countperrow+1;
//            rowcounts=rowcounts+rowtemp;
//        }
//}}
