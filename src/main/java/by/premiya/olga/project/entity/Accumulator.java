package by.premiya.olga.project.entity;

import by.premiya.olga.project.constants.accumulator.Polarity;
import by.premiya.olga.project.constants.producers.AccumulatorsProducer;
import by.premiya.olga.project.util.json.PairJSON;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author vlad
 */

@Entity
@Table(name = "ACCUMULATORS")
public class Accumulator implements Serializable {

    private static final long serialVersionUID = -3763363501863805445L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "IMAGE_ID")
    private Integer imageId = -1;
    @Column(name = "PRODUCER", nullable = false)
    private AccumulatorsProducer producer = AccumulatorsProducer.NAN;
    @Column(name = "MODEL", nullable = false)
    private String model = "";
    @Column(name = "PRICE", nullable = false)
    private Integer price = 0;
    @Column(name = "VOLTAGE", nullable = false)
    private Integer voltage = 0;
    @Column(name = "CAPACITY")
    private Integer capacity = 0;
    @Column(name = "COLD_CRANKING")
    private Integer coldСranking = 0;
    @Column(name = "POLARITY", nullable = false)
    @Enumerated(EnumType.STRING)
    private Polarity polarity = Polarity.NAN;
    @Column(name = "LENGTH")
    private Integer length = 0;
    @Column(name = "WIDTH")
    private Integer width = 0;
    @Column(name = "HEIGHT")
    private Integer height = 0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public AccumulatorsProducer getProducer() {
        return producer;
    }

    public void setProducer(AccumulatorsProducer producer) {
        this.producer = producer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getVoltage() {
        return voltage;
    }

    public void setVoltage(Integer voltage) {
        this.voltage = voltage;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getColdСranking() {
        return coldСranking;
    }

    public void setColdСranking(Integer coldСranking) {
        this.coldСranking = coldСranking;
    }

    public Polarity getPolarity() {
        return polarity;
    }

    public void setPolarity(Polarity polarity) {
        this.polarity = polarity;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Map<String, Object> getStandardInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("model", model);
        info.put("producer", producer.getString());
        info.put("list", Arrays.asList(
                new PairJSON<>("Напряжение", String.valueOf(voltage)),
                new PairJSON<>("Полярность", this.polarity.getString()),
                new PairJSON<>("Цена", String.valueOf(this.price))));
        return info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Accumulator that = (Accumulator) o;

        return !(id != null ? !id.equals(that.id) : that.id != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (imageId != null ? imageId.hashCode() : 0);
        result = 31 * result + (producer != null ? producer.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (voltage != null ? voltage.hashCode() : 0);
        result = 31 * result + (capacity != null ? capacity.hashCode() : 0);
        result = 31 * result + (coldСranking != null ? coldСranking.hashCode() : 0);
        result = 31 * result + (polarity != null ? polarity.hashCode() : 0);
        result = 31 * result + (length != null ? length.hashCode() : 0);
        result = 31 * result + (width != null ? width.hashCode() : 0);
        result = 31 * result + (height != null ? height.hashCode() : 0);
        return result;
    }
}
