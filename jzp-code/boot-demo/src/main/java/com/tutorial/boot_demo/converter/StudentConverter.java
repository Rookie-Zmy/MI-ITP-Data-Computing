package com.tutorial.boot_demo.converter;

import com.tutorial.boot_demo.dao.Student;
import com.tutorial.boot_demo.dto.StudentDTO;

public class StudentConverter {

    public static StudentDTO convertStudent(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setEmail(student.getEmail());
        return studentDTO;
    }

    public static Student convertStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        return student;
    }
}
