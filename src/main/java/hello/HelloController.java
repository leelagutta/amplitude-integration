package hello;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@RestController
public class HelloController {
    
    @RequestMapping("/")
    public String index() throws IOException {
        return "Greetings from Spring Boot!";
    }
    
}
