package com.course.management.service.service;

import com.course.management.service.dto.StudentCourseDto;
import com.course.management.service.entity.Course;
import com.course.management.service.entity.Student;
import com.course.management.service.entity.StudentCourse;
import com.course.management.service.exception.FieldNullException;
import com.course.management.service.exception.ResourceNotFoundException;
import com.course.management.service.repository.StudentCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentCourseService {
    private StudentCourseRepository studentCourseRepository;
    private StudentService studentService;
    private CourseService courseService;

    @Autowired
    public StudentCourseService(StudentCourseRepository studentCourseRepository, StudentService studentService, CourseService courseService) {
        this.studentCourseRepository = studentCourseRepository;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public StudentCourse registerStudentWithCourse(StudentCourseDto studentCourseDto)  {
        if (studentCourseDto.getCourseId()== null || studentCourseDto.getStudentEnrollmentId()==null){
           throw new FieldNullException("Please enter both course id and student enrollment id both to continue");
        }
        Student studentByEnrollmentId = studentService.getStudentByEnrollmentId(studentCourseDto.getStudentEnrollmentId());
        Course courseById = courseService.getCourseById(studentCourseDto.getCourseId());

        StudentCourse studentCourse= new StudentCourse();
        studentCourse.setStudent(studentByEnrollmentId);
        studentCourse.setCourse(courseById);

        return studentCourseRepository.save(studentCourse);
    }

    public List<StudentCourse> unregisterStudentFronCourse(StudentCourseDto studentCourseDto){
        if (studentCourseDto.getCourseId()== null || studentCourseDto.getStudentEnrollmentId()==null){
            throw new FieldNullException("Please enter both course id and student enrollment id to continue");
        }

        Student studentByEnrollmentId = studentService.getStudentByEnrollmentId(studentCourseDto.getStudentEnrollmentId());
        Course courseById = courseService.getCourseById(studentCourseDto.getCourseId());

        List<StudentCourse> byStudentAndCourse = studentCourseRepository.findByStudentAndCourse(studentByEnrollmentId, courseById);
        byStudentAndCourse.forEach(e->{
           e.setEnabled(false);
        });
        return studentCourseRepository.saveAll(byStudentAndCourse);
    }

    public List<Course> getAllCoursesByStudent(Long studentEnrollmentId){
        Student studentByEnrollmentId = studentService.getStudentByEnrollmentId(studentEnrollmentId);
        List<StudentCourse> byStudentAndEnabled = studentCourseRepository.findByStudentAndEnabled(
                studentByEnrollmentId, Boolean.TRUE);
        if (byStudentAndEnabled == null){
            throw new ResourceNotFoundException("No course found for this student enrollment id");
        }

        return byStudentAndEnabled.stream().map(StudentCourse::getCourse).collect(Collectors.toList());
    }

    public List<Student> getAllStudentByCourse(Long courseId){
        Course courseById = courseService.getCourseById(courseId);
        List<StudentCourse> byCourseAndEnabled = studentCourseRepository.findByCourseAndEnabled(courseById, Boolean.TRUE);
        if (byCourseAndEnabled == null){
            throw new ResourceNotFoundException(" No Student found for this courseId");
        }
        return byCourseAndEnabled.stream().map(StudentCourse::getStudent).collect(Collectors.toList());
    }
}
