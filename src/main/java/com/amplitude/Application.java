package com.amplitude;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.text.ParseException;
import java.util.Arrays;

@SpringBootApplication
//@ComponentScan(basePackages = {"amplitude-integration"})
public class Application {

    public static void main(String[] args) throws ParseException {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        System.out.println("Let's inspect the beans provided by Spring Boot:");
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
//        EventReader fileReader = new EventFileReader(new File("events.txt"));
//        List<Event> eventList = null;
//        try {
//            eventList = fileReader.readEvents();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

}
