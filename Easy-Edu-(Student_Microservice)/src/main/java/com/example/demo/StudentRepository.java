package com.example.demo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.*;
public interface StudentRepository extends JpaRepository<Student,Long>{
    List<Student> findByNameContainingIgnoreCaseOrPhoneContaining(String name,String phone);

}
