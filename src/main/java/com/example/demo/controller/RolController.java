package com.example.demo.controller;

import com.example.demo.entity.rol.TitreRolEntity;
import com.example.demo.service.ManipulerXml;
import com.example.demo.service.Randomizer;
import com.example.demo.service.rol.RolService;
import com.example.demo.service.rol.TitreRolService;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipOutputStream;

@Controller
public class RolController {

    @Autowired
    RolService rolService;
    @Autowired
    TitreRolService titreRolService;

    @Autowired
    ManipulerXml manipulerXml;

    @Autowired
    Randomizer randomizer;

    // Affichage de l'écran principal (tpaTitreRol/ToutesLesFactures.html)
    /**
     * Récupère et ajoute dans une liste toute les factures de type Rol en base de données,
     * Inverse l'odre de cette liste pour que la dernière facture crée apparaîsse en haut,
     * Envoyer cette liste au front TouteLesFactures.html (qui affichera sur la page toute ces factures dans un tabelau
     * du plus récent au plus ancien)
     */
    @GetMapping("/tpaTitreRol/TouteLesFactures")
    public String showAllFacture(Model model) {
        //Récuperer tout les factures en BDD
        List<TitreRolEntity> invoices = titreRolService.getAllTpaTitreRol();
        //Inverser l'odre de la liste
        Collections.reverse(invoices);
        //Envoyer liste "invoices" au front
        model.addAttribute("invoices", invoices);
        return "tpaTitreRol/TouteLesFactures";
    }

    // Créer facture
    /**
     * Crée en base données une nouvelle facture Rol avec des valeurs fixe pour chaque champs
     * (valeurs définies dans le service titreRolService)
     */
     @GetMapping("/saveNewRol")
    public String newFacture() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        titreRolService.saveTpaTitreRol();
        return "redirect:/tpaTitreRol/TouteLesFactures";
    }

    // Mise à jour facture
    /**
     * Récupère le TitreRolEntity associé à l'id sélectionné par l'utilisateur sur le front et affiche le formulaire de mise à jour
     * de ses valeurs en base de données.
     */
    @GetMapping("/tpaTitreRol/updateFacture/{id}")
    public String showUpdateFacture(@PathVariable("id") Long id, Model model) {
        TitreRolEntity titreRolEntity = titreRolService.getTpaTitreRolById(id);
        model.addAttribute("updateNew", titreRolEntity);
        model.addAttribute("idRol", id);
        return "tpaTitreRol/updateFacture";
    }
    /**
     *
     */
    @PostMapping("/updateInvoiceRol")
    public String updateInvoice(@ModelAttribute TitreRolEntity updateEntity, @RequestParam("idRol") Long idRol) {
        TitreRolEntity titreRolEntity = titreRolService.getTpaTitreRolById(idRol);
        TitreRolEntity tempEntity = new TitreRolEntity();
        int nombreFacture = titreRolEntity.getNombreFacture();
        titreRolEntity = manipulerXml.updateFields(updateEntity, titreRolEntity, tempEntity);
        titreRolEntity.setNombreFacture(nombreFacture);
        // Le nom du fichier étant une concaténation de plusieurs valeurs, il est important de le re-calculer après un update.
        titreRolEntity.setNomFic(titreRolService.generateNomFicRol(titreRolEntity));
        titreRolService.updateInvoice(titreRolEntity);
        return "redirect:/tpaTitreRol/TouteLesFactures";
    }
    // Supprimer facture

    /**
     * Récupère le TitreRolEntity associé à l'id sélectionné par l'utilisateur sur le front et le supprime de la base de données.
     */
    @PostMapping("/deleteInvoiceRol")
    public String deleteInvoice(@RequestParam("id") TitreRolEntity tpaTitreRolEntity) {
        titreRolService.deleteTpaTitreRol(tpaTitreRolEntity);
        return "redirect:/tpaTitreRol/TouteLesFactures";
    }

    /**
     * Va chercher en  base de données toutes les factures Rol présente puis les supprimes toute de la base.
     */
    @PostMapping("/deleteAllRol")
    public String deleteAllInvoices() {
        titreRolService.deleteAllTpaTitreRol();
        return "redirect:/tpaTitreRol/TouteLesFactures";
    }

    // Télécharger facture
    @PostMapping("/downloadFactureRol/{id}")
    public void downloadOneFacture(
            @PathVariable("id") Long id,
            HttpServletResponse response) throws IOException {

        TitreRolEntity titreRolEntity = titreRolService.getTpaTitreRolById(id);
        XmlMapper xmlMapper = new XmlMapper();
        SimpleModule module = new SimpleModule();
        xmlMapper.registerModule(module);

        String xml = xmlMapper.writeValueAsString(rolService.remplirXmlAvecBDD(titreRolEntity));

        response.setContentType("application/xml;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + titreRolEntity.getNomFic() + randomizer.generateRandomNumberAndChooseHowMuch(3) + ".xml");
        response.setHeader("Content-Length", String.valueOf(xml.length()));
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(xml);
    }

    @GetMapping("/downloadAllFactureRol")
    public void downloadAllFactures(HttpServletResponse response) throws IOException {
        List<TitreRolEntity> titreRolEntityListTmp = titreRolService.getAllTpaTitreRol();
        XmlMapper xmlMapper = new XmlMapper();
        String xml = null;
        TitreRolEntity titreRolEntity = new TitreRolEntity();

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(date);
        SimpleDateFormat dateFormatFull = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
        String dateFullString = dateFormatFull.format(date);

        File tempDirectory = Files.createTempDirectory(titreRolEntityListTmp.size() + "RolFactures_" + dateString + "___").toFile();
        int i = 1;
        for (TitreRolEntity titreRolTmp : titreRolEntityListTmp) {
            String uniqueFilename = titreRolTmp.getNomFic() + i + ".xml";
            File xmlFile = new File(tempDirectory, uniqueFilename);

            xmlMapper.writeValue(xmlFile, rolService.remplirXmlAvecBDD(titreRolTmp));

            if (xml == null) {
                xml = xmlFile.getAbsolutePath();
                titreRolEntity = titreRolService.getTpaTitreRolById(titreRolTmp.getId());
            } else {
                xml = xml + "," + xmlFile.getAbsolutePath();
            }
            i++;
        }

        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=\"Rol_Factures_"+dateFullString+".zip\"");

        try (ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream())) {
            for (String xmlFilePath : xml.split(",")) {
                File xmlFile = new File(xmlFilePath);

                File sourceFolder = xmlFile.getParentFile();

                manipulerXml.zipFile(sourceFolder, sourceFolder.getName(), zipOut);
            }
            response.setCharacterEncoding("UTF-8");
        }
        FileUtils.deleteDirectory(tempDirectory);
    }

    // Mise à jour du nombre de facture à télécharger pour chaque facture
    @PostMapping("/updateNombreFactureRol/{id}")
    public String updateNombreFacture(@PathVariable("id") Long id, HttpServletRequest request) {
        int newNombreFacture = Integer.parseInt(request.getParameter("nombreFacture"));
        TitreRolEntity titreRolEntity = titreRolService.getTpaTitreRolById(id);
        titreRolEntity.setNombreFacture(newNombreFacture);
        titreRolService.updateInvoice(titreRolEntity);
        return "redirect:/tpaTitreRol/TouteLesFactures";
    }
}
