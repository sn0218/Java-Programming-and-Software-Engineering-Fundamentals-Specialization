
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        for (String word : myWords) {
            ret += word + " ";
        }

        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        // compare the length with another wordGram
        if (this.length() != other.length()) {
            return false;
        }
        
        // compare string in wordGram
        for (int i = 0; i < myWords.length; i++) {
            if (!myWords[i].equals(other.wordAt(i))) {
                return false;
            }
        }
        
        return true;

    }

    public WordGram shiftAdd(String word) {	
        // get a string arr copy from myWords
        String[] newWords = new String[myWords.length];
        System.arraycopy(myWords, 0, newWords, 0, myWords.length);
       
        // shift all words one towards 0 and add word at the end. 
        for (int i = 0; i < newWords.length - 1; i++) {
            newWords[i] = newWords[i+1];
        }
        newWords[newWords.length - 1] = word;
        
        WordGram out = new WordGram(newWords, 0, myWords.length);
        return out;
    }
    
    public int hashCode() {
       String str = this.toString();
       return str.hashCode();
    }
    
    
}