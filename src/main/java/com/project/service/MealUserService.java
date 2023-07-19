package com.project.service;

import com.project.model.MealPlan;
import com.project.model.MealUser;
import com.project.model.enums.Gender;
import com.project.repository.MealUserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class MealUserService {
    private final MealUserRepository mealUserRepository;

    private final EntityManager entityManager;
    @Autowired
    public MealUserService(MealUserRepository mealUserRepository, EntityManager entityManager) {
        this.mealUserRepository = mealUserRepository;
        this.entityManager = entityManager;
    }

    public void addUser(MealUser user){
        user.setNecessaryCalories(caloriesNeeded(user));
        List<MealPlan> mealPlanList = new ArrayList<>();
        mealPlanList = findCaloriesRange(user.getNecessaryCalories()-100, user.getNecessaryCalories()+100);

        for(MealPlan mealPlan : mealPlanList){
            //adauga pe coloana de utilizatori din MealPlan utilizatorii care se potrivesc
            //adauga pe coloana din utilizatori planurile alimentare care se potrivesc

        }
        mealUserRepository.save(user);
    }
    public static double caloriesNeeded(MealUser user) {
        double BMR = 0;
        if (user.getGender().equals(Gender.M)) {
            BMR = 88.362 + (13.397 * user.getWeight()) + (4.799 * user.getHeight() * 100) - (5.677 * user.getAge());
        } else {
            BMR = 447.593 + (9.247 * user.getWeight()) + (3.098 * user.getHeight() * 100) - (4.330 * user.getAge());
        }
       return BMR* user.getActivityFactor();

    }

    public List<MealPlan> findCaloriesRange(double minKcal, double maxKcal){
        Query query = entityManager.createQuery("SELECT * FROM meal_plan where total_kcal >= :minKcal AND total_kcal <= :maxKcal");
        query.setParameter("minKcal", minKcal);
        query.setParameter("maxKcal", maxKcal);

        return query.getResultList();
    }
}
