package com.tydic.spc.common;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tydic.spc.exception.JsonResultException;
import com.tydic.spc.util.JsonUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 包含 Ajax 请求处理结果的通用结构。该结构包含
 * {@link #success}, {@link #message}, {@link #data} 三个属性。
 *
 */
public class JsonResult {

    /**
     * 请求是否成功处理
     */
    private boolean success;

    /**
     * 处理成功或失败时的相关信息
     */
    private String message;

    /**
     * 返回值
     */
    private Map<String, Object> data = new HashMap<String, Object>();

    /**
     * 以特定类型获取返回值属性
     *
     * @param key  属性名
     * @param type 属性值
     * @param <T>  属性值类型
     * @return 如果属性所代表的 JsonElement 对象能够被转化成 T 对象的话，则返回一个包含了属性值的 T 对象。
     */
    public <T> T getObject(String key, Class<T> type) {
        Object o = data.get(key);
        if (o == null) {
            return null;
        }

        if (type.isAssignableFrom(o.getClass())) {
            return (T) o;
        } else if (o instanceof JsonElement) {
            JsonElement obj = (JsonElement) o;
            return JsonUtils.parse(type, obj);
        }

        throw new JsonResultException("Unmatched Type: value of type "
                + o.getClass() + " cannot be cast to " + type);
    }

    public List<Map<String, String>> getList(String key) {
        Object o = data.get(key);
        if (o == null) {
            return null;
        }

        if (o instanceof JsonElement) {
            JsonElement obj = (JsonElement) o;

            if (obj.isJsonArray()) {
                JsonArray arr = (JsonArray) obj;
                List<Map<String, String>> list = new ArrayList<Map<String, String>>();

                for (int i = 0; i < arr.size(); i++) {
                    if (arr.get(i) == null) {
                        list.add(null);
                    } else {
                        Map<String, String> map = new HashMap<String, String>();
                        JsonObject object = arr.get(i).getAsJsonObject();
                        for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
                            map.put(entry.getKey(), entry.getValue().isJsonNull() ? null : entry.getValue().getAsString());
                        }
                        list.add(map);
                    }
                }

                return list;
            }
        }

        throw new JsonResultException("Unmatched Type: value of type "
                + o.getClass() + " cannot be cast to Map");
    }

    public <T> List<T> getList(String key, Class<T> type) {
        Object o = data.get(key);
        if (o == null) {
            return null;
        }

        if (o instanceof JsonElement) {
            JsonElement obj = (JsonElement) o;

            if (obj.isJsonArray()) {
                JsonArray arr = (JsonArray) obj;
                List<T> list = new ArrayList<T>();

                for (int i = 0; i < arr.size(); i++) {
                    list.add(JsonUtils.parse(type, arr.get(i)));
                }

                return list;
            }
        }

        throw new JsonResultException("Unmatched Type: value of type "
                + o.getClass() + " cannot be cast to " + type);
    }

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

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    /**
     * 把数据转换为Map
     * @param key
     * @return
     */
    public Map<String, Object> getListAsMap(String key) {

        JsonObject jsonObject = (JsonObject) data.get(key);
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        if(jsonObject==null){
            return jsonMap;
        }
        if(!jsonObject.isJsonNull()){
            for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
                //如果类型为List则解析存储到jsonMap否则存储格式为String
                if(entry.getValue().isJsonArray()){
                    JsonArray arr = (JsonArray) entry.getValue();
                    List<Map<String, String>> list  = new ArrayList<Map<String, String>>();
                    for (int i = 0; i < arr.size(); i++) {
                        if (arr.get(i) == null) {
                            list.add(null);
                        } else {
                            Map<String, String> map = new HashMap<String, String>();
                            JsonObject object1 = arr.get(i).getAsJsonObject();
                            for (Map.Entry<String, JsonElement> entryList : object1.entrySet()) {
                                map.put(entryList.getKey(), entryList.getValue().isJsonNull() ? null : entryList.getValue().getAsString());
                            }
                            list.add(map);
                        }
                    }
                    jsonMap.put(entry.getKey(), list);
                } else {
                    jsonMap.put(entry.getKey(), entry.getValue().isJsonNull() ? null : entry.getValue().getAsString());
                }
            }
        }
        return jsonMap;  //To change body of created methods use File | Settings | File Templates.
    }
}
