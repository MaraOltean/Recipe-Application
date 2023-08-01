package com.project.service;

import com.project.model.MealPlan;
import com.project.model.MealUser;
import com.project.model.enums.Gender;
import com.project.repository.MealPlanRepository;
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
    private final MealPlanRepository mealPlanRepository;

    private final EntityManager entityManager;
    @Autowired
    public MealUserService(MealUserRepository mealUserRepository, MealPlanRepository mealPlanRepository, EntityManager entityManager) {
        this.mealUserRepository = mealUserRepository;
        this.mealPlanRepository = mealPlanRepository;
        this.entityManager = entityManager;
    }

    public void addUser(MealUser user){
        user.setNecessaryCalories(caloriesNeeded(user));
        if(!user.getHaveDisease()){
            nonDiseaseMealPlans(user);
        }
        else{
            withDiseaseMealPlans(user);
        }
        mealUserRepository.save(user);

    }

    private void withDiseaseMealPlans(MealUser user){
        List<MealPlan> mealPlanList = new ArrayList<>();
        mealPlanList = findByDisease(user.getDiseaseName());
        for(MealPlan mealPlan : mealPlanList){
            //adauga pe coloana de utilizatori din MealPlan utilizatorii care se potrivesc
            mealPlan.getUsers().add(user);
            //adauga pe coloana din utilizatori planurile alimentare care se potrivesc
            user.getMeals().add(mealPlan);
            mealPlanRepository.save(mealPlan);

        }
    }

    private void nonDiseaseMealPlans(MealUser user) {
        List<MealPlan> mealPlanList = new ArrayList<>();
        mealPlanList = findCaloriesRange(user.getNecessaryCalories()-100, user.getNecessaryCalories()+100);
        for(MealPlan mealPlan : mealPlanList){
            //adauga pe coloana de utilizatori din MealPlan utilizatorii care se potrivesc
            mealPlan.getUsers().add(user);
            //adauga pe coloana din utilizatori planurile alimentare care se potrivesc
            user.getMeals().add(mealPlan);
            mealPlanRepository.save(mealPlan);

        }
    }
    private List<MealPlan> findByDisease(String disease){
        String sql = "SELECT * FROM meal_plan WHERE type = :disease";
        Query query = entityManager.createNativeQuery(sql, MealPlan.class);
        query.setParameter("disease",disease);
        return query.getResultList();
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
       // Query query = entityManager.createQuery(
        String sql = "SELECT * FROM meal_plan WHERE total_kcal >= :minKcal AND total_kcal <= :maxKcal";
        Query query = entityManager.createNativeQuery(sql, MealPlan.class); //"SELECT * FROM meal_plan WHERE total_kcal >= :minKcal AND total_kcal <= :maxKcal", MealPlan.class);
        query.setParameter("minKcal", minKcal);
        query.setParameter("maxKcal", maxKcal);

        return query.getResultList();
    }
}
