package com.test.jsoup;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Iterator;

public class TestJSoup {
    public static void main(String[] args) throws IOException {
    //获取页面
        //生成url字符串
        String url = "https://movie.douban.com/review/best/";
        //获取指定url的连接
        Connection connection = Jsoup.connect(url);
        //设置连接参数
        //connection.timeout(3000);//设置超时
        //connection.userAgent("Mozilla");
        //connection.cookie("auth","token");//模拟设置cookie
        //Jsoup支持链式编程
        //Jsoup.connect(url:"").timeout(3000).userAgent("").data("username","zhangsan").data("password","123456").post;
        //发送get请求
        Document document = connection.get();
        String html = document.html();//获取源码
        System.out.println(html);
        String text = document.text();//获取文本
        System.out.println(text);
    //解析&定位
        //依据DOM进行解析定位 -- html标签的相应信息
        //Elements elements =  document.getElementsByAttributeValue("class","main.review-item");//根据属性和值来获取页面元素
        //Elements elements = document.getElementsByClass("main.review-item");//临时性获取指定类选择器的值的页面元素

        //使用cssSelector 进行解析和定位
        Elements elements = document.select("div.main.review-item");
        //遍历获取所有的元素 === 对ArrayList使用iterator 进行遍历
        Iterator<Element> iterator = elements.iterator();
        while(iterator.hasNext()){
            Element element = iterator.next();
            //获取其下某个标签
            Element subjectImgElement = element.getElementsByTag("a").get(0);//获取其下第一个a标签
            String movieHref = subjectImgElement.attr("href");//获取电影页面的超链
            //subjectImgElement.getElementsByTag("img").first()
            Element movieInfoElement = subjectImgElement.child(0);
            String movieTitle = movieInfoElement.attr("title");
            String movieImgUrlStr = movieInfoElement.attr("src");
            Element mainHDElement = element.selectFirst("header.main-hd");//获取header的标签
            Element authorInfoElement = mainHDElement.child(1);
            String authorUrlStr = authorInfoElement.attr("href");
            String authorName = authorInfoElement.text();
            Element mainBDElement = element.getElementsByClass("main-bd").first();//获取div main-bd
            String movieReviewName = mainBDElement.getElementsByTag("h2").first().getElementsByTag("a").first().text();
            System.out.println(movieHref + "==" + movieTitle + "==" + movieImgUrlStr + "==" + authorUrlStr + "==" + authorName + "==" + movieReviewName);
        }
    }
}
