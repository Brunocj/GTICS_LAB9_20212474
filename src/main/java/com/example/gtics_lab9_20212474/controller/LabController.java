package com.example.gtics_lab9_20212474.controller;

import com.example.gtics_lab9_20212474.entity.Favmeal;
import com.example.gtics_lab9_20212474.repository.FavMealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class LabController {
    @Autowired
    ApiController apiController;
    @Autowired
    FavMealRepository favMealRepository;
    public LabController() {
    }

    @GetMapping({"/mealCategories"})
    public String showCocktails(Model model) {
        List<List<String>> categories = this.apiController.listar();
        model.addAttribute("categories", categories);
        return "categories";
    }

    @GetMapping({"/favsList"})
    public String showFavs(Model model){
        List<Favmeal> favMeals = favMealRepository.findAll();
        model.addAttribute("FavMeals", favMeals);
        return "myFavs";
    }
}
