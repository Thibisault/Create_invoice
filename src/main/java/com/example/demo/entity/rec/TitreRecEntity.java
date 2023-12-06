package com.example.demo.entity.rec;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Data
public class TitreRecEntity {

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

    @Column(name = "xmlns_xsi")
    private String xmlns_xsi;

    @Column(name = "xmlns_n")
    private String xmlns_n;

    @Column(name = "xsi_schemaLocation")
    private String xsi_schemaLocation;

    @Column(name = "Enveloppe")
    private String Enveloppe;

    @Column(name = "EnTetePES")
    private String EnTetePES;

    @Column(name = "PES_TIPI_Recette")
    private String PES_TIPI_Recette;

    @Column(name = "version")
    private String version;

    @Column(name = "typFic")
    private String typFic;

    @Column(name = "nomFic")
    private String nomFic;

    @Temporal(TemporalType.DATE)
    private Date DteStr;

    @Column(name = "IdPost")
    private String IdPost;

    @Column(name = "LibellePoste")
    private String LibellePoste;

    @Column(name = "IdColl")
    private String IdColl;

    @Column(name = "CodCol")
    private String CodCol;

    @Column(name = "CodBud")
    private String CodBud;

    @Column(name = "LibelleColBud")
    private String LibelleColBud;

    @Column(name = "Bordereau")
    private String Bordereau;

    @Column(name = "BlocBordereau")
    private String BlocBordereau;

    @Column(name = "Piece")
    private String Piece;

    @Column(name = "Exer")
    private String Exer;

    @Column(name = "IdBord")
    private String IdBord;

    @Column(name = "TypBord")
    private String TypBord;

    @Column(name = "NbrePce")
    private String NbrePce;

    @Size(max = 8)
    @Column(name = "IdPce", length = 8)
    private long IdPce;

    @Column(name = "TypPce")
    private String TypPce;

    @Column(name = "NatPce")
    private String NatPce;

    @Column(name = "CatPce")
    private String CatPce;

    @Column(name = "EtatPce")
    private String EtatPce;

    @Temporal(TemporalType.DATE)
    @Column(name = "DtePcePec")
    private Date DtePcePec;

    @Column(name = "IdLigne")
    private String IdLigne;

    @Column(name ="ObjLignePce")
    private String ObjLignePce;

    @Column(name = "Nature")
    private String Nature;

    @Column(name = "MtTTC")
    private BigDecimal MtTTC;

    @Column(name = "RefTiers")
    private String RefTiers;

    @Column(name = "CatTiers")
    private String CatTiers;

    @Column(name = "TypTiers")
    private String TypTiers;

    @Column(name = "Civilite")
    private String Civilite;

    @Column(name = "Nom")
    private String Nom;

    @Column(name = "Prenom")
    private String Prenom;

    @Column(name = "Adr1")
    private String Adr1;

    @Column(name = "Adr2")
    private String Adr2;

    @Column(name = "Adr3")
    private String Adr3;

    @Column(name = "CP")
    private String CP;

    @Column(name = "Ville")
    private String Ville;
}
