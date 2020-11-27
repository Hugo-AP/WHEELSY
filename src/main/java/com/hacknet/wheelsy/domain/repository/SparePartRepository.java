package com.hacknet.wheelsy.domain.repository;

import com.hacknet.wheelsy.domain.model.SparePart;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SparePartRepository extends JpaRepository<SparePart,Long> {
}