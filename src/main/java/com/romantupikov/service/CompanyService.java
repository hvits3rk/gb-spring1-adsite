package com.romantupikov.service;

import com.romantupikov.entity.Ad;
import com.romantupikov.entity.Company;
import com.romantupikov.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository repository;

    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    public Company findById(String companyId) {
        return repository.findById(companyId).orElse(null);
    }

    public Company findByName(String name) {
        return repository.findByName(name).orElse(null);
    }

    public List<Company> findAll() {
        return repository.findAll();
    }

    public Company findByAd(Ad ad) {
        return findById(ad.getCompany().getId());
    }

    public void add(Company company) {
        repository.save(company);
    }

    public void update(Company company) {
        Optional<Company> optionalCategory = repository.findById(company.getId());
        if (optionalCategory.isPresent()) {
            repository.save(company);
        }
    }

    public void delete(Company company) {
        repository.delete(company);
    }
}
