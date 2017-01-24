package com.amplitude.EventReader;

import com.amplitude.domain.Event;

import java.util.List;

public interface EventReader {

    public List<Event> readEvents() throws Exception;
}
