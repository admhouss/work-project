package by.premiya.olga.project.entity.constants.wheel;

/**
 * @author Vlad Abramov
 */
public enum TypeOfConstruction {
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
}
