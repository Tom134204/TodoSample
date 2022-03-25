package com.springdemo.example.todo.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.springdemo.example.todo.domains.services.TodoService;

// 本テストケースはSpring Boot起動/未起動に関わらず実行できる
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class TodoControllerTest
{
	
	private MockMvc mockMvc;
	private AutoCloseable autoCloseable;
	
	// Controllerから呼び出すServiceクラスのモック化
	@MockBean
	private TodoService todoService;
	
	// テスト対象Controllerクラスにモックをインジェクション
	@Autowired
	private TodoController todoController;
	
	@BeforeEach
	public void setUp() throws Exception
	{
		this.autoCloseable = MockitoAnnotations.openMocks(this);
		
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.todoController).build();
	}
	
	@AfterEach
	public void tearDown() throws Exception
	{
		if(this.autoCloseable != null)
		{
			this.autoCloseable.close();
		}
		
	}
	
	
	@Test
	@Order(1)
	public void test1() throws Exception
	{
		this.mockMvc.perform(get("/todos"))
			.andExpect(status().isOk())
			// top.htmlを返すかチェック
			.andExpect(view().name("todos/top"))
			// "todo"がセットされているかチェック
			.andExpect(model().attributeExists("todo"));
	}
	
	@Test
	@Order(2)
	public void test2() throws Exception
	{
		this.mockMvc.perform(get("/todos/new"))
			.andExpect(status().isOk())
			.andExpect(view().name("todos/new"))
			.andExpect(model().attributeExists("todo"));
	}
	
	@Test
	@Order(3)
	public void test3() throws Exception
	{
		this.mockMvc.perform(post("/todos/new")
			// inputフィールドに空文字セット
			.param("task", ""))
			// Validation Error発生のチェック
			.andExpect(model().hasErrors())
			.andExpect(view().name("todos/new"));
	}
	
	@Test
	@Order(4)
	public void test4() throws Exception
	{
		this.mockMvc.perform(post("/todos/new")
			.param("task", "unit test!"))
			.andExpect(model().hasNoErrors())
			.andExpect(view().name("redirect:/todos"));
	}
	
	// あとは同様にテストしていけば良い・・・かな？
}
