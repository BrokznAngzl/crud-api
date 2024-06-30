package freetime.porkyapi.breeds.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;

@Entity
@Data
@Table(name = "breeds")
public class BreedsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "breedsid")
    private BigInteger breedsID;

    @Column(name = "breedsname")
    private String breedsName;
}
