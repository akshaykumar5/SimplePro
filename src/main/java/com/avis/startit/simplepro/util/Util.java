/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avis.startit.simplepro.util;

import com.avis.startit.simplepro.json.bean.ErrorData;
import com.avis.startit.simplepro.json.bean.JsonRequest;
import com.avis.startit.simplepro.service.Translator;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author avistech
 */
@Log4j2
@Component
public class Util {

    @Autowired
    private Gson gson;

    @Autowired
    Translator translator;

    private static final AtomicInteger AUTO_INC_VALUE = new AtomicInteger(0);

    private final Integer NUMBER_OF_PROCESSERS = Runtime.getRuntime().availableProcessors();

    public String createAccountCode(String prefix) {
        String dateVar = dateToString(getCurrentDateTime(), "MMddyyyyHHmmss");
        AUTO_INC_VALUE.compareAndSet(9999, 0);
        String accountCode = prefix + dateVar + AUTO_INC_VALUE.getAndIncrement();
        return accountCode;
    }

    public boolean isJson(String Json) {
        try {
            return !gson.fromJson(Json, Object.class).getClass().equals(String.class);
        } catch (com.google.gson.JsonSyntaxException e) {
            log.error("Exception occured. {}", e.getMessage());
            log.catching(Level.FATAL, e);
            return false;
        }
    }

    public Date getCurrentDateTime() {
        Date date = new Date();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //formatter.setTimeZone(TimeZone.getTimeZone(NettyServer.timeZoneId));
        String dateString = formatter.format(date);
        try {
            date = (Date) formatter.parse(dateString);
        } catch (ParseException e) {
            log.error("Exception occured. {}", e.getMessage());
            log.catching(Level.FATAL, e);
        }
        return date;
    }

