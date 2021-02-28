package com.unitedcoder.regression.databasetest;


public class TestDataHolder {
    private String productName;

    public TestDataHolder(String productName) {
        this.productName = productName;
    }

    public TestDataHolder() {
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }
}

