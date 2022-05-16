package ru.oshkin.util;

public enum ContactType {
    SKYPE("Skype"),
    VIBER("Viber"),
    VK("Vk");

    private final String text;

    ContactType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
