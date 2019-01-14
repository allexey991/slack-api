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
      replaceText(stringBuilder,CI_JOB_NAME,messageForSlack.getCiJobName());
    }
    if(template.contains(CI_PIPE_LINE_SOURCE)){
      replaceText(stringBuilder,CI_PIPE_LINE_SOURCE,messageForSlack.getCiPipeLineSource());
    }
    if(template.contains(CI_PIPE_LINE_URL)){
      replaceText(stringBuilder,CI_PIPE_LINE_URL,messageForSlack.getCiPipelineUrl());
    }
    if(template.contains(CI_PROJECT_NAME)) {
      replaceText(stringBuilder,CI_PROJECT_NAME,messageForSlack.getCiProjectName());
    }
    if(template.contains(ACTION_TYPE)){
      replaceText(stringBuilder,ACTION_TYPE,dictActionType.DICTIONARY.get(messageForSlack.getActionType()));
    }
    if(template.contains(CI_RELEASE_VERSION)){
      replaceText(stringBuilder,CI_RELEASE_VERSION,messageForSlack.getCiReleaseVersion());
    }
    return stringBuilder.toString();
  }

  private StringBuilder replaceText(StringBuilder originalString,String temaplateWord,String value){

    while(originalString.indexOf(temaplateWord)>-1){
      int indexOf = originalString.indexOf(temaplateWord);
      originalString.delete(indexOf,indexOf+temaplateWord.length());
      originalString.insert(indexOf,value);
    }
    return  originalString;
  }
}
