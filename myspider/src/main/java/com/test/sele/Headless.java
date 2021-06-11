package com.test.sele;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;


public class Headless {
    public static void main(String[] args){
//爬取数据
        System.setProperty("webdriver.chrome.driver","D:\\chromedriver\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        WebDriver browser = new ChromeDriver(chromeOptions);
        String url = "https://movie.douban.com/review/best/";
        browser.get(url);//获取响应页面

        //电影 url
        List<WebElement> ImgUrls = browser.findElements(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div[1]/div/div/a"));
        ArrayList<String> ImgUrlValues = new ArrayList<>();
        for(int i=0;i<ImgUrls.size();i++){
            String ImgUrlValue = (ImgUrls.get(i)).getAttribute("href");
            ImgUrlValues.add(ImgUrlValue);
        }
        //作者信息
        List<WebElement> authorInfos = browser.findElements(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div[1]/div/div/header/a[2]"));
        ArrayList<String> authorInfoValues = new ArrayList<>();
        for(int i=0;i<authorInfos.size();i++){
            String authorInfoValue = (authorInfos.get(i)).getAttribute("href");
            authorInfoValues.add(authorInfoValue);
        }
        //文章名称
        List<WebElement> authorNames = browser.findElements(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div[1]/div/div/div/h2/a"));
        ArrayList<String> authorNameValues = new ArrayList<>();
        for(int i=0;i<authorNames.size();i++){
            String authorNameValue = (authorNames.get(i)).getText();
            authorNameValues.add(authorNameValue);
        }
        for(int i=0;i<ImgUrlValues.size();i++){
            System.out.println("电影链接" + ImgUrlValues.get(i) + "====" + "作者信息链接" + authorInfoValues.get(i) + "====" + "文章标题  " + authorNameValues.get(i));
        }

//模拟登录
        String url2 = "https://www.douban.com/";
        browser.get(url2);
        browser.findElement(By.xpath("/html/body/div[1]/div[1]/ul[1]/li[2]")).click();
        browser.findElement(By.xpath("//*[@id=\"username\"]")).clear();
        browser.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("17302226170");
        browser.findElement(By.xpath("//*[@id=\"password\"]")).clear();
        browser.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("mlx000127");
        browser.findElement(By.cssSelector("btn.btn-account")).click();
        browser.switchTo().window("https://www.douban.com/");
    }
}
