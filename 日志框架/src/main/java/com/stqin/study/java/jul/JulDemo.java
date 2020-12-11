package com.stqin.study.java.jul;

import org.junit.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

public class JulDemo {


    public static void main(String[] args) {
        Logger logger = Logger.getLogger(JulDemo.class.getName());
        logger.setLevel(Level.WARNING);

        logger.info("info");
    }



    @Test
    public  void test1() {
        Logger logger = Logger.getLogger(JulDemo.class.getName());
        logger.setLevel(Level.ALL);

        logger.info("info");
        logger.warning("warning");
    }
}
