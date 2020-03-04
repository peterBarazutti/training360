package training.employees;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HelloService {

/*    @Value("${welcome_message}")
    private String greetings;*/

    private String greetings;

    public HelloService(@Value("${employees.hello}")String greetings) {
        this.greetings = greetings;
    }

    public String sayHello() {
        return greetings + " " + LocalDateTime.now();
    }
}
