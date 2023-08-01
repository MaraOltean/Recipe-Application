package com.project.model;

import com.project.model.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @Column(nullable = true)
    private Boolean haveDisease;
    @Column(nullable = true) //coloana accepta valori nule
    private double necessaryCalories;
    @ManyToMany(fetch = FetchType.LAZY) //relația se va face doar atunci când acestea sunt explicit accesate în cod.
    @JoinTable(name = "meal_user_relationship",
            joinColumns = {@JoinColumn(name = "meal_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<MealPlan> meals = new HashSet<>();



}
