package com.etiqa.assessment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiqa.assessment.custom.exception.BusinessException;
import com.etiqa.assessment.custom.exception.ControllerException;
import com.etiqa.assessment.entity.Student;
import com.etiqa.assessment.service.StudentServiceInterface;

@RestController
@RequestMapping("/etiqa")
public class StudentController {
	
	@Autowired
	private StudentServiceInterface studentServiceInterface;
		
	@PostMapping("/save")
	public ResponseEntity<Student> addStudent(@RequestBody Student student){
		Student studentSaved = studentServiceInterface.addStudent(student);
		return new ResponseEntity<Student>(studentSaved, HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllStudents() {	
		try {
			List<Student> listOfStudents = studentServiceInterface.getAllStudents();
			return new ResponseEntity<List<Student>>(listOfStudents, HttpStatus.OK);
		}catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			ControllerException ce = new ControllerException("610","Something went wrong in Controller layer");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/student/{sid}")
	public ResponseEntity<Student> getStudentById(@PathVariable("sid") Long sidL) {		
		Student stdRetrieved = studentServiceInterface.getStudentById(sidL);
		return new ResponseEntity<Student>(stdRetrieved, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{sid}")
	public ResponseEntity<Void> deleteStudentById(@PathVariable("sid") Long sidL) {		
		studentServiceInterface.deleteStudentById(sidL);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Student> updateStudent(@RequestBody Student student){
		Student studentSaved = studentServiceInterface.addStudent(student);
		return new ResponseEntity<Student>(studentSaved, HttpStatus.CREATED);
	}
}
