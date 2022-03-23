package com.springdemo.example.todo.controllers;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.springdemo.example.todo.common.AppProperties;
import com.springdemo.example.todo.selenide.page.TodoNewPageObject;
import com.springdemo.example.todo.selenide.page.TodoPageObject;

// SpringBootTest, ExtendWith(SpringExtension.class)はJUnit5でSpringのAutowired
// を使いたい場合に指定する
// Selenideを使用する場合、各画面部品にはidを付けておいたほうが良さそう
@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
public class SelenideTodoTest
{
	@Autowired
	// application.propertiesバインドクラス
	private AppProperties appProperties;
	
	private WebDriver driver;
		
	@BeforeEach
	public void createDriver()
	{
		Configuration.browser = WebDriverRunner.CHROME;
							
		System.setProperty("webdriver.chrome.driver", appProperties.getWebdriverPath());
		
		ChromeOptions chromeOptions = new ChromeOptions();
		// Headlessモードの指定
		if(appProperties.isHeadLess())
		{
			chromeOptions.addArguments("--headless");
		}
		
		Map<String, Object> chromePrefs = new HashMap<>();
		// Popup表示抑制
		chromePrefs.put("profile.default_content_settings.popups", 0);
		//ダウンロードフォルダ指定
		chromePrefs.put("download.default_directory", "/DownloadFile");
        //ダウンロード先指定ダイアログ表示抑制
        chromePrefs.put("download.prompt_for_download", false);
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        
        this.driver = new ChromeDriver(chromeOptions);
        
        //WebDriveRunnerにdriverを設定
        WebDriverRunner.setWebDriver(this.driver);
        
        // Javascriptを使用することで入力速度向上する設定
        Configuration.fastSetValue = true;
        // スクリーンショット保存フォルダ
        Configuration.reportsFolder = "/report";
	}
	
	@AfterEach
	public void after()
	{
		if(this.driver != null)
		{
			this.driver.close();
		}
	}
	
	
	@Test
	@Order(1)
	public void test1()
	{
		// Pege open(フルパス指定)
		TodoPageObject pageObject = open("http://localhost:8080/todos", TodoPageObject.class);
		assertTrue(pageObject.getTable().getTableRows().size() > 1);
		assertEquals(5, pageObject.getTable().getTableRows().get(1).getDatas().size());
	}
	
	@Test
	@Order(2)
	public void test2()
	{
		// Pege open(フルパス指定)
		TodoPageObject pageObject = open("http://localhost:8080/todos", TodoPageObject.class);
		assertNotNull(pageObject.toDetail());
		assertEquals("登録内容確認", $("h2").text());
		
		// $$で複数要素の取得
		assertEquals(2, $$("td").size());
	}
	
	@Test
	@Order(3)
	public void test3()
	{
		// Pege open(フルパス指定)
		TodoPageObject pageObject = open("http://localhost:8080/todos", TodoPageObject.class);
		
		TodoNewPageObject newPage = pageObject.toNew();
		
		assertNotNull(newPage);
		newPage.doCreate("TestTodo");
	}
}
