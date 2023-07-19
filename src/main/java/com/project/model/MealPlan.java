package com.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MealPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;
    private String breakfastHour;
    private double breakfastQuantity;
    private double breakfastProteins;
    private double breakfastCarbohydrates;
    private double breakfastLipid;
    private String lunchHour;
    private double lunchQuantity;
    private double lunchProteins;
    private double lunchCarbohydrates;
    private double lunchLipid;
    private String dinnerHour;
    private double dinnerQuantity;
    private double dinnerProteins;
    private double dinnerCarbohydrates;
    private double dinnerLipid;
    private double totalKcal;


}
