package by.premiya.olga.project.util.json;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * @author Vlad Abramov
 */
public class PairJSON<F, S> implements Externalizable {

    private static final long serialVersionUID = -7417528978393824789L;
    private F first;
    private S second;

    public PairJSON() {

    }

    public PairJSON(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public F getFirst() {
        return first;
    }

    public void setFirst(F first) {
        this.first = first;
    }

    public S getSecond() {
        return second;
    }

    public void setSecond(S second) {
        this.second = second;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(first);
        out.writeObject(second);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        first = (F) in.readObject();
        second = (S) in.readObject();
    }
}
