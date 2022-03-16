package com.springdemo.example.todo.domains.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springdemo.example.todo.domains.TodoModel;
import com.springdemo.example.todo.mappers.TodoMapper;

@Service
public class TodoService
{
	@Autowired	
	private TodoMapper toDoMapper;
	
	// 全件取得
	// TransactionalアノテーションはDIインジェクションされたクラスから直接コールされる
	// メソッドにつける
	@Transactional
	public List<TodoModel> selectAll()
	{
		return this.toDoMapper.selectAll();
	}
	
	// 1件取得
	@Transactional
	public TodoModel selectById(Long id)
	{
		return this.toDoMapper.selectById(id);
	}
	
	// 登録
	@Transactional
	public void insert(TodoModel todo)
	{
		this.toDoMapper.insert(todo);
	}
	
	// 更新
	@Transactional
	public int update(TodoModel todo)	
	{
		return this.toDoMapper.update(todo);
	}
	
	// 削除
	@Transactional
	public void delete(Long id)
	{
		this.toDoMapper.delete(id);
	}
	
}
