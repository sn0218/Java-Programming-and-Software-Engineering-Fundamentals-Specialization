
/**
 * Write a description of EfficientRater here.
 * 
 * @author Samuel Ng
 * @version 1.0.0
 */

import java.util.*;

public class EfficientRater implements Rater {
    private String myID;
    private HashMap<String, Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String, Rating>();
    }

    public void addRating(String item, double rating) {
        myRatings.put(item, new Rating(item,rating)); // item refers to movie ID
    }

    public boolean hasRating(String item) {
        // check if the hashmap contains item(movieID)
        return myRatings.containsKey(item);
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {        
        if (myRatings.containsKey(item)) {
            return myRatings.get(item).getValue(); // get the associated rating by item(movieID)
        }
        
        return -1;
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>(myRatings.keySet());
        
        return list;
    }
}
