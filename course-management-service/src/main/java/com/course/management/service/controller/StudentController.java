package com.course.management.service.controller;

import com.course.management.service.entity.Course;
import com.course.management.service.entity.Student;
import com.course.management.service.service.StudentService;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "save student data ",notes = "save the student records",response = Student.class)
    public Student saveStudent(@RequestBody Student student){
        return studentService.saveStudent(student);
    }

    @GetMapping("/getStudentByEnrollmentId/{enrollmentId}")
    @ApiOperation(value = "fetch student by enrollmentId",notes = "find the student by enrollment Id",response = Student.class)
    public Student getStudnetByEnrollmentId(@PathVariable("enrollmentId")Long enrollmentId){
        return studentService.getStudentByEnrollmentId(enrollmentId);
    }

    @DeleteMapping("/deleteStudent/{enrollmentId}")
    @ApiOperation(value = "delete student by enrollmentId",notes = "delete student by enrollmentId",response = Student.class)
    public void deleteStudent(@PathVariable("enrollmentId")Long enrollmentId){
        studentService.deleteStudent(enrollmentId);
    }

    @PutMapping("/updateStudentDetails/{enrollmentId}")
    @ApiOperation(value = "update student by enrollmentId",notes = "update the student by enrollmentId",response = Student.class)
    public Student updateStudentDetails(@PathVariable("enrollmentId")Long enrollmentId,@RequestBody Student student){
        return studentService.updateStudent(enrollmentId,student);
    }
}
