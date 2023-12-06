package com.example.demo.service.hop;

import com.example.demo.entity.hop.HopEntity;
import com.example.demo.entity.hop.TitreHopEntity;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;

@Service
public class HopService {

    /**
     * Permet de calculer combien de Piece il faut rajouter en fonction du nombre de facture / nbrPce
     */
    public void generateNewPieceAndBlocBordereauInFacture(TitreHopEntity titreHopEntity, HopEntity hopEntity){
        long idPce = titreHopEntity.getIdPce();
        hopEntity.getPesTipiRecette().getBordereaux().getPieces().clear();
        hopEntity.getPesTipiRecette().getBordereaux().setBlocBordereau(this.generateBlocBordereau(titreHopEntity));
        for (int i = 0; i < titreHopEntity.getNombreFacture(); i++){
            hopEntity.getPesTipiRecette().getBordereaux().addPiece(this.generatePieceHop(titreHopEntity));
            HopEntity.IdPce idPces = new HopEntity.IdPce();
            idPces.setV(idPce);
            hopEntity.getPesTipiRecette().getBordereaux().getPieces().get(i).getBlocPiece().setIdPce(idPces);
            idPce++;
        }
    }

    /**
     * Récupère les valeurs deupis la base de données TitreHopEntity pour contruire le BlocBordereau dans HopEntity(FichierXml)
     */
    public HopEntity.BlocBordereau generateBlocBordereau(TitreHopEntity titreHopEntity){
        HopEntity.BlocBordereau blocBordereau = new HopEntity.BlocBordereau();

        HopEntity.Exer exer = new HopEntity.Exer();
        exer.setV(titreHopEntity.getExer());
        blocBordereau.setExer(exer);

        HopEntity.IdBord idBord = new HopEntity.IdBord();
        idBord.setV(titreHopEntity.getIdBord());
        blocBordereau.setIdBord(idBord);

        HopEntity.TypBord typBord = new HopEntity.TypBord();
        typBord.setV(titreHopEntity.getTypBord());
        blocBordereau.setTypBord(typBord);

        HopEntity.NbrePce nbrePce = new HopEntity.NbrePce();
        nbrePce.setV(titreHopEntity.getNbrePce());
        blocBordereau.setNbrePce(nbrePce);

        return blocBordereau;
    }

    /**
     * Récupère les valeurs deupis la base de données TitreHopEntity pour contruire la Piece dans HopEntity(FichierXml)
     */
    public HopEntity.Piece generatePieceHop(TitreHopEntity titreHopEntity) {
        HopEntity.Piece piece = new HopEntity.Piece();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // Pour Piece
        HopEntity.IdPce idPce = new HopEntity.IdPce();
        idPce.setV(titreHopEntity.getIdPce());
        piece.blocPiece.setIdPce(idPce);

        HopEntity.TypPce typPce = new HopEntity.TypPce();
        typPce.setV(titreHopEntity.getTypPce());
        piece.blocPiece.setTypPce(typPce);

        HopEntity.NatPce natPce = new HopEntity.NatPce();
        natPce.setV(titreHopEntity.getNatPce());
        piece.blocPiece.setNatPce(natPce);

        HopEntity.CatPce catPce = new HopEntity.CatPce();
        catPce.setV(titreHopEntity.getCatPce());
        piece.blocPiece.setCatPce(catPce);

        HopEntity.DebFact debFact = new HopEntity.DebFact();
        debFact.setV(sdf.format(titreHopEntity.getDebFact()));
        piece.blocPiece.setDebFact(debFact);

        HopEntity.FinFact finFact = new HopEntity.FinFact();
        finFact.setV(sdf.format(titreHopEntity.getFinFact()));
        piece.blocPiece.setFinFact(finFact);

        HopEntity.EtatPce etatPce = new HopEntity.EtatPce();
        etatPce.setV(titreHopEntity.getEtatPce());
        piece.blocPiece.setEtatPce(etatPce);

        HopEntity.DtePcePec dtePcePec = new HopEntity.DtePcePec();
        dtePcePec.setV(sdf.format(titreHopEntity.getDtePcePec()));
        piece.blocPiece.setDtePcePec(dtePcePec);

        // Pour LigneDePiece
        HopEntity.IdLigne idLigne = new HopEntity.IdLigne();
        idLigne.setV(titreHopEntity.getIdLigne());
        piece.ligneDePiece.blocLignePiece.infoLignePiece.setIdLigne(idLigne);

        HopEntity.ObjLignePce objLignePce = new HopEntity.ObjLignePce();
        objLignePce.setV(titreHopEntity.getObjLignePce());
        piece.ligneDePiece.blocLignePiece.infoLignePiece.setObjLignePce(objLignePce);

        HopEntity.Nature nature = new HopEntity.Nature();
        nature.setV(titreHopEntity.getNature());
        piece.ligneDePiece.blocLignePiece.infoLignePiece.setNature(nature);

        HopEntity.MtTTC mtTTC = new HopEntity.MtTTC();
        mtTTC.setV(titreHopEntity.getMtTTC());
        piece.ligneDePiece.blocLignePiece.infoLignePiece.setMtTTC(mtTTC);

        HopEntity.CodEtGeo codEtGeo = new HopEntity.CodEtGeo();
        codEtGeo.setV(titreHopEntity.getCodEtGeo());
        piece.ligneDePiece.blocLignePiece.infoLignePiece.setCodEtGeo(codEtGeo);

        // Pour Tiers
        HopEntity.DteMalade dteMalade = new HopEntity.DteMalade();
        dteMalade.setV(sdf.format(titreHopEntity.getDteMalade()));
        piece.ligneDePiece.tiers.infoTiers.setDteMalade(dteMalade);

        HopEntity.RefTiers refTiers = new HopEntity.RefTiers();
        refTiers.setV(titreHopEntity.getRefTiers());
        piece.ligneDePiece.tiers.infoTiers.setRefTiers(refTiers);

        HopEntity.CatTiers catTiers = new HopEntity.CatTiers();
        catTiers.setV(titreHopEntity.getCatTiers());
        piece.ligneDePiece.tiers.infoTiers.setCatTiers(catTiers);

        HopEntity.TypTiers typTiers = new HopEntity.TypTiers();
        typTiers.setV(titreHopEntity.getTypTiers());
        piece.ligneDePiece.tiers.infoTiers.setTypTiers(typTiers);

        HopEntity.Civilite civilite = new HopEntity.Civilite();
        civilite.setV(titreHopEntity.getCivilite());
        piece.ligneDePiece.tiers.infoTiers.setCivilite(civilite);

        HopEntity.Nom nom = new HopEntity.Nom();
        nom.setV(titreHopEntity.getNom());
        piece.ligneDePiece.tiers.infoTiers.setNom(nom);

        HopEntity.Prenom prenom = new HopEntity.Prenom();
        prenom.setV(titreHopEntity.getPrenom());
        piece.ligneDePiece.tiers.infoTiers.setPrenom(prenom);

        // Pour Adresse
        HopEntity.Adr1 adr1 = new HopEntity.Adr1();
        adr1.setV(titreHopEntity.getAdr1());
        piece.ligneDePiece.tiers.adresse.setAdr1(adr1);

        HopEntity.Adr2 adr2 = new HopEntity.Adr2();
        adr2.setV(titreHopEntity.getAdr2());
        piece.ligneDePiece.tiers.adresse.setAdr2(adr2);

        HopEntity.Adr3 adr3 = new HopEntity.Adr3();
        adr3.setV(titreHopEntity.getAdr3());
        piece.ligneDePiece.tiers.adresse.setAdr3(adr3);

        HopEntity.CP cp = new HopEntity.CP();
        cp.setV(titreHopEntity.getCP());
        piece.ligneDePiece.tiers.adresse.setCp(cp);

        HopEntity.Ville ville = new HopEntity.Ville();
        ville.setV(titreHopEntity.getVille());
        piece.ligneDePiece.tiers.adresse.setVille(ville);

        return piece;
    }

