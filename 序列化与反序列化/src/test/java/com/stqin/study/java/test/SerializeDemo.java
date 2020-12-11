package com.stqin.study.java.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializeDemo {
    public static void main(String[] args) {
        Employee e = new Employee();
        e.setName("Reyan Ali");
        e.setAddress("Phokka Kuan, Ambehta Peer");
        e.setSSN(11122333);
        e.setNumber(101);
        try {
            FileOutputStream fileOut = new FileOutputStream("序列化与反序列化/employee.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(e);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in 序列化与反序列化/employee.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
