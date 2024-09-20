package com.newproject.activity.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newproject.activity.entity.Task;
import com.newproject.activity.entity.Users;
import com.newproject.activity.exception.ApiException;
import com.newproject.activity.exception.TaskNotFound;
import com.newproject.activity.exception.UserNotFound;
import com.newproject.activity.payload.TaskDto;
import com.newproject.activity.repository.TaskRepository;
import com.newproject.activity.repository.UserRepository;
import com.newproject.activity.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TaskRepository taskRepository;

	@Override
	public TaskDto saveTask(long users_id, TaskDto taskDto) {
		Users user= userRepository.findById(users_id).orElseThrow(
				()-> new UserNotFound(String.format("User_Id %d not found", users_id))
				);
		Task task = modelMapper.map(taskDto,Task.class);
		 task.setUsers(user);
		Task saveTask = taskRepository.save(task);
		return modelMapper.map(saveTask,TaskDto.class);
	}

	@Override
	public List<TaskDto> getAllTasks(long users_id) {
		userRepository.findById(users_id).orElseThrow(
				()-> new UserNotFound(String.format("User_Id %d not found", users_id))
				);
		List<Task> tasks = taskRepository.findAllByUsersId(users_id);
		return tasks.stream().map(
				task -> modelMapper.map(task, TaskDto.class)
				).collect(Collectors.toList());
				
	}

	@Override
	public TaskDto GetTask(long users_id, long taskid) {
		Users user= userRepository.findById(users_id).orElseThrow(
				()-> new UserNotFound(String.format("User_Id %d not found", users_id))
				);
		Task task = taskRepository.findById(taskid).orElseThrow(
				()-> new TaskNotFound(String.format("Task_ID %d not found", taskid)));
		if(user.getId()!=task.getUsers().getId()) {
			throw new ApiException(String.format("Task_Id %d is not belonging to user_id %d", taskid, users_id));
		}
		
		return modelMapper.map(task, TaskDto.class);
	}

	@Override
	public void DeleteTask(long users_id, long taskid) {
		Users user= userRepository.findById(users_id).orElseThrow(
				()-> new UserNotFound(String.format("User_Id %d not found", users_id))
				);
		Task task = taskRepository.findById(taskid).orElseThrow(
				()-> new TaskNotFound(String.format("Task_ID %d not found", taskid)));
		if(user.getId()!=task.getUsers().getId()) {
			throw new ApiException(String.format("Task_Id %d is not belonging to user_id %d", taskid, users_id));
		}
		
		taskRepository.deleteById(taskid);
	
		
	}

}
