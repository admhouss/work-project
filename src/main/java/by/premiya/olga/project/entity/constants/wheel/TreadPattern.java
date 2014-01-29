package by.premiya.olga.project.entity.constants.wheel;

/**
 * @author Vlad Abramov
 */
public enum TreadPattern {
    NAN {
        {
            type = "--";
        }
    },
    ONROAD {
        {
            type = "Дорожная";
        }
    },
    OFFROAD {
        {
            type = "Внедорожная";
        }
    },
    ALLSEASON {
        {
            type = "Всесезонная";
        }
    },
    WINTER {
        {
            type = "Зимняя";
        }
    },
    SUMMER {
        {
            type = "Летняя";
        }
    },
    UNIVERSAL {
        {
            type = "Универсальная";
        }
    },
    RAISEDPASSABLENESS {
        {
            type = "Повышенной проходимости";
        }
    },
    CAREER {
        {
            type = "Карьерная";
        }
    },
    PERFORMANCE {
        {
            type = "Скоростная";
        }
    },
    ALLSEASONPERFORMANCE {
        {
            type = "Всесезонная скоростная";
        }
    };

    String type;

    public String getType() {
        return type;
    }
}
