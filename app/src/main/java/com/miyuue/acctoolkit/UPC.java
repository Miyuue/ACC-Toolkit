package com.miyuue.acctoolkit;

public class UPC {
    private String name, upc;

    public UPC(String name, String upc) {
        this.name = name;
        this.upc = upc;
    }

    public UPC() {
        this("default", "0");
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUPC() {
        return this.upc;
    }

    public void setUPC(String upc) {
        this.upc = upc;
    }
}
