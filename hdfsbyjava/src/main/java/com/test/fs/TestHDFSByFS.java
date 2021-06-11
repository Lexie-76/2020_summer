package com.test.fs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FsStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.net.URI;

public class TestHDFSByFS {
    static{
        System.setProperty("HADOOP_USER_NAME","icss");
    }

    public static void main(String[] args) throws IOException {
        String hdfsRootURIStr = "hdfs://master:9000";
        URI hdfsRootURI = URI.create(hdfsRootURIStr);
        String osInfo = System.getProperty("os.name");
        if (osInfo.toLowerCase().indexOf("windows") != -1){
            System.setProperty("hadoop.home.dir","D:/hadoop");
        }

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(hdfsRootURI,conf);

        FsStatus fsStatus = fs.getStatus();
        long used = fsStatus.getUsed();
        long remaining = fsStatus.getRemaining();
        long capacity = fsStatus.getCapacity();
        System.out.println(used+" "+remaining+" "+capacity);

        String coreSiteXmlURLStr = "hdfs://master:9000/user/icss/input/core-site.xml";
        Path coreSiteXmlPath = new Path(coreSiteXmlURLStr);
        FSDataInputStream is = fs.open(coreSiteXmlPath);
        IOUtils.copyBytes(is,System.out,4096,false);
        IOUtils.closeStream(is);

        Path dlSrc = new Path(coreSiteXmlURLStr);
        Path dlDst = new Path("D:/1.xml");
        fs.copyToLocalFile(dlSrc,dlDst);

        Path ulSrc = new Path("D:/1.xml");
        Path ulDst = new Path("/data");
        fs.copyFromLocalFile(ulSrc,ulDst);
    }
}
