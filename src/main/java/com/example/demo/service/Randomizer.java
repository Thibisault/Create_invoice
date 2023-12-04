package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;

@Service
public class Randomizer {

    public String generateRandomString() {
        java.util.Random random = new java.util.Random();
        return String.valueOf(random.nextInt((8) + 1));
    }

    public BigDecimal generateRandomBigDecimal(){
        Random random = new Random();
        return BigDecimal.valueOf(random.nextInt((899)+99));
    }

    // Entrer en paramètre le nombre de caractère aléatoire
    public String generateRandomStringAndChooseHowMuch(int randomZero) {
        String nombreFinal = "";
        if (randomZero > 1) {
            for (int i = 0; i < randomZero; i++) {
                Random random = new Random();
                if (nombreFinal == null) {
                    nombreFinal = String.valueOf(random.nextInt(8) + 1);
                } else {
                    nombreFinal = nombreFinal + String.valueOf(random.nextInt(8) + 1);
                }
            }
        } else {
            if (randomZero == 1) {
                Random random = new Random();
                nombreFinal = String.valueOf(random.nextInt(8) + 1);
            }
        }
        return nombreFinal;
    }

    public long generateRandomNumberAndChooseHowMuch(int randomZero) {
        String nombreFinal = "";
        if (randomZero > 1) {
            for (int i = 0; i < randomZero; i++) {
                Random random = new Random();
                if (nombreFinal == null) {
                    nombreFinal = String.valueOf(random.nextInt(8) + 1);
                } else {
                    nombreFinal = nombreFinal + String.valueOf(random.nextInt(8) + 1);
                }
            }
        } else {
            if (randomZero == 1) {
                Random random = new Random();
                nombreFinal = String.valueOf(random.nextInt(8) + 1);
            }
        }
        long finalNombre = Integer.parseInt(nombreFinal);
        return finalNombre;
    }

    public int generateRandomNumber () {
        java.util.Random random = new java.util.Random();
        return random.nextInt((8) + 1);
    }

    public String generateStringThreeNumber () {
        java.util.Random random = new java.util.Random();
        return String.valueOf(random.nextInt((899) + 100));
    }
}