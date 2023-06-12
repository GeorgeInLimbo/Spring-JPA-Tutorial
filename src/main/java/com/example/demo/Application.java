package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student maria = new Student("Maria", "Jones", "mjones@amigoscode.edu", 21);
            Student maria2 = new Student("Maria", "Jones", "mjones2@amigoscode.edu", 25);
            Student ahmed = new Student("Ahmed", "Ali", "ahmed.ali@amigoscode.edu", 18);
            studentRepository.saveAll(List.of(maria, ahmed, maria2));

            studentRepository
                    .findStudentByEmail("ahmed.ali@amigoscode.edu")
                    .ifPresentOrElse(System.out::println, () -> System.out.println("Student with this email is not found"));

            studentRepository.findStudentsByFirstNameEqualsAndAgeIsGreaterThanEqualNative("Maria", 18).forEach(System.out::println);

            System.out.println("Deleting the other Maria");
            System.out.println(studentRepository.deleteStudentById(3L));
        };
    }
}


