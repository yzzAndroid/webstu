package com.yzz.java.bean;

/**
 * Created by yzz on 2017/9/10.
 * mail:yzzstyle@163.com
 */

public class Account {
    private int acc_id;
    private String acc_name;
    private double acc_money;

    public int getAcc_id() {
        return acc_id;
    }

    public void setAcc_id(int acc_id) {
        this.acc_id = acc_id;
    }

    public String getAcc_name() {
        return acc_name;
    }

    public void setAcc_name(String acc_name) {
        this.acc_name = acc_name;
    }

    public double getAcc_money() {
        return acc_money;
    }

    public void setAcc_money(double acc_money) {
        this.acc_money = acc_money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "acc_id=" + acc_id +
                ", acc_name='" + acc_name + '\'' +
                ", acc_money=" + acc_money +
                '}';
    }
}
