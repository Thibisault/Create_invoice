package com.example.demo.entity.rol;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.math.BigDecimal;
import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "n:PES_Retour")
@XmlAccessorType(XmlAccessType.FIELD)
public class RolEntity {

    @JacksonXmlProperty(localName = "xmlns:xsi", isAttribute = true)
    public String getXmlnsXsi() {
        return "http://www.w3.org/2001/XMLSchema-instance";
    }

    @JacksonXmlProperty(localName = "xmlns:n", isAttribute = true)
    public String getXmlnsN() {
        return "http://www.minefi.gouv.fr/cp/helios/pes_v2/Rev0/retour";
    }

    @JacksonXmlProperty(localName = "xsi:schemaLocation", isAttribute = true)
    public String getXsiSchemaLocation() {
        return "http://www.minefi.gouv.fr/cp/helios/pes_v2/Rev0/retour /opt/tx5115/users/ediprod/messages/XML/PES_V2/Externe/Schemas_PES/PES_V2/Rev0/PES_V2_TIPI_Role_Autonome.xsd";
    }

    @JacksonXmlProperty(localName = "Enveloppe")
    public RolEntity.Enveloppe enveloppe = new RolEntity.Enveloppe();

    @JacksonXmlProperty(localName = "EnTetePES")
    public RolEntity.EnTetePES enTetePES = new RolEntity.EnTetePES();

    @JacksonXmlProperty(localName = "PES_TIPI_Role")
    public RolEntity.PESTIPRole pesTipiRole = new RolEntity.PESTIPRole();

    @Data
    public static class Enveloppe {
        @JacksonXmlProperty(localName = "Parametres")
        public RolEntity.Parametres parametres = new RolEntity.Parametres();
    }

    @Data
    @JacksonXmlRootElement(localName = "Parametres")
    public static class Parametres {

        //@JsonSerialize(using = VersionSerializer.class)
        @JacksonXmlProperty(localName = "Version", isAttribute = true)
        private RolEntity.Version version;

        @JacksonXmlProperty(localName = "TypFic", isAttribute = true)
        private RolEntity.TypFic typFic;

        @JacksonXmlProperty(localName = "NomFic", isAttribute = true)
        private RolEntity.NomFic nomFic;
    }

    @Data
    public static class Version {
        @JacksonXmlProperty(localName = "V", isAttribute = true)
        public String v;
    }
    @Data
    public static class TypFic {
        @JacksonXmlProperty(localName = "V", isAttribute = true)
        public String v;
    }
    @Data
    public static class NomFic {
        @JacksonXmlProperty(localName = "V", isAttribute = true)
        public String v;
    }

    @Data
    public static class EnTetePES {

        @JacksonXmlProperty(localName = "DteStr", isAttribute = true)
        public RolEntity.DteStr dteStr;
        @JacksonXmlProperty(localName = "IdPost", isAttribute = true)
        public RolEntity.IdPost idPost;
        @JacksonXmlProperty(localName = "CodCol", isAttribute = true)
        public RolEntity.CodCol codCol;
        @JacksonXmlProperty(localName = "CodBud", isAttribute = true)
        public RolEntity.CodBud codBud;
    }
    @Data
    public static class DteStr {
        @JacksonXmlProperty(localName = "V", isAttribute = true)
        public String v;
    }
    @Data
    public static class IdPost {
        @JacksonXmlProperty(localName = "V", isAttribute = true)
        public String v;
    }
    @Data
    public static class CodCol {
        @JacksonXmlProperty(localName = "V", isAttribute = true)
        public String v;
    }
    @Data
    public static class CodBud {
        @JacksonXmlProperty(localName = "V", isAttribute = true)
        public String v;
    }

    @Data
    public static class PESTIPRole {
        @JacksonXmlProperty(localName = "Role")
        public RolEntity.Role roles = new RolEntity.Role();
    }

    @Data
    public static class Role {
        @JacksonXmlProperty(localName = "BlocRole")
        private RolEntity.BlocRole blocRole = new RolEntity.BlocRole();
        @JacksonXmlProperty(localName = "Article")
        @JacksonXmlElementWrapper(useWrapping = false)
        private ArrayList<RolEntity.Article> articles = new ArrayList<>();

        public void addArticle(RolEntity.Article article) {
            this.articles.add(article);
        }
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BlocRole {
        @JacksonXmlProperty(localName = "Exer")
        public RolEntity.Exer exer;
        @JacksonXmlProperty(localName = "IdRol")
        public RolEntity.IdRol idRol;
        @JacksonXmlProperty(localName = "TypRol")
        public RolEntity.TypRol typRol;
        @JacksonXmlProperty(localName = "DteAsp")
        public RolEntity.DteAsp dteAsp;
    }
    @Data
    public static class Exer {
        @JacksonXmlProperty(localName = "V", isAttribute = true)
        public String v;
    }
    @Data
    public static class IdRol {
        @JacksonXmlProperty(localName = "V", isAttribute = true)
        public String v;
    }
    @Data
    public static class TypRol {
        @JacksonXmlProperty(localName = "V", isAttribute = true)
        public String v;
    }
    @Data
    public static class DteAsp {
        @JacksonXmlProperty(localName = "V", isAttribute = true)
        public String v;
    }

