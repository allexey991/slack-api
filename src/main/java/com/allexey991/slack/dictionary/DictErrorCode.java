package com.allexey991.slack.dictionary;

import org.springframework.stereotype.Component;

@Component
public final class DictErrorCode extends AbstractDict{
  public DictErrorCode() {
    DICTIONARY.put(1,"SUCCESSFUL");
    DICTIONARY.put(2,"FAIL");
  }
}
