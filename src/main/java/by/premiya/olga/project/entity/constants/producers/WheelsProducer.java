package by.premiya.olga.project.entity.constants.producers;

import by.premiya.olga.project.entity.constants.BasicConstant;

/**
 * @author vlad
 */
public enum WheelsProducer implements BasicConstant {

    NAN("--"),
    KAMA("Kama");

    private final String name;

    WheelsProducer(String name) {
        this.name = name;
    }

    @Override
    public String getString() {
        return name;
    }
}
