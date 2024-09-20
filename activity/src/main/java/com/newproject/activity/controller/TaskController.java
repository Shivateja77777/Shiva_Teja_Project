package com.newproject.activity.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newproject.activity.payload.TaskDto;
import com.newproject.activity.service.TaskService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api")

public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@PostMapping("/{users_id}/tasks")
	public ResponseEntity<TaskDto> saveTask(
			@PathVariable(name= "users_id") long userid,
			@RequestBody TaskDto taskDto){
		return new ResponseEntity<>(taskService.saveTask(userid,taskDto),HttpStatus.CREATED);
		
	}
	
	@GetMapping("/{users_id}/tasks")
	public ResponseEntity<List<TaskDto>> getAllTasks(
			@PathVariable(name="users_id") long users_id){
		return new ResponseEntity<>(taskService.getAllTasks(users_id),HttpStatus.OK);
		
	}
	@GetMapping("/{users_id}/tasks/{taskid}")
	public ResponseEntity<TaskDto> getTask(
	        @PathVariable(name="users_id") long users_id, // Removed the extra ')'
	        @PathVariable(name = "taskid") long taskid) {
	    return new ResponseEntity<>(taskService.GetTask(users_id, taskid), HttpStatus.OK);
	}
	
	@DeleteMapping("/{users_id}/tasks/{taskid}")
	public ResponseEntity<String> DeleteTask(
	        @PathVariable(name="users_id") long users_id, 
	        @PathVariable(name = "taskid") long taskid) {
		taskService.DeleteTask(users_id, taskid);
	    return new ResponseEntity<>("User Delete Successfull !!", HttpStatus.OK);
	}	

}
