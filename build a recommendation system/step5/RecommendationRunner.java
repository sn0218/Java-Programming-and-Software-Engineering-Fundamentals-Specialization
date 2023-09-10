
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author Samuel Ng 
 * @version 1.0.0
 */

import java.util.*;

public class RecommendationRunner implements Recommender {
    @Override
    public ArrayList<String> getItemsToRate () {
        ArrayList<String> movieList = new ArrayList<>();
        ArrayList<String> movieId = MovieDatabase.filterBy(new TrueFilter());
        
        for (int i = 0; movieList.size() < 10; i++) {
            Random rand = new Random();
            int randIndex = rand.nextInt(movieId.size());
            
            if (!movieList.contains(movieId.get(randIndex)))
                movieList.add(movieId.get(randIndex));
        }
        
        return movieList;
    }
    
    @Override
    public void printRecommendationsFor(String webRaterID) {
        /*
        FourthRatings fourthRatings = new FourthRatings ();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        System.out.println("<p>Read data for " + Integer.toString(RaterDatabase.size()) + " raters</p>");
        System.out.println("<p>Read data for " + Integer.toString(MovieDatabase.size()) + " movies</p>");
        
        int numSimilarRaters = 50; // variable
        int minNumOfRatings = 5; // variable
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatings(webRaterID, numSimilarRaters, minNumOfRatings);
        
        if (similarRatings.size() == 0) {
            System.out.println("No matching movies were found");
        } else {
            String header = ("<table> <tr> <th>Movie Title</th> <th>Rating Value</th>  <th>Genres</th> </tr>");
            String body = "";
            for (Rating rating : similarRatings) {
                body += "<tr> <td>" + MovieDatabase.getTitle(rating.getItem()) + "</td> <td>" 
                + Double.toString(rating.getValue()) + "</td> <td>" + MovieDatabase.getGenres(rating.getItem())
                + "</td> </tr> ";
            }
            System.out.println(header  + body + "</table>");
        }
        */
    
        
        FourthRatings fr = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");

        System.out.println("<p>Read data for " + Integer.toString(RaterDatabase.size()) + " raters</p>");
        System.out.println("<p>Read data for " + Integer.toString(MovieDatabase.size()) + " movies</p>");
        
        ArrayList<Rating> similarRatings = fr.getSimilarRatings(webRaterID, 50, 5);
        
        if (similarRatings.size() == 0) {
            System.out.println("<h2>Sorry, there are no movie recommend for you based on your rating!</h2>");
        } else {
            ArrayList<String> movieList = getItemsToRate();
            ArrayList<Rating> recommendations = new ArrayList<Rating>();
            int count = 0;
            
            for (int i = 0; recommendations.size() + count != similarRatings.size() && recommendations.size() < 10; i++) {
                if (!movieList.contains(similarRatings.get(i).getItem())) {
                    recommendations.add(similarRatings.get(i));
                } else {
                    count++;
                }
            }
            System.out.println("No. of Recommendations: " + recommendations.size());
            
            
            System.out.println("<style>");
            System.out.println("h2,h3{");
            System.out.println("  text-align: center;");
            System.out.println("  height: 50px;");
            System.out.println("  line-height: 50px;");
            System.out.println("  font-family: Arial, Helvetica, sans- serif;");
            System.out.println("  background-color: black;");
            System.out.println("   color:  #ff6600 }");
            
            System.out.println(" table {");
            System.out.println("   border-collapse: collapse;");
            System.out.println("   margin: auto;}");
            System.out.println("table, th, td {");
            System.out.println("    border: 2px solid white;");
            System.out.println("    font-size: 15px;");
            
            System.out.println("    padding: 2px 6px 2px 6px; }");
            System.out.println(" td img{");
            System.out.println("    display: block;");
            System.out.println("    margin-left: auto;");
            System.out.println("    margin-right: auto; }");
            System.out.println("th {");
            System.out.println("    height: 40px;");
            System.out.println("    font-size: 18px;");
            
            System.out.println("  background-color: black;");
            System.out.println(" color: white;");
            System.out.println("text-align: center; }");
            
            System.out.println(" tr:nth-child(even) {");
            System.out.println("     background-color: #f2f2f2; }");
            System.out.println("  tr:nth-child(odd) {");
            System.out.println("background-color: #cccccc; }");
            System.out.println(" tr:hover {");
            System.out.println(" background-color: #666666; ");
            System.out.println("  color:white;}");
            
            System.out.println("table td:first-child {");
            System.out.println(" text-align: center; }");
            
            System.out.println(" tr {");
            System.out.println(" font-family: Arial, Helvetica, sans-serif; }");
            System.out.println(".rating{");
            System.out.println("    color:#ff6600;");
            System.out.println("    padding: 0px 10px;");
            System.out.println("   font-weight: bold; }");
            System.out.println("</style>");
            
            
            System.out.println("<h2>Recommended Movies To You</h2>");
            System.out.println("<table id = \"rater\">");
            System.out.println("<tr>");
            System.out.println("<th>Rank</th>");
            
            System.out.println("<th>Poster</th>");
            System.out.println("<th>Title & Rating</th>");
            System.out.println("<th>Genre</th>");
            System.out.println("<th>Country</th>");
            System.out.println("</tr>");
            
           
            int rank = 1;
            for (Rating i : recommendations) {
                System.out.println("<tr><td>" + rank + "</td>" +
                        
                        "<td><img src = \"" + MovieDatabase.getPoster(i.getItem()) + "\" width=\"50\" height=\"70\"></td> " +
                        "<td>" + MovieDatabase.getYear(i.getItem()) + "&ensp;&ensp; <a href=\"https://www.imdb.com/title/tt" +
                        i.getItem() + "\">" + MovieDatabase.getTitle(i.getItem()) + "</a><br><div class = \"rating\">&starf; &ensp;&ensp;&ensp;"
                        + String.format("%.1f", i.getValue()) + "/10</td>" +
                        "<td>" + MovieDatabase.getGenres(i.getItem()) + "</td>" +
                        "<td>" + MovieDatabase.getCountry(i.getItem()) + "</td>" +
                        "</tr> ");
                rank++;
            }
        }
        System.out.println("</table>");
        System.out.println("<h3>*The rank of movies is based on other raters who have the most similar rating to yours. Enjoy!^^</h3>");
        
    }
}    
    

    

