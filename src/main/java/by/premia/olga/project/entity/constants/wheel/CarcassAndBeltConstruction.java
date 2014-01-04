package by.premia.olga.project.entity.constants.wheel;

/**
 * @author Vlad Abramov
 */
public enum CarcassAndBeltConstruction {
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
}
