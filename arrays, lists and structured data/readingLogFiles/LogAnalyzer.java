
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author Samuel
 * @version 1.0.0
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // initialize instance var (ArrList of LogEntry object)
         records = new ArrayList<LogEntry>();
         
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         
         // iterate over lines in the file
         for (String line : fr.lines()) {
                
               // create log entry object by prewritten weblogparser
               LogEntry log = WebLogParser.parseEntry(line);
               
               records.add(log);
            }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs() {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         
         for (LogEntry le : records) {
             // get the ip in each log entry
             String ipAddress = le.getIpAddress();
             
             // check if it is in uniqueIPs
             if (!uniqueIPs.contains(ipAddress)) {
                 uniqueIPs.add(ipAddress);
             }
         }
         
         return uniqueIPs.size();
         
     }
     
     public void printAllHigherThanNum(int num) {
         ArrayList<LogEntry> entries = new ArrayList<LogEntry>();
         
         for (LogEntry le : records) {
             // get the status code in each log entry
             int statusCode = le.getStatusCode();
             
             // check if it is higher than num
             if (statusCode > num) {
                 entries.add(le);
                }
            }
            
         for (LogEntry le : entries) {
             System.out.println(le);
            };
        }
        
     public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
         ArrayList<String> uniqueIPsGivenDay = new ArrayList<String>();
         
         for (LogEntry le : records) {
             // get access time
             String accessTime = le.getAccessTime().toString();
             String ipAddress = le.getIpAddress();
             //System.out.println(accessTime);
             
             // check if access time contains someday
             if (!uniqueIPsGivenDay.contains(ipAddress) && accessTime.contains(someday)) {
                 //System.out.println(ipAddress + " " + accessTime);
                 uniqueIPsGivenDay.add(ipAddress);
             }

             
            }
         
         return uniqueIPsGivenDay;
        }
        
     public int countUniqueIPsInRange(int low, int high) {
         ArrayList<String> uniqueIPsInRange = new ArrayList<String>();
         
         for (LogEntry le : records) {
             String ipAddress = le.getIpAddress();
             int statusCode = le.getStatusCode();
             
             if ((statusCode >= low && statusCode <= high) && !uniqueIPsInRange.contains(ipAddress)) {
                 System.out.println(ipAddress + " " + statusCode);
                 uniqueIPsInRange.add(ipAddress);
                }
            }
         
         return uniqueIPsInRange.size();
     }
     
     public HashMap<String, Integer> countVisitsPerIP() {
         HashMap<String, Integer> ipCounts = new HashMap<String, Integer>();
         
         for (LogEntry le : records) {
             // get the ip address from le as key in hashmap
             String ip = le.getIpAddress();
             
             // check if the IP in the hashMap
             if (!ipCounts.containsKey(ip)) {
                 ipCounts.put(ip, 1);
             } else {
                 ipCounts.put(ip, ipCounts.get(ip) + 1);
             }
         }
         
         return ipCounts;
     }
     
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> ipCounts) {
         int maxCount = Integer.MIN_VALUE;
         
         // iterate over the hashmap.values (collection of values in the map)
         for (int count : ipCounts.values()) {
             if (count > maxCount) {
                 maxCount = count;
                }
            }
            
         return maxCount;
         
     }
        
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> ipCounts) {
         ArrayList<String> iPs = new ArrayList<String>();
         int maxNumVisits = mostNumberVisitsByIP(ipCounts);
         
         // iterate over all key-value pairs in the map
         for (Map.Entry<String, Integer> entry : ipCounts.entrySet()) {
             int count = entry.getValue();
             String ip = entry.getKey();
             
             // if ip matches the maximum # of visits
             if (count == maxNumVisits) {
                 iPs.add(ip);
                }
         }
         
         return iPs;
     }
     
     public String getMonthAndDay(String le) {
         // format of le: Wed Sep 30 20:00:32 CST 2015
         String[] parts = le.split(" ");
         String month = parts[1];
         String day = parts[2];
         
         return month + " " + day; 
        }
     
     public HashMap<String, ArrayList<String>> iPsForDays() {
         // create map for iPsForDays key:MMM DD, value: arrList of ips
         HashMap<String, ArrayList<String>> iPsForDays = new HashMap<String, ArrayList<String>>();
         
         
         for (LogEntry le : records) {
             String ip = le.getIpAddress();
             String accessTime = le.getAccessTime().toString();
             String date = getMonthAndDay(accessTime);
             ArrayList<String> ips = new ArrayList<String>();
             
             // check if the map contains the key of day
             if (!iPsForDays.containsKey(date)) {
                 ips.add(ip);
                 iPsForDays.put(date, ips);
             }
             else {
                 // update the arrList of ip in hashmap
                 ips = iPsForDays.get(date);
                 ips.add(ip);
                 // update map if it contains the date
                 iPsForDays.put(date, ips);
                }
                
            }
             

            
         return iPsForDays;
     }
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> iPsForDays) {
         String maxDay = "";
         int max = Integer.MIN_VALUE;
         
         for (String date : iPsForDays.keySet()) {
             int visitNum = iPsForDays.get(date).size();
             
             if (visitNum > max) {
                 max = visitNum;
                 maxDay = date;
                }
             
         }
         
         return maxDay;
     }
     
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> iPsForDays, String day) {
         // get the iPs for given day in map
         ArrayList<String> iPs = iPsForDays.get(day);
         
         // create a hashmap for the counting to remove duplicate
         HashMap<String, Integer> ipCounts = new HashMap<String, Integer>();
         
         // iterate over iPs
         for (String ip : iPs) {
             if (!ipCounts.containsKey(ip)) {
                 ipCounts.put(ip, 1);
             } else {
                 ipCounts.put(ip, ipCounts.get(ip) + 1);
                }
            }

         return iPsMostVisits(ipCounts);
     }
        
        
}
