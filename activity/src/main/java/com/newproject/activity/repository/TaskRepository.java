package com.newproject.activity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.newproject.activity.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

	List<Task> findAllByUsersId(long userId);

}
