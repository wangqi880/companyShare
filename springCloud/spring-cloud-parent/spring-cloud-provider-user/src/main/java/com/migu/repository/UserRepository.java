package com.migu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.migu.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
