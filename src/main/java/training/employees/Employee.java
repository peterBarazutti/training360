package training.employees;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("employees")
public class Employee {

    public Employee(String name) {
        this.name = name;
    }

    @Id
    private String id;

    private String name;

}
