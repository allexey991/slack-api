package com.allexey991.slack.util;

import com.allexey991.slack.config.SlackConfig;

import com.allexey991.slack.util.config.TestConfig;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SlackConfig.class, TestConfig.class })
public abstract class AbstractTest {
  @Autowired
  @Qualifier("classPathAccessor")
  protected ClassPathAccessor classPathAccessor;
}
