package com.amplitude.EventReader;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.amplitude.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Leela on 1/22/17.
 */

public class EventFileReader implements EventReader {

    @Value("${amplitude.event.file}")
    private File file;

    @Autowired
    public EventFileReader(@Value("${amplitude.event.file}") File file){
        this.file = file;
    }

    @Override
    public List<Event> readEvents() throws Exception {
        try {
            Scanner scanner = new Scanner(file, "UTF-8");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            List<Event> events = new ArrayList<>();
            while (scanner.hasNext()) {
                Event event = objectMapper.readValue(scanner.nextLine(), Event.class);
                events.add(event);
            }
            return events;
        }catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }
}
