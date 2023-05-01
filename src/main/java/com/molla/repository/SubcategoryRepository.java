package com.molla.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.molla.entity.Subcategory;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, String> {

}
