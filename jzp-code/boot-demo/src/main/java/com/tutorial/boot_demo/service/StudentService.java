package com.tutorial.boot_demo.service;

import com.tutorial.boot_demo.dao.Student;
import com.tutorial.boot_demo.dto.StudentDTO;

public interface StudentService {

    StudentDTO getStudentById(long id);

    Long addNewStudent(StudentDTO studentDTO);

    void deleteStudentById(long id);

    StudentDTO updateStudentById(long id, String name, String email);
}
