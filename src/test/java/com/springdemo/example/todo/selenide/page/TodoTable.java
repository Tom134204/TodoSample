package com.springdemo.example.todo.selenide.page;

import java.util.List;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.codeborne.selenide.ElementsContainer;

/**
 * 
 * 注意：内部クラスにする場合はstaticにしないとselenideの内部のcreate instanceで失敗する
 *
 */
public class TodoTable extends ElementsContainer
{
	@FindBy(how = How.TAG_NAME, using = "tr")
	private List<TodoTableRow> tableRows;

	/**
	 * @return tableRows
	 */
	public List<TodoTableRow> getTableRows()
	{
		return tableRows;
	}

	/**
	 * @param tableRows セットする tableRows
	 */
	public void setTableRows(List<TodoTableRow> tableRows)
	{
		this.tableRows = tableRows;
	}
	
	
}
