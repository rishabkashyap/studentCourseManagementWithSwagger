package com.course.management.service.service;

import com.course.management.service.entity.Course;
import com.course.management.service.exception.ResourceNotFoundException;
import com.course.management.service.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course saveCourse(Course course){
        return courseRepository.save(course);
    }

    public void deleteCourse(Long courseId){
        courseRepository.deleteById(courseId);
    }

    public Course updateCourse(Long courseId, Course course){
        Course courseToBeupdate = courseRepository.findById(courseId).orElseThrow(() ->
                new ResourceNotFoundException("Requseted course id Not Found"));

        if (course.getCourseName()!=null){
            courseToBeupdate.setCourseName(course.getCourseName());
        }
        if (course.getInstructorName()!=null){
            courseToBeupdate.setInstructorName(course.getInstructorName());        }
        if (course.getCredits()!=0){
            courseToBeupdate.setCredits(course.getCredits());
        }

        return courseRepository.save(courseToBeupdate);
    }

    public Course getCourseById(Long courseId){
        return courseRepository.findById(courseId).orElseThrow(() ->
                new ResourceNotFoundException("Requseted course id Not Found,Please Enter differentId"));
    }
}
