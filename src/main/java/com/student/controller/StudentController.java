package com.student.controller;


import com.student.model.Student;
import com.student.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/api/v1")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping(value = "/students")
    public HashMap<String, String> addStudent(@RequestBody Student student) {

        studentService.saveStudent(student);

        HashMap<String, String> return_message = new HashMap<String, String>();
        return_message.put("message", "Student created successfully.");
        return return_message;
    }

    @GetMapping("/students")
    public List<Student> ListStudent() {
        return studentService.listStudent();
    }

    @GetMapping("/students/{id}")
    public Student FindById(@PathVariable int id) {
        return studentService.getStudent(id);
    }

    @PutMapping("/students/{id}")
    public HashMap<String, String> updateStudent(@RequestBody Student student, @PathVariable int id) {

        studentService.updateStudent(student, id);
        HashMap<String, String> return_message = new HashMap<String, String>();
        return_message.put("message", "Student updated successfully.");
        return return_message;
    }

    @DeleteMapping("/students/{id}")
    public HashMap<String, String> DeleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
        HashMap<String, String> return_message = new HashMap<String, String>();
        return_message.put("message", "Student deleted successfully.");
        return return_message;
    }
}
