package com.mio.example.expandablerv.bean;

/**
 * Created by mio4kon on 16/9/8.
 */
public class Data {
    private String type;

    public Data(String type) {
        this.type = type;
    }

    public String getDataHead() {
        return type + " -- DataHead";
    }

    public String getDataMiddle() {
        return type + " -- DataMiddle";
    }

    public String getDataFoot() {
        return type + " -- DataFoot";
    }
}
