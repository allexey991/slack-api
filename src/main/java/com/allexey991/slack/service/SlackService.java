package com.allexey991.slack.service;

import javax.annotation.PostConstruct;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import com.allexey991.slack.model.MessageForSlack;
import com.allexey991.slack.model.ChannelsMap;
import com.allexey991.slack.model.SlackMessage;
import com.allexey991.slack.util.ClassPathAccessor;
import com.allexey991.slack.util.TemplateHelper;

@Service
public class SlackService {
  private static Logger log = LoggerFactory.getLogger(SlackService.class);

  @Autowired
  private TemplateHelper templateHelper;
  @Autowired
  private ClassPathAccessor classPathAccessor;
  @Value("${slackUrl}")
  private String slackDefaultUrl;

  private RestTemplate restTemplate;

  @PostConstruct
  private void init() {
    this.restTemplate = new RestTemplate();
  }

  public void startSending(MessageForSlack messageForSlack) {
    String templateConfigName = "templateChannels.json";
    ChannelsMap channelsMap = null;

    log.info("startSending: Start form message to Slack");

    String textMessage = classPathAccessor.getFileContentByPattern(messageForSlack.getTemplate());
    ObjectMapper mapper = new ObjectMapper();

    try {
      channelsMap = mapper.readValue(
          classPathAccessor.getFileContent(templateConfigName), ChannelsMap.class);

    }catch (IOException ex){
      log.error("startSending: error with json=[{}]",templateConfigName,ex);
    }

    if (Objects.nonNull(textMessage) && Objects.nonNull(channelsMap)){
      List<String> urls = channelsMap.getUrlsByTemplateId(messageForSlack.getTemplate());
      textMessage = templateHelper.replace(textMessage,messageForSlack);
      if (urls.isEmpty()) {
        log.warn("startSending: No specified channels, used default channel=[{}]",slackDefaultUrl);
        sendToSlack(textMessage,slackDefaultUrl);
      } else {
        String finTextMessage = textMessage; //effectively final variable for lambda expression
        urls.forEach(e -> sendToSlack(finTextMessage,e));
      }
    }else{
      log.error("startSending: null in objects=[templates={},Links{}]",textMessage,channelsMap);
    }
  }

  private void sendToSlack(String textMessage,String slackUrl) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    SlackMessage slackMessage = new SlackMessage(textMessage);
    HttpEntity<SlackMessage> request = new HttpEntity<>(slackMessage, headers);

    log.info("startSending: ready message=[{}]",textMessage);
    ResponseEntity<String> response
        = restTemplate.postForEntity(slackUrl, request, String.class);
    log.info("startSending: response=[{}]",response.getStatusCode());
  }


}
