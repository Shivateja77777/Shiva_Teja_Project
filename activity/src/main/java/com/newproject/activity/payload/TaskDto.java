package com.newproject.activity.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDto {
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getTaskname() {
		return taskname;
	}
	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}
	private long Id;
	private String taskname;

}
