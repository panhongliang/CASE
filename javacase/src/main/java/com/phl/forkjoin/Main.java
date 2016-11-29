package com.phl.forkjoin;

import java.io.File;

/**
 * Created by Administrator on --.
 */
public class Main {
    public static void main(String[] args)throws Exception {
        String f="D:\\360CloudUI\\Cache\\1347785479\\test";
        String w="public";
        Folder folder = Folder.fromDirectory(new File(f));
        long s=System.currentTimeMillis();
        System.out.println(WordCounter.countOccurrencesOnSingleThread(folder, w));
        long e1=System.currentTimeMillis();
        System.out.println(WordCounter.countOccurrencesInParallel(folder, w));
        long e2=System.currentTimeMillis();
        System.out.println("singlethread:"+(e1-s)+"\n inparallel:"+(e2-e1));
    }
}
