package com.zhiyou100.model;

import java.util.HashMap;
import java.util.Map;

public class ExecuteResult {

    public static Map<String, Object> jsonReturn(int statusCode) {
        Map<String, Object> jsonObj = new HashMap<>();
        if (statusCode == 200) {
            jsonObj.put("statusCode", "200");
            jsonObj.put("message", "操作成功");
        } else if (statusCode == 300) {
            jsonObj.put("statusCode", "300");
            jsonObj.put("message", "操作失败，请重试");
        }
        return jsonObj;
    }


    public static Map<String, Object> jsonReturn(String success, String message) {
        Map<String, Object> jsonObj = new HashMap<>();
        jsonObj.put("success", success);
        jsonObj.put("message", message);
        return jsonObj;
    }
}
