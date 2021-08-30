package com.student.dao;


import com.student.model.Student;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes={StudentRepository.class})
@DataJpaTest
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"com.student"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;


    @Test
    @Rollback(false)
    @Order(1)
    public void testSaveNewStudent() {

        Student student = new Student();
        student.setFirstName("test");
        student.setLastName("test");
        student.setNationality("test");
        studentRepository.save(student);

        assertThat(student.getFirstName()).isEqualTo("test");
    }

    @Test
    @Order(2)
    public void testListStudent() {
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).size().isGreaterThan(0);
    }

    @Test
    @Rollback(false)
    @Order(3)
    public void testUpdateStudent() {

        Student student = studentRepository.findById(1).orElse(null);
        student.setFirstName("test one");
        studentRepository.save(student);

        assertThat(student.getFirstName()).isEqualTo("test one");
    }

    @Test
    @Rollback(false)
    @Order(4)
    public void testDeleteStudent() {

        Student student = studentRepository.findById(1).orElse(null);

        studentRepository.deleteById(student.getId());

        Student student1 = studentRepository.findById(1).orElse(null);

        assertThat(student1).isNull();

    }
}
