
/**
 * Write a description of ThirdRatings here.
 * 
 * @author Samuel Ng 
 * @version 1.0.0
 */

import java.util.*;

public class FourthRatings {
    public FourthRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public FourthRatings(String ratingsfile) {
        FirstRatings firstRatings = new FirstRatings();
        RaterDatabase.initialize(ratingsfile);
    }
    
    
    private double getAverageByID(String id, int minimalRaters) {
        double sum = 0.0;
        int count = 0;
        
        for (Rater rater : RaterDatabase.getRaters()) {
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
    
    /**
     * This method should first translate a rating from the scale 0 to 10
     * to the scale -5 to 5 and return the dot product of the ratings of
     * movies that they both rated.
     * This method will be called by getSimilarities.
    **/
     
    private double dotProduct(Rater me, Rater r) {
        double dotProduct = 0;
        ArrayList<String> myRatedMovieIdList = me.getItemsRated();
        ArrayList<String> anotherRatedMovieIdList = r.getItemsRated();
        
        // iterate over my rated movie id list
        for (String movieID : myRatedMovieIdList) {
            // check another rated MovieID list contains my rated movie Id
            // if yes, the movieID is rated by me and another person (r), then compute dot product
            if (anotherRatedMovieIdList.contains(movieID)) {
                dotProduct += (me.getRating(movieID) - 5) * (r.getRating(movieID) - 5);
            }
        }
        
        return dotProduct;
    }
    
    /**
     * this method computes a similarity rating for each rater in the RaterDatabase 
     * (except the rater with the ID given by the parameter) to see 
     * how similar they are to the Rater whose ID is the parameter to getSimilarities. 
     * This method returns an ArrayList of type Rating sorted by ratings 
     * from highest to lowest rating with the highest rating first and 
     * only including those raters who have a positive similarity rating since those with negative values are not similar in any way.
     */
    
    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> similarityRatings = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        
        for (Rater r : RaterDatabase.getRaters()) {
            double similarityVal = -1;
            
            if (!me.getID().equals(r.getID())) {
                similarityVal = dotProduct(me, r);
            }
            
            if (similarityVal > 0) {
                /** 
                 * each Rating object the item field is a rater’s ID, and the value field is 
                 * the dot product comparison between that rater and the rater whose ID is the parameter to getSimilarities
                 */
                //System.out.println(similarityVal);
                similarityRatings.add(new Rating(r.getID(), similarityVal));
            }
        }
        
        Collections.sort(similarityRatings, Collections.reverseOrder());
        
        return similarityRatings;
    }
    
    /**
     *  This method should return an ArrayList of type Rating, of movies and 
     *  their weighted average ratings using only the top numSimilarRaters with positive ratings and 
     *  including only those movies that have at least minimalRaters ratings from those most similar raters 
     *  (not just minimalRaters ratings overall).
     */
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRater, int minimalRaters) {
        ArrayList<Rating> weightedAvgRatings = new ArrayList<Rating>();
        ArrayList<Rating> similarityRatings = getSimilarities(id);
        
        // for each movie, calculate a weighted average movie rating
        for (String movieId : MovieDatabase.filterBy(new TrueFilter())) {
            double sum = 0;
            double wAvg = 0;
            double raterCount = 0;
            // for each rater based on top numSimilarRater
            for (int i = 0; i < numSimilarRater; i++) {
                // get each similarityRating by index
                Rating similarityRating = similarityRatings.get(i);
                String raterId = similarityRating.getItem();
                double similarityRatingVal = similarityRating.getValue();

                // use rater id to get the corresponding rating on the movie
                double raterRating = RaterDatabase.getRater(raterId).getRating(movieId);
                
                // if raterRating = -1, it means the rater does not rater the movie
                if (raterRating <= 0) {
                    continue;
                }
                
                //System.out.println(raterRating);
                //System.out.println(similarityRatingVal);
                
                // compute sum of weighted average ratings (The closest one with largest weight)
                sum += similarityRatingVal * raterRating;
                raterCount++;
                //System.out.println(sum);
            }
            
            // compute weighted average ratings if raterCount >= minimal Raters
            if (raterCount >= minimalRaters) {
                wAvg = sum / raterCount;
            } 
            
            if (wAvg > 0) {
                // add weighted average rating of the movie to the list
                weightedAvgRatings.add(new Rating(movieId , wAvg));
            }
            
        }
        
        Collections.sort(weightedAvgRatings, Collections.reverseOrder());
        
        return weightedAvgRatings;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRater, int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> weightedAvgRatings = new ArrayList<Rating>();
        ArrayList<Rating> similarityRatings = getSimilarities(id);
        
        // for each movie, calculate a weighted average movie rating
        for (String movieId : MovieDatabase.filterBy(filterCriteria)) {
            double sum = 0;
            double wAvg = 0;
            double raterCount = 0;
            // for each rater based on top numSimilarRater
            for (int i = 0; i < numSimilarRater; i++) {
                // get each similarityRating by index
                Rating similarityRating = similarityRatings.get(i);
                String raterId = similarityRating.getItem();
                double similarityRatingVal = similarityRating.getValue();

                // use rater id to get the corresponding rating on the movie
                double raterRating = RaterDatabase.getRater(raterId).getRating(movieId);
                
                // if raterRating = -1, it means the rater does not rater the movie
                if (raterRating <= 0) {
                    continue;
                }

                // compute sum of weighted average ratings (The closest one with largest weight)
                sum += similarityRatingVal * raterRating;
                raterCount++;
            }
            
            // compute weighted average ratings if raterCount >= minimal Raters
            if (raterCount >= minimalRaters) {
                wAvg = sum / raterCount;
            } 
            
            if (wAvg > 0) {
                // add weighted average rating of the movie to the list
                weightedAvgRatings.add(new Rating(movieId , wAvg));
            }
            
        }
        
        Collections.sort(weightedAvgRatings, Collections.reverseOrder());
        
        return weightedAvgRatings;
        
    }
}
    
    
    

    

