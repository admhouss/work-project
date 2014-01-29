package by.premiya.olga.project.entity.constants.wheel;

import by.premiya.olga.project.entity.constants.BasicConstant;

/**
 * @author Vlad Abramov
 */
public enum SpeedIndex implements BasicConstant {
    NAN {
        {
            maxSpeed = 0;
        }
    },
    A1 {
        {
            maxSpeed = 5;
        }
    },
    A2 {
        {
            maxSpeed = 10;
        }
    },
    A3 {
        {
            maxSpeed = 15;
        }
    },
    A4 {
        {
            maxSpeed = 20;
        }
    },
    A5 {
        {
            maxSpeed = 25;
        }
    },
    A6 {
        {
            maxSpeed = 30;
        }
    },
    A7 {
        {
            maxSpeed = 35;
        }
    },
    A8 {
        {
            maxSpeed = 40;
        }
    },
    B {
        {
            maxSpeed = 50;
        }
    },
    C {
        {
            maxSpeed = 60;
        }
    },
    D {
        {
            maxSpeed = 65;
        }
    },
    E {
        {
            maxSpeed = 70;
        }
    },
    F {
        {
            maxSpeed = 80;
        }
    },
    G {
        {
            maxSpeed = 90;
        }
    },
    J {
        {
            maxSpeed = 100;
        }
    },
    K {
        {
            maxSpeed = 110;
        }
    },
    L {
        {
            maxSpeed = 120;
        }
    },
    M {
        {
            maxSpeed = 130;
        }
    },
    N {
        {
            maxSpeed = 140;
        }
    },
    P {
        {
            maxSpeed = 150;
        }
    },
    Q {
        {
            maxSpeed = 160;
        }
    },
    R {
        {
            maxSpeed = 170;
        }
    },
    S {
        {
            maxSpeed = 180;
        }
    },
    T {
        {
            maxSpeed = 190;
        }
    },
    U {
        {
            maxSpeed = 200;
        }
    },
    H {
        {
            maxSpeed = 210;
        }
    },
    V {
        {
            maxSpeed = 240;
        }
    },
    Z {
        {
            maxSpeed = 240;
        }
    },
    W {
        {
            maxSpeed = 270;
        }
    },
    WO {
        {
            maxSpeed = 270;
        }
    },
    Y {
        {
            maxSpeed = 300;
        }
    },
    YO {
        {
            maxSpeed = 300;
        }
    };

    int maxSpeed;

    public int getMaxSpeed() {
        return maxSpeed;
    }

    @Override
    public String getString() {
        String str = String.valueOf(maxSpeed);
        if (this.name().compareTo("Z") == 0 || this.name().compareTo("WO") == 0 || this.name().compareTo("YO") == 0) {
            str = "> " + str;
        } else if (str.equals("0")) {
            return "--";
        }
        return str;
    }
}
