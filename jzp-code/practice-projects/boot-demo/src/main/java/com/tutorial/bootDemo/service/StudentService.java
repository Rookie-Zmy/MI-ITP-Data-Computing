package com.tutorial.bootDemo.service;

import com.tutorial.bootDemo.dto.StudentDTO;

public interface StudentService {

    StudentDTO getStudentById(long id);

    Long addNewStudent(StudentDTO studentDTO);

    void deleteStudentById(long id);

    StudentDTO updateStudentById(long id, String name, String email);
}
