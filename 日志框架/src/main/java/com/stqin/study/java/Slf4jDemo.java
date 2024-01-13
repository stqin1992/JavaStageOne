package com.stqin.study.java;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Slf4jDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(Slf4jDemo.class);

    @Test
    public void testLog4j(){
        // 使用 SLF4J 记录不同级别的日志 使用log4j.xml
        LOGGER.debug("This is a DEBUG message");
        LOGGER.info("This is an INFO message");
        LOGGER.warn("This is a WARN message");
        LOGGER.error("This is an ERROR message");


    }

    /**
     * java调用shell脚本
     */
    @Test
    public void testShell() {
        String[] cmd = {"sh", "/Users/ideaCode/test.sh"};
        try {
            Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("----------> " +line);
            }
            int exitCode = p.waitFor();
            System.out.println("Exit code: " + exitCode);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
