package com.example.demo.controller;

import com.example.demo.entity.rec.TitreRecEntity;
import com.example.demo.service.*;
import com.example.demo.service.rec.RecService;
import com.example.demo.service.rec.TitreRecService;
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
public class RecController {

    @Autowired
    RecService recService;

    @Autowired
    TitreRecService titreRecService;

    @Autowired
    ManipulerXml manipulerXml;

    @Autowired
    Randomizer randomizer;

    @GetMapping("/saveNewRec")
    public String newFacture() throws InstantiationException, IllegalAccessException {
        titreRecService.saveTpaTitreRec();
        return "redirect:/tpaTitreRec/TouteLesFactures";
    }

    @GetMapping("/tpaTitreRec/updateFacture/{id}")
    public String showUpdateFacture(@PathVariable("id") Long id, Model model) {
        TitreRecEntity titreRecEntity = titreRecService.getTpaTitreRecById(id);
        model.addAttribute("updateNew", titreRecEntity);
        model.addAttribute("idRec", id);
        return "tpaTitreRec/updateFacture";
    }

    @PostMapping("/updateInvoiceRec")
    public String updateInvoice(@ModelAttribute TitreRecEntity updateEntity, @RequestParam("idRec") Long idRec) {
        TitreRecEntity titreRecEntity = titreRecService.getTpaTitreRecById(idRec);
        TitreRecEntity tempEntity = new TitreRecEntity();
        int nombreFacture = titreRecEntity.getNombreFacture();
        titreRecEntity = manipulerXml.updateFields(updateEntity, titreRecEntity, tempEntity);
        titreRecEntity.setNombreFacture(nombreFacture);
        titreRecEntity.setNomFic(titreRecService.generateNomFicRec(titreRecEntity));
        titreRecService.updateInvoice(titreRecEntity);
        return "redirect:/tpaTitreRec/TouteLesFactures";
    }

    @GetMapping("tpaTitreRec/TouteLesFactures")
    public String showAllFacture(Model model) {
        List<TitreRecEntity> invoices = titreRecService.getAllTpaTitreRec();
        Collections.reverse(invoices);
        model.addAttribute("invoices", invoices);
        return "tpaTitreRec/TouteLesFactures";
    }

    @PostMapping("/deleteInvoiceRec")
    public String deleteInvoice(@RequestParam("id") TitreRecEntity tpaTitreRecEntity) {
        titreRecService.deleteTpaTitreRec(tpaTitreRecEntity);
        return "redirect:/tpaTitreRec/TouteLesFactures";
    }

    @PostMapping("/deleteAllRec")
    public String deleteAllInvoices() {
        titreRecService.deleteAllTpaTitreRec();
        return "redirect:/tpaTitreRec/TouteLesFactures";
    }

    @PostMapping("/downloadFactureRec/{id}")
    public void downloadOneFacture(
            @PathVariable("id") Long id,
            HttpServletResponse response) throws IOException {

        TitreRecEntity titreRecEntity = titreRecService.getTpaTitreRecById(id);
        XmlMapper xmlMapper = new XmlMapper();
        SimpleModule module = new SimpleModule();
        xmlMapper.registerModule(module);

        String xml = xmlMapper.writeValueAsString(recService.remplirXmlAvecBDD(titreRecEntity));

        response.setContentType("application/xml;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + titreRecEntity.getNomFic() +randomizer.generateRandomNumberAndChooseHowMuch(3) + ".xml");
        response.setHeader("Content-Length", String.valueOf(xml.length()));
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(xml);
    }

    @GetMapping("/downloadAllFactureRec")
    public void downloadAllFactures(HttpServletResponse response) throws IOException {
        List<TitreRecEntity> titreRecEntityListTmp = titreRecService.getAllTpaTitreRec();
        XmlMapper xmlMapper = new XmlMapper();
        String xml = null;
        TitreRecEntity titreRecEntity = new TitreRecEntity();

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(date);
        SimpleDateFormat dateFormatFull = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
        String dateFullString = dateFormatFull.format(date);

        File tempDirectory = Files.createTempDirectory(titreRecEntityListTmp.size()+"RecFactures_" + dateString + "___").toFile();
        int i = 1;
        for (TitreRecEntity titreRecTmp : titreRecEntityListTmp) {
            String uniqueFilename = titreRecTmp.getNomFic()+i +".xml";
            File xmlFile = new File(tempDirectory, uniqueFilename);

            xmlMapper.writeValue(xmlFile, recService.remplirXmlAvecBDD(titreRecTmp));

            if (xml == null) {
                xml = xmlFile.getAbsolutePath();
                titreRecEntity = titreRecService.getTpaTitreRecById(titreRecTmp.getId());
            } else {
                xml = xml + "," + xmlFile.getAbsolutePath();
            }
            i++;
        }

        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=\"Rec_Factures"+dateFullString+".zip\"");

        // Create a ZIP output stream
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

    @PostMapping("/updateNombreFactureRec/{id}")
    public String updateNombreFacture(@PathVariable("id") Long id, HttpServletRequest request) {
        int newNombreFacture = Integer.parseInt(request.getParameter("nombreFacture"));
        TitreRecEntity titreRecEntity = titreRecService.getTpaTitreRecById(id);
        titreRecEntity.setNombreFacture(newNombreFacture);
        titreRecEntity.setNbrePce(String.valueOf(newNombreFacture));
        titreRecService.updateInvoice(titreRecEntity);
        return "redirect:/tpaTitreRec/TouteLesFactures";
    }
}
