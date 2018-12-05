package com.allexey991.slack.dictionary;

import org.springframework.stereotype.Component;

@Component
public final class DictObject extends AbstractDict{
  public DictObject() {
    DICTIONARY.put(1,"PIPELINE");
    DICTIONARY.put(2,"JOB");
  }
}
