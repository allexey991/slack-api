package com.allexey991.slack.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

public class MessageFactory {
  private static Logger log = LoggerFactory.getLogger(MessageFactory.class);

  public String getMessageTemplate(int type){
    switch(type){
    case 1:try{
                File resource =
                    new ClassPathResource("messageTemplates/msgStartFinish.txt").getFile();
                return new String(Files.readAllBytes(resource.toPath()));
              }catch (IOException ex){
                log.error("MessageFactory: IOException with type=[{}]",type,ex);
                return null;
              }
    case 2:try{
                File resource =
                    new ClassPathResource("messageTemplates/notificationReleaseBuild.txt").getFile();
                return new String(Files.readAllBytes(resource.toPath()));
              }catch (IOException ex){
                log.error("MessageFactory: IOException with type=[{}]",type,ex);
                return null;
              }
    case 3:try{
      File resource =
          new ClassPathResource("messageTemplates/notificationCreateRelease.txt").getFile();
      return new String(Files.readAllBytes(resource.toPath()));
    }catch (IOException ex){
      log.error("MessageFactory: IOException with type=[{}]",type,ex);
      return null;
    }
    default: return null;
    }
  }
}
