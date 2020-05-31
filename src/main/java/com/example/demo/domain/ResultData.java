package com.example.demo.domain;


public class ResultData {
    public static int SUCCESS_CODE = 200;
    public static int FAIL_CODE = 400;
    private Integer code;
    private Object data;
    private String message;
    private Integer rank;


    public ResultData(){}

    public ResultData(Integer code,Object data,String message,Integer rank){
        this.code = code;
        this.data = data;
        this.message = message;
        this.rank = rank;
    }



    public static ResultData success(){
        return new ResultData(SUCCESS_CODE,null,null,0);
    }

    public static ResultData fail(){
        return new ResultData(FAIL_CODE,null,null,-1);
    }

    public static ResultData success(String message){
        return new ResultData(SUCCESS_CODE,null,message,0);
    }

    public static ResultData success(Object data){
        return new ResultData(SUCCESS_CODE,data,null,0);
    }

    public static ResultData fail(String message){
        return new ResultData(FAIL_CODE,null,message,-1);
    }

    public static ResultData success(Integer rank){
        return new ResultData(SUCCESS_CODE,null,null,rank);
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}
