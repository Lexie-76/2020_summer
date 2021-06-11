package com.test.jsoup;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.Iterator;

public class Work1 {
    public static final String BASE_URL = "https://movie.douban.com/review/best/?start=";
    public static final int PAGE_SIZE = 10;
    public static void main(String[] args) throws IOException {
        for (int i = 0; i<10; i++){
            String url = BASE_URL + String.valueOf(i *PAGE_SIZE);
            Connection connection = Jsoup.connect(url);
            Document document = connection.get();
            Elements elements = document.select("div.main.review-item");
            Iterator<Element> iterator = elements.iterator();
            while(iterator.hasNext()){
                Element element = iterator.next();
                Element subjectImgElement = element.getElementsByTag("a").get(0);//电影信息
                String movieHref = subjectImgElement.attr("href");//获取电影页面的超链
                Element movieInfoElement = subjectImgElement.child(0);
                String movieTitle = movieInfoElement.attr("title");//电影名
                Element mainHDElement = element.selectFirst("header.main-hd");//获取header的标签
                Element authorInfoElement = mainHDElement.child(1);
                String authorName = authorInfoElement.text();//作者名
                Element movieStarElement = mainHDElement.child(2);//评分信息
                String movieStarClass = movieStarElement.className();
                System.out.println(movieHref + "==" + movieTitle + "==" + authorName + "==" + movieStarClass);
            }
            System.out.println("========================================" + "page" + (i+1) + "========================================");
        }
    }
}
