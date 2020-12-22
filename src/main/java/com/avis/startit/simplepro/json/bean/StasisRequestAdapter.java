/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avis.startit.simplepro.json.bean;

import com.google.gson.JsonArray;
import java.lang.reflect.Type;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 *
 * @author avistech
 */
@Log4j2
@Component
public class StasisRequestAdapter implements JsonDeserializer<Map<String, String>> {

    @Override
    public Map<String, String> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Map<String, String> args = new ConcurrentHashMap<>();
        JsonArray jsonArray = (JsonArray) json;
        for (JsonElement jsonElement : jsonArray) {
            log.debug("jsonElement: {} ", jsonElement);
            String[] data = jsonElement.getAsString().split("-");
            args.put(data[0], data[1]);
        }
        return args;
    }
}
