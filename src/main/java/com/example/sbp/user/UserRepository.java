package com.example.sbp.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<SiteUser,Long> {

	Optional<SiteUser> findByUsername(String username);
}
