package com.project.service;

import com.project.model.MealPlan;
import com.project.repository.MealPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MealPlanService {
    private final MealPlanRepository mealPlanRepository;
    @Autowired
    public MealPlanService(MealPlanRepository mealPlanRepository) {
        this.mealPlanRepository = mealPlanRepository;
    }

    public MealPlan createMealPlan(MealPlan mealPlan){
        double menuCalories = totalKcal(mealPlan);
        mealPlan.setTotalKcal(menuCalories);
        return mealPlanRepository.save(mealPlan);
    }
    public List<MealPlan> getMealPlans(){
        return mealPlanRepository.findAll();
    }

    public void deleteMealPlan(Long id){
        mealPlanRepository.deleteById(id);
    }

    public MealPlan updateMealPlan(MealPlan mealPlan){
        return mealPlanRepository.save(mealPlan);
    }

    public Optional<MealPlan> findMealPlanById(Long id){
       return mealPlanRepository.findById(id);
    }

    public double totalKcal(MealPlan mealPlan){
        double menuCalories = 4 * (mealPlan.getBreakfastCarbohydrates()+ mealPlan.getLunchCarbohydrates() + mealPlan.getDinnerCarbohydrates())
                            + 4 * (mealPlan.getBreakfastProteins() + mealPlan.getLunchProteins()+mealPlan.getDinnerProteins())
                             + 9 * (mealPlan.getBreakfastLipid() + mealPlan.getLunchLipid() + mealPlan.getDinnerLipid());
        return menuCalories;
    }

}
