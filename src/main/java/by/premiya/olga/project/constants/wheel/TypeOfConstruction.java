package by.premiya.olga.project.constants.wheel;

import by.premiya.olga.project.constants.BasicConstant;

/**
 * @author Vlad Abramov
 */
public enum TypeOfConstruction implements BasicConstant {
    NAN("--"),
    RADIAL("Радиальная"),
    DIAGONAL("Диагональная");

    private final String type;

    TypeOfConstruction(String type) {
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
