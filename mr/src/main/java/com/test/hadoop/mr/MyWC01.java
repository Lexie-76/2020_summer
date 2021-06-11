package com.test.hadoop.mr;

import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;

public class MyWC01 {
    static {
        String osInfo = System.getProperty("os.name");
        if (osInfo.toLowerCase().indexOf("windows") != -1){
            System.setProperty("hadoop.home.dir","D:/hadoop");
            System.setProperty("hadoop.tmp.dir","d:/mrtmp");
            System.setProperty("HADOOP_USER_NAME","icss");
        }

    }

    //1.完成mapper
    public static class WCGenderMapper
            extends Mapper<LongWritable, Text,Text, IntWritable>{

        private IntWritable one = new IntWritable(1);
        private Text word = new Text();
        //覆盖重写map
        @Override
        protected void map(LongWritable key, Text value, Context context)
                throws IOException, InterruptedException {


            String line = value.toString();
            String [] strs = line.split(" ");
            for(String str:strs){
                //Text word = new Text(str);
                //IntWritable one = new IntWritable(1);
                word.set(str);
                context.write(word,one);
            }
        }
    }

    //2.完成reducer
    public static class WCGenderReducer
            extends Reducer<Text,IntWritable,Text,IntWritable>{
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int sum = 0;
            Iterator<IntWritable> iterator = values.iterator();
            while(iterator.hasNext()){
                IntWritable value = iterator.next();
                int intValue = value.get();
                sum = sum+intValue;
            }
            IntWritable total = new IntWritable(sum);
            context.write(key,total);
        }
    }

    //3.完成controller
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf,"Version1.0");
        job.setJarByClass(MyWC01.class); //!!!

        //输入
        //for(int i=0;i>args.length-1;i++)
        //    FileInputFormat.addInputPath(job, new Path(args[i]));
        String [] hadoopArgs = new GenericOptionsParser(conf,args).getRemainingArgs();
        if(hadoopArgs.length<2){
            System.err.println("Args is Error");
            System.exit(2);
        }
        //输入位置
        for(int i=0;i<hadoopArgs.length-1;i++){
            Path inputPath = new Path(hadoopArgs[i]);
            FileInputFormat.addInputPath(job,inputPath);
        }
        //输入类型
        job.setInputFormatClass(TextInputFormat.class);

        //mapper类
        job.setMapperClass(WCGenderMapper.class);
        //mapMid
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //reducer个数
        job.setNumReduceTasks(2);
        //reducer类
        job.setReducerClass(WCGenderReducer.class);
        //mapDst 结果
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //输出位置
        Path outPath = new Path(hadoopArgs[hadoopArgs.length-1]);
        //删除output文件
        outPath.getFileSystem(conf).delete(outPath,true);
        FileOutputFormat.setOutputPath(job,outPath);
        //输出类型
        job.setOutputFormatClass(TextOutputFormat.class);

        //提交
        //job.submit();  无返回值无法追踪状态
        //boolean result = job.waitForCompletion(true);   多用于多个job按顺序
        boolean resultCompletion = job.waitForCompletion(true);
        boolean resultSuccessful = job.isSuccessful();
        System.exit(resultCompletion && resultSuccessful ?0:1);



    }
}
