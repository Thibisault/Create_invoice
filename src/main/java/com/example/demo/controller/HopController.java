package com.example.demo.controller;

import com.example.demo.entity.hop.TitreHopEntity;
import com.example.demo.service.ManipulerXml;
import com.example.demo.service.hop.HopService;
import com.example.demo.service.Randomizer;
import com.example.demo.service.hop.TitreHopService;
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
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipOutputStream;

@Controller
public class HopController {

    @Autowired
    HopService hopService;
    @Autowired
    TitreHopService titreHopService;
    @Autowired
    ManipulerXml manipulerXml;
    @Autowired
    Randomizer randomizer;

    /**
     * Créer est sauvegarge une nouvelle Facture dans la base de données
     */
    @GetMapping("/saveNew")
    public String newFacture() throws InstantiationException, IllegalAccessException  {
        titreHopService.saveTpaTitreHop();
        return "redirect:/tpaTitreHop/TouteLesFactures";
    }

    /**
     * Ouvre la page "updateFacture" qui affichera le formulaire de mise à jour de la facture
     */
    @GetMapping("/tpaTitreHop/updateFacture/{id}")
    public String showUpdateFacture(@PathVariable("id") Long id, Model model) {
        TitreHopEntity titreHopEntity = titreHopService.getTpaTitreHopById(id);
        model.addAttribute("updateNew", titreHopEntity);
        model.addAttribute("idHop", id);
        return "tpaTitreHop/updateFacture";
    }

    /**
     * Pour gérer la logique du formualire de mise à jour,
     */
    @PostMapping("/updateInvoice")
    public String updateInvoice(@ModelAttribute TitreHopEntity updateEntity, @RequestParam("idHop") Long idHop) {
        TitreHopEntity titreHopEntity = titreHopService.getTpaTitreHopById(idHop);
        TitreHopEntity tempEntity = new TitreHopEntity();
        int nombreFacture = titreHopEntity.getNombreFacture();
        titreHopEntity = manipulerXml.updateFields(updateEntity, titreHopEntity, tempEntity);
        titreHopEntity.setNombreFacture(nombreFacture);
        titreHopService.updateInvoice(titreHopEntity);
        return "redirect:/tpaTitreHop/TouteLesFactures";
    }

    @GetMapping("/tpaTitreHop/TouteLesFactures")
    public String showAllFacture(Model model) {
        List<TitreHopEntity> invoices = titreHopService.getAllTpaTitreHop();
        Collections.reverse(invoices);
        model.addAttribute("invoices", invoices);
        return "tpaTitreHop/TouteLesFactures";
    }

    @PostMapping("/deleteInvoice")
    public String deleteInvoice(@RequestParam("id") TitreHopEntity tpaTitreHopEntity) {
        titreHopService.deleteTpaTitreHop(tpaTitreHopEntity);
        return "redirect:/tpaTitreHop/TouteLesFactures";
    }

    @PostMapping("/deleteAll")
    public String deleteAllInvoices() {
        titreHopService.deleteAllTpaTitreHop();
        return "redirect:/tpaTitreHop/TouteLesFactures";
    }

    @PostMapping("/downloadFacture/{id}")
    public void downloadOneFacture(
            @PathVariable("id") Long id,
            HttpServletResponse response) throws IOException {

        TitreHopEntity titreHopEntity = titreHopService.getTpaTitreHopById(id);
        XmlMapper xmlMapper = new XmlMapper();
        SimpleModule module = new SimpleModule();
        xmlMapper.registerModule(module);

        String xml = xmlMapper.writeValueAsString(hopService.remplirXmlAvecBDD(titreHopEntity));

        response.setContentType("application/xml;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + titreHopEntity.getNomFic() +randomizer.generateRandomNumberAndChooseHowMuch(3) + ".xml");
        response.setHeader("Content-Length", String.valueOf(xml.length()));
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(xml);
    }

    @GetMapping("/downloadAllFacture")
    public void downloadAllFactures(HttpServletResponse response) throws IOException {
        List<TitreHopEntity> titreHopEntityListTmp = titreHopService.getAllTpaTitreHop();
        XmlMapper xmlMapper = new XmlMapper();
        String xml = null;
        TitreHopEntity titreHopEntity = new TitreHopEntity();

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(date);
        SimpleDateFormat dateFormatFull = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
        String dateFullString = dateFormatFull.format(date);

        File tempDirectory = Files.createTempDirectory(titreHopEntityListTmp.size()+"HopFactures_" + dateString + "___").toFile();
        int i = 1;
        for (TitreHopEntity titreHopTmp : titreHopEntityListTmp) {
            String uniqueFilename = titreHopTmp.getNomFic()+i +".xml";
            File xmlFile = new File(tempDirectory, uniqueFilename);

            xmlMapper.writeValue(xmlFile, hopService.remplirXmlAvecBDD(titreHopTmp));

            if (xml == null) {
                xml = xmlFile.getAbsolutePath();
                titreHopEntity = titreHopService.getTpaTitreHopById(titreHopTmp.getId());
            } else {
                xml = xml + "," + xmlFile.getAbsolutePath();
            }
            i++;
        }

        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=\"Hop_Factures_"+dateFullString+".zip\"");

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

    @PostMapping("/updateNombreFacture/{id}")
    public String updateNombreFacture(@PathVariable("id") Long id, HttpServletRequest request) {
        int newNombreFacture = Integer.parseInt(request.getParameter("nombreFacture"));
        TitreHopEntity titreHopEntity = titreHopService.getTpaTitreHopById(id);
        titreHopEntity.setNombreFacture(newNombreFacture);
        titreHopEntity.setNbrePce(String.valueOf(newNombreFacture));
        titreHopService.updateInvoice(titreHopEntity);
        return "redirect:/tpaTitreHop/TouteLesFactures";
    }
}
