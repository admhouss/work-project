package by.premia.olga.project.entity;

import by.premia.olga.project.entity.constants.wheel.*;
import by.premia.olga.project.entity.constants.wheel.Version;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @author Vlad Abramov
 */
@Entity
@Table(name = "WHEELS")
public class Wheel implements Serializable {
    private static final long serialVersionUID = 76991695407351358L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "SPEED_INDEX", nullable = false)
    @Enumerated(EnumType.STRING)
    private SpeedIndex speedIndex;

    @ElementCollection
    @Column(name = "LOAD_INDEXES")
    private Set<Integer> loadIndexes;

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
}
