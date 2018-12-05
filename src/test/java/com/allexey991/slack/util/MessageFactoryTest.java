package com.allexey991.slack.util;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageFactoryTest {
  private static Logger log = LoggerFactory.getLogger(MessageFactoryTest.class);

  @Test public void getMessageTemplate1() {
    MessageFactory messageFactory = new MessageFactory();
    messageFactory.getMessageTemplate(1);
    String result = messageFactory.getMessageTemplate(1);
    Assert.assertNotNull(result);
    log.info(String.format(result,"Start","project-name","api","http://www.test.com"));

    String result2 = messageFactory.getMessageTemplate(2);
    Assert.assertNotNull(result2);
    log.info(String.format(result2,"X.X"));

    String result3 = messageFactory.getMessageTemplate(3);
    Assert.assertNotNull(result3);
    log.info(String.format(result3,"X.X"));
  }
}