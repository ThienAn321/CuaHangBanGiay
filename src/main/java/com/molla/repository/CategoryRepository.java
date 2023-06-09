package com.molla.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.molla.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

}
