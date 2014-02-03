package by.premiya.olga.project.util.json;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vlad Abramov
 */
public class ItemJSON implements Serializable {

    private static final long serialVersionUID = -5455539925485031460L;
    private List<PairJSON<String,PairJSON<String, String>>> properties = new ArrayList<>();
    private List<String> notSetFields = new ArrayList<>();
    private List<String> failedFields = new ArrayList<>();
    private String objectModel = "";
    private String objectProducer = "";
    private String objectProduct = "";
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

    public void addProperty(PairJSON<String, PairJSON<String, String>> property) {
        properties.add(property);
    }

    public boolean addNotSetField(String str) {
        return this.notSetFields.add(str);
    }

    public boolean addFailedField(String str) {
        return this.failedFields.add(str);
    }

    public String getObjectModel() {
        return objectModel;
    }

    public void setObjectModel(String objectModel) {
        this.objectModel = objectModel;
    }

    public String getObjectProducer() {
        return objectProducer;
    }

    public void setObjectProducer(String objectProducer) {
        this.objectProducer = objectProducer;
    }

    public String getObjectProduct() {
        return objectProduct;
    }

    public void setObjectProduct(String objectProduct) {
        this.objectProduct = objectProduct;
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