    public String getCurrentDateTimeString(Date date) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //formatter.setTimeZone(TimeZone.getTimeZone(NettyServer.timeZoneId));
        String dateTimeString = formatter.format(date);
        return dateTimeString;
    }

    public String getCurrentDateString(Date date) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        //formatter.setTimeZone(TimeZone.getTimeZone(NettyServer.timeZoneId));
        String dateString = formatter.format(date);
        return dateString;
    }

    public String getDayOfWeek(Date date) {
//        SimpleDateFormat simpleDateformat = new SimpleDateFormat("E"); // the day of the week abbreviated
//        System.out.println(simpleDateformat.format(now));

        SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
        return simpleDateformat.format(date);
    }

    public Integer getDayOfWeekNumber(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK); // the day of the week in numerical format
    }

    public Long currentEpoch() {
        try {
            Date date = new Date();
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(date);
            date = (Date) formatter.parse(dateString);
            return (date.getTime() / 1000);
        } catch (ParseException e) {
            log.error("Exception occured. {}", e.getMessage());
            log.catching(Level.FATAL, e);
        }
        return 0l;
    }

    public String dateToString(Date date, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public Date StringToDate(String dateString) throws Exception {
        Date dateValue = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (dateString != null && dateString.length() > 0) {
            dateValue = simpleDateFormat.parse(dateString);
        }
        return dateValue;
    }

    public Long getEpoch(String dateTime) {
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = formatter.parse(dateTime);
            return (date.getTime() / 1000);
        } catch (ParseException e) {
            log.error("Exception occured. {}", e.getMessage());
            log.catching(Level.FATAL, e);
        }
        return null;
    }

    public Date getDate(Long milliSecond) {
        if (milliSecond != null) {
            return new Date(milliSecond);
        }
        return null;
    }

    /*get Only Digits Function*/
    public String getOnlyDigits(String s) {
        Pattern pattern = Pattern.compile("[^0-9]");
        Matcher matcher = pattern.matcher(s);
        String number = matcher.replaceAll("");
        return number;
    }

    public Timestamp currentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public String timestampToString(Timestamp date, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public Timestamp stringToTimestamp(String dateString, String format) {
        Timestamp timeStampDate = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            Date date = formatter.parse(dateString);
            timeStampDate = new Timestamp(date.getTime());
        } catch (ParseException e) {
            log.error("Exception occured. {}", e.getMessage());
            log.catching(Level.FATAL, e);
        }
        return timeStampDate;
    }

    public Integer getCoreProcessorsCount() {
        return NUMBER_OF_PROCESSERS;
    }

    public String secondToHMS(long seconds) {
        long hour = seconds / 3600;
        long rem = seconds % 3600;
        long minute = rem / 60;
        long sec = rem % 60;

        String h = (hour < 10 ? "0" : "") + hour;
        String m = (minute < 10 ? "0" : "") + minute;
        String s = (sec < 10 ? "0" : "") + sec;

        return h + ":" + m + ":" + s;
    }

    public JsonRequest buildResponse(JsonElement data, Query query, Long domainId) {
        JsonRequest jsonRequest = new JsonRequest();
        jsonRequest.setData(data);
        jsonRequest.setQuery(query.getValue());
        jsonRequest.setDomainId(domainId);
        return jsonRequest;
    }

    public JsonRequest buildData(JsonElement data) {
        JsonRequest jsonRequest = new JsonRequest();
        jsonRequest.setData(data);
        return jsonRequest;
    }

    public JsonRequest buildData(JsonElement data, Type managerType) {
        JsonRequest jsonRequest = new JsonRequest();
        jsonRequest.setData(data);
        jsonRequest.setType(managerType.getValue());
        return jsonRequest;
    }

    public JsonRequest buildSuccessMsg() {
        JsonRequest jsonRequest = new JsonRequest();
        jsonRequest.setData(successMsg("Ok"));
        return jsonRequest;
    }

    public JsonRequest buildSuccessMsg(String message) {
        JsonRequest jsonRequest = new JsonRequest();
        jsonRequest.setData(successMsg(message));
        return jsonRequest;
    }

    private JsonObject successMsg(String message) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("success", message);
        return jsonObject;
    }

    public JsonRequest buildErrorMsg(String message) {
        return buildErrorMsg(message, "");
    }

    public JsonRequest buildErrorMsg(String message, String description, String language) {
        Integer errorCode = 0;
        if (ErrorCodes.getContext(message) != null) {
            errorCode = ErrorCodes.getContext(message).getValue();
            log.debug("errorCode: {}", errorCode);
        }
        return buildErrorMsg(message, description, errorCode, language);
    }

    public JsonRequest buildErrorMsg(String message, String language) {
        Integer errorCode = 0;
        if (ErrorCodes.getContext(message) != null) {
            errorCode = ErrorCodes.getContext(message).getValue();
            log.debug("errorCode: {}", errorCode);
        }
        return buildErrorMsg(message, "", errorCode, language);
    }

    public JsonRequest buildErrorMsg(String message, String description, JsonElement responseJson, String language) {
        return buildErrorMsg(message, description, 0, responseJson, language);
    }

    public JsonRequest buildErrorMsg(String message, String description, Integer code, String language) {
        return buildErrorMsg(message, description, code, null, language);
    }

    public JsonRequest buildErrorMsg(String message, String description, Integer code, JsonElement data, String language) {
        if (language == null) {
            language = "en";
        }
        log.debug("language: {}", language);

        try {
            message = translator.getMessage(message, language);
        } catch (Exception e) {
            log.catching(e);
            log.warn("Message not found using default message key in langauge: {}", language);
        }

        ErrorData errorData = new ErrorData();
        errorData.setCode(code);
        errorData.setMessage(message);
        errorData.setDescription(description);

        JsonRequest jsonRequest = new JsonRequest();
        jsonRequest.setData(data);
        jsonRequest.setError(errorData);
        jsonRequest.setResponse(Status.FAILURE.getValue());

        return jsonRequest;
    }

    public Map<String, Object> convertMapKeysToLowerCase(Map<String, Object> dataMap) {
        if (dataMap == null || dataMap.isEmpty()) {
            return dataMap;
        }

        return dataMap.entrySet()
                .parallelStream()
                .map(entry -> {
                    if (entry.getValue() == null) {
                        entry.setValue("");
                    }
                    return entry;
                })
                .collect(Collectors.toMap(entry -> entry.getKey().toLowerCase(), Map.Entry::getValue));
    }
}
