package com.phl.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by panhongliang on 15/8/16.
 */
@Configuration
@ImportResource("classpath:springcontext.xml")
public class T0 {

    @Value("${key1}")
    String key1;

    @Value("${key2}")
    String key2;
}
