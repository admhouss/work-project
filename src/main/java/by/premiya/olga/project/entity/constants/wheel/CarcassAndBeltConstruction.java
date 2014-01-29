package by.premiya.olga.project.entity.constants.wheel;

import by.premiya.olga.project.entity.constants.BasicConstant;

/**
 * @author Vlad Abramov
 */
public enum CarcassAndBeltConstruction implements BasicConstant {
    NAN {
        {
            type = "--";
        }
    },
    COMBINED {
        {
            type = "Комбинированная";
        }
    },
    TEXTILE {
        {
            type = "Текстильная";
        }
    },
    ALLSTEEL {
        {
            type = "ЦМК";
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
