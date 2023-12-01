package com.example.bitlab_spring_trelllo.repository;

import com.example.bitlab_spring_trelllo.model.Users;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findAllByEmail(String email);
}
