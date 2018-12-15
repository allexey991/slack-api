package com.allexey991.slack.util;

import com.allexey991.slack.model.MessageForSlack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.allexey991.slack.dictionary.DictActionType;
@Component
public class TemplateHelper {
  @Autowired
  private DictActionType dictActionType;

  private static final String ACTION_TYPE = "%actionType%";
  private static final String CI_JOB_NAME = "%ciJobName%";
  private static final String CI_PIPE_LINE_SOURCE = "%ciPipeLineSource%";
  private static final String CI_PIPE_LINE_URL = "%ciPipeLineUrl%";
  private static final String CI_PROJECT_NAME = "%ciProjectName%";
  private static final String CI_RELEASE_VERSION = "%ciReleaseVersion%";

  public String replace(String template, MessageForSlack messageForSlack){
    StringBuilder stringBuilder = new StringBuilder(template);

    if(template.contains(CI_JOB_NAME)){
      stringBuilder.insert(replaceWordAndGetIndex(CI_JOB_NAME,stringBuilder)
          ,messageForSlack.getCiJobName());
    }
    if(template.contains(CI_PIPE_LINE_SOURCE)){
      stringBuilder.insert(replaceWordAndGetIndex(CI_PIPE_LINE_SOURCE,stringBuilder)
          ,messageForSlack.getCiPipeLineSource());
    }
    if(template.contains(CI_PIPE_LINE_URL)){
      stringBuilder.insert(replaceWordAndGetIndex(CI_PIPE_LINE_URL,stringBuilder)
          ,messageForSlack.getCiPipelineUrl());
    }
    if(template.contains(CI_PROJECT_NAME)) {
      stringBuilder.insert(replaceWordAndGetIndex(CI_PROJECT_NAME,stringBuilder),
          messageForSlack.getCiProjectName());
    }
    if(template.contains(ACTION_TYPE)){
      stringBuilder.insert(replaceWordAndGetIndex(ACTION_TYPE,stringBuilder)
          ,dictActionType.DICTIONARY.get(messageForSlack.getActionType()));
    }
    if(template.contains(CI_RELEASE_VERSION)){
      stringBuilder.insert(replaceWordAndGetIndex(CI_RELEASE_VERSION,stringBuilder)
          ,messageForSlack.getCiReleaseVersion());
    }
    return stringBuilder.toString();
  }

  private int replaceWordAndGetIndex(String temaplateWord,StringBuilder stringBuilder){
    int result = stringBuilder.indexOf(temaplateWord);
    stringBuilder.delete(result,result+temaplateWord.length());
    return  result;
  }
}
