package com.hacknet.wheelsy.domain.repository;

import com.hacknet.wheelsy.domain.model.Entrepreneur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrepreneurRepository extends JpaRepository<Entrepreneur,Long> {
}