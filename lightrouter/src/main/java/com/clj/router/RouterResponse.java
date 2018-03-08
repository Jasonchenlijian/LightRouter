package com.clj.router;

import org.json.JSONException;
import org.json.JSONObject;


public class RouterResponse {

    public final static int SUCCESS_CODE = 1;
    public final static int FAIL_CODE = 0;
    public final static String SUCCESS_DESC = "success";
    public final static String FAIL_DESC = "fail";
    public final static String FAIL_DESCRIPTION_PROVIDER = "Not found the provider!";
    public final static String FAIL_DESCRIPTION_ACTION = "Not found the action!";

    private int code;
    private String desc;
    private Object data;

    public void setResult(int code,
                          String desc,
                          Object data) {
        this.code = code;
        this.desc = desc;
        this.data = data;
    }

    public JSONObject getResult() {
        JSONObject jsResult = null;
        try {
            jsResult = new JSONObject()
                    .put("code", code)
                    .put("desc", desc)
                    .put("data", data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsResult;
    }

    public boolean isSuccess() {
        return code == SUCCESS_CODE;
    }

    public Object getData() {
        return data;
    }

}
