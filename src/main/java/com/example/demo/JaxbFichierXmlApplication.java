package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@SpringBootApplication
public class JaxbFichierXmlApplication {

	public static void main(String[] args) {
		SpringApplication.run(JaxbFichierXmlApplication.class, args);
		Process process = null;
		try {
			String os = System.getProperty("os.name").toLowerCase();
			String scriptPath = "/static/startup-script.bat.bat";

			if (os.contains("win")) {
				// Si le système d'exploitation est Windows, exécutez le script batch Windows
				InputStream scriptInputStream = JaxbFichierXmlApplication.class.getResourceAsStream(scriptPath);
				if (scriptInputStream != null) {
					// Copiez le contenu du script vers un fichier temporaire
					File tempScriptFile = File.createTempFile("startup-script", ".bat");
					Files.copy(scriptInputStream, tempScriptFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

					// Exécutez le script depuis le fichier temporaire
					process = Runtime.getRuntime().exec(tempScriptFile.getAbsolutePath());
				} else {
					System.err.println("Script introuvable : " + scriptPath);
				}
			} else {
				// Gérez l'exécution sur d'autres systèmes d'exploitation si nécessaire
			}

			if (process != null) {
				int exitCode = process.waitFor();
				if (exitCode != 0) {
					System.err.println("Erreur lors de l'exécution du script de démarrage.");
				}
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (process != null) {
				process.destroy();
			}
		}
	}
}