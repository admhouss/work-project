package by.premiya.olga.project.util.json;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vlad Abramov
 */
public class NewItemJSON implements Serializable {

    private static final long serialVersionUID = 616167140819619522L;
    private List<PairJSON<String,PairJSON<String, String>>> properties = new ArrayList<>();
    private List<String> notSetFields = new ArrayList<>();
    private List<String> failedFields = new ArrayList<>();
    private boolean success = false;

    public List<PairJSON<String, PairJSON<String, String>>> getProperties() {
        return properties;
    }

    public void setProperties(List<PairJSON<String, PairJSON<String, String>>> properties) {
        this.properties = properties;
    }

    public List<String> getNotSetFields() {
        return notSetFields;
    }

    public void setNotSetFields(List<String> notSetFields) {
        this.notSetFields = notSetFields;
    }

    public List<String> getFailedFields() {
        return failedFields;
    }

    public void setFailedFields(List<String> failedFields) {
        this.failedFields = failedFields;
    }

    public boolean addNotSetField(String str) {
        return this.notSetFields.add(str);
    }

    public boolean addFailedField(String str) {
        return this.failedFields.add(str);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void clearErrors() {
        this.failedFields.clear();
        this.notSetFields.clear();
    }
}
