package com.allexey991.slack.dictionary;

import java.util.HashMap;

public class AbstractDict {
  public final HashMap<Integer,String> DICTIONARY = new HashMap<Integer, String>();;

  public String getValue(int code){
    return DICTIONARY.get(code);
  }
}
