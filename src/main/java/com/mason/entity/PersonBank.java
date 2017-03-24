package com.mason.entity;

/**
 * Created by huangsiqian on 2017/3/24 0024.
 */
public class PersonBank {
    private String region;
    private String bankName;
    private String spportDesc;

    public PersonBank(String region, String bankName, String spportDesc) {
        this.region = region;
        this.bankName = bankName;
        this.spportDesc = spportDesc;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getSpportDesc() {
        return spportDesc;
    }

    public void setSpportDesc(String spportDesc) {
        this.spportDesc = spportDesc;
    }

    @Override
    public String toString() {
        return "PersonBank{" +
                "region='" + region + '\'' +
                ", bankName='" + bankName + '\'' +
                ", spportDesc='" + spportDesc + '\'' +
                '}';
    }
}
