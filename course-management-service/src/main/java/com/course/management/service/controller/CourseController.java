package com.course.management.service.controller;

import com.course.management.service.entity.Course;
import com.course.management.service.entity.Student;
import com.course.management.service.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {
    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/save")
    public Course saveCourse(@RequestBody Course course){
        return courseService.saveCourse(course);
    }

    @GetMapping("/getCourseById/{Id}")
    public Course getCourseById(@PathVariable("Id")Long Id){
        return courseService.getCourseById(Id);
    }

    @DeleteMapping("/deleteCourse/{courseId}")
    public void deleteCourse(@PathVariable("courseId")Long courseId){
        courseService.deleteCourse(courseId);
    }

    @PutMapping("/updateCourseDetails/{courseId}")
    public Course updateCourse(@PathVariable("courseId")Long courseId,@RequestBody Course course){
        return courseService.updateCourse(courseId,course);
    }
}
