package com.example.demo.service.rnf;

import com.example.demo.entity.rnf.RnfEntity;
import com.example.demo.entity.rnf.TitreRnfEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Service
public class RnfService {

    // Permet d'assembler l'entête avec un nombre d'"articlesDétails" correspondant à la valeur de "nombreArticle"
    public String contatenerRnfComplet(TitreRnfEntity titreRnfEntity) throws IllegalAccessException {
        String enTete = this.enTeteConcatenateRnf(titreRnfEntity);
        String detailArticleTemporal = this.articleDetailConcatenateRnf(titreRnfEntity);
        String detailArticle = "";

        for (int i = 0; i < titreRnfEntity.getNombreAticles(); i++) {
            detailArticle = detailArticle + detailArticleTemporal;
        }
        return enTete + detailArticle;
    }

    // créer une concatenation de l'entête selon la base de données
    public String enTeteConcatenateRnf(TitreRnfEntity titreRnfEntity) throws IllegalAccessException {
        RnfEntity rnfEntity = new RnfEntity();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateEnTete = sdf.format(titreRnfEntity.getDateEnTete());
        int dateEnTeteInter = tailleChamps(rnfEntity, "dateEnTete");
        if (dateEnTete.length() < dateEnTeteInter){
            int caracteresManquant = dateEnTeteInter - dateEnTete.length();
            dateEnTete = addBlankSpace(caracteresManquant, dateEnTete);
        }

        int nombrerAticlesInter = tailleChamps(rnfEntity, "nombreAticles");
        String valueOfNumberArticles = String.valueOf(titreRnfEntity.getNombreAticles());
        if (valueOfNumberArticles.length() < nombrerAticlesInter){
            int caracteresManquant = nombrerAticlesInter - valueOfNumberArticles.length();
            valueOfNumberArticles = addZero(caracteresManquant, valueOfNumberArticles);
        }

        int fillerEnteteAvecBlancInter = tailleChamps(rnfEntity, "fillerEnteteAvecBlanc");
        String valueOfFillerEnteteAvecBlanc = "";
        int caracteresManquant = fillerEnteteAvecBlancInter - valueOfFillerEnteteAvecBlanc.length();
        valueOfFillerEnteteAvecBlanc = addBlankSpace(caracteresManquant-1, valueOfFillerEnteteAvecBlanc);

        return dateEnTete + titreRnfEntity.getREP() + valueOfNumberArticles + valueOfFillerEnteteAvecBlanc + "\n";
    }

