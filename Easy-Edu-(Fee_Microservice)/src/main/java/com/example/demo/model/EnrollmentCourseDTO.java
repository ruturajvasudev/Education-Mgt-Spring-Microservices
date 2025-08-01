package com.example.demo.model;
//In fee-service

public class EnrollmentCourseDTO {
 private String courseName;
 private String courseCode;
 private Double courseFee;
 private Long enrollmentId;
 private Long courseId;
 private String remark;
 private String status;

 public String getCourseCode() {
	return courseCode;
}

 public void setCourseCode(String courseCode) {
	this.courseCode = courseCode;
 }

 public EnrollmentCourseDTO() {}

 public String getCourseName() {
     return courseName;
 }

 public void setCourseName(String courseName) {
     this.courseName = courseName;
 }

 public Double getCourseFee() {
     return courseFee;
 }

 public void setCourseFee(Double courseFee) {
     this.courseFee = courseFee;
 }

 public Long getEnrollmentId() {
     return enrollmentId;
 }

 public void setEnrollmentId(Long enrollmentId) {
     this.enrollmentId = enrollmentId;
 }

 public Long getCourseId() {
     return courseId;
 }

 public void setCourseId(Long courseId) {
     this.courseId = courseId;
 }

 public String getRemark() {
     return remark;
 }

 public void setRemark(String remark) {
     this.remark = remark;
 }

 public String getStatus() {
     return status;
 }

 public void setStatus(String status) {
     this.status = status;
 }
}

