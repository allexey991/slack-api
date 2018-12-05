package com.allexey991.slack.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class MessageForSlack {

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


  public MessageForSlack(){}

  public String getCiJobName() {
    return ciJobName;
  }

  public String getCiPipeLineSource() {
    return ciPipeLineSource;
  }

  @Override public String toString() {
    return "MessageForSlack{" + "actionType=" + actionType + ", ciJobName='" + ciJobName + '\''
        + ", ciPipeLineSource='" + ciPipeLineSource + '\'' + ", ciProjectName='" + ciProjectName
        + '\'' + ", ciPipelineUrl='" + ciPipelineUrl + '\'' + '}';
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

  public List getAllParameters(){
    List result = new ArrayList();
    result.add(ciJobName);
    result.add(ciPipeLineSource);
    result.add(ciProjectName);
    result.add(ciPipelineUrl);
    return  result;
  }
}
