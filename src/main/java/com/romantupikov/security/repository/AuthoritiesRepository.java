package com.romantupikov.security.repository;

import com.romantupikov.security.entity.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepository extends JpaRepository<Authorities, String> {
}
