package com.project.controller;

import com.project.model.MealUser;
import com.project.service.MealUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MealUserResource {

    private final MealUserService mealUserService;

    @Autowired
    public MealUserResource(MealUserService mealUserService) {
        this.mealUserService = mealUserService;
    }

    @PostMapping
    public ResponseEntity<String> addMealUser(@RequestBody MealUser mealUser){
        mealUserService.addUser(mealUser);
        return ResponseEntity.ok("ok");


    }
}
