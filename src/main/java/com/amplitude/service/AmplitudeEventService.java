package com.amplitude.service;

import com.amplitude.EventReader.EventFileReader;
import com.amplitude.client.AmplitudeClient;
import com.amplitude.domain.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;
import java.util.List;

@Slf4j
@Service
public class AmplitudeEventService {

    private AmplitudeClient amplitudeClient;

    @Autowired
    public AmplitudeEventService(AmplitudeClient amplitudeClient) {
        this.amplitudeClient = amplitudeClient;
    }


    public String publishEvents() throws Exception {
        return amplitudeClient.publishEvents(readEventsFromFile());
    }

    private List<Event> readEventsFromFile() throws Exception {
        EventFileReader eventFileReader = new EventFileReader(new File("events.txt"));
        return eventFileReader.readEvents();
    }

}
