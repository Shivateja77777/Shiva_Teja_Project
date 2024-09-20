package com.newproject.activity.service;

import java.util.List;

import com.newproject.activity.payload.TaskDto;

public interface TaskService {
	public TaskDto saveTask(long userId,TaskDto taskDto);
	

	public List<TaskDto> getAllTasks(long userId);
	
	public TaskDto GetTask(long users_id,long taskid);
	
	public void DeleteTask(long users_id,long taskid);

}
