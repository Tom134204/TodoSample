package com.springdemo.example.todo.domains.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springdemo.example.todo.domains.TodoModel;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class TodoServiceTest
{
	@Autowired
	private TodoService todoService;

	// ※注意：データが存在してることを前提にテストしている
	// 実際にはテスト内でデータを用意し、テスト実施後にcleanする
	
	@Test
	@Order(1)
	public void testSelectAll()
	{
		List<TodoModel> result = this.todoService.selectAll();
		
		assertAll("結果確認", () -> assertNotNull(result), () -> assertTrue(result.size() > 0));
	}
	
	@Test
	@Order(2)
	public void testSelectById()
	{
		TodoModel result = this.todoService.selectById(new Long(1));
		
		assertAll("結果確認", 
					() -> assertNotNull(result), 
					() -> assertEquals(new Long(1), result.getId()),
					() -> assertTrue(result.getTask().length() > 0) );
	}
	
	@Test
	@Order(3)
	public void testInsert()
	{
		final String testTask = "Insert Test";
		
		TodoModel model = new TodoModel();
		model.setTask(testTask);
			
		assertDoesNotThrow(() -> this.todoService.insert(model));
		
		List<TodoModel> result = this.todoService.selectAll();
		
		for(TodoModel m : result)
		{
			if(m.getTask().equals(testTask))
			{
				return;
			}
		}
		
		fail("Insertテスト失敗");
	}
	
	@Test
	@Order(4)
	public void testUpdate()
	{
		final String testTask = "Update Test";
		TodoModel model = new TodoModel();
		model.setId(new Long(3)); // 存在する前提・・・
		model.setTask(testTask);
		
		assertDoesNotThrow(() -> this.todoService.update(model));
		
		TodoModel result = this.todoService.selectById(new Long(3));
		
		assertAll("結果確認", 
					() -> assertNotNull(result), 
					() -> assertEquals(new Long(3), result.getId()),
					() -> assertEquals(testTask, result.getTask()) );
	}
	
	@Test
	@Order(5)
	public void testDelete()
	{
	
		assertDoesNotThrow(() -> this.todoService.delete(new Long(17)));
	}
	
}
