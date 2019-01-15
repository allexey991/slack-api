package com.allexey991.slack.util;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration()
@EnableAutoConfiguration
public class TestConfig {
  public TestConfig(){
    System.out.println("*** Logger configuration ***");
    LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
    StatusPrinter.print(lc);
    System.out.println("****************************");
  }
}
