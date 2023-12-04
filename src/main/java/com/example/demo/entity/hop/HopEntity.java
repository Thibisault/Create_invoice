package com.example.demo.entity.hop;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.xml.bind.annotation.*;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "n:PES_Retour")
@XmlAccessorType(XmlAccessType.FIELD)
public class HopEntity {
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
    public Enveloppe enveloppe = new Enveloppe();

    @JacksonXmlProperty(localName = "EnTetePES")
    public EnTetePES enTetePES = new EnTetePES();
    @JacksonXmlProperty(localName = "PES_TIPI_Recette")
    public PESTIPIRecette pesTipiRecette = new PESTIPIRecette();
    @Data
    public static class Enveloppe {
        @JacksonXmlProperty(localName = "Parametres")
        public Parametres parametres = new Parametres();
    }

    @Data
    @JacksonXmlRootElement(localName = "Parametres")
    public static class Parametres {

        @JacksonXmlProperty(localName = "Version", isAttribute = true)
        private Version version;

        @JacksonXmlProperty(localName = "TypFic", isAttribute = true)
        private TypFic typFic;

        @JacksonXmlProperty(localName = "NomFic", isAttribute = true)
        private NomFic nomFic;
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
        public DteStr dteStr;
        @JacksonXmlProperty(localName = "IdPost", isAttribute = true)
        public IdPost idPost;
        @JacksonXmlProperty(localName = "LibellePoste", isAttribute = true)
        public LibellePoste libellePoste;
        @JacksonXmlProperty(localName = "IdColl", isAttribute = true)
        public IdColl idColl;
        @JacksonXmlProperty(localName = "FinJur", isAttribute = true)
        public FinJur finJur;
        @JacksonXmlProperty(localName = "CodCol", isAttribute = true)
        public CodCol codCol;
        @JacksonXmlProperty(localName = "CodBud", isAttribute = true)
        public CodBud codBud;
        @JacksonXmlProperty(localName = "LibelleColBud", isAttribute = true)
        public LibelleColBud libelleColBud;
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
    public static class FinJur {
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
        public Bordereau bordereaux = new Bordereau();
    }

    @Data
    public static class Bordereau {
        @JacksonXmlProperty(localName = "BlocBordereau")
        private BlocBordereau blocBordereau = new BlocBordereau();
        @JacksonXmlProperty(localName = "Piece")
        @JacksonXmlElementWrapper(useWrapping = false)
        private ArrayList<Piece> pieces = new ArrayList<>();

        public void addPiece(Piece piece) {
            this.pieces.add(piece);
        }
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BlocBordereau {
        @JacksonXmlProperty(localName = "Exer")
        public Exer exer;
        @JacksonXmlProperty(localName = "IdBord")
        public IdBord idBord;
        @JacksonXmlProperty(localName = "TypBord")
        public TypBord typBord;
        @JacksonXmlProperty(localName = "NbrePce")
        public NbrePce nbrePce;
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
        public BlocPiece blocPiece = new BlocPiece();
        @JacksonXmlProperty(localName = "LigneDePiece")
        public LigneDePiece ligneDePiece = new LigneDePiece();
    }

    @Data
    public static class BlocPiece {
        @JacksonXmlProperty(localName = "IdPce")
        public IdPce idPce;
        @JacksonXmlProperty(localName = "TypPce")
        public TypPce typPce;
        @JacksonXmlProperty(localName = "NatPce")
        public NatPce natPce;
        @JacksonXmlProperty(localName = "CatPce")
        public CatPce catPce;
        @JacksonXmlProperty(localName = "DebFact")
        public DebFact debFact;
        @JacksonXmlProperty(localName = "FinFact")
        public FinFact finFact;
        @JacksonXmlProperty(localName = "EtatPce")
        public EtatPce etatPce;
        @JacksonXmlProperty(localName = "DtePcePec")
        public DtePcePec dtePcePec;
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
    public static class DebFact {
        @JacksonXmlProperty(localName = "V", isAttribute = true)
        public String v;
    }
    @Data
    public static class FinFact {
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
        public BlocLignePiece blocLignePiece = new BlocLignePiece();
        @JacksonXmlProperty(localName = "Tiers")
        public Tiers tiers = new Tiers();
    }

    @Data
    public static class BlocLignePiece {
        @JacksonXmlProperty(localName = "InfoLignePiece")
        public InfoLignePiece infoLignePiece = new InfoLignePiece();
    }

    @Data
    public static class InfoLignePiece {
        @JacksonXmlProperty(localName = "IdLigne")
        public IdLigne idLigne;
        @JacksonXmlProperty(localName = "ObjLignePce")
        public ObjLignePce objLignePce;
        @JacksonXmlProperty(localName = "Nature")
        public Nature nature;
        @JacksonXmlProperty(localName = "MtTTC")
        public MtTTC mtTTC;
        @JacksonXmlProperty(localName = "CodEtGeo")
        public CodEtGeo codEtGeo;
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
    public static class CodEtGeo {
        @JacksonXmlProperty(localName = "V", isAttribute = true)
        public String v;
    }

    @Data
    public static class Tiers {
        @JacksonXmlProperty(localName = "InfoTiers")
        public InfoTiers infoTiers = new InfoTiers();
        @JacksonXmlProperty(localName = "Adresse")
        public Adresse adresse = new Adresse();
    }

    @Data
    public static class InfoTiers {
        @JacksonXmlProperty(localName = "DteMalade")
        public DteMalade dteMalade;
        @JacksonXmlProperty(localName = "RefTiers")
        public RefTiers refTiers;
        @JacksonXmlProperty(localName = "CatTiers")
        public CatTiers catTiers;
        @JacksonXmlProperty(localName = "TypTiers")
        public TypTiers typTiers;
        @JacksonXmlProperty(localName = "Civilite")
        public Civilite civilite;
        @JacksonXmlProperty(localName = "Nom")
        public Nom nom;
        @JacksonXmlProperty(localName = "Prenom")
        public Prenom prenom;
    }
    @Data
    public static class DteMalade {
        @JacksonXmlProperty(localName = "V", isAttribute = true)
        public String v;
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
        public Adr1 adr1;
        @JacksonXmlProperty(localName = "Adr2")
        public Adr2 adr2;
        @JacksonXmlProperty(localName = "Adr3")
        public Adr3 adr3;
        @JacksonXmlProperty(localName = "CP")
        public CP cp;
        @JacksonXmlProperty(localName = "Ville")
        public Ville ville;
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
