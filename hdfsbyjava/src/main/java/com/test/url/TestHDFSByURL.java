package com.test.url;

import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class TestHDFSByURL {
    static {
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());

    }
    private static String hdfsURL = "hdfs://master:9000/user/icss/input/core-site.xml";


    private static void copyToLocal(String hdfsURL) throws Exception {
        URL url = new URL(hdfsURL);
        URLConnection urlConnection = url.openConnection();
        InputStream is = urlConnection.getInputStream();
        IOUtils.copyBytes(is,System.out,4096,false);
        /*InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String buffer = null;
        while((buffer = br.readLine())!=null){
            System.out.println(buffer);
        }
        br.close();isr.close();is.close();*/
    }

    public static void main(String[] args) throws Exception {
        copyToLocal(hdfsURL);
    }
}
