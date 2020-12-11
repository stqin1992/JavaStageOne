package com.stqin.study.java.test;

import java.io.Serializable;

/**
 *  类的对象要想序列化成功，必须满足两个条件：
 *  1、该类必须实现 java.io.Serializable 接口；
 *  2、该类的所有属性必须是可序列化的。如果有一个属性不是可序列化的，则该属性必须注明是短暂的。
 *
 *
 */

public class Employee implements Serializable {

    public Employee() {
    }

    private String name;
    private String address;
    private transient int SSN;
    private int number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSSN() {
        return SSN;
    }

    public void setSSN(int SSN) {
        this.SSN = SSN;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", SSN=" + SSN +
                ", number=" + number +
                '}';
    }

    public void mailCheck() {
        System.out.println("Mailing a check to " + name
                + " " + address);
    }

}
