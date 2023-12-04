package com.example.demo.service.rol;

import com.example.demo.entity.rol.TitreRolEntity;
import com.example.demo.repository.TitreRolRepository;
import com.example.demo.service.ManipulerXml;
import com.example.demo.service.Randomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;

@Service
public class TitreRolService {

    @Autowired
    TitreRolRepository titreRolRepository;

    @Autowired
    ManipulerXml manipulerXml;

    @Autowired
    Randomizer randomizer;

    public TitreRolEntity saveTpaTitreRol() throws IllegalAccessException, InstantiationException {
        TitreRolEntity titreRolEntity = manipulerXml.generateRandomValue(TitreRolEntity.class);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        Date actualDate = new Date(System.currentTimeMillis());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.format(actualDate);

        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        String year = yearFormat.format(timestamp);

        long millisecondsForSevenDays = actualDate.getTime() + ((1 * 60 * 60 * 24 * 7) * (1000));
        //long milliseconds = actualDate.getTime() + 172800000;
        Date dateActuellePlus2Jours = new Date(millisecondsForSevenDays);

        // Parametres
        titreRolEntity.setVersion("2");
        titreRolEntity.setTypFic("TIPIROL");

        // EnTetePES
        titreRolEntity.setDteStr(actualDate);
        titreRolEntity.setIdPost("001021");
        titreRolEntity.setCodCol("816");
        titreRolEntity.setCodBud("00");

        // BlocBordereau
        titreRolEntity.setExer(year);
        titreRolEntity.setIdRol("7");
        titreRolEntity.setTypRol("01");
        titreRolEntity.setDteAsp(actualDate);

        // BlocPiece
        titreRolEntity.setIdPce(randomizer.generateRandomNumberAndChooseHowMuch(7));
        titreRolEntity.setNumDette(randomizer.generateRandomStringAndChooseHowMuch(7 ));
        titreRolEntity.setPer("1");
        titreRolEntity.setEtatPce("01");
        titreRolEntity.setDtePcePec(dateActuellePlus2Jours);
        titreRolEntity.setCodProdLoc("83");
        titreRolEntity.setObjPce("BOUALOUCHEN BOUDROT Ryad - Facture Cantine Juillet 2023");
        titreRolEntity.setMtTTC(BigDecimal.valueOf(randomizer.generateRandomNumberAndChooseHowMuch(4)));

        // InfoTiers
        titreRolEntity.setRefTiers("310107814635");
        titreRolEntity.setCatTiers("01");
        titreRolEntity.setCivilite("MOUMME");
        titreRolEntity.setNom("BOUDROT");
        titreRolEntity.setPrenom("MARION");

        // Adresse
        titreRolEntity.setAdr1("206 rue de la Californie");
        titreRolEntity.setAdr2("LA RESIDENCE");
        titreRolEntity.setAdr3("BP 60");
        titreRolEntity.setCp("01450");
        titreRolEntity.setVille("CERDON");

        titreRolEntity.setNombreFacture(1);
        titreRolEntity.setNomFacture("Nom facture");

        titreRolEntity.setReferenceTitre(this.generateReferenceTitreRol(titreRolEntity));
        titreRolEntity.setNomFic(this.generateNomFicRol(titreRolEntity));

        return titreRolRepository.save(titreRolEntity);
    }

    public List<TitreRolEntity> getAllTpaTitreRol() {
        return titreRolRepository.findAll();
    }

    public void deleteTpaTitreRol(TitreRolEntity tpaTitreRol) {
        titreRolRepository.delete(tpaTitreRol);
    }

    public void deleteAllTpaTitreRol() {
        titreRolRepository.deleteAll();
    }

    public TitreRolEntity getTpaTitreRolById(Long id) {
        return titreRolRepository.findById(id).orElse(null);
    }

    public void updateInvoice(TitreRolEntity updatedInvoice) {
        updatedInvoice.setReferenceTitre(this.generateReferenceTitreRol(updatedInvoice));
        updatedInvoice.setNomFic(this.generateNomFicRol(updatedInvoice));
        titreRolRepository.save(updatedInvoice);
    }

    public String generateNomFicRol(TitreRolEntity titreRolEntity) {
        return titreRolEntity.getTypFic() + "_" + titreRolEntity.getIdPost() + "_" + titreRolEntity.getCodCol() + "_" + titreRolEntity.getCodBud() + "_" + titreRolEntity.getExer() + "_";
    }

    public String generateReferenceTitreRol(TitreRolEntity titreRolEntity) {
        return titreRolEntity.getExer() + "_" + titreRolEntity.getCodProdLoc() + "_" + titreRolEntity.getNumDette();
    }
}
