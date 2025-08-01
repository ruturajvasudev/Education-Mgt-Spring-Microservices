package com.example.demo.studentControllers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.StudentRepository;
import com.example.demo.model.Student;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class studentDashboardControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private StudentRepository repo;
	
	@Autowired
	studentDashboardController sdash;
	
	@Test
	void registerStudentShouldAddStudentSuccessfully() throws Exception{
		
		mockMvc.perform(post("/students/save")
				.param("id","12")
				.param("name","Rutu")
				.param("email","demo@gmail.com")
				.param("phone","123")
				.param("address","xyz"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/students/register?success"));
				
			List<Student> students=repo.findAll();
			assertFalse(students.isEmpty());
			
		
	System.out.println("Hello I am Test");
	}
	
	@Test
	void updateStudentTest() throws Exception{
		Student student1=new Student();
		student1.setName("Arjun Malik");
		student1.setPhone("789456");
		student1.setEmail("abc@a.com");
		student1.setAddress("ABC");
		
		repo.save(student1);
		
		List<Student> res=repo.findByNameContainingIgnoreCaseOrPhoneContaining("Arjun", "Arjun");
		String name=res.get(0).getName();
		Long id=res.get(0).getId();
		
		student1.setName("Arjun Kappor");
	
		
		repo.save(student1);
		
		res=repo.findByNameContainingIgnoreCaseOrPhoneContaining("789456", "789456");
		String isup=res.get(0).getName();
		System.out.println(isup);
		assertTrue(isup.equals("Arjun Kappor"));
	}

	@Test
	void studentSearchByName() throws Exception{
		Student student1=new Student();
		student1.setName("Arjun Malik");
		student1.setPhone("789456");
		student1.setEmail("abc@a.com");
		student1.setAddress("ABC");
		
		Student student2=new Student();
		student2.setName("MAHESH Malik");
		student2.setPhone("123456");
		student2.setEmail("pqr@a.com");
		student2.setAddress("pqr");
		
		repo.save(student1);
		repo.save(student2);
		
		List<Student> result=repo.findByNameContainingIgnoreCaseOrPhoneContaining("Arjun", "Arjun");
		
		assertFalse(result.isEmpty());
		assertTrue(result.stream().anyMatch(s->s.getEmail().equalsIgnoreCase("abc@a.com")));
		
		
		
	}
	
	
	@Test
	void dashboardShouldOpen() throws Exception{
		
		mockMvc.perform(get("/students/student_dashboard"))
				.andExpect(status().isOk())
				.andExpect(view().name("studentdashboard"));
		
	}
}
