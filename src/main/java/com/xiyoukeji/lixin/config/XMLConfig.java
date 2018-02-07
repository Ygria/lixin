package com.xiyoukeji.lixin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by ygria on 2017/11/21.
 */

@Configuration
@ImportResource(locations = {"classpath:application-bean.xml"})
public class XMLConfig {
}
