package com.stqin.study.java;

import cn.hutool.crypto.SecureUtil;
import org.junit.Test;

public class Test1 {

    @Test
    public void test(){
        System.out.println("123");
    }

    @Test
    public void getHuTool() {
        System.out.printf(SecureUtil.md5("1234"));
    }
}
