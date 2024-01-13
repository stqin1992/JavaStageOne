package com.stqin.study.java.log4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class Log4jDemo {
    private static final Logger LOGGER = LogManager.getLogger(Log4jDemo.class);

    @Test
    public void test1() {
        // 使用 Log4j 记录不同级别的日志 使用log4j2.xml
        LOGGER.debug("This is a DEBUG message");
        LOGGER.info("This is an INFO message");
        LOGGER.warn("This is a WARN message");
        LOGGER.error("This is an ERROR message");

    }
    public static void main(String[] args) {

    }


}
