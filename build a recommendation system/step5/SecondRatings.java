
/**
 * Write a description of SecondRatings here.
 * 
 * @author Samuel Ng 
 * @version 1.0.0
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings firstRatings = new FirstRatings();
        myMovies = firstRatings.loadMovies(moviefile);
        myRaters = firstRatings.loadRaters(ratingsfile);
       
    }
    
    public int getMovieSize() {
        return myMovies.size();
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
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        
        // for each movie
        for (Movie movie : myMovies) {
            String movieID = movie.getID();
            double averageRating = getAverageByID(movieID, minimalRaters);
            
            // if the movie has at least the minimal number of raters supplying a rating
            if (averageRating > 0.0) {
                Rating rating = new Rating(movieID, averageRating);
                ratings.add(rating);
            }
        }
        
        return ratings; 
        
    }
    
    public String getTitle(String id) {
        String title = "The ID was not found.";
        
        for (Movie movie : myMovies) {
            if (movie.getID().equals(id)) {
                return movie.getTitle();
            }
        }
        
        return title;
    }
    
    public String getID(String title) {
        String id = "NO SUCH TITLE";
        
        for (Movie movie : myMovies) {
            if (movie.getTitle().equals(title)) {
                id = movie.getID();
                return id;
            }
        }
        
        return id;
    }
    
}
