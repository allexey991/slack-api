package com.allexey991.slack.util.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import com.allexey991.slack.model.MessageForSlack;
import com.allexey991.slack.util.AbstractServiceTest;
import com.allexey991.slack.util.TemplateHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class TemplateHelperTest extends AbstractServiceTest {
  private static Logger log = LoggerFactory.getLogger(TemplateHelperTest.class);
  private final String ASSERTION_TEMPLATE_NAME = "assertions/7_testMessage.txt";
  private MessageForSlack messageForSlack;

  @Autowired
  private TemplateHelper templateHelper;

  @Before
  public void messageInit(){
    messageForSlack = new MessageForSlack(7,
        1,
        "1.3",
        "ciPipeLineSource",
        "ciProjectName",
        "ciPipelineUrl",
        "99.99");
  }

  @Test public void replaceTest() {
    String textMessage = classPathAccessor.getFileContentByPattern(7);
    String assertionTextMessage = classPathAccessor.getFileContent(ASSERTION_TEMPLATE_NAME);
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