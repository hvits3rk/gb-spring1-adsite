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

    public Company add(Company company) {
        Optional<Company> optionalCompany = repository.findByName(company.getName());

        if (optionalCompany.isPresent() && optionalCompany.get().equals(company)) {
            return optionalCompany.get();
        }

        repository.save(company);
        return company;
    }

    public Company update(Company company) {
        Optional<Company> optionalCompany = repository.findById(company.getId());

        if (optionalCompany.isPresent()) {
            repository.save(company);
            return company;
        }

        return null;
    }

    public void delete(Company company) {
        repository.delete(company);
    }
}
