package com.hacknet.wheelsy.service;

import com.hacknet.wheelsy.domain.model.Entrepreneur;
import com.hacknet.wheelsy.domain.repository.EntrepreneurRepository;
import com.hacknet.wheelsy.domain.repository.UserRepository;
import com.hacknet.wheelsy.domain.service.EntrepreneurService;
import com.hacknet.wheelsy.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntrepreneurServicelmpl implements EntrepreneurService {
    @Autowired
    private EntrepreneurRepository entrepreneurRepository;

    @Override
    public Page<Entrepreneur> getAllEntrepreneurs(Pageable pageable) {
        return entrepreneurRepository.findAll(pageable);
    }


    @Override
    public Entrepreneur getEntrepreneurById(Long entrepreneurId) {
        return entrepreneurRepository.findById(entrepreneurId).orElseThrow(()->new ResourceNotFoundException("Entrepreneur","Id", entrepreneurId));
    }

    @Override
    public Entrepreneur createEntrepreneur(Entrepreneur entrepreneur) {
        return entrepreneurRepository.save(entrepreneur);
    }

    @Override
    public Entrepreneur updateEntrepreneur(Long entrepreneurId, Entrepreneur entrepreneurDetails) {
        return entrepreneurRepository.findById(entrepreneurId).map(entrepreneur -> {

        entrepreneur.setName(entrepreneurDetails.getName());
        entrepreneur.setEmail(entrepreneurDetails.getEmail());
        entrepreneur.setPhone(entrepreneurDetails.getPhone());
        entrepreneur.setRuc(entrepreneurDetails.getRuc());
        return entrepreneurRepository.save(entrepreneur);
        }).orElseThrow(()-> new ResourceNotFoundException("Entrepreneur","Id",entrepreneurId));

    }

    @Override
    public ResponseEntity<?> deleteEntrepreneur(Long entrepreneurId) {
        Entrepreneur entrepreneur = entrepreneurRepository.findById(entrepreneurId).
                orElseThrow(()-> new ResourceNotFoundException("Specialist", "Id", entrepreneurId));
        entrepreneurRepository.delete(entrepreneur);
        return ResponseEntity.ok().build();

    }
}
