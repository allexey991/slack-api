package com.allexey991.slack.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import com.allexey991.slack.model.MessageForSlack;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.text.normalizer.UTF16;

public class TemplateHelperTest {
  private static Logger log = LoggerFactory.getLogger(TemplateHelperTest.class);
  private final String TEMPLATE_NAME = "messageTemplates/7_testMessage.txt";
  private final String ASSERTION_TEMPLATE_NAME = "assertions/7_testMessage.txt";
  @Test public void replaceTest() {

    String textMessage = readResourceFile(TEMPLATE_NAME);
    //String textMessage = readResourceFile(TEMPLATE_NAME);
    String assertionTextMessage = readResourceFile(ASSERTION_TEMPLATE_NAME);
    TemplateHelper templateHelper = new TemplateHelper();
    MessageForSlack messageForSlack = new MessageForSlack(7,
        1,
        "1.3",
        "ciPipeLineSource",
        "ciProjectName",
        "ciPipelineUrl",
        "99.99");

    String result = templateHelper.replace(textMessage,messageForSlack);
    log.debug(result);
    Assert.assertTrue(result.equals(assertionTextMessage));

  }
  private String readResourceFile(String fileName){
    InputStream is = this.getClass().getClassLoader().getResourceAsStream(fileName);
    Scanner s = new Scanner(is).useDelimiter("\\A");
    String result = s.next();

    try {
    is.close();
    }catch (IOException ex){
      log.error("Wrong path",ex);
    }
    return result;
  }
}