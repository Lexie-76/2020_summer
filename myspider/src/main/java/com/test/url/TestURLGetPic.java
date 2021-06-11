package com.test.url;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.*;

import java.net.URL;

public class TestURLGetPic {
    public static void main(String[] args) throws Exception {
        String urlStr = "https://m.media-amazon.com/images/M/MV5BMjIyOTU2OTgzNV5BMl5BanBnXkFtZTcwMTg3NTQzMw@@._V1_SX1500_CR0,0,1500,999_AL_.jpg";
        URL url = new URL(urlStr);
        /*发送HTTP请求（GET），强制类型转换*/
        URLConnection urlConnection = url.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection)urlConnection;
        /*获取响应状态码*/
        int statusCode = httpURLConnection.getResponseCode();
        /*访问成功时值为200*/
        if (statusCode == HttpsURLConnection.HTTP_OK){
            InputStream is = httpURLConnection.getInputStream();
            /*基于IO的文件复制*/
            String filename = "d:"+ File.separator+"1.jpg";
            File file = new File(filename);
            OutputStream fos = new FileOutputStream(file);
            byte[] temp = new byte[1024];
            int len=0;
            while((len=is.read(temp)) != -1){
                fos.write(temp,0,len);
            }
            fos.close();is.close();
        }
    }
}
