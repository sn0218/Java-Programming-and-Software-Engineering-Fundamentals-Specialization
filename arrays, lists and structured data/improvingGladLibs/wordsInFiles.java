
/**
 * Write a description of wordsInFiles here.
 * 
 * @author Samuel Ng
 * @version 1.0.0
 */

import java.lang.*;
import java.util.*;
import java.io.*;
import edu.duke.*;

public class wordsInFiles {
    private HashMap<String, ArrayList<String>> wordMap;
    
    public wordsInFiles() {
        wordMap = new HashMap<String, ArrayList<String>>();
    }
    
    public void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);
        
        for (String word : fr.words()) {
            if (!wordMap.containsKey(word)) {
                ArrayList<String> fileNameList = new ArrayList<String>(); 
                fileNameList.add(f.getName());
                wordMap.put(word, fileNameList);
            } else {
                ArrayList<String> fileNames = wordMap.get(word);
                // check if the key associated arrList contains the same fileName
                if (!fileNames.contains(f.getName())) {
                   // get the associated arrList by key and add the filename to it
                   fileNames.add(f.getName());
                }
                
              
                
            }
        
        }
    }
    
    public void buildWordFileMap() {
        wordMap.clear();
        
        // prompt user to choose one or more files
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
        
    }
    
    public int maxNumber() {
        int max = Integer.MIN_VALUE;
        
        // iterate over key in hashmap
        for (String key: wordMap.keySet()) {
            if (wordMap.get(key).size() > max) {
                max = wordMap.get(key).size();
            }
        }
        
        return max;
        
    }
    
    public ArrayList wordsInNumFiles(int number) {
        ArrayList<String> wordList = new ArrayList<String>();
        
        for (String key: wordMap.keySet()) {
            // check if wordMap key has the associated arrList size that matches the given number
            if (wordMap.get(key).size() == number) {
                // append the word (key) into wordList
                wordList.add(key);
            }
        }
        
        return wordList;

    }
    
    public void printFilesIn(String word) {
        // get the associated arraylist via the key word
        ArrayList<String> fileNames = wordMap.get(word);
        
        // iterate iterables of the arraylist
        for (String fname: fileNames) {
            System.out.println(fname);
        }
        

    }
    
    public void tester() {
        buildWordFileMap();
        
        int maxOccur = maxNumber();
        
        
        
        ArrayList<String> wordList = wordsInNumFiles(maxOccur);
        
        for (String word: wordList) {
            System.out.println("\"" + word + "\" " + "appears in the files:");
            printFilesIn(word);
            System.out.println();
        }
        
        System.out.println("Total words in 4 files = " + wordsInNumFiles(4).size());
        System.out.println("Total words in 5 files = " + wordsInNumFiles(5).size());
        System.out.println("Total words in 7 files = " + wordsInNumFiles(7).size());
        
        printFilesIn("sea");
        System.out.println();
        
        printFilesIn("tree");
        System.out.println();
        System.out.println("Maximum no. of occurence in files: " + maxOccur);
        
    }
    
    

}
