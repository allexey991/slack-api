package com.allexey991.slack.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

@Component
public class ClassPathAccessor {
  private static Logger log = LoggerFactory.getLogger(ClassPathAccessor.class);

  public ClassPathAccessor() {
  }

  @Autowired
  private ApplicationContext applicationContext;

  public String getFileContentByPattern(int id){
    ResourcePatternResolver resourcePatternResolver
        = new PathMatchingResourcePatternResolver();
    try {
      Resource[] resources = resourcePatternResolver.getResources("classpath:/messageTemplates/"+id+"_*.txt");
      String template = resources[0].getFilename();
      log.debug("getFileContentByPattern: Template=[{}]",template);
      return getFileContent("messageTemplates/"+template);
    } catch (IOException e) {
      log.error("getFileContentByPattern: Not found template with prefix=[{}_]",id);
      return null;
    }
  }
  public String getFileContent(String path){
    try {

      Resource resource = this.applicationContext.getResource("classpath:/".concat(path));
      if (resource != null) {
        InputStream is = resource.getInputStream();
        Throwable var4 = null;

        String var7;
        try {
          Scanner s = new Scanner(is);
          s.useDelimiter("\\A");
          String result = s.hasNext() ? s.next() : "";
          s.close();
          var7 = result;
        } catch (Throwable var17) {
          var4 = var17;
          throw var17;
        } finally {
          if (is != null) {
            if (var4 != null) {
              try {
                is.close();
              } catch (Throwable var16) {
                var4.addSuppressed(var16);
              }
            } else {
              is.close();
            }
          }

        }

        return var7;
      } else {
        log.error(String.format("File %s not found", path));
        return null;
      }
    } catch (Exception var19) {
      log.error("getFileContent: " + var19.getMessage(), var19);
      log.error(String.format("File %s has incorrect content", path), var19);
      return null;
    }
  }
}
