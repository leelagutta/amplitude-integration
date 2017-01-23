package util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Event;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Leela on 1/22/17.
 */

public class PojoConverter {

    public List<Event> convertJsonToPojo(File file) throws Exception {
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
