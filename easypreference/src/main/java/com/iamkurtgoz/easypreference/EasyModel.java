package com.iamkurtgoz.easypreference;

@Deprecated
public class EasyModel {

    private String key;
    private Object value;

    public EasyModel(String key, Object value){
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }
}
