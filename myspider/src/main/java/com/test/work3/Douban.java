package com.test.work3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;

//豆瓣影评
public class Douban  {
    public static final String REVIEW_URL = "https://movie.douban.com/review/best/?start=";
    public static final String URL = "https://movie.douban.com/review/";
    public static final int PAGE_SIZE = 10;
    public static void main(String[] args){
        System.setProperty("webdriver.chrome.driver","D:\\chromedriver\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        WebDriver browser = new ChromeDriver(chromeOptions);

        ArrayList<String> movieID = new ArrayList<>();
        ArrayList<String> commentUrl = new ArrayList<>();
        ArrayList<String> movieMark = new ArrayList<>();
        ArrayList<String> movieUrl = new ArrayList<>();


        for (int i = 0; i<10; i++){
            String url = REVIEW_URL + String.valueOf(i *PAGE_SIZE);
            browser.get(url);

            //电影id
            List<WebElement> movieDataIds = browser.findElements(By.xpath("//*[@id=\"content\"]/div/div[1]/div[1]/div"));
            for(int j=0;j<movieDataIds.size();j++){
                String movieDataId = (movieDataIds.get(j)).getAttribute("data-cid");
                movieID.add(movieDataId);
            }

            //评分信息
            List<WebElement> movieMarkStars = browser.findElements(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div[1]/div/div/header/span[1]"));
            for(int j=0;j<movieMarkStars.size();j++){
                String movieMarkStar = (movieMarkStars.get(j)).getAttribute("title");
                movieMark.add(changeStar(movieMarkStar));
            }

            //电影地址
            List<WebElement> movieInfoUrls = browser.findElements(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div[1]/div/div/a"));
            for(int j=0;j<movieInfoUrls.size();j++){
                String movieInfoUrl = (movieInfoUrls.get(j)).getAttribute("href");
                movieUrl.add(movieInfoUrl);
            }

        }
        //影评网址
        for(int i = 0;i<movieID.size();i++){
            String movieCommentUrl = URL+movieID.get(i);
            commentUrl.add(movieCommentUrl);
        }

        //输出影评信息
        //for(int i=0;i<movieID.size();i++){
        //    System.out.println(movieID.get(i)+"==="+movieMark.get(i)+"==="+commentUrl.get(i));
        //}



        //movieUrl去重
        ArrayList<String> tempList = new ArrayList(movieUrl.size());
        for(int i=0;i<movieUrl.size();i++){
            if(!tempList.contains(movieUrl.get(i)))
                tempList.add(movieUrl.get(i));
        }

        //电影信息
        for(int i=0;i<tempList.size();i++){
            //上映日期
            String movie_url = tempList.get(i);
            browser.get(movie_url);
            WebElement movieName = browser.findElement(By.xpath("//*[@id=\"content\"]/h1/span[1]"));
            String movieNameInfo = movieName.getText();
            List<WebElement> movieAllInfo = browser.findElements(By.xpath("//*[@id=\"info\"]/span"));
            String movieRelease = "";
            for(int j=0;j<movieAllInfo.size();j++){
                String InnerHtml = (movieAllInfo.get(j)).getAttribute("innerHTML");
                if(InnerHtml.contains("-")){
                    movieRelease += (movieAllInfo.get(j)).getText();
                }
            }
            //演职员表
            String performer_url = movie_url+"celebrities";
            browser.get(performer_url);
            //导演
            List<WebElement> Director = browser.findElements(By.xpath("//*[@id=\"celebrities\"]/div[1]/ul/li/a"));
            ArrayList<String> DirectorNames = new ArrayList<>();
            for(int j=0;j<Director.size();j++){
                String CastName = (Director.get(j)).getAttribute("title");
                DirectorNames.add(CastName);
            }
            //演员
            List<WebElement> Cast = browser.findElements(By.xpath("//*[@id=\"celebrities\"]/div[2]/ul/li/a"));
            ArrayList<String> CastNames = new ArrayList<>();
            for(int j=0;j<Cast.size();j++){
                String CastName = (Cast.get(j)).getAttribute("title");
                CastNames.add(CastName);
            }
            //编剧
            List<WebElement> Writer = browser.findElements(By.xpath("//*[@id=\"celebrities\"]/div[3]/ul/li/a"));
            ArrayList<String> WriterNames = new ArrayList<>();
            for(int j=0;j<Writer.size();j++){
                String WriterName = (Writer.get(j)).getAttribute("title");
                WriterNames.add(WriterName);
            }

            //输出电影信息
            System.out.println(movieNameInfo+"==="+movieRelease);
            System.out.print("Director: ");
            System.out.println(DirectorNames);
            System.out.print("Cast: ");
            System.out.println(CastNames);
            System.out.print("Writer: ");
            System.out.println(WriterNames);
            System.out.println("======================================第"+(i+1)+"部=======================================");

        }
        browser.quit();
    }

    //判断评分
    private static String changeStar(String movieMarkStar) {
        if(movieMarkStar.equals("力荐")){
            return "五星";
        }
        else if(movieMarkStar.equals("推荐")){
            return "四星";
        }
        else if(movieMarkStar.equals("还行")){
            return "三星";
        }
        else if(movieMarkStar.equals("较差")){
            return "两星";
        }
        else if(movieMarkStar.equals("很差")){
            return "四星";
        }
        return "无";
    }
}
