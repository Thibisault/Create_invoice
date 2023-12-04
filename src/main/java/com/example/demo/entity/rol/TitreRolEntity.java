package com.example.demo.entity.rol;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@Entity
public class TitreRolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK")
    private Long id;

    @Column(name = "referenceTitre")
    private String referenceTitre;

    @Column(name = "nomFacture")
    private String nomFacture;

    @Column(name = "nombreFacture")
    private int nombreFacture;

    @Column(name = "version")
    private String version;

    @Column(name = "typFic")
    private String typFic;

    @Column(name = "nomFic")
    private String nomFic;

    @Column(name = "dteStr")
    private Date dteStr;

    @Column(name = "idPost")
    private String idPost;

    @Column(name = "codCol")
    private String codCol;

    @Column(name = "codBud")
    private String codBud;

    @Column(name = "exer")
    private String exer;

    @Column(name = "idRol")
    private String idRol;

    @Column(name = "typRol")
    private String typRol;

    @Column(name = "dteAsp")
    private Date dteAsp;

    @Column(name = "idPce")
    private long idPce;

    @Column(name = "numDette")
    private String numDette;

    @Column(name = "per")
    private String per;

    @Column(name = "etatPce")
    private String etatPce;

    @Column(name = "dtePcePec")
    private Date dtePcePec;

    @Column(name = "codProdLoc")
    private String codProdLoc;

    @Column(name = "objPce")
    private String objPce;

    @Column(name = "mtTTC")
    private BigDecimal mtTTC;

    @Column(name = "refTiers")
    private String refTiers;

    @Column(name = "catTiers")
    private String catTiers;

    @Column(name = "civilite")
    private String civilite;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "adr1")
    private String adr1;

    @Column(name = "adr2")
    private String adr2;

    @Column(name = "adr3")
    private String adr3;

    @Column(name = "cp")
    private String cp;

    @Column(name = "ville")
    private String ville;
}
