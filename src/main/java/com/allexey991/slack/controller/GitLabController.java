package com.allexey991.slack.controller;

import com.allexey991.slack.model.MessageForSlack;
import com.allexey991.slack.service.SlackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GitLabController {

  private static Logger log = LoggerFactory.getLogger(GitLabController.class);

  @Autowired SlackService slackService;

  @PostMapping("/send/slack")
  public void sendToSlackMsg(@RequestBody MessageForSlack messageForSlack)
  {
    slackService.sendToSlack(messageForSlack);
  }
}
