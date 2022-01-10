package com.course.management.service.controller;

import com.course.management.service.dto.StudentCourseDto;
import com.course.management.service.entity.Course;
import com.course.management.service.entity.Student;
import com.course.management.service.entity.StudentCourse;
import com.course.management.service.service.StudentCourseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student/course")
public class StudentCourseController {
    private StudentCourseService studentCourseService;

    @Autowired
    public StudentCourseController(StudentCourseService studentCourseService) {
        this.studentCourseService = studentCourseService;
    }

    @PostMapping("/register")
    @ApiOperation(value = "register student with course",notes = "api to register the student with course",response = StudentCourse.class)
    public StudentCourse RegisterStudentWithCourse(@RequestBody StudentCourseDto studentCourseDto){
        return studentCourseService.registerStudentWithCourse(studentCourseDto);
    }

    @PostMapping("/deregister")
    @ApiOperation(value = "deregister student with course",notes = "api to deregister student with course")
    public List<StudentCourse> unregisterStudentFronCourse(@RequestBody StudentCourseDto studentCourseDto){
        return studentCourseService.unregisterStudentFronCourse(studentCourseDto);
    }

    @GetMapping("/getAllCoursesByStudent/{studentEnrollmentId}")
    @ApiOperation(value = "fetch all courses register with student",notes = "api to fetch all courses register with student")
    public List<Course> getAllCoursesByStudent(@PathVariable("studentEnrollmentId") Long studentEnrollmentId){
        return studentCourseService.getAllCoursesByStudent(studentEnrollmentId);
    }

    @GetMapping("/getAllStudentByCourse/{courseId}")
    @ApiOperation(value = "fetch all student register with course",notes = "api to fetch all student register with course")
    public List<Student> getAllStudentByCourse(@PathVariable("courseId") Long courseId){
        return studentCourseService.getAllStudentByCourse(courseId);
    }
}
