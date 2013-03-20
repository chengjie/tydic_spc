package com.tydic.spc.util;

import com.google.gson.*;
import com.tydic.spc.common.JsonResult;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JsonUtils {

    private static Gson gson;

    static {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(JsonResult.class, new JsonResultDeserializer());
        gson = builder.create();
    }

    public static String toJSON(Object fromData) {
        String temp = gson.toJson(fromData);
        return temp;
    }

    public static <T> T fromJSON(String jsonStr, Class<T> t) {
        return gson.fromJson(jsonStr, t);
    }

    public static <T> T parse(Class<T> type, JsonElement obj) {
        return gson.fromJson(obj, type);
    }

    private static class JsonResultDeserializer implements JsonDeserializer<JsonResult> {

        @Override
        public JsonResult deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonResult jsonResult = new JsonResult();
            JsonObject object = json.getAsJsonObject();

            jsonResult.setSuccess(object.get("success").getAsBoolean());
            if (object.has("message")) {
                JsonElement message = object.get("message");
                if (!message.isJsonNull()) {
                    jsonResult.setMessage(message.getAsString());
                }
            }

            if (object.has("data")) {
                Map<String, Object> map = new HashMap<String, Object>();
                Set<Map.Entry<String, JsonElement>> set = object.get("data").getAsJsonObject().entrySet();
                for (Map.Entry<String, JsonElement> entry : set) {
                    String key = entry.getKey();
                    JsonElement value = entry.getValue();

                    map.put(key, value);
                }

                jsonResult.setData(map);
            }

            return jsonResult;
        }
    }
}
