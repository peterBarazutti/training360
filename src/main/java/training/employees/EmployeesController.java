package training.employees;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeesController {

    EmployeesService employeesService;

    public EmployeesController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    @GetMapping
    public List<EmployeeDto> listEmployees(@RequestParam(required = false) String prefix) {
        return employeesService.listEmployees(prefix);
    }

/*    @GetMapping("/{id}")
    public EmployeeDto findEmployeeById(@PathVariable("id") long id) {
        return employeesService.findEmployeeById(id);
    }*/

/*    @GetMapping("/{id}")
    public ResponseEntity findEmployeeById(@PathVariable("id") long id) {
        try {
            return ResponseEntity.ok(employeesService.findEmployeeById(id));
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.notFound().build();
        }
    }*/

    @GetMapping("/{id}")
    public ResponseEntity findEmployeeById(@PathVariable("id") String id) {
            return ResponseEntity.ok(employeesService.findEmployeeById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto createEmployee(@Valid @RequestBody CreateEmployeeCommand command) {
        return employeesService.createEmployee(command);
    }

    @PostMapping("/{id}")
    public EmployeeDto updateEmployee(@PathVariable("id") String id, @RequestBody UpdateEmployeeCommand command) {
        return  employeesService.updateEmployee(id, command);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployee(@PathVariable("id") String id) {
        employeesService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public void handleNotFound() {
        System.out.println("Employee not found.");
    }

}
