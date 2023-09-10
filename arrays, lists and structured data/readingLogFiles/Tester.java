
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // create a LogAnalyzer object
        LogAnalyzer myAnalyzer = new LogAnalyzer();
        String filename = "weblog2_log";
        myAnalyzer.readFile(filename);
        myAnalyzer.printAll();
        System.out.println();
        
        int uniqueIps = myAnalyzer.countUniqueIPs();
        System.out.println("There are " + uniqueIps + " unique IP addresses.");
        System.out.println();
        
        myAnalyzer.printAllHigherThanNum(400);
        System.out.println();
        
        String date = "Sep 27";
        ArrayList<String> uniqueIPsGivenDay = myAnalyzer.uniqueIPVisitsOnDay(date);
        System.out.println("No. of unique IPs access on " + date + ": " + uniqueIPsGivenDay.size());
        System.out.println();
        
        int lo = 200;
        int hi = 299;
        int ipsInRange = myAnalyzer.countUniqueIPsInRange(lo, hi);
        System.out.println("No. of unique IPs in range " + lo + " - " + hi + ": " + ipsInRange);
        System.out.println();
        
        // most no. of visits by ip
        HashMap<String, Integer> ipCounts = myAnalyzer.countVisitsPerIP();
        System.out.println("Most no. of visits by ip: " + myAnalyzer.mostNumberVisitsByIP(ipCounts));
        System.out.println("IP most vistis: " + myAnalyzer.iPsMostVisits(ipCounts));
       
        System.out.println();
        
        //System.out.println(myAnalyzer.iPsForDays());
        String dayWithMostIPVisit = myAnalyzer.dayWithMostIPVisits(myAnalyzer.iPsForDays());
        System.out.println("The day with Most IP visits: " + dayWithMostIPVisit);
        System.out.println();
        
        System.out.println("IPs with most visits on " + dayWithMostIPVisit + ": ");
        ArrayList<String> ips = myAnalyzer.iPsWithMostVisitsOnDay(myAnalyzer.iPsForDays(), "Sep 30");
        System.out.println(ips.size());
        for (String ip : ips) {
            System.out.println(ip);
        }
    }
}
