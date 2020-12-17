package com.techmojo.interview;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple TrendingHashTags.
 */
public class TrendingTweetFinderTest {

  @Before
  public void destroy(){
    TrendingTweetFinder.clear();
  }
  @Test
  public void addHashTagsToCountingMap() {
    Stream<String> tweetStream = Stream.of("#test this is the first tweet","#test this is the second tweet");
    tweetStream.forEach(tweet -> TrendingTweetFinder.addHashTagsToCountingMap(tweet));
    assertTrue(TrendingTweetFinder.getHashTagCountMap().containsKey("test"));
    assertEquals(2,TrendingTweetFinder.getHashTagCountMap().get("test").longValue());

  }

  @Test
  public void testNoHashTags() {
    Stream<String> tweetStream = Stream.of(" this is the first tweet"," this is the second tweet");
    tweetStream.forEach(tweet -> TrendingTweetFinder.addHashTagsToCountingMap(tweet));
    assertTrue(TrendingTweetFinder.getHashTagCountMap().isEmpty());

  }

  @Test
  public void printTopTenTrendingHashtags() {

    List<String> tweetList  = new ArrayList<>();
    String[] sampleHashTags = {"Java","C++","C","Haskell","Elixir","Golang","Swift","Groovy","Python",
        "Javascript","Nodejs","Ruby","Erlang"};
    //We create a lot of tweets for Java and have very few tweets of Erlang and test that erlang doesnt appear
    //in the top 10
    for(int i=0;i<50;i++){
      tweetList.add("#"+sampleHashTags[0]+" is awesome");
    }
    Random random = new Random();
    for(int i=0;i<30;i++){
      int idx = random.nextInt(11);
      tweetList.add("#"+sampleHashTags[idx]+" is also awesome");
    }
    tweetList.add("#"+sampleHashTags[12]+" is not that awesome");
    tweetList.forEach(TrendingTweetFinder :: addHashTagsToCountingMap);

    MaxLimitList<String> trendingList = TrendingTweetFinder.printTopTenTrendingHashtags();
    assertTrue(trendingList.get(0).equals("Java"));
    Assert.assertFalse(trendingList.contains("Ruby"));
  }
}
