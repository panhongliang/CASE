package com.phl.mr;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * @Title:WordCountMapper
 * @Description:
 * @Copyright:中国电信爱wifi运营中心
 * @author:panhl
 * @date 2017/11/6 0006 15:04
 */
public class WordCountMapper extends Mapper<LongWritable,Text,Text,IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line=value.toString();
        String[] words=line.split(" ");
        for(String word:words){
            //contxt.write时完成分区工作
            //分区： key 分配到哪个reduce上去执行
            context.write(new Text(word),new IntWritable(1));
        }
    }
}
