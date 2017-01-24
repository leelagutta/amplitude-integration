package com.amplitude.client;

import com.amplitude.producer.AmplitudeProducer;
import com.amplitude.domain.Event;
import com.google.gson.Gson;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@NoArgsConstructor
@Slf4j
@Component
public class AmplitudeClient {


    @Autowired
    private RestOperations restTemplate;

    private AmplitudeProducer rabbitMQUtil;

    @Value("${amplitude.base.url}")
    private String amplitudeEndpoint;

    @Setter
    @Value("${amplitude-data.exchange}")
    private String exchange;

    @Autowired
    public AmplitudeClient(RestTemplate restTemplate, @Value("${amplitude.base.url}") String amplitudeEndpoint, AmplitudeProducer rabbitMQUtil) {
        this.restTemplate = restTemplate;
        this.amplitudeEndpoint = amplitudeEndpoint;
        this.rabbitMQUtil = rabbitMQUtil;
    }

    public String publishEvents(List<Event> eventList) throws Exception {
        for (Event event : eventList) {
            Gson gson = new Gson();
            String message = gson.toJson(event);
            Message m = MessageBuilder.withBody(message.getBytes()).
                        setHeader(AmqpHeaders.DELIVERY_MODE, MessageDeliveryMode.PERSISTENT).build();
            rabbitMQUtil.sendMessage(message);
        }
        return " ";
    }

























    //        AmplitudeDataConverter amplitudeDataConverter = new AmplitudeDataConverter();
//        ParameterizedTypeReference responseType = new ParameterizedTypeReference(){};
//        HttpHeaders headers = new HttpHeaders();
//        List<String> list = new ArrayList<>();
//        list.add("040062a5d38552315b98302ba4f2f");
//        headers.put("api_key",list);
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<JSONArray> entity = new HttpEntity<>(amplitudeDataConverter.convertToJsonObjectForAmplitudeEvents(events), headers);
//        ResponseEntity responseEntity =restTemplate.exchange(amplitudeEndpoint, HttpMethod.POST, entity, responseType);
//        return responseEntity.getStatusCode().toString();

}
