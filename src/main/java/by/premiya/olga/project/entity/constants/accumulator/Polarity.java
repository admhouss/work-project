package by.premiya.olga.project.entity.constants.accumulator;

import by.premiya.olga.project.entity.constants.BasicConstant;

/**
 * @author vlad
 */
public enum Polarity implements BasicConstant{
    NAN("--"),
    REVERSE("Обратная"),
    DIRECT("Прямая");

    private final String type;

    Polarity(String type) {
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
