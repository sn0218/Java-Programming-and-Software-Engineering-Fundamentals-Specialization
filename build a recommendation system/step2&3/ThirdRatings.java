
/**
 * Write a description of ThirdRatings here.
 * 
 * @author Samuel Ng 
 * @version 1.0.0
 */

import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile) {
        FirstRatings firstRatings = new FirstRatings();
        myRaters = firstRatings.loadRaters(ratingsfile);
       
    }
    
    public int getRaterSize() {
        return myRaters.size();
    }
    
    private double getAverageByID(String id, int minimalRaters) {
        double sum = 0.0;
        int count = 0;
        
        for (Rater rater : myRaters) {
            // check if the rater has the rating of given movie id
            if (rater.hasRating(id)) {
                sum += rater.getRating(id);
                count++;
            }
        }
        
        if (count >= minimalRaters) {
            return sum / count;
        }
        
        return 0.0;
    }
    
    private void computeAverageRatings(ArrayList<Rating> ratings, ArrayList<String> movies, int minimalRaters) {
        // for each movie, add its average ratings to the ratings list that was rated by minimalRaters
        for (String movieID : movies) {
            double averageRating = getAverageByID(movieID, minimalRaters);
            
            // if the movie has at least the minimal number of raters supplying a rating
            if (averageRating > 0.0) {
                Rating rating = new Rating(movieID, averageRating);
                ratings.add(rating);
            }
        }
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        
        // get a list of movies id from MovieDatabase class
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        // compute the list of average ratings
        computeAverageRatings(ratings, movies, minimalRaters);
               
        return ratings; 
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        // create an arrList of ratings of all movies that have at least minimalRaters and satisfies the filter criteria
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        
        // get a list of movies id from MovieDatabase class using filter
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        
        // compute the list of average ratings
        computeAverageRatings(ratings, movies, minimalRaters);
        
        return ratings;
    }
    
    
}
    
    
    

    

