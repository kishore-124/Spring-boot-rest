package com.student.services;

import com.student.dao.StudentRepository;
import com.student.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {

    @Autowired
    public StudentRepository studentRepository;

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public List<Student> listStudent() {
        return studentRepository.findAll();
    }

    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    public Student getStudent(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student updateStudent(Student student, int id) {
        Student exists = studentRepository.findById(id).orElse(null);
        exists.setFirstName(student.getFirstName());
        exists.setLastName(student.getLastName());
        exists.setaClass(student.getaClass());
        exists.setNationality(student.getNationality());
        return studentRepository.save(exists);
    }
}
