package com.etiqa.assessment.service;

import java.util.List;

import com.etiqa.assessment.entity.Student;

public interface StudentServiceInterface {

	public Student addStudent(Student student);

	public List<Student> getAllStudents();

	public Student getStudentById(Long sidL);

	public void deleteStudentById(Long sidL);

}
