package com.springdemo.example.todo.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("test")
public class AppProperties
{
	private String webdriverPath;
	
	private boolean headLess;

	/**
	 * @return webdriverPath
	 */
	public String getWebdriverPath()
	{
		return webdriverPath;
	}

	/**
	 * @param webdriverPath セットする webdriverPath
	 */
	public void setWebdriverPath(String webdriverPath)
	{
		this.webdriverPath = webdriverPath;
	}

	/**
	 * @return headLess
	 */
	public boolean isHeadLess()
	{
		return headLess;
	}

	/**
	 * @param headLess セットする headLess
	 */
	public void setHeadLess(boolean headLess)
	{
		this.headLess = headLess;
	}
	
	
}
