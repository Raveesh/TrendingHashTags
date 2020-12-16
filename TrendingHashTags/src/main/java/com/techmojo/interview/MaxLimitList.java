package com.techmojo.interview;

import java.util.ArrayList;
import java.util.LinkedList;

public class MaxLimitList<E> extends ArrayList<E> {

  private final int MAX_LIMIT ;

  public MaxLimitList(final int MAX_LIMIT) {
    this.MAX_LIMIT = MAX_LIMIT;
  }

  public boolean add(E e){
    System.out.println("Adding in max list");
    System.out.println("size = "+this.size()+" Max elements = "+MAX_LIMIT+" boolean "+(this.size() > MAX_LIMIT));
    if(this.size() >= MAX_LIMIT){
      return false;
    }
    return super.add(e);
  }

}
