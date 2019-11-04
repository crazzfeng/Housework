package com.house.work.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * @author yufeng li
 * @title: Result
 * @description:
 * @date 2019/11/4 17:35
 */

public class Result<T> implements Serializable {
    private static final long serialVersionUID = 5870221908873132839L;
    private int code;
    private String msg;
    private T data;

    public Result() {
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> success(T data) {
        return success(data, "操作成功");
    }

    public static <T> Result<T> success(T data, String msg) {
        return success(data, 1, msg);
    }

    public static <T> Result<T> success(Integer code, String msg) {
        return success(null, code, msg);
    }

    public static <T> Result<T> success(T data, Integer code, String msg) {
        Result<T> resultData = new Result<>();
        resultData.setCode(code);
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }

    public static Result error() {
        Result resultData = new Result();
        resultData.setCode(0);
        resultData.setMsg("操作失败");
        return resultData;
    }

    public static Result error(String msg) {
        Result resultData = new Result();
        resultData.setCode(0);
        resultData.setMsg(msg);
        return resultData;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static Map<String, Object> addLowercase(Map<String, Object> map) {
        map.put("code", map.get("CODE"));
        map.put("msg", map.get("MSG"));
        return map;
    }

    public String toString() {
        return "Result{code=" + this.code + ", msg='" + this.msg + '\'' + ", data=" + this.data + '}';
    }

    public String toJsonString() {
        JSONObject json = new JSONObject();
        json.put("code", this.code);
        if (this.data != null) {
            json.put("data", this.data);
        }

        if (!StringUtils.isEmpty(this.msg)) {
            json.put("msg", this.msg);
        }

        return json.toString();
    }
}
