package com.example.demo;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.Course;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class EnrollCourseService {

    private final RestTemplate restTemplate;

    public EnrollCourseService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name="courseServiceBreaker",fallbackMethod="fallbackCourseMethod")
    public Course fetchCourseDetails(Long id) {
    	try {
        String url = "http://course-service/courses/" + id; //  Eureka will resolve 'course-service'
        return restTemplate.getForObject(url, Course.class);
    	}catch(Exception e) {
    		 System.out.println("Falling back due to error: " + e.getMessage());
    	        return fallbackCourseMethod(id, e);
    	}
    }
    
    public Course fallbackCourseMethod(Long id,Throwable t) {
    	Course fallbackCourse=new Course();
    	fallbackCourse.setFees((long) 0);
    	fallbackCourse.setName("Fallback Course");
    	fallbackCourse.setDuration("0 weeks");
    	fallbackCourse.setId(id);
    	
    	return fallbackCourse;
    }
    
    
}

