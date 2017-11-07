package com.phl.mr;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * @Title:MapperReducer
 * @Description:
 * @Copyright:中国电信爱wifi运营中心
 * @author:panhl
 * @date 2017/11/6 0006 15:07
 */
public class WordCountReducer extends Reducer<Text,IntWritable,Text,IntWritable> {

    /**
     * 一组相同key的任务只提交一次
     * @param key
     * @param values
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int count=0;
        for(IntWritable i:values){
            count+=i.get();
        }
        context.write(key,new IntWritable(count));
    }
}
