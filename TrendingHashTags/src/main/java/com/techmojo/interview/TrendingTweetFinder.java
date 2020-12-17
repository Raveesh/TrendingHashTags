package com.techmojo.interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 * Program to calculate top 10 trending hashtags given a specific number
 * of tweets by the user.
 *
 * @author raveesh
 */
public class TrendingTweetFinder {

    public static final String WELCOME_MESSAGE = "Twitter trending hashtags finder program. ";
    public static final String QUIT ="quit";
    public static final String QUIT_HELP_MESSAGE = "Press quit at any time to get the top 10 trending hashtags entered";

    private static final Map<String,Long> hashTagCountMap = new LinkedHashMap<>(); //we may choose to preserve the order of
    // the tweets
    public static void main( String[] args ) {
        printWelcomeMessage();
        while(true){
            Scanner sc= new Scanner(System.in);
            System.out.println("Please enter the Tweet Message:");
            String tweet = sc.nextLine();
            if(tweet.equalsIgnoreCase(QUIT)){
                printTopTenTrendingHashtags();
                break;
            }
            addHashTagsToCountingMap(tweet);
        }
    }

    public static void addHashTagsToCountingMap(String tweet) {
        try {
            List<String> hashTagList = extractHashTagFromTweet(tweet);
            for(String hashTag : hashTagList){
                if (!hashTagCountMap.containsKey(hashTag)) {
                    hashTagCountMap.put(hashTag,1L);
                } else {
                    long count = hashTagCountMap.get(hashTag);
                    count = count+1;
                    hashTagCountMap.put(hashTag,count);
                }
            }

        } catch (HashTagNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public static MaxLimitList<String> printTopTenTrendingHashtags() {
        System.out.println("Calculating trending hashtags");
        // Create a list from elements of HashMap
        List<Entry<String, Long> > list =
            new LinkedList<Entry<String, Long> >(hashTagCountMap.entrySet());

        sortListOfTweetsInDescendingOrder(list);

        // put data from sorted list to hashmap
        MaxLimitList<String> top10TrendingHashTagList = new MaxLimitList<>(10);
        for (Entry<String, Long> aa : list) {
            top10TrendingHashTagList.add(aa.getKey());
        }
        System.out.println("Top 10 trending hashtags : ");
        System.out.print(top10TrendingHashTagList);
        return top10TrendingHashTagList;

    }

    private static void sortListOfTweetsInDescendingOrder(List<Entry<String, Long>> list) {
        // Sort the list in descending order
        Collections.sort(list, new Comparator<Entry<String, Long> >() {
            public int compare(Entry<String, Long> o1,
                Entry<String, Long> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
    }

    private static void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
        printUnderLineForWelcomeMessage();
        System.out.println(QUIT_HELP_MESSAGE);
        System.out.println();
    }

    private static List<String> extractHashTagFromTweet(String tweet)  throws HashTagNotFoundException{
        if(!tweet.contains("#"))throw  new HashTagNotFoundException();
        List<String> hashTagList = new ArrayList<>();
        String[] wordArray = tweet.split(" ");
        for (String s: wordArray) {
            if(s.contains("#"))
                hashTagList.add(s.substring(1,s.length())); // remove hashTag and add to list
        }
        return hashTagList;

    }

    private static void printUnderLineForWelcomeMessage() {
        for(int i=0;i<WELCOME_MESSAGE.length();i++){
            System.out.print("-");
        }
        System.out.println();
    }

    //For Testing Purposes
    public static Map<String, Long> getHashTagCountMap() {
        return Collections.unmodifiableMap(hashTagCountMap);
    }

    public static void clear(){
        hashTagCountMap.clear();
    }
}
