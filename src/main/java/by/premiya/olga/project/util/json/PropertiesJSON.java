package by.premiya.olga.project.util.json;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Vlad Abramov
 */
@SuppressWarnings("unchecked")
public class PropertiesJSON implements Externalizable {

    private static final long serialVersionUID = -4618841375750618605L;      //todo new fields for labels and enums
    private Map<String, Object> properties = new HashMap<>();

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public void putProperty(String name, Object value) {
        properties.put(name, value);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(properties);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        properties = (Map<String, Object>) in.readObject();
    }
}
