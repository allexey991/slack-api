package com.allexey991.slack.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class SlackMessage {

  @JsonProperty
  private String text;

  public SlackMessage(String text) {
    this.text = text;
  }
}