    @Data
    public static class Article {
        @JacksonXmlProperty(localName = "BlocArticle")
        public RolEntity.BlocArticle blocArticle = new RolEntity.BlocArticle();
        @JacksonXmlProperty(localName = "Tiers")
        public RolEntity.Tiers tiers = new RolEntity.Tiers();
    }

    @Data
    public static class BlocArticle {
        @JacksonXmlProperty(localName = "InfoArticle")
        public RolEntity.InfoArticle infoArticle = new RolEntity.InfoArticle();
    }
    @Data
    public static class InfoArticle {
        @JacksonXmlProperty(localName = "IdPce")
        public RolEntity.IdPce idPce;
        @JacksonXmlProperty(localName = "NumDette")
        public RolEntity.NumDette numDette;
        @JacksonXmlProperty(localName = "Per")
        public RolEntity.Per per;
        @JacksonXmlProperty(localName = "EtatPce")
        public RolEntity.EtatPce etatPce;
        @JacksonXmlProperty(localName = "DtePcePec")
        public RolEntity.DtePcePec dtePcePec;
        @JacksonXmlProperty(localName = "CodProdLoc")
        public RolEntity.CodProdLoc codProdLoc;
        @JacksonXmlProperty(localName = "ObjPce")
        public RolEntity.ObjPce objPce;
        @JacksonXmlProperty(localName = "MtTTC")
        public RolEntity.MtTTC mtTTC;
    }
    @Data
    public static class IdPce {
        @JacksonXmlProperty(localName = "V", isAttribute = true)
        public long v;
    }
    @Data
    public static class NumDette {
        @JacksonXmlProperty(localName = "V", isAttribute = true)
        public String v;
    }
    @Data
    public static class Per {
        @JacksonXmlProperty(localName = "V", isAttribute = true)
        public String v;
    }
    @Data
    public static class EtatPce {
        @JacksonXmlProperty(localName = "V", isAttribute = true)
        public String v;
    }
    @Data
    public static class DtePcePec {
        @JacksonXmlProperty(localName = "V", isAttribute = true)
        public String v;
    }
    @Data
    public static class CodProdLoc {
        @JacksonXmlProperty(localName = "V", isAttribute = true)
        public String v;
    }
    @Data
    public static class ObjPce {
        @JacksonXmlProperty(localName = "V", isAttribute = true)
        public String v;
    }
    @Data
    public static class MtTTC {
        @JacksonXmlProperty(localName = "V", isAttribute = true)
        public BigDecimal v;
    }

    @Data
    public static class Tiers {
        @JacksonXmlProperty(localName = "InfoTiers")
        public RolEntity.InfoTiers infoTiers = new RolEntity.InfoTiers();
        @JacksonXmlProperty(localName = "Adresse")
        public RolEntity.Adresse adresse = new RolEntity.Adresse();
    }

    @Data
    public static class InfoTiers {
        @JacksonXmlProperty(localName = "RefTiers")
        public RolEntity.RefTiers refTiers;
        @JacksonXmlProperty(localName = "CatTiers")
        public RolEntity.CatTiers catTiers;
        @JacksonXmlProperty(localName = "Civilite")
        public RolEntity.Civilite civilite;
        @JacksonXmlProperty(localName = "Nom")
        public RolEntity.Nom nom;
        @JacksonXmlProperty(localName = "Prenom")
        public RolEntity.Prenom prenom;
    }
    @Data
    public static class RefTiers {
        @JacksonXmlProperty(localName = "V", isAttribute = true)
        public String v;
    }
    @Data
    public static class CatTiers {
        @JacksonXmlProperty(localName = "V", isAttribute = true)
        public String v;
    }
    @Data
    public static class Civilite {
        @JacksonXmlProperty(localName = "V", isAttribute = true)
        public String v;
    }
    @Data
    public static class Nom {
        @JacksonXmlProperty(localName = "V", isAttribute = true)
        public String v;
    }
    @Data
    public static class Prenom {
        @JacksonXmlProperty(localName = "V", isAttribute = true)
        public String v;
    }

    @Data
    public static class Adresse {
        @JacksonXmlProperty(localName = "Adr1")
        public RolEntity.Adr1 adr1;
        @JacksonXmlProperty(localName = "Adr2")
        public RolEntity.Adr2 adr2;
        @JacksonXmlProperty(localName = "Adr3")
        public RolEntity.Adr3 adr3;
        @JacksonXmlProperty(localName = "CP")
        public RolEntity.CP cp;
        @JacksonXmlProperty(localName = "Ville")
        public RolEntity.Ville ville;
    }
    @Data
    public static class Adr1 {
        @JacksonXmlProperty(localName = "V", isAttribute = true)
        public String v;
    }
    @Data
    public static class Adr2 {
        @JacksonXmlProperty(localName = "V", isAttribute = true)
        public String v;
    }
    @Data
    public static class Adr3 {
        @JacksonXmlProperty(localName = "V", isAttribute = true)
        public String v;
    }
    @Data
    public static class CP {
        @JacksonXmlProperty(localName = "V", isAttribute = true)
        public String v;
    }
    @Data
    public static class Ville {
        @JacksonXmlProperty(localName = "V", isAttribute = true)
        public String v;
    }
}