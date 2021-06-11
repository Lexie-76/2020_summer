package com.test.work3;

import org.apache.commons.exec.util.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.lang.String;

//IMDb电影信息+对应票房搜索
public class IMDb {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","D:\\chromedriver\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        WebDriver browser = new ChromeDriver(chromeOptions);

        String url = "https://www.imdb.com/chart/top/?ref_=nv_mv_250";
        browser.get(url);

        ArrayList<String> movieInfo = new ArrayList<>();

        //电影信息网址获取
        List<WebElement> movieUrls = browser.findElements(By.xpath("/html/body/div[3]/div/div[2]/div[3]/div/div[1]/div/span/div/div/div[3]/table/tbody/tr/td[2]/a"));
        for(int i=0;i<movieUrls.size();i++){
            String movieUrl = (movieUrls.get(i)).getAttribute("href");
            movieInfo.add(movieUrl);
        }

        for(int i=0;i<movieInfo.size();i++){
            browser.get(movieInfo.get(i));

            //电影名
            WebElement movieName = browser.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[5]/div[1]/div/div/div[1]/div[2]/div/div[2]/div[2]/h1"));
            String tempName = movieName.getText();
            String Name=tempName.substring(0, tempName.indexOf("("));

            //上映时间
            WebElement releaseDate;
            releaseDate = browser.findElement(By.xpath("(//*[@id=\"title-overview-widget\"]/div[1]/div[2]/div/div[2]/div[2]/div/a)[last()]"));
            String Date = releaseDate.getText();

            //电影评分
            WebElement movieMark = browser.findElement(By.xpath("//*[@id=\"title-overview-widget\"]/div[1]/div[2]/div/div[1]/div[1]/div[1]/strong/span"));
            String Mark = movieMark.getText();

            //演职员表
            WebElement performLink = browser.findElement(By.xpath("//*[@id=\"titleCast\"]/div[1]/a"));
            String perform_url = performLink.getAttribute("href");
            browser.get(perform_url);

            //导演
            WebElement Director = browser.findElement(By.xpath("//*[@id=\"fullcredits_content\"]/table[1]/tbody/tr/td[1]/a"));
            String DirectorName = Director.getText();

            //编剧
            List<WebElement> Writer = browser.findElements(By.xpath("//*[@id=\"fullcredits_content\"]/table[2]/tbody/tr/td[1]/a"));
            ArrayList<String> WriterNames = new ArrayList<>();
            for(int j=0;j<Writer.size();j++){
                String WriterName = (Writer.get(j)).getText();
                WriterNames.add(WriterName);
            }

            //演员
            List<WebElement> Cast = browser.findElements(By.xpath("//*[@id=\"fullcredits_content\"]/table[3]/tbody/tr/td[2]/a"));
            ArrayList<String> CastNames = new ArrayList<>();
            for(int j=0;j<10;j++){
                String CastName = (Cast.get(j)).getText();
                CastNames.add(CastName);
            }
            //影评网页
            WebElement commentLink = browser.findElement(By.xpath("//*[@id=\"titleUserReviewsTeaser\"]/div/a[2]"));
            String comment_url = commentLink.getAttribute("href");
            browser.get(comment_url);

            //影评标题和链接
            List<WebElement>  allComments = browser.findElements(By.xpath("//*[@id=\"main\"]/section/div[2]/div[2]/div/div/div[1]/a"));
            ArrayList<String> articleTitles = new ArrayList<>();
            ArrayList<String> articleUrls = new ArrayList<>();
            for(int j=0;j<10;j++){
                String articleTitle = (allComments.get(i)).getText();
                String articleUrl = (allComments.get(i)).getAttribute("href");
                articleTitles.add(articleTitle);
                articleUrls.add(articleUrl);
            }

            //票房
            String box_url = "https://maoyan.com/";
            browser.get(box_url);
            String search_handle = browser.getWindowHandle();

            //模拟搜索
            WebElement inputElement = browser.findElement(By.cssSelector("input.search"));
            WebElement btnElement = browser.findElement(By.cssSelector("input.submit"));
            //输入电影名
            inputElement.sendKeys(Name);
            btnElement.click();
            Thread.sleep(3000);
            //跳转电影搜索页面
            Set<String> handles = browser.getWindowHandles();
            for(String handle : handles){
                if (browser.switchTo().window(handle).getTitle().contains(Name)){
                    browser.switchTo().window(handle);
                }
            }
            Thread.sleep(3000);

            //找到选择的电影进入
            WebElement movieLink = browser.findElement(By.xpath("/html/body/div[5]/div/dl/dd[1]/div[2]/a"));
            movieLink.click();
            Thread.sleep(3000);
            Set<String> handles2 = browser.getWindowHandles();
            for(String handle : handles2){
                if (browser.switchTo().window(handle).getTitle().contains("购票")){
                    browser.switchTo().window(handle);
                }
            }
            Thread.sleep(3000);

            //获取票房信息
            WebElement boxNumber = browser.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[3]/div[2]/div/span"));
            String boxContent = boxNumber.getAttribute("innerHTML");

            /*System.out.println(Name+"==="+Date+"==="+Mark);
            System.out.print("Director: ");
            System.out.println(DirectorName);
            System.out.print("Writer: ");
            System.out.println(WriterNames);
            System.out.print("Cast: ");
            System.out.println(CastNames);
            /*System.out.println("Comments: ");
            for(int j=0;j<10;j++){
                System.out.println(articleTitles.get(j)+"==="+articleUrls.get(j));
            }
            System.out.println("Box: ");
            System.out.println(boxContent);*/
            System.out.println("========================================"+(i+1)+"=====================================");
        }
        browser.quit();
    }

}
