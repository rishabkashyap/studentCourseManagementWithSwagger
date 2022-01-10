package com.course.management.service.controller;

import com.course.management.service.entity.Student;
import com.course.management.service.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/student")
public class StudentController {
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/save")
    public Student saveStudent(@RequestBody Student student){
        return studentService.saveStudent(student);
    }

    @GetMapping("/getStudentByEnrollmentId/{enrollmentId}")
    public Student getStudnetByEnrollmentId(@PathVariable("enrollmentId")Long enrollmentId){
        return studentService.getStudentByEnrollmentId(enrollmentId);
    }

    @DeleteMapping("/deleteStudent/{enrollmentId}")
    public void deleteStudent(@PathVariable("enrollmentId")Long enrollmentId){
        studentService.deleteStudent(enrollmentId);
    }

    @PutMapping("/updateStudentDetails/{enrollmentId}")
    public Student updateStudentDetails(@PathVariable("enrollmentId")Long enrollmentId,@RequestBody Student student){
        return studentService.updateStudent(enrollmentId,student);
    }
}
