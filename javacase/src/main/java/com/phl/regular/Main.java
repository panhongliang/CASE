package com.phl.regular;

import io.netty.handler.codec.marshalling.MarshallingEncoder;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017-01-05.
 * 捕获组是通过从左至右计算其开括号来编号。例如，在表达式（（A）（B（C））），有四个这样的组：
 *((A)(B(C)))
 *(A)
 *(B(C))
 *(C)
 *可以通过调用 matcher 对象的 groupCount 方法来查看表达式有多少个分组。groupCount 方法返回一个 int 值，表示matcher对象当前有多个捕获组。
 *还有一个特殊的组（group(0)），它总是代表整个表达式。该组不包括在 groupCount 的返回值中。
 * group(): group(0) 满足pattern的字符串，group(1):第1 组...
 * start(1):第一组的开始索引
 * end(1):第一组的结束索引
 */
public class Main {

    @Test
    public  void count(){
        String context="abcdeddggddghi";
        String word="dd";
        Pattern p=Pattern.compile("("+word+")\\w+\\1(\\w)");
        Matcher matcher=p.matcher(context);
        if(matcher.find()){
            for(int i=0;i<=matcher.groupCount();i++){
                System.out.println(matcher.group(i));
            }
        }
    }
    @Test
    public void count1(){
        String context="abcdeddggddghi";
        String word="dd";
        System.out.println( context.split(word).length-1);
    }

    /**
     * matches 用来匹配整个字符串，整个字符串符合正则表达式，才返回true.
     */
    @Test
    public void matches(){
        String context="ddd";
        Pattern p=Pattern.compile("\\w{2,}");
        Matcher m=p.matcher(context);
        if (m.matches()){
            System.out.println("group:"+m.group(0));
            System.out.println("start:"+m.start());
            System.out.println("end:"+m.end());
        }else {
            System.out.println("not matches");
        }
    }

    /**
     * find 用来匹配部分字符串， 整个字符串中有部分子串符合正则表达式，返回true.
     */
    @Test
    public void find(){
        String context="abcddabb";
        Pattern p=Pattern.compile("(\\w)\\1");
        Matcher m=p.matcher(context);
        while (m.find()){
            System.out.println("group:"+m.group()+",start:"+m.start()+",end:"+m.end());
        }
    }
    @Test
    public void match_find(){
        Pattern pattern = Pattern.compile("\\d{3,5}");
        String charSequence = "123-34345-234-00";
        Matcher matcher = pattern.matcher(charSequence);
        print(matcher.matches());
        matcher.find();
        print(matcher.start());
    }
    @Test
    public void match_find_lookingAt(){
        Pattern pattern = Pattern.compile("\\d{3,5}");
        String charSequence = "123-34345-234-00";
        Matcher matcher = pattern.matcher(charSequence);

        //虽然匹配失败，但由于charSequence里面的"123"和pattern是匹配的,所以下次的匹配从位置4开始
        print(matcher.matches());
        //测试匹配位置
        matcher.find();
        print(matcher.start());

        //使用reset方法重置匹配位置
        print("=======reset======");
        matcher.reset();

        //第一次find匹配以及匹配的目标和匹配的起始位置
        print(matcher.find());
        print(matcher.group()+" - "+matcher.start());
        //第二次find匹配以及匹配的目标和匹配的起始位置
        print(matcher.find());
        print(matcher.group()+" - "+matcher.start());

        //第一次lookingAt匹配以及匹配的目标和匹配的起始位置
        print(matcher.lookingAt());
        print(matcher.group()+" - "+matcher.start());

        //第二次lookingAt匹配以及匹配的目标和匹配的起始位置
        print(matcher.lookingAt());
        print(matcher.group()+" - "+matcher.start());
    }

    public static void print(Object o){
        System.out.println(o);
    }


    @Test
    public  void group(){
        // 按指定模式在字符串查找
        String line = "This order was placed for QT3000! OK?";
        String pattern = "(\\D*)(\\d+)(.*)";
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);
        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);
        if (m.find( )) {
            System.out.println("Found value: " + m.group(0) );// group o 代表整个字符串
            System.out.println("Found value: " + m.group(1) );
            System.out.println("Found value: " + m.group(2) );
            System.out.println("Found value: " + m.group(3) );
        } else {
            System.out.println("NO MATCH");
        }
    }
    //反向引用
    @Test
    public void fxyy(){
        // 按指定模式在字符串查找
        String line = "Is is the u u u u cost of of gasoline going up up ?";
        String pattern = "\\b([a-z]+) \\1 \\1";
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);
        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);
        while (m.find()) {
            System.out.println(m.group());
        }
    }

}
