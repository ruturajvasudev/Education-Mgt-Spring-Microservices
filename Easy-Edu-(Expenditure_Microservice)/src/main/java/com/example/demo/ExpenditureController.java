package com.example.demo;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ExpenditureController {

    @Autowired
    private ExpenditureService service;

    @Autowired
    private ExpenditureRepository repo;

    // ✅ REST endpoint to expose expenditures for other apps
    @GetMapping("/api/expenditures")
    @ResponseBody
    public List<Expenditure> getAllExpendituresAsJson() {
        return service.getAll();
    }

    // ✅ REST endpoint to add expenditures via API
    @PostMapping("/api/expenditures")
    @ResponseBody
    public Expenditure addExpenditureViaApi(@RequestBody Expenditure exp) {
        return service.save(exp);
    }

    // ✅ Thymeleaf view to show all expenditures (browser UI)
    @GetMapping("/expenditures")
    public String showExpenditureForm(Model model) {
        model.addAttribute("expenditures", repo.findAll());
        model.addAttribute("expenditure", new Expenditure());
        return "expenditures";
    }

    // ✅ Handle form submission from browser UI
    @PostMapping("/expenditures")
    public String saveExpenditureFromForm(@ModelAttribute Expenditure expenditure) {
        repo.save(expenditure);
        return "redirect:/expenditures";
    }
}
