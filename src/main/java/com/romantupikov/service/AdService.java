package com.romantupikov.service;

import com.romantupikov.entity.Ad;
import com.romantupikov.repository.AdRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdService {

    private final AdRepository repository;

    public AdService(AdRepository repository) {
        this.repository = repository;
    }

    public Ad findById(String id) {
        return repository.findById(id).orElse(null);
    }

    public List<Ad> findByCategoryId(String categoryId) {
        return repository.findByCategoryId(categoryId);
    }

    public List<Ad> findByCompanyId(String companyId) {
        return repository.findByCompanyId(companyId);
    }

    public List<Ad> findAll() {
        return repository.findAll();
    }

    public void add(Ad ad) {
        repository.save(ad);
    }

    public void update(Ad ad) {
        Optional<Ad> optionalCategory = repository.findById(ad.getId());
        if (optionalCategory.isPresent()) {
            repository.save(ad);
        }
    }

    public void delete(Ad ad) {
        repository.delete(ad);
    }
}