package com.hacknet.wheelsy.domain.service;

import com.hacknet.wheelsy.domain.model.Entrepreneur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface EntrepreneurService {
    Page<Entrepreneur> getAllEntrepreneurs(Pageable pageable);
    Entrepreneur getEntrepreneurById(Long entrepreneurId);
    Entrepreneur createEntrepreneur(Entrepreneur entrepreneur);
    Entrepreneur updateEntrepreneur(Long entrepreneurId, Entrepreneur entrepreneurDetails);
    ResponseEntity<?> deleteEntrepreneur(Long entrepreneurId);
}
