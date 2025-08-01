package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.model.Student;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendWelcomeEmail(Student student) {
		
		String studentName=student.getName();
		String studentEmail=student.getEmail();
		String studentPhone=student.getPhone();
		String studentAddress=student.getAddress();
		Long rollNo=student.getId();
		
		SimpleMailMessage message=new SimpleMailMessage();
		message.setTo(studentEmail);
		message.setSubject("Registeration Successfull welcome to IT-Info Academy!");
		message.setText("\nDear "+studentName+" \n\nThank you for registering with us!\n\nFollowing are important details"
				+ " about you keep them for future reference.\n\nRoll No:"+rollNo+"\nPhone No:"+studentPhone+"\nAddress:"+studentAddress+
				"\n\nHappy Learning :) \n\nRegards,\nIT-Info Academy,\nSangli.\nPhone: 8830257210 | 8007633732\nAddress: 2nd floor blue sapphire building near KFC vijaynagar, Sangli");
		
		mailSender.send(message);
	}
	

}
