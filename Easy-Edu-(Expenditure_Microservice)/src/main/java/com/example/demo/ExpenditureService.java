package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenditureService {
    @Autowired
    private ExpenditureRepository repo;

    public List<Expenditure> getAll() {
        return repo.findAll();
    }

    public Expenditure save(Expenditure exp) {
        return repo.save(exp);
    }
}
