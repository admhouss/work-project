package by.premia.olga.project.entity.constants.wheel;

/**
 * @author Vlad Abramov
 */
public enum Version {
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
}
