
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        int seed = 1024;
        
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, seed);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);

        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);
        
       
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);
        
        /*
        IMarkovModel mz = new MarkovZero();
        runModel(mz, st, size);
    
        IMarkovModel mOne = new MarkovOne();
        runModel(mOne, st, size);
        
        IMarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size);
        
        IMarkovModel mFour = new MarkovFour();
        runModel(mFour, st, size);
        */

    }
    
    public void testHashMap() {
        //String st = "yes-this-is-a-thin-pretty-pink-thistle";
        //String st = "yes-this-is-a-thin";
        FileResource fr = new FileResource();
	String st = fr.asString();
        st = st.replace('\n', ' ');
        
        int size = 1000;
        int seed = 531;
        
        EfficientMarkovModel em = new EfficientMarkovModel(5);
        runModel(em, st, size, seed);
        
        em.printHashMapInfo();
        System.out.println("running with " + em);
        
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
    
    public void compareMethods() {
	int size = 1000;
	int seed = 42;
	int runningTimes = 3;
	
	FileResource fr = new FileResource();
	String st = fr.asString();
        st = st.replace('\n', ' ');
	
	MarkovModel markov = new MarkovModel(2);
	long t1 = System.nanoTime();
	for (int i = 0; i < runningTimes; i++) {
		runModel(markov, st, size, seed);
		//System.out.println("running with " + markov);
	}
	long t2 = System.nanoTime();
	
	long runTimeInNanoSec = t2 - t1;
	double seconds = (double) runTimeInNanoSec / 1000000000.0;
	System.out.println("Time taken for MarkovModel: " + seconds + " seconds");
	
	
	EfficientMarkovModel markovE = new EfficientMarkovModel(2);
	markovE.setTraining(st);
	markovE.setRandom(seed);
	
	t1 = System.nanoTime();;
	for (int i = 0; i < runningTimes; i++) {
		runModel(markovE, st, size, seed);
		//System.out.println("running with " + markovE);
	}
	t2 = System.nanoTime();
	runTimeInNanoSec = t2 - t1;
	seconds = (double) runTimeInNanoSec / 1000000000.0;
	System.out.println("Time taken for EfficientMarkovModel: " + seconds + " seconds");
	
}
    
    
}
