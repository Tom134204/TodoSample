package com.springdemo.example.todo.selenide.page;

import static com.codeborne.selenide.Selenide.*;

import org.openqa.selenium.By;

public class TodoNewPageObject
{
	public void doCreate(String todoVal)
	{
		$(By.id("task")).setValue(todoVal).pressEnter();
	}
}
