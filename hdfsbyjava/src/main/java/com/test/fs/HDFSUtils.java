package com.test.fs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.net.URI;

public class HDFSUtils {
    static{
        System.setProperty("HADOOP_USER_NAME","icss");
        String osInfo = System.getProperty("os.name");
        if (osInfo.toLowerCase().indexOf("windows") != -1){
            System.setProperty("hadoop.home.dir","D:/hadoop");
        }
    }

    //获取FileSystem
    public static FileSystem getHDFSFileSystem(String hdfsRootURIStr)throws Exception{
        URI uri = new URI(hdfsRootURIStr);
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(uri,conf);
        return fs;
    }

    //获取文件系统的信息
    public static String getFileSystemInfo(FileSystem fs) throws IOException {
        FsStatus fsStatus = fs.getStatus();
        long used = fsStatus.getUsed();
        long remaining = fsStatus.getRemaining();
        long capacity = fsStatus.getCapacity();
        return used + " -- " + remaining +  " -- " + capacity;
    }

    //完成上传
    public static void upLoadFile(FileSystem fs,String local,String toHDFS) throws IOException {
        Path ulSrc = new Path(local);
        Path ulDst = new Path(toHDFS);         // new Path 以 / 开头，就表示这是一个 hdfs 中的 Path ！！！
        fs.copyFromLocalFile(ulSrc, ulDst);
    }

    //完成下载
    public static void downLoadFile(FileSystem fs,String xmlURLStr,String toLocal) throws Exception {
        Path xmlPath = new Path(xmlURLStr);
        FSDataInputStream is = fs.open(xmlPath);
        IOUtils.copyBytes(is, System.out, 4096, false);
        IOUtils.closeStream(is);
    }

    //完成复制
    public static void copyFile(FileSystem fs,String fileURLStr,String toFileURLStr,String newFileURLStr) throws IOException {
        Path dlSrc = new Path(fileURLStr);
        Path dlDst = new Path(toFileURLStr);
        fs.copyToLocalFile(dlSrc, dlDst);
        Path ulSrc = new Path(toFileURLStr);
        Path ulDst = new Path(newFileURLStr);         // new Path 以 / 开头，就表示这是一个 hdfs 中的 Path ！！！
        fs.copyFromLocalFile(ulSrc, ulDst);
    }

    //完成移动
    public static void moveFile(FileSystem fs,String fileURLStr,String toFileURLStr,String newFileURLStr) throws IOException {
        copyFile(fs,fileURLStr,toFileURLStr,newFileURLStr);
        deleteFile(fs,fileURLStr);
    }

    //完成ls功能
    public static void listFiles(FileSystem fs,String listDirStr) throws IOException {
        Path listDir = new Path(listDirStr);
        FileStatus[] statuses = fs.listStatus(listDir);
        for(FileStatus file : statuses){
            System.out.println("file or dir name:"+file.getPath().getName()+" is file?"+file.isFile());
        }
    }
    //完成删除
    public static void deleteFile(FileSystem fs,String fileURLStr) throws IOException {
        Path df = new Path(fileURLStr);
            fs.delete(df,true);
    }
}