    /**
     * Rècupère le fichier XML modèle dans resources puis
     */
    public HopEntity remplirXmlAvecBDD(TitreHopEntity titreHopEntity) throws IOException {
        InputStream xmlInputStream = getClass().getClassLoader().getResourceAsStream("static/TIPIHOP_073037_816_00_2023_164.xml");
        XmlMapper xmlMapper = new XmlMapper();
        HopEntity hopEntity = xmlMapper.readValue(xmlInputStream, HopEntity.class);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        this.generateNewPieceAndBlocBordereauInFacture(titreHopEntity, hopEntity);

        // Pour Enveloppe
        HopEntity.Version version = new HopEntity.Version();
        version.setV(titreHopEntity.getVersion());
        hopEntity.getEnveloppe().getParametres().setVersion(version);

        HopEntity.TypFic typFic = new HopEntity.TypFic();
        typFic.setV(titreHopEntity.getTypFic());
        hopEntity.getEnveloppe().getParametres().setTypFic(typFic);

        HopEntity.NomFic nomFic = new HopEntity.NomFic();
        nomFic.setV(titreHopEntity.getNomFic());
        hopEntity.getEnveloppe().getParametres().setNomFic(nomFic);

        // Pour EnTetePES
        HopEntity.DteStr dteStr = new HopEntity.DteStr();
        dteStr.setV(sdf.format(titreHopEntity.getDteStr()));
        hopEntity.getEnTetePES().setDteStr(dteStr);

        HopEntity.IdPost idPost = new HopEntity.IdPost();
        idPost.setV(titreHopEntity.getIdPost());
        hopEntity.getEnTetePES().setIdPost(idPost);

        HopEntity.LibellePoste libellePoste = new HopEntity.LibellePoste();
        libellePoste.setV(titreHopEntity.getLibellePoste());
        hopEntity.getEnTetePES().setLibellePoste(libellePoste);

        HopEntity.IdColl idColl = new HopEntity.IdColl();
        idColl.setV(titreHopEntity.getIdColl());
        hopEntity.getEnTetePES().setIdColl(idColl);

        HopEntity.FinJur finJur = new HopEntity.FinJur();
        finJur.setV(titreHopEntity.getFinJur());
        hopEntity.getEnTetePES().setFinJur(finJur);

        HopEntity.CodCol codCol = new HopEntity.CodCol();
        codCol.setV(titreHopEntity.getCodCol());
        hopEntity.getEnTetePES().setCodCol(codCol);

        HopEntity.CodBud codBud = new HopEntity.CodBud();
        codBud.setV(titreHopEntity.getCodBud());
        hopEntity.getEnTetePES().setCodBud(codBud);

        HopEntity.LibelleColBud libelleColBud = new HopEntity.LibelleColBud();
        libelleColBud.setV(titreHopEntity.getLibelleColBud());
        hopEntity.getEnTetePES().setLibelleColBud(libelleColBud);

        return hopEntity;
    }
}
