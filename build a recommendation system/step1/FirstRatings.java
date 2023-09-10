
/**
 * Write a description of FirstRatings here.
 * 
 * @author Samuel Ng
 * @version 1.0.0
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename) {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        
        // for each row in csv file
        for (CSVRecord record : parser) {
            // create movie instance from csv data
            Movie movieRecord = new Movie(record.get("id"), record.get("title"), record.get("year"),
            record.get("genre"), record.get("director"),record.get("country"), record.get("poster"), 
            Integer.parseInt(record.get("minutes")));
            
            // add a movie instance in arrList
            movies.add(movieRecord);
        }
        
        
        return movies;
    }
    
    public ArrayList<Rater> loadRaters(String filename) {
        ArrayList<Rater> raters = new ArrayList<Rater>();
        
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        
        for (CSVRecord record : parser) {
            Boolean addedFlag = false;
            
            // get the rater id, movie id and rating
            String raterID = record.get("rater_id");
            String movieID = record.get("movie_id"); 
            Double rating = Double.parseDouble(record.get("rating"));
            
            // check if a rater exists in raters
            for (Rater rater : raters) {
                if (rater.getID().equals(raterID)) {
                    rater.addRating(movieID, rating);
                    addedFlag = true;
                    break;
                }
                
            }
            
            // add a new rater if the rater does not exist (addedFlag is false)
            if (!addedFlag) {
                Rater newRater = new Rater(raterID);
                newRater.addRating(movieID, rating);
                raters.add(newRater);
            }
            
        }
        
        return raters;
    }
    
    public void testLoadRaters() {
        //ArrayList<Rater> raters = loadRaters("data/ratings_short.csv");
        ArrayList<Rater> raters = loadRaters("data/ratings.csv");
        System.out.println("Total no. of raters: " + raters.size());
        
        /*
        for (Rater rater : raters) {
            System.out.println("Rater's ID: " + rater.getID() + ", No. of ratings: " + rater.numRatings());
            ArrayList<String> itemsRated = rater.getItemsRated();
            for (String item : itemsRated) {
                System.out.println("Movie ID: " + item + ", Rating: " + rater.getRating(item));
            }
        }
        */
        System.out.println();
        for (Rater rater: raters) {
            if (rater.getID().equals("193")) {
                System.out.println(rater.getID() + " has " + rater.getItemsRated().size() + " ratings.");
            }
        }
        
        System.out.println();
        
        int maxRatingNum = Integer.MIN_VALUE;
        int raterNum = 0;
        ArrayList<String> maxRaterIDs = new ArrayList<String>();
        
        for (Rater rater : raters) {
            int ratingNum = rater.getItemsRated().size();
            if (ratingNum > maxRatingNum) {
                maxRatingNum = ratingNum;
            }
        }
        
        for (Rater rater : raters) {
            if (rater.getItemsRated().size() == maxRatingNum) {
                maxRaterIDs.add(rater.getID());
                raterNum++;
            }
        }
        System.out.println("Max no. of ratings: " + maxRatingNum);
        System.out.println("No. of raters have the max no. of ratings: " + raterNum);
        System.out.println("Raters who has the max no. of ratings: ");
        for (String raterID : maxRaterIDs) {
            System.out.println(raterID);
        }
        
        System.out.println();
        int raterNumForMovie = 0;
        String movieID = "1798709";
        for (Rater rater : raters) {
            if (rater.hasRating(movieID)) {
                raterNumForMovie++;
            }
        }
        System.out.println("Movie \"1798709\" has " + raterNumForMovie + " raters.");
        
        System.out.println();
        
        HashMap<String, Integer> movieRatingCounts = new HashMap<String, Integer>();
        for (Rater rater : raters) {
            ArrayList<String> itemsRated = rater.getItemsRated();
            for (String item : itemsRated) {
                if (!movieRatingCounts.containsKey(item)) {
                    movieRatingCounts.put(item, 1);
                } else {
                    int count = movieRatingCounts.get(item);
                    movieRatingCounts.put(item, count + 1);
                }
            }
           
        }
        System.out.println("No. of movies rated: " + movieRatingCounts.size());
    }
    
    public void testLoadMovies() {
        ArrayList<Movie> movies = loadMovies("data/ratedmoviesfull.csv");
        //ArrayList<Movie> movies = loadMovies("data/ratedmovies_short.csv");
        System.out.println("No. of movies: " + movies.size());
        
        int genCount = 0;
        int minCount = 0;
        HashMap<String, Integer> directorAppears = new HashMap<String, Integer>();
        
        for (Movie movieRec : movies) {
            String directorName = movieRec.getDirector();
            
            if (movieRec.getGenres().contains("Comedy")) {
                genCount++;
            }
            
            if (movieRec.getMinutes() > 150) {
                minCount++;
            }
            
            if (!directorAppears.containsKey(directorName)) {
               directorAppears.put(directorName, 1); 
            } else {
                int appearances = directorAppears.get(directorName);
                directorAppears.put(directorName, appearances + 1);
            }
            
        }
        
        String directorsMostMovies = "";
        int maxAppearances = Integer.MIN_VALUE;
        int dirNum = 0;
        
        for (String key : directorAppears.keySet()) {
            if (directorAppears.get(key) > maxAppearances) {
                maxAppearances = directorAppears.get(key);
            }
            
        }
        
        for (String key : directorAppears.keySet()) {
            if (directorAppears.get(key) == maxAppearances) {
                directorsMostMovies += key + ", ";
                dirNum++;
            }
            
        }
        // remove trailing comma
        directorsMostMovies = directorsMostMovies.replaceAll(", $", "");
        
        System.out.println("No. of movies with Comedy genre: " + genCount);
        System.out.println("No. of movies greater than 150 mins: " + minCount);
        System.out.println("Max. no. of movies by any director: " + maxAppearances);
        System.out.println("No. of directors directed max no. of movies: " + dirNum);
        System.out.println("Director with max no. of movies: " + directorsMostMovies);
    }
}
