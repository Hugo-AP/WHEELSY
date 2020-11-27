package com.hacknet.wheelsy.domain.repository;

import com.hacknet.wheelsy.domain.model.Accessories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessoriesRepository extends JpaRepository<Accessories,Long> {
}