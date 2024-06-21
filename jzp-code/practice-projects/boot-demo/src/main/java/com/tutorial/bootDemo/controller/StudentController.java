package com.tutorial.bootDemo.controller;

import com.tutorial.bootDemo.Response;
import com.tutorial.bootDemo.dto.StudentDTO;
import com.tutorial.bootDemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/student/{id}")
    public Response<StudentDTO> getStudentByID(@PathVariable long id) {
        return Response.newSuccess(studentService.getStudentById(id));
    }

    @PostMapping("/student")
    public Response<Long> addNewStudent(@RequestBody StudentDTO studentDTO) {
        return Response.newSuccess(studentService.addNewStudent(studentDTO));
    }

    @DeleteMapping("/student/{id}")
    public void deleteStudentByID(@PathVariable long id) {
        studentService.deleteStudentById(id);
    }

    @PutMapping("/student/{id}")
    public Response<StudentDTO> updateStudentById(@PathVariable long id, @RequestParam(required = false) String name,
                                                  @RequestParam(required = false) String email) {
        return Response.newSuccess(studentService.updateStudentById(id, name, email));
    }
}
