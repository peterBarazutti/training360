package training.employees;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class HelloControllerIT {

    @Autowired
    HelloController helloController;

    @Test
    void testSayHello() {
        assertThat(helloController.sayHello()).startsWith("Hello Spring Boot ".toUpperCase());
    }
}
