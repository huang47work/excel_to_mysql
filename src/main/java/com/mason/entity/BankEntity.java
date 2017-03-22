package com.mason.entity;

/**
 * Created by huangsiqian on 2017/3/22 0022.
 */
public class BankEntity {

    String bankName;
    String bankCode;

    public BankEntity(String bankName, String bankCode) {
        this.bankName = bankName;
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    @Override
    public String toString() {
        return "BankEntity{" +
                "bankName='" + bankName + '\'' +
                ", bankCode='" + bankCode + '\'' +
                '}';
    }
}
