
/**
 * Write a description of Tester here.
 * 
 * @author Samuel Ng
 * @version 1.0.0
 */
import java.util.*;
import edu.duke.*;

public class Tester {
    public void testGetFollows() {
        MarkovOne markov = new MarkovOne();
        String trainingText = "this is a test yes this is a test.";
        markov.setTraining(trainingText);
        ArrayList<String> follows = markov.getFollows(".");
        ArrayList<String> follows2 = markov.getFollows("t.");
        ArrayList<String> follows3 = markov.getFollows("t");
        ArrayList<String> follows4 = markov.getFollows("h");
        ArrayList<String> follows5 = markov.getFollows("e");
        ArrayList<String> follows6 = markov.getFollows("es");
        ArrayList<String> follows7 = markov.getFollows("thi");
        
        System.out.println(". " + follows);
        System.out.println("t. " + follows2);
        System.out.println("t " + follows3);
        System.out.println("h " + follows4);
        System.out.println("e " + follows5);
        System.out.println("es " + follows6);
        System.out.println("thi " + follows7);
    }
    
    public void testGetFollowsWithFile() {
        MarkovOne markov = new MarkovOne();
        FileResource fr = new FileResource();
        markov.setTraining(fr.asString());
        ArrayList<String> follows = markov.getFollows("o");
        System.out.println(follows.size());
        
        follows = markov.getFollows("he");
        System.out.println(follows.size());
        
        
    }
    
    public void testGetFollows2() {
        MarkovFour markov = new MarkovFour();
        String trainingText = "this is a test yes this is a test.";
        markov.setTraining(trainingText);
        ArrayList<String> follows = markov.getFollows(".");
        ArrayList<String> follows2 = markov.getFollows("t.");
        ArrayList<String> follows3 = markov.getFollows("t");
        ArrayList<String> follows4 = markov.getFollows("h");
        ArrayList<String> follows5 = markov.getFollows("e");
        ArrayList<String> follows6 = markov.getFollows("es");
        ArrayList<String> follows7 = markov.getFollows("thi");
        
        System.out.println(". " + follows);
        System.out.println("t. " + follows2);
        System.out.println("t " + follows3);
        System.out.println("h " + follows4);
        System.out.println("e " + follows5);
        System.out.println("es " + follows6);
        System.out.println("thi " + follows7);
    }
    
}
