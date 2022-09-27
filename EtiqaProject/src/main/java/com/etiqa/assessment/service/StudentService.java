package com.etiqa.assessment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiqa.assessment.custom.exception.BusinessException;
import com.etiqa.assessment.custom.exception.EmptyInputException;
import com.etiqa.assessment.entity.Student;
import com.etiqa.assessment.repos.StudentCrudRepo;

@Service
public class StudentService implements StudentServiceInterface{

	@Autowired
	private StudentCrudRepo crudRepo;

	@Override
	public Student addStudent(Student student) {
		
		if(student.getName().isEmpty() || student.getName().length()==0) {
			throw new EmptyInputException("601","Input fields are empty");
		}
//		try {
			Student savedStudent = crudRepo.save(student);
			return savedStudent;
//		}catch (IllegalArgumentException e) {
//			throw new BusinessException("602","given employee is null" + e.getMessage());
//		}
//		catch (Exception e) {
//			throw new BusinessException("603","Something went wrong in service layer" + e.getMessage());
//		}
		
	}

	@Override
	public List<Student> getAllStudents() {
		List<Student> studentsList = null;
		try{
			studentsList = crudRepo.findAll();
		}catch (Exception e) {
			throw new BusinessException("605","Something went wrong in service layer while fetching from db");
		}
		if(studentsList.isEmpty())
			throw new BusinessException("604","List is empty, we have nothing to return");
		return studentsList;
	}

	@Override
	public Student getStudentById(Long sidL) {
		return crudRepo.findById(sidL).get();
	}

	@Override
	public void deleteStudentById(Long sidL) {
		crudRepo.deleteById(sidL);
		
	}

}
