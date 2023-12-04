package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class ManipulerXml {

    @Autowired
    Randomizer randomizer;

    /**
     *Permet de créer le fichier Zip pour y mettre toute les factures téléchargés
     */
    public void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(fileName));
            } else {
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
            }
            zipOut.closeEntry();

            File[] children = fileToZip.listFiles();
            if (children != null) {
                for (File childFile : children) {
                    zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
                }
            }
            return;
        }
        try (FileInputStream fis = new FileInputStream(fileToZip)) {
            ZipEntry zipEntry = new ZipEntry(fileName);
            zipOut.putNextEntry(zipEntry);
            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
        }
    }

    public <T> T updateFields(T updateEntity, T entityId, T tempEntity) {
        List<String> emptyFields = this.getEmptyFields(updateEntity);

        // Boucle de lecture pour chaque champs de l'entity,
        // Comparaison du nom du champ contenu dans field avec les éléments contenu dans emptyField,
        // Pour chaque champs dans emptyFiels récupère dans un objet Entity temporaire la valeur associé à l'entity modifié avant la mise à jour.
        for (Field field : updateEntity.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            if (emptyFields.contains(fieldName)) {
                try {
                    Object value = field.get(entityId);
                    field.set(tempEntity, value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        entityId = updateEntity;
        // Pour chaque champs vide contenu dans emptyField, modifie la valeur de ces champs dans l'entity en cours de modifications
        // avec les valeurs de l'entity temporaire correspondante.
        for (Field field : updateEntity.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();

            if (emptyFields.contains(fieldName)) {
                try {
                    Object value = field.get(tempEntity);
                    field.set(entityId, value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return entityId;
    }

    /**
     * Permet de remplir aléatoirement tout les champs d'une classe
     */
    public <T> T generateRandomValue(Class<T> entityClass) throws IllegalAccessException, InstantiationException {
        T entity = entityClass.newInstance();

        // récupère tout les champs de la classe
        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);

            // Récupère le type du champs et genère une valeur aléatoire pour ce champ qui sera du bon type
            Object randomValue;
            if (field.getType() == String.class) {
                randomValue = randomizer.generateRandomString();
            } else if (field.getType() == Integer.class || field.getType() == int.class) {
                randomValue = randomizer.generateRandomNumber();
            } else if (field.getType() == BigDecimal.class || field.getType() == BigDecimal.class) {
                randomValue = randomizer.generateRandomBigDecimal();
            } else {
                randomValue = null;
            }

            // sauvegarde la nouvelle valeur pour le champ de la classe.
            try {
                field.set(entity, randomValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return entity;
    }

    /**
     * Récupère après validation du formulaire de mise à jour les champs vides dans le TitreRolEntity en cours de mise à jour.
     * (Si un champ est vide c'est qu'il n'apparaît pas dans les champs modifiable du updateFacture.html, ce qui est normal
     * car tout les champs ne doivent pas pouvoir être modifiés)
     */
    public List<String> getEmptyFields(Object entity) {
        List<String> emptyFields = new ArrayList<>();

        if (entity != null) {
            Class<?> clazz = entity.getClass();
            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);

                try {
                    Object value = field.get(entity);

                    if (value == null) {
                        emptyFields.add(field.getName());
                    } else if (value instanceof String && ((String) value).isEmpty()) {
                        emptyFields.add(field.getName());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return emptyFields;
    }
}
