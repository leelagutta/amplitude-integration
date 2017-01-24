package com.amplitude.controller;

import com.amplitude.service.AmplitudeEventService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@Getter
@RequestMapping("/event")
public class AmplitudeEventController {

    private AmplitudeEventService amplitudeEventService;

    @Autowired
    public AmplitudeEventController(AmplitudeEventService amplitudeEventService){
        this.amplitudeEventService = amplitudeEventService;
    }


    @RequestMapping(value = "/publishEvents", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> publishEventsToAmplitude(){

        try {
            amplitudeEventService.publishEvents();
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
