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
    @Autowired FavMealRepository favMealRepository;
    public LabController() {
    }

    @GetMapping({"/mealCategories"})
    public String showCocktails(Model model) {
        List<List<String>> categories = this.apiController.listar();
        model.addAttribute("categories", categories);
        return "categories";
    }

    @GetMapping({"/buscar"})
    public String searchMeals(Model model, @RequestParam("query") String query) {
        List<List<String>> meals = this.apiController.buscar(query);
        model.addAttribute("meals", meals);
        return "meals";
    }

    @GetMapping({"/info"})
    public String showMeals(Model model, String id) {
        List<String>meal = this.apiController.info(id);
        System.out.println(meal);
        model.addAttribute("meal", meal);
        boolean isfav = favMealRepository.existsByIdMeal(id);
        model.addAttribute("isFavorite", isfav);

        return "meal";
    }
    @PostMapping({"/addFav"})
    public String addFav(@RequestParam List<String> favmeal, RedirectAttributes redirectAttributes, Model model){
        Favmeal comidafavorita = new Favmeal();
        comidafavorita.setStrMeal(favmeal.get(0));
        comidafavorita.setStrMealThumb(favmeal.get(1));
        comidafavorita.setStrCategory(favmeal.get(2));
        comidafavorita.setStrArea(favmeal.get(3));
        comidafavorita.setStrTags(favmeal.get(4));
        comidafavorita.setStrInstructions(favmeal.get(5));
        comidafavorita.setStrIngredient1(favmeal.get(6));
        comidafavorita.setStrMeasure1(favmeal.get(7));
        comidafavorita.setStrIngredient2(favmeal.get(8));
        comidafavorita.setStrMeasure2(favmeal.get(9));
        comidafavorita.setStrIngredient3(favmeal.get(10));
        comidafavorita.setStrMeasure3(favmeal.get(11));
        comidafavorita.setStrIngredient4(favmeal.get(12));
        comidafavorita.setIdMeal(favmeal.get(13));
        System.out.println(favmeal.get(11));
        System.out.println(favmeal.get(12));
        System.out.println(favmeal.get(13));

        favMealRepository.save(comidafavorita);

        redirectAttributes.addFlashAttribute("showModal", true);
        model.addAttribute("isFavorite", true);

        return "redirect:/info?id=" + favmeal.get(13);
    }
}
