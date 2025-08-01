package com.example.demo.feeservice;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.demo.model.Enrollment;
import com.example.demo.model.EnrollmentCourseDTO;

@FeignClient(name="enrollment-service")
public interface EnrollmentClient {

	@GetMapping("api/enrollment/student/{studentId}")
	List<Enrollment> getByStudentId(@PathVariable("studentId") Long studentId);

	@GetMapping("/enrollment/student/{studentId}/courses")
    List<EnrollmentCourseDTO> getEnrollmentsWithCourses(@PathVariable("studentId") Long studentId);


}
