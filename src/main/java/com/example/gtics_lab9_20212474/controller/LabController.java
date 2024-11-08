package com.example.gtics_lab9_20212474.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LabController {
    @Autowired
    ApiController apiController;

    public LabController() {
    }

    @GetMapping({"/cocktails"})
    public String showCocktails(Model model) {
        List<List<String>> cocktails = this.apiController.listar();
        model.addAttribute("cocktails", cocktails);
        return "cocktails";
    }
}
