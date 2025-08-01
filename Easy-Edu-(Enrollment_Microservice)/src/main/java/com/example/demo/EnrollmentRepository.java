package com.example.demo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment,Long>{
	@Query("SELECT e FROM Enrollment e WHERE e.student_id = :studentId")
	List<Enrollment> findByStudentId(@Param("studentId") Long studentId);

	@Query(
		    value = "SELECT c.name as courseName, c.fees as courseFee, e.id as enrollmentId, c.code as courseCode ,c.id as courseId, e.remark as remark, e.status as status " +
		            "FROM enrollment e JOIN course c ON e.course_id = c.id " +
		            "WHERE e.student_id = :studentId",
		    nativeQuery = true
		)
		List<EnrollmentCourseInfo> findEnrollmentsWithCourseInfo(@Param("studentId") Long studentId);


}
