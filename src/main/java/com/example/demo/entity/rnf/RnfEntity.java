package com.example.demo.entity.rnf;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class RnfEntity {


    @Column(name = "nomFacture")
    private String nomFacture;

    @Column(name = "nombreFacture")
    private String nombreFacture;

    // En tête
    //Date doit être antérieur d'un ou deux jours à la date sytème mais plus récente que la date de prise en charge
    @Column(name = "dateEnTete")
    private String[] dateEnTete = new String[]{"1","8"};

    // Rajouter "REP" tel quel entre les deux champs date/nombreArticle
    @Column(name = "REP")
    private String[] rep = new String[]{"9", "11"};

    // Le nombre d'article permettra de genrer le nombre d'article après l'entête qui est saisie par l'utilisateur.
    @Column(name = "nombreAticles")
    private String[] nombreAticles = new String[]{"12", "19"};

    // Remplir toute cette zone avec des espaces blancs
    @Column (name= "fillerEnteteAvecBlanc")
    private String[] fillerEnteteAvecBlanc = new String[]{"20", "70"};

    // Détail article
    // Rempli par l'utilisateur
    @Column(name = "numeroClient")
    private String[] numeroClient = new String[]{"1", "6"};

    // devra être concatené en fonction de ce que l'utilisateur à rentré
    @Column (name = "referenceTitre")
    private String[] referenceTitre = new String []{"7", "16"};

    @Column(name = "numeroPriseEnCharge")
    private String[] numeroPriseEnCharge = new String[]{"17", "22"};

    // Doit être antiérieur à la date système mais plus ancienne que la date
    @Column(name = "datePriseEnCharge")
    private String[] datePriseEnCharge = new String[]{"23", "30"};

    @Column(name = "montant")
    private String[] montant = new String[]{"31" , "41"};

    @Column(name = "objetDette")
    private String[] objetDette =  new String[]{"42","150"};

    // Remplir toute ces zones avec des espaces blanc
    @Column(name = "filler")
    private String[] filler = new String[]{"151", "302"};

}

