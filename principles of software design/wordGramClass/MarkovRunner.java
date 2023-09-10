
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        int order = 5;
        MarkovWord markovWord = new MarkovWord(order); 
        runModel(markovWord, st, 200, 844); 
    } 

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 
    
    public void testHashMap() {
        FileResource fr = new FileResource();
	String st = fr.asString();
        st = st.replace('\n', ' ');
        //String st = "this is a test yes this is really a test yes a test this is wow";
        
        int seed = 65;
        int size = 200;
        int order = 2;
        EfficientMarkovWord emw = new EfficientMarkovWord(order);
        runModel(emw, st, size, seed);
        
        emw.printHashMapInfo();
        System.out.println("running with " + emw);
    }
    
    public void compareMethods() {
	int size = 100;
	int seed = 42;
	int runningTimes = 3;
	
	FileResource fr = new FileResource();
	String st = fr.asString();
        st = st.replace('\n', ' ');
	
	MarkovWord markov = new MarkovWord(2);
	long t1 = System.nanoTime();
	for (int i = 0; i < runningTimes; i++) {
		runModel(markov, st, size, seed);
		//System.out.println("running with " + markov);
	}
	long t2 = System.nanoTime();
	
	long runTimeInNanoSec = t2 - t1;
	double seconds = (double) runTimeInNanoSec / 1000000000.0;
	System.out.println("Time taken for MarkovWord: " + seconds + " seconds");
	
	
	EfficientMarkovWord emarkovE = new EfficientMarkovWord(2);
	emarkovE.setTraining(st);
	emarkovE.setRandom(seed);
	
	t1 = System.nanoTime();;
	for (int i = 0; i < runningTimes; i++) {
		runModel(emarkovE, st, size, seed);
		//System.out.println("running with " + markovE);
	}
	t2 = System.nanoTime();
	runTimeInNanoSec = t2 - t1;
	seconds = (double) runTimeInNanoSec / 1000000000.0;
	System.out.println("Time taken for EfficientMarkovWord: " + seconds + " seconds");
	
}
    
}
