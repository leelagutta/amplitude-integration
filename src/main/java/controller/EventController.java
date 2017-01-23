package controller;

import domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.EventService;
import util.PojoConverter;

import java.io.File;
import java.util.List;

/**
 * Created by Leela on 1/22/17.
 */

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    private PojoConverter pojoConverter;


    @RequestMapping(value= "/events", method = RequestMethod.POST)
    public void sendEventsToAmplify(){
         pojoConverter = new PojoConverter();
        try {
            //get it from app config
            List<Event> eventList = pojoConverter.convertJsonToPojo(new File("resources/events.txt"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
