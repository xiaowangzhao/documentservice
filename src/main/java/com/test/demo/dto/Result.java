package com.test.demo.dto;

public class Result<T> {


    private T data; //成功时返回的数据

    private String errorMsg;//错误信息

    private int status;  // 状态值：200 极为成功，其他数值代表失败


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static Result  success(){
        Result result = new Result();
        result.setStatus(200);
        result.setData("处理成功");
        return  result;
    }
    public static Result error(int code,String msg){
        Result result = new Result();
        result.setStatus(code);
        result.setErrorMsg(msg);
        return result;
    }
    public Result<T> add(Object data){
        this.data = (T) data;
        return this;
    }
}