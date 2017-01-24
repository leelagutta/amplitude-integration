package com.amplitude.util;

import com.amplitude.domain.Event;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.text.ParseException;
import java.util.List;

public class AmplitudeDataConverter {

    public JSONArray convertToJsonObjectForAmplitudeEvents(List<Event> events) throws ParseException {

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();

        try {

            for (Event event : events) {
                jsonObject.put("user_id", event.getId());
                jsonObject.put("device_id", event.getContext().getDevice().getId());
                jsonObject.put("event_id", event.getId());
                jsonObject.put("app_version", event.getVersion());
                jsonObject.put("os_name", event.getContext().getOs().getName());
                jsonArray.add(jsonObject);
            }
            return jsonArray;
        } catch (Exception e) {
//                log.error("error while parsing the date" + sourceDate);
            e.printStackTrace();
            throw new ParseException("error while formatting date", 1);
        }

    }


}