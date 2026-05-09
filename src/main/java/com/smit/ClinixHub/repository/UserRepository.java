package com.smit.ClinixHub.repository;

import com.smit.ClinixHub.entity.User;
import com.smit.ClinixHub.entity.types.AuthProviderType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByProviderIdAndProviderType(String providerId, AuthProviderType providerType);

}
