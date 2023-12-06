package com.example.demo.service.rnf;

import com.example.demo.entity.rnf.TitreRnfEntity;
import com.example.demo.repository.TitreRnfRepository;
import com.example.demo.service.Randomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class TitreRnfService {

    @Autowired
    TitreRnfRepository titreRnfRepository;

    @Autowired
    Randomizer randomizer;

    public TitreRnfEntity saveTpaTitreRnf() {
        TitreRnfEntity titreRnfEntity = new TitreRnfEntity();

        Date actualDate = new Date(System.currentTimeMillis());
        // Date actuelle -1 jour
        Date dateMoinsUnJour = new Date(actualDate.getTime() - 86400000);
        /*
        // Date actuelle -2 jour
        Date dateMoinsDeuxJours = new Date(actualDate.getTime() - 172800000);
         */
        long millisecondsForSevenDays = actualDate.getTime() + ((1 * 60 * 60 * 24 * 7) * (1000));
        Date dateMoinsDeuxJours = new Date(millisecondsForSevenDays);

        titreRnfEntity.setNomFacture("Nom facture");
        titreRnfEntity.setNombreFacture(1);
        titreRnfEntity.setRandomizeNomFic(randomizer.generateRandomStringAndChooseHowMuch(9) +"0");

        // EnTête
        titreRnfEntity.setDateEnTete(dateMoinsUnJour);
        titreRnfEntity.setREP("REP");
        titreRnfEntity.setNombreAticles(00000001);

        // Détails article
        titreRnfEntity.setNumeroClient("014556");
        titreRnfEntity.setNumeroPriseEnCharge(000070);
        titreRnfEntity.setDatePriseEnCharge(dateMoinsDeuxJours);
        titreRnfEntity.setMontant(randomizer.generateRandomNumberAndChooseHowMuch(4));
        titreRnfEntity.setObjetDette("test facture infraction voirie forestier1");

        titreRnfEntity.setReferenceTitre(this.generateReferenceTitreRnf(titreRnfEntity));
        titreRnfEntity.setNomFic(this.generateNomFicRnf(titreRnfEntity));

        return titreRnfRepository.save(titreRnfEntity);
    }

    public List<TitreRnfEntity> getAllTpaTitreRnf() {
        return titreRnfRepository.findAll();
    }

    public void deleteTpaTitreRnf(TitreRnfEntity titreRnfEntity){
        titreRnfRepository.delete(titreRnfEntity);
    }

    public void deleteAllTpaTitreRnf() {
        titreRnfRepository.deleteAll();
    }

    public TitreRnfEntity getTpaTitreRnfById(Long id) {
        return titreRnfRepository.findById(id).orElse(null);
    }

    public void updateInvoice(TitreRnfEntity updatedInvoice) {
        updatedInvoice.setReferenceTitre(this.generateReferenceTitreRnf(updatedInvoice));
        updatedInvoice.setNomFic(this.generateNomFicRnf(updatedInvoice));
        titreRnfRepository.save(updatedInvoice);
    }

    public String generateNomFicRnf(TitreRnfEntity titreRnfEntity){
        return "RNF_"+titreRnfEntity.getDateEnTete()+"_"+titreRnfEntity.getRandomizeNomFic();
    }

    public String generateReferenceTitreRnf(TitreRnfEntity titreRnfEntity){
        return titreRnfEntity.getRandomizeNomFic();
    }

}
