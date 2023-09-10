
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author Samuel Ng
 * @version 1.0.0
 */

import edu.duke.*;
import java.util.*;

public class MovieRunnerWithFilters {
        public void printAverageRatings() {
        //ThirdRatings tr = new ThirdRatings("data/ratings_short.csv");
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        
        int raterNum = tr.getRaterSize();
        System.out.println("read data for " + raterNum + " raters");
        
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        int minimalRaters = 35;
        ArrayList<Rating> avgRatings = tr.getAverageRatings(minimalRaters);
        
        System.out.println("found " + avgRatings.size() + " movies");
        
        Collections.sort(avgRatings);
        
        for (Rating rating : avgRatings) {
            String movieID = rating.getItem();
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(movieID));
        }
        
        //System.out.println("No. of movies have 50 or more movies: " + avgRatings.size());
    }
    
    public void printAverageRatingsByYearAfter() {
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");

        int raterNum = tr.getRaterSize();
        System.out.println("read data for " + raterNum + " raters");
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        int minimalRaters = 20;
        int year = 2000;
        Filter f = new YearAfterFilter(year);
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(minimalRaters, f);
        
        System.out.println("found " + avgRatings.size() + " movies");
        
        Collections.sort(avgRatings);
        
        for (Rating rating : avgRatings) {
            String movieID = rating.getItem();
            System.out.println(rating.getValue() + " " + MovieDatabase.getYear(movieID) + " " + MovieDatabase.getTitle(movieID));
        }
        
    }
    
    public void printAverageRatingsByGenre() {
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");

        int raterNum = tr.getRaterSize();
        System.out.println("read data for " + raterNum + " raters");
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        int minimalRaters = 20;
        String genre = "Comedy";
        Filter f = new GenreFilter(genre);
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(minimalRaters, f);
        
        System.out.println("found " + avgRatings.size() + " movies");
        
        Collections.sort(avgRatings);
        
        for (Rating rating : avgRatings) {
            String movieID = rating.getItem();
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(movieID));
            System.out.println("\t" + MovieDatabase.getGenres(movieID));
        }
    }
    
    public void printAverageRatingsByMinutes() {
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
    
        int raterNum = tr.getRaterSize();
        System.out.println("read data for " + raterNum + " raters");
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        int minimalRaters = 5;
        int maxMin = 135;
        int minMin = 105;
        Filter f = new MinutesFilter(maxMin, minMin);
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(minimalRaters, f);
        
        System.out.println("found " + avgRatings.size() + " movies");
        
        Collections.sort(avgRatings);
        
        /*
        for (Rating rating : avgRatings) {
            String movieID = rating.getItem();
            System.out.println(rating.getValue() + " Time: " + MovieDatabase.getMinutes(movieID) + " " + MovieDatabase.getTitle(movieID));
        }
        */
    }
    
    public void printAverageRatingsByDirectors() {
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");

        int raterNum = tr.getRaterSize();
        System.out.println("read data for " + raterNum + " raters");
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        int minimalRaters = 4;
        String directors = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
        Filter f = new DirectorsFilter(directors);
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(minimalRaters, f);
        
        System.out.println("found " + avgRatings.size() + " movies");
        
        Collections.sort(avgRatings);
        
        /*
        for (Rating rating : avgRatings) {
            String movieID = rating.getItem();
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(movieID));
            System.out.println("\t" + MovieDatabase.getDirector(movieID));
        }
        */
    }
    
    public void printAverageRatingsByYearAfterAndGenre() {
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");

        int raterNum = tr.getRaterSize();
        System.out.println("read data for " + raterNum + " raters");
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        int minimalRaters = 8;
        int year = 1990;
        String genre = "Drama";
        
        AllFilters allF = new AllFilters();
        allF.addFilter(new YearAfterFilter(year));
        allF.addFilter(new GenreFilter(genre));
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(minimalRaters, allF);
        
        System.out.println("found " + avgRatings.size() + " movies");
        
        Collections.sort(avgRatings);
        
        /*
        for (Rating rating : avgRatings) {
            String movieID = rating.getItem();
            System.out.println(rating.getValue() + " " + MovieDatabase.getYear(movieID) + " " + MovieDatabase.getTitle(movieID));
            System.out.println("\t" + MovieDatabase.getGenres(movieID));
        }
        */
    }
    
    public void printAverageRatingsByDirectorsAndMinutes() {
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");

        int raterNum = tr.getRaterSize();
        System.out.println("read data for " + raterNum + " raters");
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        int minimalRaters = 3;
        int min = 90;
        int max = 180;
        String directors = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
        
        AllFilters allF = new AllFilters();
        allF.addFilter(new MinutesFilter(max, min));
        allF.addFilter(new DirectorsFilter(directors));
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(minimalRaters, allF);
        
        System.out.println("found " + avgRatings.size() + " movies");
        
        Collections.sort(avgRatings);
        
        for (Rating rating : avgRatings) {
            String movieID = rating.getItem();
            System.out.println(rating.getValue() + " Time: " + MovieDatabase.getMinutes(movieID) + " " + MovieDatabase.getTitle(movieID));
            System.out.println("\t" + MovieDatabase.getDirector(movieID));
        }
    }
}
