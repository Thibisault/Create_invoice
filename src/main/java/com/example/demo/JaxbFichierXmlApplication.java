package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class JaxbFichierXmlApplication {

	public static void main(String[] args) {
		SpringApplication.run(JaxbFichierXmlApplication.class, args);
		Process process = null; // Déclaration en dehors des blocs if-else
		try {
			String os = System.getProperty("os.name").toLowerCase();

			if (os.contains("win")) {
				// Si le système d'exploitation est Windows, exécutez le script batch Windows
				process = Runtime.getRuntime().exec("src/main/resources/startup-script.bat.bat");
			} else {
				// Sinon, exécutez le script shell
				process = Runtime.getRuntime().exec("src/main/resources/startup-script.sh");
			}

			int exitCode = process.waitFor();
			if (exitCode != 0) {
				System.err.println("Erreur lors de l'exécution du script de démarrage.");
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
