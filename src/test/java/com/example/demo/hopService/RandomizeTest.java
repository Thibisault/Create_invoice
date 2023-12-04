package com.example.demo.hopService;

import com.example.demo.service.Randomizer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RandomizeTest {

    @Autowired
    Randomizer randomizer;

    @Test
    void contextLoads() {
    }

    /*
    @Test
    void generateRandomNumberAndChooseHowMuchTest(){
        String str = randomizer.generateRandomNumberAndChooseHowMuch(5);
        String str2 = randomizer.generateRandomNumberAndChooseHowMuch(4);
        String str3 = randomizer.generateRandomNumberAndChooseHowMuch(3);
        String str4 = randomizer.generateRandomNumberAndChooseHowMuch(2);
        String str5 = randomizer.generateRandomNumberAndChooseHowMuch(1);
        String str6 = randomizer.generateRandomNumberAndChooseHowMuch(0);
        String str67= randomizer.generateRandomNumberAndChooseHowMuch(10);
        System.out.println(str.length()+" : "+str);
        System.out.println(str2.length()+" : "+str2);
        System.out.println(str3.length()+" : "+str3);
        System.out.println(str4.length()+" : "+str4);
        System.out.println(str5.length()+" : "+str5);
        System.out.println(str6.length()+" : "+str6);
        System.out.println(str67.length()+" : "+str67);
    }
     */
}
