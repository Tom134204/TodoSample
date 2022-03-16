/**
 * 
 */
package com.springdemo.example.todo.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springdemo.example.todo.domains.TodoModel;

/**
 * @author tom
 *
 */
@Mapper
public interface TodoMapper
{
	// 全件取得
	List<TodoModel> selectAll();
	
	// id検索
	TodoModel selectById(Long id);
	
	// 登録
	//@Options(useGeneratedKeys = true)
	void insert(TodoModel todo);
	
	// 更新
	int update(TodoModel todo);
	
	// 削除
	void delete(Long id);
}
