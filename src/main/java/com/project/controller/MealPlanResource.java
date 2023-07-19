package com.project.controller;

import com.project.model.MealPlan;
import com.project.service.MealPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MealPlanResource {
    private final MealPlanService mealPlanService;
    @Autowired
    public MealPlanResource(MealPlanService mealPlanService) {
        this.mealPlanService = mealPlanService;
    }
    @PostMapping("/add")
    public MealPlan addMealPlan(@RequestBody MealPlan mealPlan){
       MealPlan serviceMealPlane = mealPlanService.createMealPlan(mealPlan);
      return serviceMealPlane;
    }
    @GetMapping("/all")
    public List<MealPlan> getMealPlans(){
       return mealPlanService.getMealPlans();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMealPlan(@PathVariable("id") Long id){
        mealPlanService.deleteMealPlan(id);
    }

    @PutMapping("/update")
    public MealPlan updateMealPlan(@RequestBody MealPlan mealPlan){
        return mealPlanService.updateMealPlan(mealPlan);
    }

    @GetMapping("/find/{id}")
    public Optional<MealPlan> findMealPlanById(@PathVariable("id") Long id){
       return mealPlanService.findMealPlanById(id);
    }
}
