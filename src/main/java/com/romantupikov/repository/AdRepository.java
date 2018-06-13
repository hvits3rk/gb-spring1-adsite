package com.romantupikov.repository;

import com.romantupikov.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdRepository extends JpaRepository<Ad, String> {

    List<Ad> findByCategoryId(String categoryId);

    List<Ad> findByCompanyId(String companyId);
}
