package com.example.demo.service.rec;

import com.example.demo.entity.rec.RecEntity;
import com.example.demo.entity.rec.TitreRecEntity;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

@Service
public class RecService {

    /**
     * Permet de calculer combien de Piece il faut rajouter en fonction du nombre de facture / nbrPce
     */
    public void generateNewPieceAndBlocBordereauInFacture(TitreRecEntity titreRecEntity, RecEntity recEntity){

        long idPce = titreRecEntity.getIdPce();
        recEntity.getPesTipiRecette().getBordereaux().getPieces().clear();
        recEntity.getPesTipiRecette().getBordereaux().setBlocBordereau(this.generateBlocBordereau(titreRecEntity));
        for (int i = 0; i < titreRecEntity.getNombreFacture(); i++){
            recEntity.getPesTipiRecette().getBordereaux().addPiece(this.generatePieceRec(titreRecEntity));
            RecEntity.IdPce idPces = new RecEntity.IdPce();
            idPces.setV(idPce);
            recEntity.getPesTipiRecette().getBordereaux().getPieces().get(i).getBlocPiece().setIdPce(idPces);
            idPce++;
        }
    }

    /**
     * Récupère les valeurs deupis la base de données TitreRecEntity pour contruire le BlocBordereau dans RecEntity(FichierXml)
     */
    public RecEntity.BlocBordereau generateBlocBordereau(TitreRecEntity titreRecEntity){
        RecEntity.BlocBordereau blocBordereau = new RecEntity.BlocBordereau();

        RecEntity.Exer exer = new RecEntity.Exer();
        exer.setV(titreRecEntity.getExer());
        blocBordereau.setExer(exer);

        RecEntity.IdBord idBord = new RecEntity.IdBord();
        idBord.setV(titreRecEntity.getIdBord());
        blocBordereau.setIdBord(idBord);

        RecEntity.TypBord typBord = new RecEntity.TypBord();
        typBord.setV(titreRecEntity.getTypBord());
        blocBordereau.setTypBord(typBord);

        RecEntity.NbrePce nbrePce = new RecEntity.NbrePce();
        nbrePce.setV(titreRecEntity.getNbrePce());
        blocBordereau.setNbrePce(nbrePce);

        return blocBordereau;
    }

    /**
     * Récupère les valeurs deupis la base de données TitreRecEntity pour contruire la Piece dans RecEntity(FichierXml)
     */
    public RecEntity.Piece generatePieceRec(TitreRecEntity titreRecEntity) {
        RecEntity.Piece piece = new RecEntity.Piece();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // Pour Piece
        RecEntity.IdPce idPce = new RecEntity.IdPce();
        idPce.setV(titreRecEntity.getIdPce());
        piece.blocPiece.setIdPce(idPce);

        RecEntity.TypPce typPce = new RecEntity.TypPce();
        typPce.setV(titreRecEntity.getTypPce());
        piece.blocPiece.setTypPce(typPce);

        RecEntity.NatPce natPce = new RecEntity.NatPce();
        natPce.setV(titreRecEntity.getNatPce());
        piece.blocPiece.setNatPce(natPce);

        RecEntity.CatPce catPce = new RecEntity.CatPce();
        catPce.setV(titreRecEntity.getCatPce());
        piece.blocPiece.setCatPce(catPce);

        RecEntity.EtatPce etatPce = new RecEntity.EtatPce();
        etatPce.setV(titreRecEntity.getEtatPce());
        piece.blocPiece.setEtatPce(etatPce);

        RecEntity.DtePcePec dtePcePec = new RecEntity.DtePcePec();
        dtePcePec.setV(sdf.format(titreRecEntity.getDtePcePec()));
        piece.blocPiece.setDtePcePec(dtePcePec);

        // Pour LigneDePiece
        RecEntity.IdLigne idLigne = new RecEntity.IdLigne();
        idLigne.setV(titreRecEntity.getIdLigne());
        piece.ligneDePiece.blocLignePiece.infoLignePiece.setIdLigne(idLigne);

        RecEntity.ObjLignePce objLignePce = new RecEntity.ObjLignePce();
        objLignePce.setV(titreRecEntity.getObjLignePce());
        piece.ligneDePiece.blocLignePiece.infoLignePiece.setObjLignePce(objLignePce);

        RecEntity.Nature nature = new RecEntity.Nature();
        nature.setV(titreRecEntity.getNature());
        piece.ligneDePiece.blocLignePiece.infoLignePiece.setNature(nature);

        RecEntity.MtTTC mtTTC = new RecEntity.MtTTC();
        mtTTC.setV(titreRecEntity.getMtTTC());
        piece.ligneDePiece.blocLignePiece.infoLignePiece.setMtTTC(mtTTC);

        // Pour Tiers
        RecEntity.RefTiers refTiers = new RecEntity.RefTiers();
        refTiers.setV(titreRecEntity.getRefTiers());
        piece.ligneDePiece.tiers.infoTiers.setRefTiers(refTiers);

        RecEntity.CatTiers catTiers = new RecEntity.CatTiers();
        catTiers.setV(titreRecEntity.getCatTiers());
        piece.ligneDePiece.tiers.infoTiers.setCatTiers(catTiers);

        RecEntity.TypTiers typTiers = new RecEntity.TypTiers();
        typTiers.setV(titreRecEntity.getTypTiers());
        piece.ligneDePiece.tiers.infoTiers.setTypTiers(typTiers);

        RecEntity.Civilite civilite = new RecEntity.Civilite();
        civilite.setV(titreRecEntity.getCivilite());
        piece.ligneDePiece.tiers.infoTiers.setCivilite(civilite);

        RecEntity.Nom nom = new RecEntity.Nom();
        nom.setV(titreRecEntity.getNom());
        piece.ligneDePiece.tiers.infoTiers.setNom(nom);

        RecEntity.Prenom prenom = new RecEntity.Prenom();
        prenom.setV(titreRecEntity.getPrenom());
        piece.ligneDePiece.tiers.infoTiers.setPrenom(prenom);

        // Pour Adresse
        RecEntity.Adr1 adr1 = new RecEntity.Adr1();
        adr1.setV(titreRecEntity.getAdr1());
        piece.ligneDePiece.tiers.adresse.setAdr1(adr1);

        RecEntity.Adr2 adr2 = new RecEntity.Adr2();
        adr2.setV(titreRecEntity.getAdr2());
        piece.ligneDePiece.tiers.adresse.setAdr2(adr2);

        RecEntity.Adr3 adr3 = new RecEntity.Adr3();
        adr3.setV(titreRecEntity.getAdr3());
        piece.ligneDePiece.tiers.adresse.setAdr3(adr3);

        RecEntity.CP cp = new RecEntity.CP();
        cp.setV(titreRecEntity.getCP());
        piece.ligneDePiece.tiers.adresse.setCp(cp);

        RecEntity.Ville ville = new RecEntity.Ville();
        ville.setV(titreRecEntity.getVille());
        piece.ligneDePiece.tiers.adresse.setVille(ville);

        return piece;
    }

