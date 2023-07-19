package com.project.repository;


import com.project.model.MealUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealUserRepository extends JpaRepository<MealUser,Long> {

}
