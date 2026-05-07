package com.smit.ClinixHub.repository;

import com.smit.ClinixHub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
