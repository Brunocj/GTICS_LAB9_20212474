package com.example.gtics_lab9_20212474.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "favmeal")
public class Favmeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfavMeal", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "strMeal", nullable = false)
    private String strMeal;

    @Lob
    @Column(name = "strMealThumb", nullable = false)
    private String strMealThumb;

    @Lob
    @Column(name = "strCategory", nullable = false)
    private String strCategory;

    @Lob
    @Column(name = "strArea", nullable = false)
    private String strArea;

    @Lob
    @Column(name = "strTags", nullable = false)
    private String strTags;

    @Lob
    @Column(name = "strInstructions", nullable = false)
    private String strInstructions;

    @Lob
    @Column(name = "strIngredient1", nullable = false)
    private String strIngredient1;

    @Lob
    @Column(name = "strMeasure1", nullable = false)
    private String strMeasure1;

    @Lob
    @Column(name = "strIngredient2", nullable = false)
    private String strIngredient2;

    @Lob
    @Column(name = "strMeasure2", nullable = false)
    private String strMeasure2;

    @Lob
    @Column(name = "strIngredient3", nullable = false)
    private String strIngredient3;

    @Lob
    @Column(name = "strMeasure3", nullable = false)
    private String strMeasure3;

    @Lob
    @Column(name = "strIngredient4", nullable = false)
    private String strIngredient4;

    @Lob
    @Column(name = "idMeal", nullable = false)
    private String idMeal;

}