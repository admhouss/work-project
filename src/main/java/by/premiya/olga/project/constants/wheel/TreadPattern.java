package by.premiya.olga.project.constants.wheel;

import by.premiya.olga.project.constants.BasicConstant;

/**
 * @author Vlad Abramov
 */
public enum TreadPattern implements BasicConstant {
    NAN("--"),
    ONROAD("Дорожный"),
    OFFROAD("Внедорожный"),
    ALLSEASON("Всесезонный"),
    WINTER("Зимний"),
    SUMMER("Летний"),
    UNIVERSAL("Универсальный"),
    RAISEDPASSABLENESS("Повышенной проходимости"),
    CAREER("Карьерный"),
    PERFORMANCE("Скоростной"),
    ALLSEASONPERFORMANCE("Всесезонный скоростной");

    private final String type;

    TreadPattern(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
    @Override
    public String getString() {
        return type;
    }
}
