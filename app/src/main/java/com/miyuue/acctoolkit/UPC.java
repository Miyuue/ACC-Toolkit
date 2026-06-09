package com.miyuue.acctoolkit;

public class UPC {
    private String name;
    private long upc;

    public UPC(String name, long upc) {
        this.name = name;
        this.upc = upc;
    }

    public UPC() {
        this("default", 0);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUPC() {
        return this.upc;
    }

    public void setUPC(long upc) {
        this.upc = upc;
    }
}
