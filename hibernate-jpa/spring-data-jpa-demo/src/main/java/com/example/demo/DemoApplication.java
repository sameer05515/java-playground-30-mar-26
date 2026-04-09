package com.example.demo;

import com.example.demo.controller.DemoController;
import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepo;
import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Value("${demo.greeting}")
    private String greeting;

    @Autowired
    private Environment environment;

    @Autowired
    private DemoController demoController;

    @Autowired
    private EmployeeRepo employeeRepo;

    @PostConstruct
    public void init(){
        System.out.println("This is init method");
    }

	public static void main(String[] args) {
        System.out.println("Welcome to Spring Boot Application");
		SpringApplication.run(DemoApplication.class, args);
        System.out.println("Hello World");
	}

    @Override
    public void run(String... args) throws Exception {
        System.out.println("I love my India");
        demoController.printMessage(greeting);

        demoController.printMessage(environment.getProperty("environment.development.value"));

//        employeeRepo.deleteAll();
        if(employeeRepo.count()==0){
            employeeRepo.saveAll(List.of(
                new Employee(0, "Alice Smith", "Engineering", 80000),
                new Employee(0, "Bob Johnson", "Marketing", 70000),
                new Employee(0, "Carol Williams", "Finance", 75000),
                new Employee(0, "David Jones", "Engineering", 82000),
                new Employee(0, "Eva Brown", "HR", 68000),
                new Employee(0, "Frank Miller", "Sales", 73000),
                new Employee(0, "Grace Davis", "Engineering", 81000),
                new Employee(0, "Henry Wilson", "Marketing", 72000),
                new Employee(0, "Ivy Moore", "Finance", 76000),
                new Employee(0, "Jack Taylor", "HR", 69000),
                new Employee(0, "Kathy Anderson", "Sales", 74000),
                new Employee(0, "Liam Thomas", "Engineering", 83000),
                new Employee(0, "Mia Jackson", "Marketing", 71000),
                new Employee(0, "Noah White", "Finance", 77000),
                new Employee(0, "Olivia Harris", "HR", 69500),
                new Employee(0, "Paul Martin", "Sales", 75000),
                new Employee(0, "Quinn Thompson", "Engineering", 82500),
                new Employee(0, "Ruby Garcia", "Marketing", 70500),
                new Employee(0, "Sam Martinez", "Finance", 76500),
                new Employee(0, "Tina Robinson", "HR", 68200),
                new Employee(0, "Uma Clark", "Sales", 74800),
                new Employee(0, "Vik Patel", "Engineering", 80800),
                new Employee(0, "Wendy Lewis", "Marketing", 71500),
           
                new Employee(0, "Xander Lee", "Finance", 75500),
                new Employee(0, "Yara Walker", "HR", 69900)
            ));
        }

        System.out.println("All employees: ");
        employeeRepo.findAll().forEach(System.out::println);
        System.out.println("Total employees: " + employeeRepo.count());
        System.out.println("Employee with ID 1: " + employeeRepo.findById(1).orElse(null));
        System.out.println("Employee with ID 100: " + employeeRepo.findById(100).orElse(null));
        System.out.println("Employee with ID 100: " + employeeRepo.findById(100).orElse(null));


        System.out.println("Employees in the database: ");
        employeeRepo.findAll().forEach(System.out::println);

        System.out.println("Employees with pagination: ");
        PageRequest pageable = PageRequest.of(0, 3);
        employeeRepo.findAll(pageable).forEach(System.out::println);

        System.out.println("Page details: ");
       Page<Employee> page = employeeRepo.findAll(pageable);
       System.out.println("Total pages: " + page.getTotalPages());
       System.out.println("Total elements: " + page.getTotalElements());
       System.out.println("Page number: " + page.getNumber());
       System.out.println("Page size: " + page.getSize());
       System.out.println("Page content: " + page.getContent());


       System.out.println("Employees sorted by name: ");
       employeeRepo.findAll(Sort.by("name").ascending()).forEach(System.out::println);
       System.out.println("Employees sorted by name descending: ");
       employeeRepo.findAll(Sort.by("name").descending()).forEach(System.out::println);
       System.out.println("Employees sorted by name and age: ");
       employeeRepo.findAll(Sort.by("name").ascending().and(Sort.by("age").descending())).forEach(System.out::println);
       System.out.println("Employees sorted by name and age descending: ");
       employeeRepo.findAll(Sort.by("name").descending().and(Sort.by("age").ascending())).forEach(System.out::println);
       System.out.println("Employees sorted by name and age ascending: ");
       employeeRepo.findAll(Sort.by("name").ascending().and(Sort.by("age").ascending())).forEach(System.out::println);


    //    System.out.println("Employees with salary greater than 70000: ");
    //    employeeRepo.findBySalaryGreaterThan(70000).forEach(System.out::println);

       System.out.println("Employee count by department: ");
       employeeRepo.findDepartmentAndCount().forEach(System.out::println);

    }
}
