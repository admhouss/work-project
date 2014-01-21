package by.premiya.olga.project.util.json;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.List;
import java.util.Map;

/**
 * @author Vlad Abramov
 */
@SuppressWarnings("unchecked")
public class PropertiesJSON implements Externalizable {

    private static final long serialVersionUID = 2511421981399985911L;
    private List<String> labels;
    private Map<String, List<String>> enums;

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public Map<String, List<String>> getEnums() {
        return enums;
    }

    public void setEnums(Map<String, List<String>> enums) {
        this.enums = enums;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(labels);
        out.writeObject(enums);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        labels = (List<String>) in.readObject();
        enums = (Map<String, List<String>>) in.readObject();
    }
}
