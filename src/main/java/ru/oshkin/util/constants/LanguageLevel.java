package ru.oshkin.util.constants;

public enum LanguageLevel {
    Beginner("Начальный уровень (Beginner)"),
    Elementary("Элементарный уровень (Elementary)"),
    PreIntermediate("Ниже среднего (Pre-Intermediate)"),
    Intermediate("Средний (Intermediate)"),
    UpperIntermediate("Выше среднего (Upper Intermediate)"),
    Advanced("Продвинутый (Advanced)"),
    Mastery("Супер продвинутый (Mastery)");

    private String description;

    LanguageLevel(String description) {
        this.description = description;
    }

    public String getDescription(){
        return description;
    }
}
