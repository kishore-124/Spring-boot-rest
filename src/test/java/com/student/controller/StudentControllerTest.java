package com.student.controller;


import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.student.model.Student;
import com.student.services.StudentService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {
        Student student = new Student();
        student.setFirstName("test");
        student.setLastName("test");
        student.setNationality("test");

        when(studentService.saveStudent(student));
        this.mockMvc.perform(post("/api/v1/students")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Student created successfully")));
    }
}