    /**
     * Rècupère le fichier XML modèle dans resources puis
     */
    public RecEntity remplirXmlAvecBDD(TitreRecEntity titreRecEntity) throws IOException {
        InputStream xmlInputStream = getClass().getClassLoader().getResourceAsStream("static/TIPIREC_076029_638_00_2023_167.xml");
        XmlMapper xmlMapper = new XmlMapper();
        RecEntity recEntity = xmlMapper.readValue(xmlInputStream, RecEntity.class);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        this.generateNewPieceAndBlocBordereauInFacture(titreRecEntity, recEntity);

        // Pour Enveloppe
        RecEntity.Version version = new RecEntity.Version();
        version.setV(titreRecEntity.getVersion());
        recEntity.getEnveloppe().getParametres().setVersion(version);

        RecEntity.TypFic typFic = new RecEntity.TypFic();
        typFic.setV(titreRecEntity.getTypFic());
        recEntity.getEnveloppe().getParametres().setTypFic(typFic);

        RecEntity.NomFic nomFic = new RecEntity.NomFic();
        nomFic.setV(titreRecEntity.getNomFic());
        recEntity.getEnveloppe().getParametres().setNomFic(nomFic);

        // Pour EnTetePES
        RecEntity.DteStr dteStr = new RecEntity.DteStr();
        dteStr.setV(sdf.format(titreRecEntity.getDteStr()));
        recEntity.getEnTetePES().setDteStr(dteStr);

        RecEntity.IdPost idPost = new RecEntity.IdPost();
        idPost.setV(titreRecEntity.getIdPost());
        recEntity.getEnTetePES().setIdPost(idPost);

        RecEntity.LibellePoste libellePoste = new RecEntity.LibellePoste();
        libellePoste.setV(titreRecEntity.getLibellePoste());
        recEntity.getEnTetePES().setLibellePoste(libellePoste);

        RecEntity.IdColl idColl = new RecEntity.IdColl();
        idColl.setV(titreRecEntity.getIdColl());
        recEntity.getEnTetePES().setIdColl(idColl);

        RecEntity.CodCol codCol = new RecEntity.CodCol();
        codCol.setV(titreRecEntity.getCodCol());
        recEntity.getEnTetePES().setCodCol(codCol);

        RecEntity.CodBud codBud = new RecEntity.CodBud();
        codBud.setV(titreRecEntity.getCodBud());
        recEntity.getEnTetePES().setCodBud(codBud);

        RecEntity.LibelleColBud libelleColBud = new RecEntity.LibelleColBud();
        libelleColBud.setV(titreRecEntity.getLibelleColBud());
        recEntity.getEnTetePES().setLibelleColBud(libelleColBud);

        return recEntity;
    }

}
