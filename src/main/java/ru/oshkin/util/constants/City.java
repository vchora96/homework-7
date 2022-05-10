package ru.oshkin.util.constants;

public enum City {
    BUHARA("Бухара", "Узбекистан"),
    SAMARKAND("Самарканд", "Узбекистан"),
    ANGREN("Ангрен", "Узбекистан"),
    CHIRCHICK("Чирчик", "Узбекистан");

    final String cityName;
    final String country;

    City(String cityName, String country) {
        this.cityName = cityName;
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public String getCityName() {
        return cityName;
    }
}