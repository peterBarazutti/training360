package training.employees;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeesService {


    private ModelMapper modelMapper;

    //private EmployeesRepository employeesRepository;

    private DataEmployeesRepository repository;

    public EmployeesService(ModelMapper modelMapper, DataEmployeesRepository repository) {
        this.modelMapper = modelMapper;
        this.repository = repository;
    }

    @Transactional
    public EmployeeDto createEmployee(CreateEmployeeCommand command) {
        var employee = repository.save(new Employee(command.getName()));
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public List<EmployeeDto> listEmployees(String prefix) {

        log.info("Alkalmazottak listázása");

        log.debug("A paraméter: {}", prefix);

        return repository.findAll()
                .stream()
                .map(e -> modelMapper.map(e, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    public EmployeeDto findEmployeeById(long id) {
        return modelMapper.map(repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found")),
                EmployeeDto.class);
    }

    @Transactional
    public EmployeeDto updateEmployee(long id, UpdateEmployeeCommand command) {
        var employee = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        employee.setName(command.getName());
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public void deleteEmployee(long id) {
        repository.deleteById(id);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

}