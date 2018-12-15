package com.allexey991.slack.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ChannelsMap {
  @JsonProperty
  private Map<Integer, List<String>> templateAndChannels = new HashMap<>();

  public ChannelsMap() {
  }

  public ChannelsMap(Map map) {
    templateAndChannels = map;
  }

  public List<String> getUrlsByTemplateId(int id){
    return templateAndChannels.get(id);
  }

  @Override public String toString() {
    return "ChannelsMap{" + "templateAndChannels=" + templateAndChannels + '}';
  }
}
