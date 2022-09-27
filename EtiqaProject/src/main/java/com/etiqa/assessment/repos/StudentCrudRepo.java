package com.etiqa.assessment.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etiqa.assessment.entity.Student;

@Repository
public interface StudentCrudRepo extends JpaRepository<Student, Long>{
	

}
