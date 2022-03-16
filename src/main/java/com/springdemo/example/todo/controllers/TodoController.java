package com.springdemo.example.todo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springdemo.example.todo.domains.TodoModel;
import com.springdemo.example.todo.domains.services.TodoService;

// @RequestMappingをクラスに追加すると親パスとみなされる
@Controller
@RequestMapping("/todos")
public class TodoController
{
	@Autowired
	private TodoService todoService;
	
	// 全件取得したTodoをtopに表示する
	@GetMapping("")
	public String top(Model model)
	{
		model.addAttribute("todo", this.todoService.selectAll());
		
		// top画面へ遷移
		return "todos/top";
	}
	
	// topからnew画面
	// @ModelAttributeで指定した引数がnew画面のth:objectで参照される
	// @ModelAttributeで指定した引数はthymeleaf側では"クラス名を小文字にしたもの"で取得できる
	// @ModelAttribute("名前")で属性名を指定することも可能
	@GetMapping("new")
	public String newTodo(Model model, @ModelAttribute("todo") TodoModel todo)
	{
		return "todos/new";
	}
	
	@PostMapping("new")
	public String create(@Validated @ModelAttribute("todo") TodoModel todo, BindingResult result)
	{
		// validationに引っかかるとnew画面のまま
		if(result.hasErrors())
		{
			return "todos/new";
		}
		
		this.todoService.insert(todo);
		
		// topに戻る
		return "redirect:/todos";
	}
	
	// 1件分のデータ確認
	@GetMapping("{id}")
	public String show(@PathVariable Long id, Model model)
	{
		model.addAttribute("todo", this.todoService.selectById(id));
		
		
		return "todos/show";
	}
	
	// 編集画面肉までの画面
	@GetMapping("{id}/change")
	public String change(@PathVariable Long id, Model model)
	{
		model.addAttribute("todo", this.todoService.selectById(id));
		
		// change画面へ
		return "todos/change";
	}
	
	@PutMapping("put/{id}")
	public String update(TodoModel todo)
	{
		this.todoService.update(todo);
		
		return "redirect:/todos";
	}
	
	@DeleteMapping("{id}/delete")
	public String dast(@PathVariable Long id)
	{
		this.todoService.delete(id);
		
		return "redirect:/todos";
	}
}
