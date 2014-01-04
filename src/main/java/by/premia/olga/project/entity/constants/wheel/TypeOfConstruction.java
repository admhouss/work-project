package by.premia.olga.project.entity.constants.wheel;

/**
 * @author Vlad Abramov
 */
public enum TypeOfConstruction {
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
