package com.springdemo.example.todo.domains;

import javax.validation.constraints.NotBlank;

public class TodoModel
{
	private Long id;
	
	// Null, 空文字、半角スペースのみを許可しないアノテーション
	@NotBlank
	private String task;

	/**
	 * @return id
	 */
	public Long getId()
	{
		return id;
	}

	/**
	 * @param id セットする id
	 */
	public void setId(Long id)
	{
		this.id = id;
	}

	/**
	 * @return task
	 */
	public String getTask()
	{
		return task;
	}

	/**
	 * @param task セットする task
	 */
	public void setTask(String task)
	{
		this.task = task;
	}
	
	
}
