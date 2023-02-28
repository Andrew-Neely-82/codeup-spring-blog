package com.codeup.codeupspringblog.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeup.codeupspringblog.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
  User findUserById(long id);
}