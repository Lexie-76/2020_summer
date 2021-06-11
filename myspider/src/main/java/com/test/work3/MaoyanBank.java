package com.test.work3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;

//猫眼电影票房
public class MaoyanBank {
    public static void main(String[] args){
        System.setProperty("webdriver.chrome.driver","D:\\chromedriver\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        WebDriver browser = new ChromeDriver(chromeOptions);
        String url = "https://maoyan.com/board/1";
        browser.get(url);

        ArrayList<String> movieName = new ArrayList<>();
        ArrayList<String> movieBox = new ArrayList<>();

        List<WebElement> movieNames = browser.findElements(By.xpath("//*[@id=\"app\"]/div/div/div/dl/dd/div/div/div[1]/p[1]/a"));
        for(int i=0;i<movieNames.size();i++){
            String Name = (movieNames.get(i)).getText();
            movieName.add(Name);
        }

        List<WebElement> movieBoxes = browser.findElements(By.xpath("/html/body/div[4]/div/div/div/dl/dd/div/div/div[2]/p"));
        for(int i=0;i<movieBoxes.size();i++){
            String Box = (movieBoxes.get(i)).getText();
            movieBox.add(Box);
        }
        browser.quit();

        //for(int i=0;i<movieName.size();i++){
        //    System.out.println(movieName.get(i)+"==="+movieBox.get(i));
        //}

    }
}
