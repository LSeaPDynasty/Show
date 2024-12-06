package com.servelt.librarysystem_servlet.comon;

import com.google.gson.Gson;

public class Result<T> {

    private boolean success;
    private String message;
    private T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Result(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
    public Result(boolean success) {
        this.success = success;
    }
    public Result() {
    }
    public static <T> Result<T> success(T data){
        return new Result<T>(true,"success",data);
    }
    public static <T> Result<T> success(String message,T data){
        return new Result<T>(true,message,data);
    }
    public static <T> Result<T> success(String message){
        return new Result<T>(true,message);
    }
    public static <T> Result<T> success(boolean success,String message,T data){
        return new Result<>(success,message,data);
    }
    public static <T> Result<T> error(String message){
        return new Result<T>(false,message);
    }


    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public String toJsonString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
