package com.example.demo.entity.rnf;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@Entity
public class TitreRnfEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK")
    private Long id;

    @Column(name = "nomFacture")
    private String nomFacture;

    @Column(name = "nombreFacture")
    private int nombreFacture;

    @Column(name = "nomFic")
    private String nomFic;

    // EnTête
    @Column(name = "dateEnTete")
    private Date dateEnTete;

    @Column(name = "REP")
    private String REP = "REP";

    @Column(name = "nombreAticles")
    private Integer nombreAticles;

    // Détail article
    @Column(name = "numeroClient")
    private String numeroClient;

    @Column (name = "referenceTitre")
    private String referenceTitre;

    @Column(name = "numeroPriseEnCharge")
    private Integer numeroPriseEnCharge;

    @Column(name = "datePriseEnCharge")
    private Date datePriseEnCharge;

    @Column(name = "montant")
    private long montant;

    @Column(name = "objetDette")
    private String objetDette;
}
