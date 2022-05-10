package ru.oshkin.util.constants;

public enum Country {
    RUSSIA("Россия"),
    BELARUS("Республика Беларусь"),
    CHINA("Китай"),
    UZBEKISTAN("Узбекистан");

    final String country;

    Country(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
}

