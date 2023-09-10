
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author Samuel Ng
 * @version 1.0.0
 */

import edu.duke.*;
import java.util.*;

public class MovieRunnerAverage {
    public void printAverageRatings() {
        //SecondRatings secondRatings = new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");
        SecondRatings secondRatings = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        int movieNum = secondRatings.getMovieSize();
        int raterNum = secondRatings.getRaterSize();
        System.out.println(movieNum + " movies");
        System.out.println(raterNum + " raters");
        
        System.out.println();
        int minimalRaters = 50;
        ArrayList<Rating> avgRatings = secondRatings.getAverageRatings(minimalRaters);
        
        Collections.sort(avgRatings);
        
        for (Rating rating : avgRatings) {
            String movieID = rating.getItem();
            System.out.println(rating.getValue() + " " + secondRatings.getTitle(movieID));
        }
        
        System.out.println("No. of movies have 50 or more movies: " + avgRatings.size());
    }
    
    public void getAverageRatingOneMovie() {
        //SecondRatings secondRatings = new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");
        SecondRatings secondRatings = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        
        int minimalRaters = 3;
        
        String movieTitle = "Vacation";
        String movieID = secondRatings.getID(movieTitle);
        
        ArrayList<Rating> avgRatings = secondRatings.getAverageRatings(minimalRaters);

        for (Rating rating : avgRatings) {
            if (rating.getItem().equals(movieID)) {
                System.out.println("Average ratings of " + movieTitle + ": " + rating.getValue());
            }
        }
    }
    
}
