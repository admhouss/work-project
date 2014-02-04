package by.premiya.olga.project.constants.producers;

import by.premiya.olga.project.constants.BasicConstant;

/**
 * @author vlad
 */
public enum AccumulatorsProducer implements BasicConstant {

    NAN("--"),
    KRAFT("Kraft");

    private final String name;

    AccumulatorsProducer(String name) {
        this.name = name;
    }

    @Override
    public String getString() {
        return name;
    }
}
