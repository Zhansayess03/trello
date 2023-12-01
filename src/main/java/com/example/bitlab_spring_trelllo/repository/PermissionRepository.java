package com.example.bitlab_spring_trelllo.repository;

import com.example.bitlab_spring_trelllo.model.Permission;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Permission findAllByRole(String role);
}
