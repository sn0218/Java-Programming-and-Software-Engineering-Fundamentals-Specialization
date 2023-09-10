
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author Samuel Ng
 * @version 1.0.0
 */

import edu.duke.*;
import java.util.*;

public class MovieRunnerSimilarRatings {
   public void printAverageRatings() {
        FourthRatings fr = new FourthRatings("ratings.csv");
        
        int raterNum = RaterDatabase.size();
        System.out.println("read data for " + raterNum + " raters");
        
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        int minimalRaters = 35;
        ArrayList<Rating> avgRatings = fr.getAverageRatings(minimalRaters);
        
        System.out.println("found " + avgRatings.size() + " movies");
        
        Collections.sort(avgRatings);
        
        for (Rating rating : avgRatings) {
            String movieID = rating.getItem();
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(movieID));
        }
        
        //System.out.println("No. of movies have 50 or more movies: " + avgRatings.size());
   }    
    
   public void printAverageRatingsByYearAfterAndGenre() {
        FourthRatings fr = new FourthRatings("ratings.csv");
            
        int raterNum = RaterDatabase.size();
        System.out.println("read data for " + raterNum + " raters");
            
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
            
        int minimalRaters = 8;
        int year = 1990;
        String genre = "Drama";
            
        AllFilters allF = new AllFilters();
        allF.addFilter(new YearAfterFilter(year));
        allF.addFilter(new GenreFilter(genre));
        ArrayList<Rating> avgRatings = fr.getAverageRatingsByFilter(minimalRaters, allF);
            
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
    
   public void printSimilarRatings() {
       FourthRatings fr = new FourthRatings("ratings.csv");
       
       int raterNum = RaterDatabase.size();
       System.out.println("read data for " + raterNum + " raters");
            
       MovieDatabase.initialize("ratedmoviesfull.csv");
       System.out.println("read data for " + MovieDatabase.size() + " movies");
       
       int minimalRaters = 5;
       int numSimilarRaters = 20;
       String raterId = "71";
       
       ArrayList<Rating> similarRatings = fr.getSimilarRatings(raterId, numSimilarRaters, minimalRaters);
       

       for (int i = 0 ; i < numSimilarRaters; i++) {
           System.out.println(MovieDatabase.getTitle(similarRatings.get(i).getItem()) + "\t" + similarRatings.get(i).getValue());
       }
       
      

   }
   
   public void printSimilarRatingsByGenre() {
       FourthRatings fr = new FourthRatings("ratings.csv");
       
       int raterNum = RaterDatabase.size();
       System.out.println("read data for " + raterNum + " raters");
            
       MovieDatabase.initialize("ratedmoviesfull.csv");
       System.out.println("read data for " + MovieDatabase.size() + " movies");
       
       int minimalRaters = 5;
       int numSimilarRaters = 20;
       String raterId = "964";
       
       Filter genreFilter = new GenreFilter("Mystery");
       ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter(raterId, numSimilarRaters, minimalRaters, genreFilter);
       

       for (int i = 0 ; i < numSimilarRaters; i++) {
           System.out.println(MovieDatabase.getTitle(similarRatings.get(i).getItem()) + "\t" + similarRatings.get(i).getValue());
           System.out.println("\t" + MovieDatabase.getGenres(similarRatings.get(i).getItem()));
          
       }
       
      
   }
   
   public void printSimilarRatingsByDirector() {
       FourthRatings fr = new FourthRatings("ratings.csv");
       
       int raterNum = RaterDatabase.size();
       System.out.println("read data for " + raterNum + " raters");
            
       MovieDatabase.initialize("ratedmoviesfull.csv");
       System.out.println("read data for " + MovieDatabase.size() + " movies");
       
       int minimalRaters = 2;
       int numSimilarRaters = 10;
       String raterId = "120";
       
       Filter directorsFilter = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
       ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter(raterId, numSimilarRaters, minimalRaters, directorsFilter);
       

       for (Rating r : similarRatings) {
           System.out.println(MovieDatabase.getTitle(r.getItem()) + "\t" + r.getValue());
           System.out.println("\t" + MovieDatabase.getDirector(r.getItem()));
          
       }
       
      
   }
   
   public void printSimilarRatingsByGenreAndMinutes() {
       FourthRatings fr = new FourthRatings("ratings.csv");
       
       int raterNum = RaterDatabase.size();
       System.out.println("read data for " + raterNum + " raters");
            
       MovieDatabase.initialize("ratedmoviesfull.csv");
       System.out.println("read data for " + MovieDatabase.size() + " movies");
       
       int minimalRaters = 3;
       int numSimilarRaters = 10;
       String raterId = "168";
       int min = 80;
       int max = 160;
       String genre = "Drama";
       
       AllFilters allF = new AllFilters();
       allF.addFilter(new MinutesFilter(max, min));
       allF.addFilter(new GenreFilter(genre));
       ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter(raterId, numSimilarRaters, minimalRaters, allF);
       

       for (Rating r : similarRatings) {
           System.out.println(MovieDatabase.getTitle(r.getItem()) + "\t" + r.getValue());
           System.out.println("\t" + MovieDatabase.getGenres(r.getItem()));
           System.out.println("\t" + MovieDatabase.getMinutes(r.getItem()));
          
       }
       
   }
   
   public void printSimilarRatingsYearAfterAndMinutes () {
       FourthRatings fr = new FourthRatings("ratings.csv");
       
       int raterNum = RaterDatabase.size();
       System.out.println("read data for " + raterNum + " raters");
            
       MovieDatabase.initialize("ratedmoviesfull.csv");
       System.out.println("read data for " + MovieDatabase.size() + " movies");
       
       int minimalRaters = 5;
       int numSimilarRaters = 10;
       String raterId = "314";
       int min = 70;
       int max = 200;
       int year = 1975;
       
       AllFilters allF = new AllFilters();
       allF.addFilter(new MinutesFilter(max, min));
       allF.addFilter(new YearAfterFilter(year));
       ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter(raterId, numSimilarRaters, minimalRaters, allF);
       

       for (Rating r : similarRatings) {
           System.out.println(MovieDatabase.getTitle(r.getItem()) + "\t" + r.getValue());
           System.out.println("\t" + MovieDatabase.getYear(r.getItem()));
           System.out.println("\t" + MovieDatabase.getMinutes(r.getItem()));
          
       }
       
      
   }
}
