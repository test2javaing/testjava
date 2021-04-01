package com.asecna.utils;

public class Json {
    public static final String SUCCESS = "success";
    public static final String FAIL = "fail";
    public static final String ERROR = "error";
    private String status;
    private Object data;
    private String message;

    public String getStatus() {
            return status;
    }

    public void setStatus(String status) {
            this.status = status;
    }

    public Object getData() {
            return data;
    }

    public void setData(Object data) {
            this.data = data;
    }

    public String getMessage() {
            return message;
    }

    public void setMessage(String message) {
            this.message = message;
    }
    
    public Json(String status) {
        setStatus(status);
    }

    public Json(String status, Object data, String message) {
        setStatus(status);
        setData(data);
        setMessage(message);
    }
}
