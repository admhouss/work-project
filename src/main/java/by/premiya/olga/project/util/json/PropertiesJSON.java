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
    private List<PairJSON<String,String>> labels;
    private Map<String, PairJSON<String, List<PairJSON<String, String>>>> enums;

    public List<PairJSON<String, String>> getLabels() {
        return labels;
    }

    public void setLabels(List<PairJSON<String, String>> labels) {
        this.labels = labels;
    }

    public Map<String, PairJSON<String, List<PairJSON<String, String>>>> getEnums() {
        return enums;
    }

    public void setEnums(Map<String, PairJSON<String, List<PairJSON<String, String>>>> enums) {
        this.enums = enums;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(labels);
        out.writeObject(enums);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        labels = (List<PairJSON<String, String>>) in.readObject();
        enums = (Map<String, PairJSON<String, List<PairJSON<String, String>>>>) in.readObject();
    }
}
