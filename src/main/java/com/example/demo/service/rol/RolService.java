package com.example.demo.service.rol;

import com.example.demo.entity.rol.RolEntity;
import com.example.demo.entity.rol.TitreRolEntity;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

@Service
public class RolService {

    // Cette classe permet de construire le fichier xml en reprenant la structure XML définie dans "RolEntity".
    // Dans cette classe pour chaque valeurs du XML ("RolEntity") téléchargeable je récupère la valeur du champs associé dans la base de données "TitreRolEntity"

    /**
     * Permet de calculer combien de Piece il faut rajouter en fonction du nombre de facture / nbrPce
     */
    public void generateNewPieceAndBlocBordereauInFacture(TitreRolEntity titreRolEntity, RolEntity rolEntity){

        long numDette = titreRolEntity.getIdPce();
        rolEntity.getPesTipiRole().getRoles().getArticles().clear();
        rolEntity.getPesTipiRole().getRoles().setBlocRole(this.generateBlocRole(titreRolEntity));
        for (int i = 0; i < titreRolEntity.getNombreFacture(); i++){
            rolEntity.getPesTipiRole().getRoles().addArticle(this.generateArticleRec(titreRolEntity));
            RolEntity.NumDette numDettes = new RolEntity.NumDette();
            numDettes.setV(String.valueOf(numDette));
            rolEntity.getPesTipiRole().getRoles().getArticles().get(i).getBlocArticle().getInfoArticle().setNumDette(numDettes);
            numDette++;
        }
    }

    /**
     * Récupère les valeurs deupis la base de données TitreRolEntity pour contruire le BlocBordereau dans RolEntity(FichierXml)
     */
    public RolEntity.BlocRole generateBlocRole(TitreRolEntity titreRolEntity){
        RolEntity.BlocRole blocRole = new RolEntity.BlocRole();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        RolEntity.Exer exer = new RolEntity.Exer();
        exer.setV(titreRolEntity.getExer());
        blocRole.setExer(exer);

        RolEntity.IdRol idRol = new RolEntity.IdRol();
        idRol.setV(titreRolEntity.getIdRol());
        blocRole.setIdRol(idRol);

        RolEntity.TypRol typBord = new RolEntity.TypRol();
        typBord.setV(titreRolEntity.getTypRol());
        blocRole.setTypRol(typBord);

        RolEntity.DteAsp dteAsp = new RolEntity.DteAsp();
        dteAsp.setV(sdf.format(titreRolEntity.getDteAsp()));
        blocRole.setDteAsp(dteAsp);

        return blocRole;
    }

