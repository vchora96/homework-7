package ru.oshkin.util.constants;

public enum City {
    BUHARA("Бухара"),
    SAMARKAND("Самарканд"),
    ANGREN("Ангрен"),
    CHIRCHICK("Чирчик");

    final String cityName;

    City(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }
}