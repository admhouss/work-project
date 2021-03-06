package by.premiya.olga.project.entity.comparators.wheels;

import by.premiya.olga.project.entity.Wheel;

import java.util.Comparator;

/**
 * @author Vlad Abramov
 */
public final class WheelsCompare {
    public static final Comparator<Wheel> BY_NAME = new Comparator<Wheel>() {
        @Override
        public int compare(Wheel w1, Wheel w2) {
            int cmp = w1.getModel().compareToIgnoreCase(w2.getModel());
            if (cmp == 0) {
                cmp = w2.getPrice().compareTo(w2.getPrice());
            }
            return cmp;
        }
    };
}
