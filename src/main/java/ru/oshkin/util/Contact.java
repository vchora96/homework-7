package ru.oshkin.util;

public class Contact {
    private String type;
    private String value;

    public Contact(String type, String value) {
        this.type = type;//Skype, Viber..
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}
