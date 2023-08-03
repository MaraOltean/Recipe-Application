package com.project.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
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
    @ManyToMany(mappedBy = "meals")
    private Set<MealUser> users = new HashSet<>();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Meal ID: ").append(id).append("\n");
        sb.append("Type: ").append(type).append("\n");


        sb.append("Breakfast:\n");
        sb.append("  Hour: ").append(breakfastHour).append("\n");
        sb.append("  Quantity: ").append(breakfastQuantity).append("\n");
        sb.append("  Proteins: ").append(breakfastProteins).append("g\n");
        sb.append("  Carbohydrates: ").append(breakfastCarbohydrates).append("g\n");
        sb.append("  Lipid: ").append(breakfastLipid).append("g\n");


        sb.append("Lunch:\n");
        sb.append("  Hour: ").append(lunchHour).append("\n");
        sb.append("  Quantity: ").append(lunchQuantity).append("\n");
        sb.append("  Proteins: ").append(lunchProteins).append("g\n");
        sb.append("  Carbohydrates: ").append(lunchCarbohydrates).append("g\n");
        sb.append("  Lipid: ").append(lunchLipid).append("g\n");


        sb.append("Dinner:\n");
        sb.append("  Hour: ").append(dinnerHour).append("\n");
        sb.append("  Quantity: ").append(dinnerQuantity).append("\n");
        sb.append("  Proteins: ").append(dinnerProteins).append("g\n");
        sb.append("  Carbohydrates: ").append(dinnerCarbohydrates).append("g\n");
        sb.append("  Lipid: ").append(dinnerLipid).append("g\n");

        sb.append("Total Kcal: ").append(totalKcal).append("\n");

        return sb.toString();
    }

}
