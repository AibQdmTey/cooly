/*
 * TestExample.java
 * Copyright 2020 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package cn.aib.selenium.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


/**
 *
 * @author shiqi
 * Created by shiqi on 2020/4/26.
 */
public class TestExample {
	public static void main(final String[] args) {
		System.setProperty("webdriver.chrome.driver", "selenium\\src\\main\\resources\\chromedriver_81.exe");
		final WebDriver driver = new ChromeDriver();
		driver.get("http://www.baidu.com");
		
		final String title = driver.getTitle();
		System.out.printf(title);
		
		driver.close();
	}
}