    // créer une concatenation d'un article détail selon la base de données
    public String articleDetailConcatenateRnf(TitreRnfEntity titreRnfEntity) throws IllegalAccessException {
        RnfEntity rnfEntity = new RnfEntity();

        int numeroClientInter = tailleChamps(rnfEntity, "numeroClient");
        String valueOfNumeroClient = String.valueOf(titreRnfEntity.getNumeroClient());
        if (valueOfNumeroClient.length() < numeroClientInter){
            int caracteresManquant = numeroClientInter - valueOfNumeroClient.length();
            valueOfNumeroClient = addBlankSpace(caracteresManquant, valueOfNumeroClient);
        }

        int referenceTitreInter = tailleChamps(rnfEntity, "referenceTitre");
        String valueOfReferenceTitre = String.valueOf(titreRnfEntity.getReferenceTitre());
        if (valueOfReferenceTitre.length() < referenceTitreInter){
            int caracteresManquant = numeroClientInter - valueOfReferenceTitre.length();
            valueOfReferenceTitre = addBlankSpace(caracteresManquant, valueOfReferenceTitre);
        }

        int numeroPriseEnChargeInter = tailleChamps(rnfEntity, "numeroPriseEnCharge");
        String valueOfNumeroPriseEnCharge = String.valueOf(titreRnfEntity.getNumeroPriseEnCharge());
        if (valueOfNumeroPriseEnCharge.length() < numeroPriseEnChargeInter){
            int caracteresManquant = numeroPriseEnChargeInter - valueOfNumeroPriseEnCharge.length();
            valueOfNumeroPriseEnCharge = addZero(caracteresManquant, valueOfNumeroPriseEnCharge);
        }

        int datePriseEnChargeInter = tailleChamps(rnfEntity, "datePriseEnCharge");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String datePriseEnCharge = sdf.format(titreRnfEntity.getDatePriseEnCharge());
        if (datePriseEnCharge.length() < datePriseEnChargeInter){
            int caracteresManquant = datePriseEnChargeInter - datePriseEnCharge.length();
            datePriseEnCharge = addBlankSpace(caracteresManquant, datePriseEnCharge);
        }

        int montantInter = tailleChamps(rnfEntity, "montant");
        String valueOfMontant = String.valueOf(titreRnfEntity.getMontant());
        if (valueOfMontant.length() < montantInter){
            int caracteresManquant = montantInter - valueOfMontant.length();
            valueOfMontant = addZero(caracteresManquant, valueOfMontant);
        }

        int ObjetDetteInter = tailleChamps(rnfEntity, "objetDette");
        String valueOfObjetDette = String.valueOf(titreRnfEntity.getObjetDette());
        if (valueOfObjetDette.length() < ObjetDetteInter){
            int caracteresManquant = ObjetDetteInter - valueOfObjetDette.length();
            valueOfObjetDette = addBlankSpace(caracteresManquant, valueOfObjetDette);
        }

        int fillerInter = tailleChamps(rnfEntity, "filler");
        String valueOffiller = "";
        int caracteresManquant = fillerInter - valueOffiller.length();
        valueOffiller = addBlankSpace(caracteresManquant-1, valueOffiller);

        return valueOfNumeroClient + valueOfReferenceTitre + valueOfNumeroPriseEnCharge +
                datePriseEnCharge + valueOfMontant + valueOfObjetDette + valueOffiller + "\n";
    }

    // Ici je récupère l'intervalle dans une map pour un champs, je récupère la position la taille du champs.
    public Map<Integer, Integer> champsIntervalle(RnfEntity rnfEntity, String fieldName) throws IllegalAccessException {
        Map<Integer, Integer> resultMap = new HashMap<>();

        Field[] fields = RnfEntity.class.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            if (field.getName().equals(fieldName) && field.getType().isArray() && field.getType().getComponentType() == String.class) {
                String[] arrayValue = (String[]) field.get(rnfEntity);

                int debutIntervalle = Integer.parseInt(arrayValue.length > 0 ? arrayValue[0] : null);
                int finIntervalle = Integer.parseInt(arrayValue.length > 1 ? arrayValue[arrayValue.length - 1] : null);

                resultMap.put(debutIntervalle, finIntervalle);
                break;
            }
        }
        return resultMap;
    }

    public int tailleChamps(RnfEntity rnfEntity, String fieldName) throws IllegalAccessException {
        Map<Integer, Integer> resultMap = this.champsIntervalle(rnfEntity, fieldName);
        int intervalleDepart = 0;
        int intervalleFin = 0;
        for (Map.Entry<Integer, Integer> entry : resultMap.entrySet()) {
            intervalleDepart = entry.getKey();
            intervalleFin = entry.getValue();
        }
        return intervalleFin - intervalleDepart + 1;
    }

    public static String addZero(int nbreZeroManquant, String originalString){
        String newZero = "";
        for (int i=0; i<nbreZeroManquant;i++){
            newZero = newZero+"0";
        }
        return newZero + originalString;
    }
    public static String addBlankSpace(int nbreZeroManquant, String originalString) {
        String newBlankSpace = "";
        for (int i = 0; i < nbreZeroManquant; i++) {
            newBlankSpace = newBlankSpace + " ";
        }
        return originalString + newBlankSpace;
    }
}
