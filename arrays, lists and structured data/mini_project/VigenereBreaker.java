import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        
        // iterate over the input message
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            sb.append(message.charAt(i));
        }
        
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        // array of shifts
        int[] key = new int[klength];
        
        // hashmap for storing sliced string where key: shifted index, value: associated slice string
        HashMap<Integer, String> sliceStrings =  new HashMap<Integer, String>();
        
        // put slice string with associated key in map 
        for (int i = 0; i < klength; i++) {
            sliceStrings.put(i, sliceString(encrypted, i, klength));
        }
        
        CaesarCracker cracker = new CaesarCracker(mostCommon);
        
        // get the key (shift) for each slice string and put the key in key arr
        for (int i = 0; i < klength; i++) {
            // get the encrypted slice string
            String sliceEncrypted = sliceStrings.get(i);
            int dkey = cracker.getKey(sliceEncrypted);
            key[i] = dkey;
            
        }
        
        return key;
    }

    public void breakVigenere () {
        /* breakVigenere known key
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        
        System.out.println("");
        System.out.println(encrypted);
        
        String dkey = "flute";
        int klength = dkey.length();
        char mostCommon = 'e';
        
        int[] keys = tryKeyLength(encrypted, klength, mostCommon);
        VigenereCipher vc = new VigenereCipher(keys);
        System.out.println(vc.decrypt(encrypted));
        */
        
        /*
        // breakVigenere unknown key
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        
        FileResource fr2 = new FileResource("dictionaries/English");
        HashSet<String> dict = readDictionary(fr2);
        String decrypted = breakForLanguage(encrypted, dict);
        int firstLineBreak = decrypted.indexOf('\n');
        String firstLine = decrypted.substring(0, firstLineBreak);
        System.out.println(firstLine);
        */
        
        // breakVigenere unknown key and unknown language
        HashMap<String, HashSet<String>> masterDict = new HashMap<String, HashSet<String>>();
        String[] langs = {"Danish", "Dutch", "English", "French", 
            "German", "Italian", "Portuguese", "Spanish"};
        
        // read different lang dict into map
        for (String lang : langs) {
            FileResource fr = new FileResource("dictionaries/" + lang);
            masterDict.put(lang, readDictionary(fr));
            System.out.println("Reading " + lang + " dictionary and storing words...");
            
        }
        
        System.out.println("Storing completed.");
        
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        String decrypted = breakForAllLangs(encrypted, masterDict);
        int firstLineBreak = decrypted.indexOf('\n');
        String firstLine = decrypted.substring(0, firstLineBreak);
        System.out.println(firstLine);
    }
    
    public void tester() {
        /*
        String ss = sliceString("abcdefghijklm", 0, 3);
        System.out.println(ss);
        ss = sliceString("abcdefghijklm", 1, 3);
        System.out.println(ss);
        ss = sliceString("abcdefghijklm", 3, 5);
        System.out.println(ss);
        ss = sliceString("abcdefghijklm", 4, 5);
        System.out.println(ss);
        */
        
        FileResource fr = new FileResource("quiz_data/secretmessage1.txt");
        String encrypted = fr.asString();
        String dkey = "this";
        int klength = dkey.length();
        char mostCommon = 'e';
        
        int[] keys = tryKeyLength(encrypted, klength, mostCommon);
        System.out.print("The keys: ");
        for (int key : keys) {
            System.out.print(key + " ");
        }
        
        System.out.println();
        
        VigenereCipher vc = new VigenereCipher(keys);
        // print first line of the decrypted message
        String decrypted = vc.decrypt(encrypted);
        int firstLineBreak = decrypted.indexOf('\n');
        String firstLine = decrypted.substring(0, firstLineBreak);
        
        System.out.println(firstLine);
    }
    
    public HashSet<String> readDictionary(FileResource fr) {
        // set to store a list of English words
        HashSet<String> words = new HashSet<String>();
        
        for (String line : fr.lines()) {
            line = line.toLowerCase();
            words.add(line);
        }
        
        return words;
    }
    
    public int countWords(String message, HashSet<String> dictionary) {
        int count = 0;
        
        // split the input message to word (delimiter is any non-word char)
        for (String word : message.split("\\W+")) {
            // check if each word in dict
            if (dictionary.contains(word.toLowerCase())) {
                count++;
            }
            
        }
        
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        //char mostCommon = 'e';
        char mostCommon = mostCommonCharIn(dictionary);
        int largestCount = Integer.MIN_VALUE;
        int bestKeyLength = 0;
        String bestDecrypted = "";
        
        
        // try all key lengths from 1 to 100
        for (int i = 1; i <= 100; i++) {
            int[] keys = tryKeyLength(encrypted, i, mostCommon);
            VigenereCipher vc = new VigenereCipher(keys);
            String decrypted = vc.decrypt(encrypted);
            
            // count how many real words
            int count = countWords(decrypted, dictionary);

            // check if count > largestCount
            if (count > largestCount) {
                largestCount = count;
                bestKeyLength = i;
                bestDecrypted = decrypted;
            }
        }
        
        System.out.println("Best key length: " + bestKeyLength);
        System.out.println("Valid words: " + largestCount);
        return bestDecrypted;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary) {
        // create map for letter count
        HashMap<Character, Integer> counts = new HashMap<Character, Integer>();
        int maxCount = Integer.MIN_VALUE;
        char mostCommonChar = '\0';
        
        for (String word : dictionary) {
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (!counts.containsKey(ch)) {
                    counts.put(ch, 1);
                } else {
                    counts.put(ch, counts.get(ch) + 1);
                    
                }
            }
            
        }
        
        for (Character ch : counts.keySet()){
            if (counts.get(ch) > maxCount) {
                maxCount = counts.get(ch);
                mostCommonChar = ch.charValue();
            }
        }
        
        return mostCommonChar;
    }
    
    public String breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        int maxValidWords = Integer.MIN_VALUE;
        String bestLang = "";
        String bestDecrypted = "";
        
        for (String lang : languages.keySet()) {
             System.out.println("Language in process: " + lang);
             HashSet<String> dict = languages.get(lang);   
             String curDecrypted = breakForLanguage(encrypted, dict);
             int validWords = countWords(curDecrypted, dict);
             
             if (validWords > maxValidWords) {
                 maxValidWords = validWords;
                 bestLang = lang;
                 bestDecrypted = curDecrypted;
             }
             
             
        }
        
        System.out.println("The best language: " + bestLang);
        System.out.println();
        //System.out.println(bestDecrypted);
        return bestDecrypted;
    }
    
    
}
