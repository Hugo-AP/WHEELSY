package com.hacknet.wheelsy.domain.repository;

import com.hacknet.wheelsy.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {

}
