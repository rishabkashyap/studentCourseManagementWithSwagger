package com.course.management.service.controller;

import com.course.management.service.entity.Course;
import com.course.management.service.entity.Student;
import com.course.management.service.service.CourseService;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "save course",notes = "save the course details",response = Course.class)
    public Course saveCourse(@RequestBody Course course){
        return courseService.saveCourse(course);
    }

    @GetMapping("/getCourseById/{Id}")
    @ApiOperation(value = "fetch course by courseId",notes = "find the course by courseId",response = Course.class)
    public Course getCourseById(@PathVariable("Id")Long Id){
        return courseService.getCourseById(Id);
    }

    @DeleteMapping("/deleteCourse/{courseId}")
    @ApiOperation(value = "delete course by courseId",notes = "delete the course details from courseId",response = Course.class)
    public void deleteCourse(@PathVariable("courseId")Long courseId){
        courseService.deleteCourse(courseId);
    }

    @PutMapping("/updateCourseDetails/{courseId}")
    @ApiOperation(value = "update course data",notes = "update the course details",response = Course.class)
    public Course updateCourse(@PathVariable("courseId")Long courseId,@RequestBody Course course){
        return courseService.updateCourse(courseId,course);
    }
}
