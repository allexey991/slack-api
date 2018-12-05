package com.allexey991.slack.dictionary;

import org.springframework.stereotype.Component;

@Component
public final class DictActionType extends AbstractDict{
  public DictActionType() {
    DICTIONARY.put(1,"STARTED");
    DICTIONARY.put(2,"FINISHED");
  }
}
