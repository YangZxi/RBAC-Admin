package cn.xiaosm.yadmin.basic.entity;

public class ResponseEntity {

    private Integer code;
    private String msg;
    private Object data;

    public static ResponseEntity ok(Object data) {
        ResponseEntity body = new ResponseEntity("success", data);
        body.setCode(200);
        return body;
    }

    public static ResponseEntity ok(String msg, Object data) {
        ResponseEntity body = new ResponseEntity(msg, data);
        body.setCode(200);
        return body;
    }

    public static ResponseEntity fail(Object data) {
        ResponseEntity body = new ResponseEntity("success", data);
        body.setCode(400);
        return body;
    }

    public static ResponseEntity fail(String msg, Object data) {
        ResponseEntity body = new ResponseEntity(msg, data);
        body.setCode(400);
        return body;
    }

    public ResponseEntity() {}

    public ResponseEntity(Integer code) {
        this.code = code;
        this.msg = "empty msg";
    }

    public ResponseEntity(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseEntity(String msg, Object data) {
        this.code = 200;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public ResponseEntity setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResponseEntity setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public ResponseEntity setData(Object data) {
        this.data = data;
        return this;
    }
}