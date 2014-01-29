package by.premiya.olga.project.entity.constants.wheel;

import by.premiya.olga.project.entity.constants.BasicConstant;

/**
 * @author Vlad Abramov
 */
public enum TypeOfConstruction implements BasicConstant {
    NAN {
        {
            type = "--";
        }
    },
    RADIAL {
        {
            type = "Радиальная";
        }
    },
    DIAGONAL {
        {
            type = "Диагональная";
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
