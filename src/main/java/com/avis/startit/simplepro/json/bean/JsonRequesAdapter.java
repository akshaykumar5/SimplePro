/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avis.startit.simplepro.json.bean;

import static com.avis.startit.simplepro.util.Type.*;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.Map;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 *
 * @author avistech
 */
@Log4j2
@Component
public class JsonRequesAdapter implements JsonSerializer<JsonRequest>, JsonDeserializer<JsonRequest> {

    private static final String TYPE = "type";
    private static final String DATA = "data";

    @Override
    public JsonRequest deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Gson gson = new Gson();
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        log.debug("jsonObject: {}", jsonObject);
        log.debug("type: {}", type);
        JsonPrimitive jsonPrimitive = (JsonPrimitive) jsonObject.get(TYPE);
        String classOfType = "";
        if (jsonPrimitive != null) {
            classOfType = jsonPrimitive.getAsString();
        }
        String className;
        if (classOfType.equals(ADMIN_REQUEST.getValue())) {
            className = AgentRequestData.class.getName();
//            className = AdminRequestData.class.getName();
        } else if (classOfType.equals(AGENT_REQUEST.getValue())) {
            className = AgentRequestData.class.getName();
        } else if (classOfType.equals(MANAGER_REQUEST.getValue())) {
            className = ManagerRequestData.class.getName();
        } else {
            className = Map.class.getName();
        }
        log.debug("className: {}", className);
        Class<? extends ChatData> classObj = getClassObject(className);
        log.debug("classObj: {}", classObj);

        JsonRequest jsonRequest = gson.fromJson(jsonObject, JsonRequest.class);
        log.debug("jsonRequest: {}", jsonRequest);

        jsonRequest.setData(gson.fromJson(jsonObject.get(DATA), classObj));
        log.debug("jsonRequest: {}", jsonRequest);

        return jsonRequest;
    }

    @Override
    public JsonElement serialize(JsonRequest jsonElement, Type type, JsonSerializationContext jsonSerializationContext) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.toJsonTree(jsonElement).getAsJsonObject();
        return jsonObject;
    }

    public Class getClassObject(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
            throw new JsonParseException(e.getMessage());
        }
    }
}
