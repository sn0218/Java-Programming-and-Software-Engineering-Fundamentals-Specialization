
/**
 * Write a description of MarkovWorld here.
 * 
 * @author Samuel Ng
 * @version 1.0.0
 */
import java.util.*;

public class MarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovWord(int order) {
        myOrder = order;
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - myOrder);  // random word to start with
        
        // choose a wordGram with given order at random from myText
        WordGram wg = new WordGram(myText, index, myOrder);
        sb.append(wg);
        sb.append(" ");
        
        for(int k=0; k < numWords - myOrder; k++){
            ArrayList<String> follows = getFollows(wg);

            //System.out.println("key " + key + " " + follows);
            if (follows.size() == 0) {
                break;
            }
            
            index = myRandom.nextInt(follows.size());
            
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            
            // update the wordGram to be the original wordGram that shifting by one + the next word
            wg = wg.shiftAdd(next);
        }
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        
        int pos = 0;
        // iterate over the text arr using post as pointer 
        while (pos < myText.length) {
            // flag to search for next possible occurence starting at pos
            int index = indexOf(myText, kGram, pos);
            
            // word not found in myText
            if (index == -1) {
                break;
            }
            
            if (index + myOrder > myText.length - 1 ) {
                break;
            }
            
            String next = myText[index + myOrder];
            follows.add(next);
            pos = index + 1;
        }
        
        return follows;
    }
    
    // helper method for search for the next occurence of wordGram in string arr (similiar to .indexOf method)
    private int indexOf(String[] words, WordGram target, int start) {
        // iterate over the arr to search for the wordGram
        for (int i = start; i < words.length - myOrder; i++) {
            // get the wordGram from String arr(words) given the order
            WordGram wg = new WordGram(words, i, myOrder);
            // compare the wordGram with the target
            if (wg.equals(target)) {
                return i;
            }

        }
        
        return -1;
    }
    
}
