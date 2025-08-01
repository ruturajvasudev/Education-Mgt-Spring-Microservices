package com.example.demo.studentControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.StudentRepository;
import com.example.demo.model.Student;
import com.example.demo.*;

@Controller
@RequestMapping("/students")
public class studentDashboardController {

	@Autowired
	private StudentRepository repo;
	
	@Autowired
	EmailService emailService;
	
    @GetMapping("/student_dashboard")
    public String dashboardPage(Model model) {
        return "studentdashboard"; // mapped to studentdashboard.html
    }
    
    @GetMapping("/register")//to map register 
	public String showRegisterationPage(Model model) {
    	
    	model.addAttribute("fromupdate",false);
		model.addAttribute("student",new Student());
		
    	return "studentregisteration";
    	
	}
    
    @PostMapping("/save")
    public String saveStudent(@ModelAttribute Student student) {
    	repo.save(student);
    	emailService.sendWelcomeEmail(student);
    	return "redirect:/students/register?success";
    }
    
    @GetMapping("/view_students")
    public String displayStudents(@RequestParam(value="keyword",required=false) String keyword,Model model) {
    	List<Student> students;
    	
    	if(keyword!=null && !keyword.isEmpty()) {
    		students=repo.findByNameContainingIgnoreCaseOrPhoneContaining(keyword,keyword);
    	}else {
    		students=repo.findAll();

    	}
    	model.addAttribute("students",students);
    	model.addAttribute("keyword",keyword);
    	return "list_students";
    }
    
    @GetMapping("{id}")
    @ResponseBody
    public Student getStudentById(@PathVariable("id")Long Id) {
    	Student student=repo.findById(Id)
    			.orElseThrow(()->new IllegalArgumentException("Invalid Student Id"));
    	return student;
    }
    @GetMapping("/edit/{id}")
    public String updateStudent(@PathVariable Long id,Model model) {
    	Student student=repo.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid Student Id:"+id));
    	model.addAttribute("fromupdate",true);
    	model.addAttribute("student",student);
    	model.addAttribute("Roll",student.getId());
    	return "studentregisteration";
    }
    
    
    @GetMapping("/search_api")
    @ResponseBody// returns raw json why not model becoz its themsleaf not supported by rest api
    public List<Student> apiSearch(@RequestParam(required=false)String keyword){
    	if(keyword!=null && !keyword.isEmpty()) {
    		return repo.findByNameContainingIgnoreCaseOrPhoneContaining(keyword,keyword);
    	}else {
    		return repo.findAll();

    	}
    }
    
    @GetMapping("/get_byId/{id}")
    @ResponseBody
    public Student apiGetStudentById(@PathVariable("id")Long Id) {
    	Student student=repo.findById(Id).orElseThrow(()->new IllegalArgumentException("Invalid Student id"));
    	return student;
    }
}

