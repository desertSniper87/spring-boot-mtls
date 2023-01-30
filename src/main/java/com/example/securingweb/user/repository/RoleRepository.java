package com.example.securingweb.user.repository;

import java.util.Optional;

import com.example.securingweb.user.models.ERole;
import com.example.securingweb.user.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
