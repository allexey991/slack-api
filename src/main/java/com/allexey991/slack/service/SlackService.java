package com.allexey991.slack.service;

import javax.annotation.PostConstruct;

import java.util.Objects;

import com.allexey991.slack.dictionary.DictActionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.allexey991.slack.dictionary.DictErrorCode;
import com.allexey991.slack.dictionary.DictObject;
import com.allexey991.slack.model.MessageForSlack;
import com.allexey991.slack.model.SlackMessage;
import com.allexey991.slack.util.MessageFactory;

@Service
public class SlackService {
  private static Logger log = LoggerFactory.getLogger(SlackService.class);

  @Autowired
  DictObject dictObject;
  @Autowired DictActionType dictActionType;
  @Autowired
  DictErrorCode dictErrorCode;
  @Autowired
  MessageFactory messageFactory;

  private RestTemplate restTemplate;

  @Value("${slackUrl:http://www.example.com}")
  private String slackUrl;

  @PostConstruct
  private void init() {
    this.restTemplate = new RestTemplate();
  }

  private String getSlackUrl() {
    return slackUrl;
  }

  private void setSlackUrl(String slackUrl) {
    this.slackUrl = slackUrl;
  }

  public void printSlackUrl(){
    log.info("printSlackUrl: slackUrl=[{}]",getSlackUrl());
  }

  public void sendToSlack(MessageForSlack messageForSlack) {

    log.info("sendToSlack: Start form message to Slack");

    String textMessage = messageFactory.getMessageTemplate(messageForSlack.getActionType());
    if (Objects.nonNull(textMessage)){
      String.format(textMessage
          ,dictActionType.getValue(messageForSlack.getActionType())
          ,messageForSlack.getAllParameters());

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      SlackMessage slackMessage = new SlackMessage(textMessage);
      HttpEntity<SlackMessage> request = new HttpEntity<SlackMessage>(slackMessage, headers);

      log.info("sendToSlack: ready message=[{}]",textMessage);
      ResponseEntity<String> response = restTemplate.postForEntity(slackUrl, request, String.class);
      log.info("sendToSlack: response=[{}]",response.getStatusCode());
    }else{
      log.error("sendToSlack: Sending Error");
    }
  }
}
