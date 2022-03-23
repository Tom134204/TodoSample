package com.springdemo.example.todo.selenide.page;

import static com.codeborne.selenide.Selenide.*;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * 
 * テスト対象トップ画面Page Objectクラス
 *
 */
public class TodoPageObject
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
	
	/**
	 * 詳細ボタンクリック
	 * @return 詳細画面オブジェクト
	 */
	public TodoDetailPageObject toDetail()
	{
		// 一々オブジェクトを定義するよりこちらのほうが良いか？
		$("button", 1).click();
		return new TodoDetailPageObject();
	}
	
	public TodoNewPageObject toNew()
	{
		$("button", 0).click();
		return new TodoNewPageObject();
	}
}

