package com.amazaar.CommonCode;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.protobuf.MessageOrBuilder;

import java.io.IOException;

public class ProtoSerilizerAndDeserilizer {

    public JsonObject getJsonObject(MessageOrBuilder messageOrBuilder) {
        JsonElement jsonElement = null;
        try {
            jsonElement = new JsonParser().parse(ProtoJsonUtil.toJson(messageOrBuilder));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonElement.getAsJsonObject();
    }

    public MessageOrBuilder getProtoMessage(JsonObject json, Class clazz) {
        Gson gson = new Gson();
        return (MessageOrBuilder) gson.fromJson(json.toString(), clazz);
    }
}
