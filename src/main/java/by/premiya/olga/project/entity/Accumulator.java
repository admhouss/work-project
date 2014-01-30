package by.premiya.olga.project.entity;

import by.premiya.olga.project.entity.constants.accumulator.Polarity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author vlad
 */

@Entity
@Table(name = "ACCUMULATORS")
public class Accumulator implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "PRODUСER")
    private String producer = "";

    @Column(name = "MODEL")
    private String model = "";

    @Column(name = "PRICE",nullable = false)
    private Integer price = 0;

    @Column(name = "VOLTAGE")
    private Integer voltage = 0;

    @Column(name = "CAPACITY")
    private Integer capacity = 0;

    @Column(name = "COLD_CRANKING")
    private Integer coldСranking = 0;

    @Column(name = "POLARITY")
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

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
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
}
