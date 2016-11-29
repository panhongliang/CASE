package com.phl.forkjoin;

import java.util.concurrent.ForkJoinPool;

/**
 * Created by Administrator on 2016-08-03.
 */
public class WordCounter {

    static String[] wordsIn(String line) {
        return line.trim().split("(\\s|\\p{Punct})+");
    }
    public static Long occurrencesCount(Document document, String searchedWord) {

        long count = 0;
        for (String line : document.getLines()) {
            for (String word : wordsIn(line)) {
                if (searchedWord.equals(word)) {
                    count = count + 1;
                }
            }
        }
        return count;
    }
    public static Long countOccurrencesOnSingleThread(Folder folder, String searchedWord) {

        long count = 0;
        for (Folder subFolder : folder.getSubFolders()) {
            count = count + countOccurrencesOnSingleThread(subFolder, searchedWord);
        }
        for (Document document : folder.getDocuments()) {
            count = count + WordCounter.occurrencesCount(document, searchedWord);
        }
        return count;
    }

    public static  Long countOccurrencesInParallel(Folder folder, String searchedWord) {
         ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new FolderSearchTask(folder, searchedWord));
    }
}
