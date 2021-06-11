package com.test.sele;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

public class TestSele {
    public static void main(String[] args){
        System.setProperty("webdriver.chrome.driver","D:\\chromedriver\\chromedriver.exe");
        //获取环境变量
        Map map = System.getenv();
        //遍历map。。。

        //获取系统变量
        Properties properties = System.getProperties();
        //遍历。。

        //1.启动WebDriver
        //设置代理
//        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
//        desiredCapabilities.setCapability("chrome.switch", Arrays.asList("--proxy-server=http://user:password@xxx.com//portnum:"));
//        WebDriver browser = new ChromeDriver(desiredCapabilities);
        WebDriver browser = new ChromeDriver();
        String url = "https://www.baidu.com";
        browser.get(url);//获取响应页面

        String title = browser.getTitle();
        System.out.println(title);

        //2.解析定位 By
        //WebElement element = browser.findElement(By.id("content"));

        //3.获取内容
        //String attvalue = element.getAttribute("h1");
        //String elementText = element.getText();

        //模拟百度搜索操作
        //1.打开百度页面
        //2.定位 搜索框、搜索按钮
        WebElement inputElement = browser.findElement(By.id("kw"));
        WebElement btnElement = browser.findElement(By.cssSelector("input.bg.s_btn"));
        //3.搜索框的输入-模拟键盘操作，搜索按钮的单机-模拟鼠标操作
        inputElement.sendKeys("天津");
        btnElement.click();

        //关闭、退出
        browser.quit();

    }
}
