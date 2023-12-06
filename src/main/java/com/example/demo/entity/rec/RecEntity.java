package com.example.demo.entity.rec;

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
public class RecEntity {

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
        return "http://www.minefi.gouv.fr/cp/helios/pes_v2/Rev0/retour /opt/tx5115/users/ediprod/messages/XML/PES_V2/Externe/Schemas_PES/PES_V2/Rev0/PES_V2_TIPI_RECETTE_Autonome.xsd";
    }

    @JacksonXmlProperty(localName = "Enveloppe")
    public RecEntity.Enveloppe enveloppe = new RecEntity.Enveloppe();

    @JacksonXmlProperty(localName = "EnTetePES")
    public RecEntity.EnTetePES enTetePES = new RecEntity.EnTetePES();

    @JacksonXmlProperty(localName = "PES_TIPI_Recette")
    public RecEntity.PESTIPIRecette pesTipiRecette = new RecEntity.PESTIPIRecette();

    @Data
    public static class Enveloppe {
        @JacksonXmlProperty(localName = "Parametres")
        public RecEntity.Parametres parametres = new RecEntity.Parametres();
    }

    @Data
    @JacksonXmlRootElement(localName = "Parametres")
    public static class Parametres {

        //@JsonSerialize(using = VersionSerializer.class)
        @JacksonXmlProperty(localName = "Version", isAttribute = true)
        private RecEntity.Version version;

        @JacksonXmlProperty(localName = "TypFic", isAttribute = true)
        private RecEntity.TypFic typFic;

        @JacksonXmlProperty(localName = "NomFic", isAttribute = true)
        private RecEntity.NomFic nomFic;
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
        public RecEntity.DteStr dteStr;
        @JacksonXmlProperty(localName = "IdPost", isAttribute = true)
        public RecEntity.IdPost idPost;
        @JacksonXmlProperty(localName = "LibellePoste", isAttribute = true)
        public RecEntity.LibellePoste libellePoste;
        @JacksonXmlProperty(localName = "IdColl", isAttribute = true)
        public RecEntity.IdColl idColl;
        @JacksonXmlProperty(localName = "CodCol", isAttribute = true)
        public RecEntity.CodCol codCol;
        @JacksonXmlProperty(localName = "CodBud", isAttribute = true)
        public RecEntity.CodBud codBud;
        @JacksonXmlProperty(localName = "LibelleColBud", isAttribute = true)
        public RecEntity.LibelleColBud libelleColBud;
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
    public static class LibellePoste {
        @JacksonXmlProperty(localName = "V", isAttribute = true)
        public String v;
    }

    @Data
    public static class IdColl {
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
    public static class LibelleColBud {
        @JacksonXmlProperty(localName = "V", isAttribute = true)
        public String v;
    }


    @Data
    public static class PESTIPIRecette {
        @JacksonXmlProperty(localName = "Bordereau")
        public RecEntity.Bordereau bordereaux = new RecEntity.Bordereau();
    }

    @Data
    public static class Bordereau {
        @JacksonXmlProperty(localName = "BlocBordereau")
        private RecEntity.BlocBordereau blocBordereau = new RecEntity.BlocBordereau();
        @JacksonXmlProperty(localName = "Piece")
        @JacksonXmlElementWrapper(useWrapping = false)
        private ArrayList<RecEntity.Piece> pieces = new ArrayList<>();

        public void addPiece(RecEntity.Piece piece) {
            this.pieces.add(piece);
        }
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BlocBordereau {
        @JacksonXmlProperty(localName = "Exer")
        public RecEntity.Exer exer;
        @JacksonXmlProperty(localName = "IdBord")
        public RecEntity.IdBord idBord;
        @JacksonXmlProperty(localName = "TypBord")
        public RecEntity.TypBord typBord;
        @JacksonXmlProperty(localName = "NbrePce")
        public RecEntity.NbrePce nbrePce;
    }

    @Data
    public static class Exer {
        @JacksonXmlProperty(localName = "V", isAttribute = true)
        public String v;
    }

    @Data
    public static class IdBord {
        @JacksonXmlProperty(localName = "V", isAttribute = true)
        public String v;
    }

    @Data
    public static class TypBord {
        @JacksonXmlProperty(localName = "V", isAttribute = true)
        public String v;
    }

    @Data
    public static class NbrePce {
        @JacksonXmlProperty(localName = "V", isAttribute = true)
        public String v;
    }


    @Data
    public static class Piece {
        @JacksonXmlProperty(localName = "BlocPiece")
        public RecEntity.BlocPiece blocPiece = new RecEntity.BlocPiece();
        @JacksonXmlProperty(localName = "LigneDePiece")
        public RecEntity.LigneDePiece ligneDePiece = new RecEntity.LigneDePiece();
    }

    @Data
    public static class BlocPiece {
        @JacksonXmlProperty(localName = "IdPce")
        public RecEntity.IdPce idPce;
        @JacksonXmlProperty(localName = "TypPce")
        public RecEntity.TypPce typPce;
        @JacksonXmlProperty(localName = "NatPce")
        public RecEntity.NatPce natPce;
        @JacksonXmlProperty(localName = "CatPce")
        public RecEntity.CatPce catPce;
        @JacksonXmlProperty(localName = "EtatPce")
        public RecEntity.EtatPce etatPce;
        @JacksonXmlProperty(localName = "DtePcePec")
        public RecEntity.DtePcePec dtePcePec;
    }

    @Data
    public static class IdPce {
        @JacksonXmlProperty(localName = "V", isAttribute = true)
        public long v;
    }

    @Data
    public static class TypPce {
        @JacksonXmlProperty(localName = "V", isAttribute = true)
        public String v;
    }

    @Data
    public static class NatPce {
        @JacksonXmlProperty(localName = "V", isAttribute = true)
        public String v;
    }

    @Data
    public static class CatPce {
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
    public static class LigneDePiece {
        @JacksonXmlProperty(localName = "BlocLignePiece")
        public RecEntity.BlocLignePiece blocLignePiece = new RecEntity.BlocLignePiece();
        @JacksonXmlProperty(localName = "Tiers")
        public RecEntity.Tiers tiers = new RecEntity.Tiers();
    }

    @Data
    public static class BlocLignePiece {
        @JacksonXmlProperty(localName = "InfoLignePiece")
        public RecEntity.InfoLignePiece infoLignePiece = new RecEntity.InfoLignePiece();
    }

    @Data
    public static class InfoLignePiece {
        @JacksonXmlProperty(localName = "IdLigne")
        public RecEntity.IdLigne idLigne;
        @JacksonXmlProperty(localName = "ObjLignePce")
        public RecEntity.ObjLignePce objLignePce;
        @JacksonXmlProperty(localName = "Nature")
        public RecEntity.Nature nature;
        @JacksonXmlProperty(localName = "MtTTC")
        public RecEntity.MtTTC mtTTC;

    }

    @Data
    public static class IdLigne {
        @JacksonXmlProperty(localName = "V", isAttribute = true)
        public String v;
    }

    @Data
    public static class ObjLignePce {
        @JacksonXmlProperty(localName = "V", isAttribute = true)
        public String v;
    }

    @Data
    public static class Nature {
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
        public RecEntity.InfoTiers infoTiers = new RecEntity.InfoTiers();
        @JacksonXmlProperty(localName = "Adresse")
        public RecEntity.Adresse adresse = new RecEntity.Adresse();
    }

    @Data
    public static class InfoTiers {
        @JacksonXmlProperty(localName = "RefTiers")
        public RecEntity.RefTiers refTiers;
        @JacksonXmlProperty(localName = "CatTiers")
        public RecEntity.CatTiers catTiers;
        @JacksonXmlProperty(localName = "TypTiers")
        public RecEntity.TypTiers typTiers;
        @JacksonXmlProperty(localName = "Civilite")
        public RecEntity.Civilite civilite;
        @JacksonXmlProperty(localName = "Nom")
        public RecEntity.Nom nom;
        @JacksonXmlProperty(localName = "Prenom")
        public RecEntity.Prenom prenom;
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
    public static class TypTiers {
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
        public RecEntity.Adr1 adr1;
        @JacksonXmlProperty(localName = "Adr2")
        public RecEntity.Adr2 adr2;
        @JacksonXmlProperty(localName = "Adr3")
        public RecEntity.Adr3 adr3;
        @JacksonXmlProperty(localName = "CP")
        public RecEntity.CP cp;
        @JacksonXmlProperty(localName = "Ville")
        public RecEntity.Ville ville;
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
