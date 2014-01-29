package by.premiya.olga.project.entity.constants.accumulator;

import by.premiya.olga.project.entity.constants.BasicConstant;

/**
 * @author vlad
 */
public enum Polarity implements BasicConstant{
    NAN {
        {
            type = "--";
        }
    },
    REVERSE {
        {
            type = "Обратная";
        }
    },
    //direct
    ;

    String type;

    public String getType() {
        return type;
    }

    @Override
    public String getString() {
        return null;
    }
}
