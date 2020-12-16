package com.techmojo.interview;

public class HashTagNotFoundException extends Exception {


  private final String message = "No hashtag in the tweet. Ignoring this tweet for calculation of trending "
                                     + "hashtag.";
  public HashTagNotFoundException() {
  }

  public HashTagNotFoundException(final String message) {
    super(message);
  }

  @Override
  public String getMessage() {
    return this.message;
  }
}
