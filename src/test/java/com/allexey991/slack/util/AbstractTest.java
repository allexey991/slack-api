package com.allexey991.slack.util;

import com.allexey991.slack.config.SlackConfig;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SlackConfig.class,TestConfig.class})
public abstract class AbstractTest {
}
