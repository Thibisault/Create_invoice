package com.example.demo.serviceTest;

import com.example.demo.entity.rnf.RnfEntity;
import com.example.demo.entity.rnf.TitreRnfEntity;
import com.example.demo.service.rnf.RnfService;
import com.example.demo.service.rnf.TitreRnfService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RnfServiceTest {

    @Autowired
    RnfService rnfService;

    @Autowired
    TitreRnfService titreRnfService;

    @Test
    void contextLoads() {
    }

    @Test
    void champsIntervalle() throws IllegalAccessException {
        RnfEntity rnfEntity = new RnfEntity();
        rnfService.champsIntervalle(rnfEntity, "idtech");
    }

    @Test
    void modifyStringAtPosition() {
        String strTest = "20200917REP00000007                                                  \n" +
                "014556ABBB2084070000702020091400000006000test facture infraction voirie forestier1                                                                                                                                                                                                                           \n";
        String replacement = "az";
        //replacement = rnfService.modifyStringAtPosition(strTest, 5, replacement);
        //System.out.println("Modified string: " + replacement);
        System.out.println("taille strTest: " + strTest.length());
    }

    @Test
    void addBlankSpace(){
        String original = "123456789";
        String replacement = "";
        //replacement = rnfService.addBlankSpace(original, replacement);
        System.out.println("Modified string: " + replacement+".");
    }

    @Test
    void addZero(){
        String original = "123";
        String replacement = "";
        //replacement = rnfService.addZero();
        System.out.println("Modified string: " + replacement+".");
    }

    //Test pour savoir si la concaténation de base a bien lieu avec les valeurs automatiques de créations du Rnf
    @Test
    void concatenation(){
        TitreRnfEntity titreRnfEntity = titreRnfService.saveTpaTitreRnf();
        //String enTete = rnfService.enTeteConcatenateRnf(titreRnfEntity);
        //String articleDetail = rnfService.articleDetailConcatenateRnf(titreRnfEntity);

    }

    @Test
    void integrationTestModifierChamp(){
        TitreRnfEntity titreRnfEntity = titreRnfService.saveTpaTitreRnf();
        titreRnfEntity.setNombreAticles(5);
        //String concatenationComplete = rnfService.contatenerRnfComplet(titreRnfEntity);
    }

    @Test
    void limiteChamp() throws IllegalAccessException {
        RnfEntity rnfEntity = new RnfEntity();

        int retour = rnfService.tailleChamps(rnfEntity, "numeroClient");
        System.out.println(retour);
    }
}

