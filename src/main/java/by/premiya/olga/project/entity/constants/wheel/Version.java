package by.premiya.olga.project.entity.constants.wheel;

import by.premiya.olga.project.entity.constants.BasicConstant;

/**
 * @author Vlad Abramov
 */
public enum Version implements BasicConstant {
    NAN {
        {
            type = "--";
        }
    },
    TUBELESS {
        {
            type = "Безкамерная";
        }
    },
    WITHTUBE {
        {
            type = "С камерой";
        }
    };

    String type;

    public String getType() {
        return type;
    }

    @Override
    public String getString() {
        return type;
    }
}
