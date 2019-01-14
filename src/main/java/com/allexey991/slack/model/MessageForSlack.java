package com.allexey991.slack.model;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class MessageForSlack {

  @JsonProperty
  private int template;
  @JsonProperty
  private int actionType;
  @JsonProperty
  private String ciJobName;
  @JsonProperty
  private String ciPipeLineSource;
  @JsonProperty
  private String ciProjectName;
  @JsonProperty
  private String ciPipelineUrl;
  @JsonProperty
  private String ciReleaseVersion;


  public MessageForSlack(){}

  public MessageForSlack(int template, int actionType, String ciJobName, String ciPipeLineSource,
      String ciProjectName, String ciPipelineUrl, String ciReleaseVersion) {
    this.template = template;
    this.actionType = actionType;
    this.ciJobName = ciJobName;
    this.ciPipeLineSource = ciPipeLineSource;
    this.ciProjectName = ciProjectName;
    this.ciPipelineUrl = ciPipelineUrl;
    this.ciReleaseVersion = ciReleaseVersion;
  }

  public String getCiJobName() {
    return ciJobName;
  }

  public String getCiPipeLineSource() {
    return ciPipeLineSource;
  }

  public String getCiProjectName() {
    return ciProjectName;
  }

  public int getActionType() {
    return actionType;
  }

  public String getCiPipelineUrl() {
    return ciPipelineUrl;
  }

  public String getCiReleaseVersion() { return ciReleaseVersion; }

  public int getTemplate() {
    return template;
  }

  @Override public String toString() {
    return "MessageForSlack{" + "template=" + template + ", actionType=" + actionType
        + ", ciJobName='" + ciJobName + '\'' + ", ciPipeLineSource='" + ciPipeLineSource + '\''
        + ", ciProjectName='" + ciProjectName + '\'' + ", ciPipelineUrl='" + ciPipelineUrl + '\''
        + ", ciReleaseVersion='" + ciReleaseVersion + '\'' + '}';
  }

  public HashMap getAllParameters(){
    HashMap<String,String> map = new HashMap<>();
    map.put("ciJobName",ciJobName);
    map.put("ciPipeLineSource",ciPipeLineSource);
    map.put("ciProjectName",ciProjectName);
    map.put("ciPipelineUrl",ciPipelineUrl);
    map.put("ciReleaseVersion",ciReleaseVersion);
    return  map;
  }
}
