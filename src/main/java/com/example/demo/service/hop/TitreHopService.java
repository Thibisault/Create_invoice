package com.example.demo.service.hop;

import com.example.demo.entity.hop.TitreHopEntity;
import com.example.demo.repository.TitreHopRepository;
import com.example.demo.service.ManipulerXml;
import com.example.demo.service.Randomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class TitreHopService {

    @Autowired
    ManipulerXml manipulerXml;

    @Autowired
    TitreHopRepository titreHopRepository;

    @Autowired
    Randomizer randomizer;

    public TitreHopEntity saveTpaTitreHop() throws IllegalAccessException, InstantiationException {
        TitreHopEntity titreHopEntity = manipulerXml.generateRandomValue(TitreHopEntity.class);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        Date actualDate = new Date(System.currentTimeMillis());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.format(actualDate);

        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        String year = yearFormat.format(timestamp);

        long millisecondsForSevenDays = actualDate.getTime() + ((1 * 60 * 60 * 24 * 7) * (1000));
        //long milliseconds = actualDate.getTime() + 172800000;
        Date dateActuellePlus2Jours = new Date(millisecondsForSevenDays);

        //Parametres
        titreHopEntity.setVersion("2");
        titreHopEntity.setTypFic("TIPIHOP");
        //EnTetePES
        titreHopEntity.setDteStr(actualDate);
        titreHopEntity.setIdPost("073037");
        titreHopEntity.setCodCol("816");
        titreHopEntity.setCodBud("00");
        titreHopEntity.setLibellePoste("CHAMBERY ETS HOSPITALIERS");
        titreHopEntity.setIdColl("26730006900015");
        titreHopEntity.setFinJur("730780525");
        titreHopEntity.setLibelleColBud("CHG BOURG ST MAURICE");
        //BlocBordereau
        titreHopEntity.setExer(year);
        titreHopEntity.setIdBord("1000115");
        titreHopEntity.setTypBord("01");
        titreHopEntity.setNbrePce("1");
        //BlocPiece
        titreHopEntity.setIdPce(randomizer.generateRandomNumberAndChooseHowMuch(7));
        titreHopEntity.setTypPce("01");
        titreHopEntity.setNatPce("01");
        titreHopEntity.setCatPce("1");
        titreHopEntity.setDebFact(actualDate);
        titreHopEntity.setFinFact(actualDate);
        titreHopEntity.setEtatPce("02");
        titreHopEntity.setDtePcePec(dateActuellePlus2Jours);
        //InfoLignePiece
        titreHopEntity.setIdLigne("1");
        titreHopEntity.setObjLignePce("SIPHON HENDRICKX SCOTT - Consultation du 01/03/2023");
        titreHopEntity.setNature("732424");
        titreHopEntity.setMtTTC(BigDecimal.valueOf(randomizer.generateRandomNumberAndChooseHowMuch(4)));
        titreHopEntity.setCodEtGeo("75");
        //InfoTiers
        titreHopEntity.setDteMalade(actualDate);
        titreHopEntity.setRefTiers("170040922304");
        titreHopEntity.setCatTiers("01");
        titreHopEntity.setTypTiers("01");
        titreHopEntity.setCivilite("MME");
        titreHopEntity.setNom("HENDRICKX");
        titreHopEntity.setPrenom("STEPHANIE");
        //Adresse
        titreHopEntity.setAdr1("17 RUE MARGUERITE YOURCENAR");
        titreHopEntity.setAdr2("LA RESIDENCE");
        titreHopEntity.setAdr3("BP 60");
        titreHopEntity.setCP("95820");
        titreHopEntity.setVille("BRUYERES SUR OISE");

        titreHopEntity.setNombreFacture(1);
        titreHopEntity.setNomFacture("Nom facture");

        titreHopEntity.setReferenceTitre(this.generateReferenceTitreHop(titreHopEntity));
        titreHopEntity.setNomFic(this.generateNomFicHop(titreHopEntity));
        return titreHopRepository.save(titreHopEntity);
    }
    public List<TitreHopEntity> getAllTpaTitreHop() {
        return titreHopRepository.findAll();
    }

    public void deleteTpaTitreHop(TitreHopEntity tpaTitreHop){
        titreHopRepository.delete(tpaTitreHop);
    }

    public void deleteAllTpaTitreHop() {
        titreHopRepository.deleteAll();
    }

    public TitreHopEntity getTpaTitreHopById(Long id) {
        return titreHopRepository.findById(id).orElse(null);
    }

    public void updateInvoice(TitreHopEntity updatedInvoice) {
        updatedInvoice.setReferenceTitre(this.generateReferenceTitreHop(updatedInvoice));
        updatedInvoice.setNomFic(this.generateNomFicHop(updatedInvoice));
        titreHopRepository.save(updatedInvoice);
    }

    public String generateNomFicHop(TitreHopEntity titreHopEntity){
        return titreHopEntity.getTypFic()+"_"+titreHopEntity.getIdPost()+"_"+titreHopEntity.getCodCol()+"_"+titreHopEntity.getCodBud()+"_"+titreHopEntity.getExer()+"_";
    }

    public String generateReferenceTitreHop(TitreHopEntity titreHopEntity){
        return titreHopEntity.getExer()+"_"+titreHopEntity.getIdPce()+"_"+titreHopEntity.getIdLigne();
    }
}
