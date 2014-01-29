package by.premiya.olga.project.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author vlad
 */

@Entity
@Table(name = "ACCUMULATORS")
public class Accumulator implements Serializable{

    private static final long serialVersionUID = 759059743358133082L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "PRODUSER")
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
    private Integer cold_cranking = 0;

    @Column(name = "POLARITY")
    private String polarity = "";

    @Column(name = "LENGTH")
    private Integer length = 0;

    @Column(name = "WIDHT")
    private Integer width = 0;

    @Column(name = "HEIGHT")
    private Integer height = 0;
}
