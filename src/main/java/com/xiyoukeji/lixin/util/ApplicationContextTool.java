package com.xiyoukeji.lixin.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by wangqiyun on 2017/12/8.
 */
@Component
public class ApplicationContextTool implements ApplicationContextAware {

    public static volatile ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextTool.applicationContext = applicationContext;
    }
}
