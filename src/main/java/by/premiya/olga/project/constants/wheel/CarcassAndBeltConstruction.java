package by.premiya.olga.project.constants.wheel;

import by.premiya.olga.project.constants.BasicConstant;

/**
 * @author Vlad Abramov
 */
public enum CarcassAndBeltConstruction implements BasicConstant {
    NAN("--"),
    COMBINED("Комбинированная"),
    TEXTILE("Текстильная"),
    ALLSTEEL("ЦМК");

    private final String type;

    CarcassAndBeltConstruction(String type) {
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
