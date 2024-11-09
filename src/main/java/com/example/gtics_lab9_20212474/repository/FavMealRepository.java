package com.example.gtics_lab9_20212474.repository;

import com.example.gtics_lab9_20212474.entity.Favmeal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavMealRepository extends JpaRepository<Favmeal, Integer> {
    boolean existsByIdMeal(String IdMeal);

}
