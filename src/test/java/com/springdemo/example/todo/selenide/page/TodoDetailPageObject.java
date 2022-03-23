package com.springdemo.example.todo.selenide.page;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * 
 * テスト対象詳細画面Page Objectクラス
 *
 */
public class TodoDetailPageObject
{
	@FindBy(how = How.TAG_NAME, using = "table")
	private TodoTable table;
		
	/**
	 * @return table
	 */
	public TodoTable getTable()
	{
		return table;
	}

	/**
	 * @param table セットする table
	 */
	public void setTable(TodoTable table)
	{
		this.table = table;
	}
	
	
}
