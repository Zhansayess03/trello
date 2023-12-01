package com.example.bitlab_spring_trelllo.repository;

import com.example.bitlab_spring_trelllo.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UsersRepository extends JpaRepository<User, Long> {
    User findAllByEmail(String email);
}
