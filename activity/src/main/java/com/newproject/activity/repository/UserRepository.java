package com.newproject.activity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.newproject.activity.entity.Users;

public interface UserRepository extends JpaRepository<Users,Long>{

}
