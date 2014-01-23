package by.premiya.olga.project.util.json;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vlad Abramov
 */
@SuppressWarnings("unchecked")
public class NewItemJSON implements Externalizable {

    private static final long serialVersionUID = 4672353801532058741L;
    private List<PairJSON<String,PairJSON<String, String>>> properties = new ArrayList<>();

    public List<PairJSON<String, PairJSON<String, String>>> getProperties() {
        return properties;
    }

    public void setProperties(List<PairJSON<String, PairJSON<String, String>>> properties) {
        this.properties = properties;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(properties);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        properties = (List<PairJSON<String, PairJSON<String, String>>>) in.readObject();
    }
}
