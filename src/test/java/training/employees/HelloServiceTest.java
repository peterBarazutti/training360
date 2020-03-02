package training.employees;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class HelloServiceTest {

    @Test
    void sayHello() {
        assertThat(new HelloService().sayHello()).startsWith("Hello Spring Boot");
    }
}