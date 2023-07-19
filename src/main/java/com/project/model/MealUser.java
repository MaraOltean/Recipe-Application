package com.project.model;

import com.project.model.enums.Gender;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.List;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MealUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private int age;
    private double height;
    private double weight;
    private double activityFactor;
    private String diseaseName;
    private double bmi;
    private double necessaryCalories;
    private List<Integer> mealPlanIDs;



}
