package by.premiya.olga.project.constants.wheel;

import by.premiya.olga.project.constants.BasicConstant;

/**
 * @author Vlad Abramov
 */
public enum Version implements BasicConstant {
    NAN("--"),
    TUBELESS("Безкамерная"),
    WITHTUBE("С камерой");

    private final String type;

    Version(String type) {
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
