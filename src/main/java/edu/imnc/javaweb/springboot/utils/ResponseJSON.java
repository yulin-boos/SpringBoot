package edu.imnc.javaweb.springboot.utils;

import lombok.Data;

@Data
public class ResponseJSON {
    private Integer code;
    private String message;
    private Object data;
    private Boolean success;

    public static ResponseJSON ok(Object data) {
        ResponseJSON json = new ResponseJSON();
        json.code = 200;
        json.message = "ok";
        json.data = data;
        json.success = true;
        return json;
    }

    public static ResponseJSON error() {
        ResponseJSON json = new ResponseJSON();
        json.code = 500;
        json.message = "error";
        json.success = false;
        return json;
    }
}
