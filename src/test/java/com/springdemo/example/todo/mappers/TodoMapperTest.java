package com.springdemo.example.todo.mappers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import com.springdemo.example.todo.domains.TodoModel;

@MybatisTest
// @AutoConfigureTestDatabaseの指定をしないと、in-memoryデータベース(h2)が使用される
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
public class TodoMapperTest
{
	@Autowired
	private TodoMapper mapper;
	
	@Test
	@Order(1)
	public void testSelectAll()
	{
		List<TodoModel> result = this.mapper.selectAll();
		assertNotNull(result);
	}
	
	@Test
	@Order(2)
	public void testSelectById()
	{
		// ・・・以下同様にテストできる
	}
	
}
