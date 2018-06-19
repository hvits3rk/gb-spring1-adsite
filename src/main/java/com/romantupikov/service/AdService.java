package com.romantupikov.service;

import com.romantupikov.entity.Ad;
import com.romantupikov.repository.AdRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public Page<Ad> findPaginated(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Ad add(Ad ad) {
        ad.setPublishedDate(new Date());
        repository.save(ad);
        return ad;
    }

    public Ad update(Ad ad) {
        Optional<Ad> optionalCategory = repository.findById(ad.getId());

        if (optionalCategory.isPresent()) {
            ad.setPublishedDate(new Date());
            repository.save(ad);
            return ad;
        }

        return null;
    }

    public void delete(Ad ad) {
        repository.delete(ad);
    }
}
