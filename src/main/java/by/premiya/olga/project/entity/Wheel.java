package by.premiya.olga.project.entity;

import by.premiya.olga.project.entity.constants.wheel.CarcassAndBeltConstruction;
import by.premiya.olga.project.entity.constants.wheel.SpeedIndex;
import by.premiya.olga.project.entity.constants.wheel.TypeOfConstruction;
import by.premiya.olga.project.entity.constants.wheel.Version;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Vlad Abramov
 */
@Entity
@Table(name = "WHEELS")
public class Wheel implements Serializable {

    private static final long serialVersionUID = -3893106271768517384L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PRICE")
    private Integer price;
    @Column(name = "SPEED_INDEX", nullable = false)
    @Enumerated(EnumType.STRING)
    private SpeedIndex speedIndex;
    @Column(name = "LOAD_INDEXES")
    private String loadIndexes; //TODO: parse it   (142/234)
    @Column(name = "PLY_RATING")
    private int plyRating;
    @Column(name = "TYPE_OF_CONSTRUCTION")
    @Enumerated(EnumType.STRING)
    private TypeOfConstruction typeOfConstruction;
    @Column(name = "CARCASS_AND_BELT_CONSTRUCTION")
    @Enumerated(EnumType.STRING)
    private CarcassAndBeltConstruction carcassAndBeltConstruction;
    @Column(name = "VERSION")
    @Enumerated(EnumType.STRING)
    private Version version;
    @Column(name = "OUTER_DIAMETER")
    private Integer outerDiameter;
    @Column(name = "SECTION_WIDTH")
    private Integer sectionWidth;
    @Column(name = "MAX_LOAD")
    private String maxLoad;     //TODO parse it 3255/5656
    @Column(name = "MAX_PRESSURE")
    private Float maxPressure;
    @Column(name = "WEIGHT")
    private Integer weight;
    @Column(name = "GATE_TYPE")
    private String gateType;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setLoadIndexes(String loadIndexes) {
        this.loadIndexes = loadIndexes;
    }

    public int getPlyRating() {
        return plyRating;
    }

    public void setPlyRating(int plyRating) {
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

    public void setMaxLoad(String maxLoad) {
        this.maxLoad = maxLoad;
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

    public Map<String,String> getStandardInfo() {
        Map<String, String> info = new HashMap<>();
        info.put("maxSpeed",speedIndex.getMaxSpeedString());
        info.put("price", price.toString());
        return info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wheel)) return false;

        Wheel wheel = (Wheel) o;

        if (id != null ? !id.equals(wheel.id) : wheel.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (speedIndex != null ? speedIndex.hashCode() : 0);
        result = 31 * result + (loadIndexes != null ? loadIndexes.hashCode() : 0);
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
                ", name=" + name +
                ", speedIndex=" + speedIndex +
                ", loadIndexes='" + loadIndexes + '\'' +
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
