package by.premiya.olga.project.util.json;

import by.premiya.olga.project.constants.FieldType;

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

    private static final long serialVersionUID = -9008803091422078525L;
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

    public String getName(FieldType type, String fieldName) {
        switch (type) {
            case LABEL:
                return getLabelName(fieldName);
            case ENUM:
                return getEnumName(fieldName);
        }
        return null;
    }

    private String getEnumName(String fieldName) {
        return enums.get(fieldName).getFirst();
    }

    private String getLabelName(String fieldName) {
        for (PairJSON<String,String> pair : labels) {
            if (pair.getFirst().equals(fieldName)) {
                return pair.getSecond();
            }
        }
        return null;
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
