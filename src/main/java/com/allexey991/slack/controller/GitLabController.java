package com.allexey991.slack.controller;

import com.allexey991.slack.model.MessageForSlack;
import com.allexey991.slack.service.SlackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GitLabController {

  @Autowired
  private SlackService slackService;

  @PostMapping("/send/slack")
  public void sendToSlackMsg(@RequestBody MessageForSlack messageForSlack)
  {
    slackService.startSending(messageForSlack);
  }
}
