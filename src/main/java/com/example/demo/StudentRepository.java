package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);

    @Query("SELECT s FROM Student s WHERE s.firstName = ?1 AND s.age >= ?2") //JPQL
    List<Student> findStudentsByFirstNameEqualsAndAgeIsGreaterThanEqual(String firstName, Integer age);

    // Might not work if you change the SQL engine for the application, JPQL will continue to work
    @Query(value = "SELECT * FROM student WHERE first_name = :first_name AND age >= :age", nativeQuery = true)  //SQL
    List<Student> findStudentsByFirstNameEqualsAndAgeIsGreaterThanEqualNative(
            @Param("first_name") String firstName,
            @Param("age") Integer age
    );

    @Transactional
    @Modifying
    @Query("DELETE FROM Student u WHERE u.id = ?1")
    int deleteStudentById(Long id);
}
