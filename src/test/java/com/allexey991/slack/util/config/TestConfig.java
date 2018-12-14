package com.allexey991.slack.util.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;

@TestConfiguration()
@ComponentScan("com.allexey991.slack")
@EnableAutoConfiguration
public class TestConfig {
}
