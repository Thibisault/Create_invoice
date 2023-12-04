package com.example.demo.controller;

import com.example.demo.entity.rnf.TitreRnfEntity;
import com.example.demo.service.ManipulerXml;
import com.example.demo.service.Randomizer;
import com.example.demo.service.rnf.RnfService;
import com.example.demo.service.rnf.TitreRnfService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipOutputStream;

@Controller
public class RnfController {

    @Autowired
    RnfService rnfService;
    @Autowired
    TitreRnfService titreRnfService;

    @Autowired
    ManipulerXml manipulerXml;
    @Autowired
    Randomizer randomizer;

    @GetMapping("/saveNewRnf")
    public String newFacture() {
        titreRnfService.saveTpaTitreRnf();
        return "redirect:/tpaTitreRnf/TouteLesFactures";
    }

    @GetMapping("/tpaTitreRnf/updateFacture/{id}")
    public String showUpdateFacture(@PathVariable("id") Long id, Model model) {
        TitreRnfEntity titreRnfEntity = titreRnfService.getTpaTitreRnfById(id);
        model.addAttribute("updateNew", titreRnfEntity);
        model.addAttribute("idRnf", id);
        return "tpaTitreRnf/updateFacture";
    }

    @PostMapping("/updateInvoiceRnf")
    public String updateInvoice(@ModelAttribute TitreRnfEntity updateEntity, @RequestParam("idRnf") Long idRnf) {
        TitreRnfEntity titreRnfEntity = titreRnfService.getTpaTitreRnfById(idRnf);
        TitreRnfEntity tempEntity = new TitreRnfEntity();
        int nombreFacture = titreRnfEntity.getNombreFacture();
        titreRnfEntity = manipulerXml.updateFields(updateEntity, titreRnfEntity, tempEntity);
        titreRnfEntity.setNombreFacture(nombreFacture);
        titreRnfService.updateInvoice(titreRnfEntity);
        return "redirect:/tpaTitreRnf/TouteLesFactures";
    }

    @GetMapping("/tpaTitreRnf/TouteLesFactures")
    public String showAllFacture(Model model) {
        List<TitreRnfEntity> invoices = titreRnfService.getAllTpaTitreRnf();
        Collections.reverse(invoices);
        model.addAttribute("invoices", invoices);
        return "tpaTitreRnf/TouteLesFactures";
    }

    @PostMapping("/deleteInvoiceRnf")
    public String deleteInvoice(@RequestParam("id") TitreRnfEntity tpaTitreRnfEntity) {
        titreRnfService.deleteTpaTitreRnf(tpaTitreRnfEntity);
        return "redirect:/tpaTitreRnf/TouteLesFactures";
    }

    @PostMapping("/deleteAllRnf")
    public String deleteAllInvoices() {
        titreRnfService.deleteAllTpaTitreRnf();
        return "redirect:/tpaTitreRnf/TouteLesFactures";
    }

    @PostMapping("/downloadFactureRnf/{id}")
    public void downloadOneFacture(
            @PathVariable("id") Long id,
            HttpServletResponse response) throws IOException, IllegalAccessException {

        TitreRnfEntity titreRnfEntity = titreRnfService.getTpaTitreRnfById(id);

        String text = rnfService.contatenerRnfComplet(titreRnfEntity);

        response.setContentType("application/octet-stream;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + titreRnfEntity.getNomFic() +randomizer.generateRandomNumberAndChooseHowMuch(3));
        byte[] contentBytes = text.getBytes("UTF-8");
        response.setHeader("Content-Length", String.valueOf(contentBytes.length));
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        response.setCharacterEncoding("UTF-8");
        try (OutputStream os = response.getOutputStream()) {
            os.write(contentBytes);
        }
    }

    @GetMapping("/downloadAllFactureRnf")
    public void downloadAllFactures(HttpServletResponse response) throws IOException, IllegalAccessException {
        List<TitreRnfEntity> titreRnfEntityListTmp = titreRnfService.getAllTpaTitreRnf();

        String text = null;
        TitreRnfEntity titreRnfEntity = new TitreRnfEntity();

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(date);
        SimpleDateFormat dateFormatFull = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
        String dateFullString = dateFormatFull.format(date);

        File tempDirectory = Files.createTempDirectory(titreRnfEntityListTmp.size() + "RnfFactures_" + dateString + "___").toFile();
        int i = 1;

        for (TitreRnfEntity titreRnfTmp : titreRnfEntityListTmp) {
            String uniqueFilename = titreRnfTmp.getNomFic() + i + ".txt";
            File textFile = new File(tempDirectory, uniqueFilename);

            text = rnfService.contatenerRnfComplet(titreRnfTmp);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(textFile))) {
                writer.write(text);
            }

            i++;
        }

        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=\"Rnf_Factures_"+ dateFullString + ".zip\"");

        try (ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream())) {
            for (File textFile : tempDirectory.listFiles()) {
                File sourceFolder = textFile.getParentFile();
                manipulerXml.zipFile(sourceFolder, sourceFolder.getName(), zipOut);
                zipOut.closeEntry();
            }
            response.setCharacterEncoding("UTF-8");
        }
        FileUtils.deleteDirectory(tempDirectory);
    }

    @PostMapping("/updateNombreFactureRnf/{id}")
    public String updateNombreFacture(@PathVariable("id") Long id, HttpServletRequest request) {
        int newNombreFacture = Integer.parseInt(request.getParameter("nombreFacture"));
        TitreRnfEntity titreRnfEntity = titreRnfService.getTpaTitreRnfById(id);
        titreRnfEntity.setNombreFacture(newNombreFacture);
        titreRnfEntity.setNombreAticles(newNombreFacture);
        titreRnfEntity.setNombreAticles(newNombreFacture);
        titreRnfService.updateInvoice(titreRnfEntity);
        return "redirect:/tpaTitreRnf/TouteLesFactures";
    }
}
