package by.premiya.olga.project.entity.constants.wheel;

/**
 * @author Vlad Abramov
 */
public enum CarcassAndBeltConstruction {
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
}
