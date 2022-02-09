package com.rodrigues.rodrigues.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rodrigues.rodrigues.model.entities.User;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Long>{

    Optional<User> findByEmail(String email);

}
