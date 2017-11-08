package com.phl.mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.MRJobConfig;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @Title:JobClient
 * @Description:
 * @Copyright:中国电信爱wifi运营中心
 * @author:panhl
 * @date 2017/11/6 0006 15:31
 */
public class JobClient {
    public static void main(String[] args) throws Exception{
        Configuration conf=new Configuration();
        //map reducer程序的运行模式，不指定会从配置文件中加载
        //先从mapred-default.xml中加载，默认为local，即本地运行，可以在调试时使用
        //conf.set("mapreduce.framework.name","local");
        //conf.set("mapreduce.framework.name","yarn");
        Job job=Job.getInstance(conf);

        //设置mapperreduce jar包的位置,硬编码 不好
        //job.setJar("d://a.jar");

        //另一种方法，jar包在 jobClient 类所有包
        job.setJarByClass(JobClient.class);

        /**maper 的四个参数类型**/
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        /**reduce的两个参数类型**/
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //可以自定义partitionerClass用于完成特殊功能
        //job.setPartitionerClass();
        //可以设置reduce数量，如果 自定义了partionerClass，此时需要考虑是否要设置reduce num
        //job.setNumReduceTasks();


        FileInputFormat.setInputPaths(job,new Path("/task_input/workcount/"));
        FileOutputFormat.setOutputPath(job,new Path("/task_output/wordcount"));

        //本地模式运行时，指定本地路径
      // FileInputFormat.setInputPaths(job,new Path("D://task_input//workcount//"));
       // FileOutputFormat.setOutputPath(job,new Path("D://task_output//wordcount//"));

        //job.submit();此方法调用后失去集群的联系，不知道任务执行的进度
        boolean rs=job.waitForCompletion(true);//rs：是否成功
        //true:是否要job 客户端印进度信息
       System.exit(rs?0:1);
    }
}
