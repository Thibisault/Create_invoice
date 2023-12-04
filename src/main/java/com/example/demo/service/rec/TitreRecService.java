package com.example.demo.service.rec;

import com.example.demo.entity.rec.TitreRecEntity;
import com.example.demo.repository.TitreRecRepository;
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
public class TitreRecService {

    @Autowired
    ManipulerXml manipulerXml;

    @Autowired
    TitreRecRepository titreRecRepository;

    @Autowired
    Randomizer randomizer;

    public TitreRecEntity saveTpaTitreRec() throws IllegalAccessException, InstantiationException {
        TitreRecEntity titreRecEntity = manipulerXml.generateRandomValue(TitreRecEntity.class);

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
        titreRecEntity.setVersion("2");
        titreRecEntity.setTypFic("TIPIREC");
        //EnTetePES
        titreRecEntity.setDteStr(actualDate);
        titreRecEntity.setIdPost("076029");
        titreRecEntity.setLibellePoste("MONTVILLE");
        titreRecEntity.setIdColl("21760136800012");
        titreRecEntity.setCodCol("638");
        titreRecEntity.setCodBud("00");
        titreRecEntity.setLibelleColBud("BRACHY");
        //BlocBordereau
        titreRecEntity.setExer(year);
        titreRecEntity.setIdBord("30");
        titreRecEntity.setTypBord("01");
        titreRecEntity.setNbrePce("1");
        //BlocPiece
        titreRecEntity.setIdPce(randomizer.generateRandomNumberAndChooseHowMuch(7));
        titreRecEntity.setTypPce("01");
        titreRecEntity.setNatPce("01");
        titreRecEntity.setCatPce("1");
        titreRecEntity.setEtatPce("02");
        titreRecEntity.setDtePcePec(dateActuellePlus2Jours);
        //InfoLignePiece
        titreRecEntity.setIdLigne("1");
        titreRecEntity.setNature("752");
        titreRecEntity.setMtTTC(BigDecimal.valueOf(randomizer.generateRandomNumberAndChooseHowMuch(4)));
        //InfoTiers
        titreRecEntity.setRefTiers("310104426498");
        titreRecEntity.setCatTiers("01");
        titreRecEntity.setTypTiers("01");
        titreRecEntity.setCivilite("MME");
        titreRecEntity.setNom("COINTRE");
        titreRecEntity.setPrenom("CINDY");
        //Adresse
        titreRecEntity.setAdr1("53 ROUTE DE LA MER");
        titreRecEntity.setAdr2("LA RESIDENCE");
        titreRecEntity.setAdr3("BP 60");
        titreRecEntity.setCP("76730");
        titreRecEntity.setVille("BRACHY");

        titreRecEntity.setNombreFacture(1);
        titreRecEntity.setNomFacture("Nom facture");

        titreRecEntity.setReferenceTitre(this.generateReferenceTitreRec(titreRecEntity));
        titreRecEntity.setNomFic(this.generateNomFicRec(titreRecEntity));
        return titreRecRepository.save(titreRecEntity);
    }
    public List<TitreRecEntity> getAllTpaTitreRec() {
        return titreRecRepository.findAll();
    }

    public void deleteTpaTitreRec(TitreRecEntity titreRecEntity){
        titreRecRepository.delete(titreRecEntity);
    }

    public void deleteAllTpaTitreRec() {
        titreRecRepository.deleteAll();
    }

    public TitreRecEntity getTpaTitreRecById(Long id) {
        return titreRecRepository.findById(id).orElse(null);
    }

    public void updateInvoice(TitreRecEntity updatedInvoice) {
        updatedInvoice.setReferenceTitre(this.generateReferenceTitreRec(updatedInvoice));
        updatedInvoice.setNomFic(this.generateNomFicRec(updatedInvoice));
        titreRecRepository.save(updatedInvoice);
    }

    public String generateNomFicRec(TitreRecEntity titreRecEntity){
        return titreRecEntity.getTypFic()+"_"+titreRecEntity.getIdPost()+"_"+titreRecEntity.getCodCol()+"_"+titreRecEntity.getCodBud()+"_"+titreRecEntity.getExer()+"_";
    }

    public String generateReferenceTitreRec(TitreRecEntity titreRecEntity){
        return titreRecEntity.getExer()+"_"+titreRecEntity.getIdPce()+"_"+titreRecEntity.getIdLigne();
    }
}
