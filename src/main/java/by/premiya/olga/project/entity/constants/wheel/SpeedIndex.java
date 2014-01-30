package by.premiya.olga.project.entity.constants.wheel;

import by.premiya.olga.project.entity.constants.BasicConstant;

/**
 * @author Vlad Abramov
 */
public enum SpeedIndex implements BasicConstant {
    NAN(0),
    A1(5),
    A2(10),
    A3(15),
    A4(20),
    A5(25),
    A6(30),
    A7(35),
    A8(40),
    B(50),
    C(60),
    D(65),
    E(70),
    F(80),
    G(90),
    J(100),
    K(110),
    L(120),
    M(130),
    N(140),
    P(150),
    Q(160),
    R(170),
    S(180),
    T(190),
    U(200),
    H(210),
    V(240),
    Z(240),
    W(270),
    WO(270),
    Y(300),
    YO(300);

    private final int maxSpeed;

    SpeedIndex(int type) {
        this.maxSpeed = type;
    }

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