    /**
     * Récupère les valeurs deupis la base de données TitreRolEntity pour contruire la Piece dans RolEntity(FichierXml)
     */
    public RolEntity.Article generateArticleRec(TitreRolEntity titreRolEntity) {
        RolEntity.Article article = new RolEntity.Article();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // Pour Article
        RolEntity.IdPce idPce = new RolEntity.IdPce();
        idPce.setV(titreRolEntity.getIdPce());
        article.blocArticle.getInfoArticle().setIdPce(idPce);

        RolEntity.NumDette numDette = new RolEntity.NumDette();
        numDette.setV(titreRolEntity.getNumDette());
        article.blocArticle.getInfoArticle().setNumDette(numDette);

        RolEntity.Per per = new RolEntity.Per();
        per.setV(titreRolEntity.getPer());
        article.blocArticle.getInfoArticle().setPer(per);

        RolEntity.EtatPce etatPce = new RolEntity.EtatPce();
        etatPce.setV(titreRolEntity.getEtatPce());
        article.blocArticle.getInfoArticle().setEtatPce(etatPce);

        RolEntity.DtePcePec dtePcePec = new RolEntity.DtePcePec();
        dtePcePec.setV(sdf.format(titreRolEntity.getDtePcePec()));
        article.blocArticle.getInfoArticle().setDtePcePec(dtePcePec);

        RolEntity.CodProdLoc codProdLoc = new RolEntity.CodProdLoc();
        codProdLoc.setV(titreRolEntity.getCodProdLoc());
        article.blocArticle.getInfoArticle().setCodProdLoc(codProdLoc);

        RolEntity.ObjPce objPce = new RolEntity.ObjPce();
        objPce.setV(titreRolEntity.getObjPce());
        article.blocArticle.getInfoArticle().setObjPce(objPce);

        RolEntity.MtTTC mtTTC = new RolEntity.MtTTC();
        mtTTC.setV(titreRolEntity.getMtTTC());
        article.blocArticle.getInfoArticle().setMtTTC(mtTTC);

        // Pour InfoTiers
        RolEntity.RefTiers refTiers = new RolEntity.RefTiers();
        refTiers.setV(titreRolEntity.getRefTiers());
        article.tiers.getInfoTiers().setRefTiers(refTiers);

        RolEntity.CatTiers catTiers = new RolEntity.CatTiers();
        catTiers.setV(titreRolEntity.getCatTiers());
        article.tiers.getInfoTiers().setCatTiers(catTiers);

        RolEntity.Civilite civilite = new RolEntity.Civilite();
        civilite.setV(titreRolEntity.getCivilite());
        article.tiers.getInfoTiers().setCivilite(civilite);

        RolEntity.Nom nom = new RolEntity.Nom();
        nom.setV(titreRolEntity.getNom());
        article.tiers.getInfoTiers().setNom(nom);

        RolEntity.Prenom prenom = new RolEntity.Prenom();
        prenom.setV(titreRolEntity.getPrenom());
        article.tiers.getInfoTiers().setPrenom(prenom);

        // Pour Adresse
        RolEntity.Adr1 adr1 = new RolEntity.Adr1();
        adr1.setV(titreRolEntity.getAdr1());
        article.tiers.getAdresse().setAdr1(adr1);

        RolEntity.Adr2 adr2 = new RolEntity.Adr2();
        adr2.setV(titreRolEntity.getAdr2());
        article.tiers.getAdresse().setAdr2(adr2);

        RolEntity.Adr3 adr3 = new RolEntity.Adr3();
        adr3.setV(titreRolEntity.getAdr3());
        article.tiers.getAdresse().setAdr3(adr3);

        RolEntity.CP cp = new RolEntity.CP();
        cp.setV(titreRolEntity.getCp());
        article.tiers.getAdresse().setCp(cp);

        RolEntity.Ville ville = new RolEntity.Ville();
        ville.setV(titreRolEntity.getVille());
        article.tiers.getAdresse().setVille(ville);

        return article;
    }

    /**
     * Rècupère le fichier XML modèle dans resources puis
     */
    public RolEntity remplirXmlAvecBDD(TitreRolEntity titreRolEntity) throws IOException {
        InputStream xmlInputStream = getClass().getClassLoader().getResourceAsStream("static/TIPIROL_001021_133_00_2023_194.xml");
        XmlMapper xmlMapper = new XmlMapper();
        RolEntity rolEntity = xmlMapper.readValue(xmlInputStream, RolEntity.class);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        this.generateNewPieceAndBlocBordereauInFacture(titreRolEntity, rolEntity);

        // Pour Enveloppe
        RolEntity.Version version = new RolEntity.Version();
        version.setV(titreRolEntity.getVersion());
        rolEntity.getEnveloppe().getParametres().setVersion(version);

        RolEntity.TypFic typFic = new RolEntity.TypFic();
        typFic.setV(titreRolEntity.getTypFic());
        rolEntity.getEnveloppe().getParametres().setTypFic(typFic);

        RolEntity.NomFic nomFic = new RolEntity.NomFic();
        nomFic.setV(titreRolEntity.getNomFic());
        rolEntity.getEnveloppe().getParametres().setNomFic(nomFic);

        // Pour EnTetePES
        RolEntity.DteStr dteStr = new RolEntity.DteStr();
        dteStr.setV(sdf.format(titreRolEntity.getDteStr()));
        rolEntity.getEnTetePES().setDteStr(dteStr);

        RolEntity.IdPost idPost = new RolEntity.IdPost();
        idPost.setV(titreRolEntity.getIdPost());
        rolEntity.getEnTetePES().setIdPost(idPost);

        RolEntity.CodCol codCol = new RolEntity.CodCol();
        codCol.setV(titreRolEntity.getCodCol());
        rolEntity.getEnTetePES().setCodCol(codCol);

        RolEntity.CodBud codBud = new RolEntity.CodBud();
        codBud.setV(titreRolEntity.getCodBud());
        rolEntity.getEnTetePES().setCodBud(codBud);

        return rolEntity;
    }

}
