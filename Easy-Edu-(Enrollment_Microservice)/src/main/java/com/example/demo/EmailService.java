package com.example.demo;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


import com.example.demo.model.*;
@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendMailConfirmation(Student student,Enrollment enroll) {
		
		SimpleMailMessage message=new SimpleMailMessage();
		
		String studentEmail=student.getEmail();
		String studentName=student.getName();
		Long studentRollno=student.getId();
		Long course_id=enroll.getCourse_id();
		LocalDate date=enroll.getDate();
		String feeStatus=enroll.getStatus();
		String addmissionRemark=enroll.getRemark();
		Long enrollmentId=enroll.getId();
		
		message.setTo(studentEmail);
		message.setSubject("Admission Confirmation!");
		message.setText(
			    "Dear " + studentName + ",\n\n" +
			    "Greetings from the ITINFO Admission Team!\n\n" +
			    "We are pleased to inform you that your admission has been successfully confirmed. Below are your enrollment details:\n\n" +
			    "Enrollment ID     : " + enrollmentId + "\n" +
			    "Student Name      : " + studentName + "\n" +
			    "Student Roll No   : " + studentRollno + "\n" +
			    "Course ID         : " + course_id + "\n" +
			    "Admission Date    : " + date + "\n" +
			    "Fee Status        : " + feeStatus + "\n" +
			    "Remarks           : " + addmissionRemark + "\n\n" +
			    "We are excited to welcome you to ITINFO Academy and wish you all the best in your academic journey.\n\n" +
			    "If you have any questions or need assistance, feel free to contact our admission desk.\n\n" +
			    "Warm regards,\n" +
			    "ITINFO Admission Team"
			);

		
		mailSender.send(message);
		
		
		
	}

}
