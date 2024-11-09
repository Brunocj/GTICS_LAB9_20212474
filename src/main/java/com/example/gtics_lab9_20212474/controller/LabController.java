package com.example.gtics_lab9_20212474.controller;

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
    public LabController() {
    }

    @GetMapping({"/mealCategories"})
    public String showCocktails(Model model) {
        List<List<String>> categories = this.apiController.listar();
        model.addAttribute("categories", categories);
        return "categories";
    }

    /*@GetMapping({"/info"})
    public String showCocktails(Model model, String id) {
        List<String>cocktail = this.apiController.info(id);
        System.out.println(cocktail);
        model.addAttribute("cocktail", cocktail);
        boolean isfav = favDrinkRepository.existsByIdDrink(id);
        model.addAttribute("isFavourite", isfav);

        return "cocktail";
    }
    @PostMapping({"/addFav"})
    public String addFav(@RequestParam List<String> favcock, RedirectAttributes redirectAttributes, Model model){
        Favdrink favdrink = new Favdrink();
        favdrink.setStrDrink(favcock.get(0));
        favdrink.setStrDrinkThumb(favcock.get(1));
        favdrink.setStrCategory(favcock.get(2));
        favdrink.setStrAlcoholic(favcock.get(3));
        favdrink.setStrGlass(favcock.get(4));
        favdrink.setStrInstructions(favcock.get(5));
        favdrink.setStrIngredient1(favcock.get(6));
        favdrink.setStrMeasure1(favcock.get(7));
        favdrink.setStrIngredient2(favcock.get(8));
        favdrink.setStrMeasure2(favcock.get(9));
        favdrink.setStrIngredient3(favcock.get(10));
        favdrink.setStrMeasure3(favcock.get(11));
        favdrink.setStrIngredient4(favcock.get(12));
        favdrink.setIdDrink(favcock.get(13));
        favDrinkRepository.save(favdrink);

        redirectAttributes.addFlashAttribute("showModal", true);
        model.addAttribute("isFavourite", true);

        return "redirect:/info?id=" + favcock.get(13);
    }*/
}
