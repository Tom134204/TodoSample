package com.springdemo.example.todo.selenide.page;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;

/**
 * 
 * 注意：内部クラスにする場合はstaticにしないとselenideの内部のcreate instanceで失敗する
 *
 */
public class TodoTableRow extends ElementsContainer
{
	@FindBy(how = How.TAG_NAME, using = "th")
	private ElementsCollection headers;
	
	@FindBy(how = How.TAG_NAME, using = "td")
	private ElementsCollection datas;

	/**
	 * @return headers
	 */
	public ElementsCollection getHeaders()
	{
		return headers;
	}

	/**
	 * @param headers セットする headers
	 */
	public void setHeaders(ElementsCollection headers)
	{
		this.headers = headers;
	}

	/**
	 * @return datas
	 */
	public ElementsCollection getDatas()
	{
		return datas;
	}

	/**
	 * @param datas セットする datas
	 */
	public void setDatas(ElementsCollection datas)
	{
		this.datas = datas;
	}
	
	
}
