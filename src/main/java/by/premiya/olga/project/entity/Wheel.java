package by.premiya.olga.project.entity;

import by.premiya.olga.project.entity.constants.producers.WheelsProducer;
import by.premiya.olga.project.entity.constants.wheel.*;
import by.premiya.olga.project.entity.constants.wheel.Version;
import by.premiya.olga.project.util.json.PairJSON;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Vlad Abramov
 */
@Entity
@Table(name = "WHEELS")
public class Wheel implements Serializable {

    private static final long serialVersionUID = 2191853262571204271L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "PIC_NAME")
    private String picName;
    @Column(name = "PRODUCER", nullable = false)
    @Enumerated(EnumType.STRING)
    private WheelsProducer producer;
    @Column(name = "MODEL", nullable = false)
    private String model;
    @Column(name = "PRICE", nullable = false)
    private Integer price;
    @Column(name = "SPEED_INDEX", nullable = false)
    @Enumerated(EnumType.STRING)
    private SpeedIndex speedIndex;
    @Column(name = "LOAD_INDEXES")
    private String loadIndexes = "";
    @Column(name = "TREAD_PATTERN")
    @Enumerated(EnumType.STRING)
    private TreadPattern treadPattern = TreadPattern.NAN;
    @Column(name = "PLY_RATING")
    private Integer plyRating = 0;
    @Column(name = "TYPE_OF_CONSTRUCTION")
    @Enumerated(EnumType.STRING)
    private TypeOfConstruction typeOfConstruction = TypeOfConstruction.NAN;
    @Column(name = "CARCASS_AND_BELT_CONSTRUCTION")
    @Enumerated(EnumType.STRING)
    private CarcassAndBeltConstruction carcassAndBeltConstruction = CarcassAndBeltConstruction.NAN;
    @Column(name = "VERSION")
    @Enumerated(EnumType.STRING)
    private Version version = Version.NAN;
    @Column(name = "OUTER_DIAMETER")
    private Integer outerDiameter = 0;
    @Column(name = "SECTION_WIDTH")
    private Integer sectionWidth = 0;
    @Column(name = "MAX_LOAD")
    private String maxLoad = "";
    @Column(name = "MAX_PRESSURE")
    private Float maxPressure = 0.0f;
    @Column(name = "WEIGHT")
    private Integer weight = 0;
    @Column(name = "GATE_TYPE")
    private String gateType = "";

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String name) {
        this.model = name;
    }

    public TreadPattern getTreadPattern() {
        return treadPattern;
    }

    public void setTreadPattern(TreadPattern treadPattern) {
        this.treadPattern = treadPattern;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public WheelsProducer getProducer() {
        return producer;
    }

    public void setProducer(WheelsProducer producer) {
        this.producer = producer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SpeedIndex getSpeedIndex() {
        return speedIndex;
    }

    public void setSpeedIndex(SpeedIndex speedIndex) {
        this.speedIndex = speedIndex;
    }

    public String getLoadIndexes() {
        return loadIndexes;
    }

    public void setLoadIndexes(String loadIndexes) throws NumberFormatException {
        String[] numbers = loadIndexes.split("/");
        for (String number : numbers) {
            Integer.parseInt(number);
            this.loadIndexes = loadIndexes;
        }
    }

    public int getPlyRating() {
        return plyRating;
    }

    public void setPlyRating(int plyRating) {
        this.plyRating = plyRating;
    }

    public void setPlyRating(Integer plyRating) {
        this.plyRating = plyRating;
    }

    public TypeOfConstruction getTypeOfConstruction() {
        return typeOfConstruction;
    }

    public void setTypeOfConstruction(TypeOfConstruction typeOfConstruction) {
        this.typeOfConstruction = typeOfConstruction;
    }

    public CarcassAndBeltConstruction getCarcassAndBeltConstruction() {
        return carcassAndBeltConstruction;
    }

    public void setCarcassAndBeltConstruction(CarcassAndBeltConstruction carcassAndBeltConstruction) {
        this.carcassAndBeltConstruction = carcassAndBeltConstruction;
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public Integer getOuterDiameter() {
        return outerDiameter;
    }

    public void setOuterDiameter(Integer outerDiameter) {
        this.outerDiameter = outerDiameter;
    }

    public Integer getSectionWidth() {
        return sectionWidth;
    }

    public void setSectionWidth(Integer sectionWidth) {
        this.sectionWidth = sectionWidth;
    }

    public String getMaxLoad() {
        return maxLoad;
    }

    public void setMaxLoad(String maxLoad) throws NumberFormatException {
        String[] numbers = loadIndexes.split("/");
        for (String number : numbers) {
            Integer.parseInt(number);
            this.maxLoad = maxLoad;
        }
    }

    public Float getMaxPressure() {
        return maxPressure;
    }

    public void setMaxPressure(Float maxPressure) {
        this.maxPressure = maxPressure;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getGateType() {
        return gateType;
    }

    public void setGateType(String gateType) {
        this.gateType = gateType;
    }

    public Map<String, Object> getStandardInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("model", model);
        info.put("producer", producer.getString());
        info.put("list", Arrays.asList(
                new PairJSON<>("Макс. скорость", this.speedIndex.getString()),
                new PairJSON<>("Цена", String.valueOf(this.price))));
        return info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wheel)) return false;

        Wheel wheel = (Wheel) o;

        return !(id != null ? !id.equals(wheel.id) : wheel.id != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (picName != null ? picName.hashCode() : 0);
        result = 31 * result + (producer != null ? producer.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (speedIndex != null ? speedIndex.hashCode() : 0);
        result = 31 * result + (loadIndexes != null ? loadIndexes.hashCode() : 0);
        result = 31 * result + (treadPattern != null ? treadPattern.hashCode() : 0);
        result = 31 * result + plyRating;
        result = 31 * result + (typeOfConstruction != null ? typeOfConstruction.hashCode() : 0);
        result = 31 * result + (carcassAndBeltConstruction != null ? carcassAndBeltConstruction.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (outerDiameter != null ? outerDiameter.hashCode() : 0);
        result = 31 * result + (sectionWidth != null ? sectionWidth.hashCode() : 0);
        result = 31 * result + (maxLoad != null ? maxLoad.hashCode() : 0);
        result = 31 * result + (maxPressure != null ? maxPressure.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (gateType != null ? gateType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Wheel{" +
                "id=" + id +
                ", picName=" + picName +
                ", producer=" + producer +
                ", model=" + model +
                ", price=" + price +
                ", speedIndex=" + speedIndex +
                ", loadIndexes='" + loadIndexes + '\'' +
                ", treadPattern='" + treadPattern + '\'' +
                ", plyRating=" + plyRating +
                ", typeOfConstruction=" + typeOfConstruction +
                ", carcassAndBeltConstruction=" + carcassAndBeltConstruction +
                ", version=" + version +
                ", outerDiameter=" + outerDiameter +
                ", sectionWidth=" + sectionWidth +
                ", maxLoad='" + maxLoad + '\'' +
                ", maxPressure=" + maxPressure +
                ", weight=" + weight +
                ", gateType='" + gateType + '\'' +
                '}';
    }
}
