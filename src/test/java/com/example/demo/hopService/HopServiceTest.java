package com.example.demo.hopService;

import com.example.demo.entity.hop.HopEntity;
import com.example.demo.entity.hop.TitreHopEntity;
import com.example.demo.service.hop.HopService;
import com.example.demo.service.hop.TitreHopService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class HopServiceTest {

    @Autowired
    HopService hopService;

    @Autowired
    TitreHopService titreHopService;

    @Test
    void contextLoads() {
    }

    @Test
    void generatePieceHopTest(){
        HopEntity hopEntity = new HopEntity();
        //hopService.generatePieceHop(hopEntity);
        //hopService.generatePieceHop(hopEntity);
    }

    /*
    @Test
    void generateNewPieceInFactureTest() throws IOException, IllegalAccessException, InstantiationException {
        HopEntity hopEntity = new HopEntity();
        List<HopEntity.Piece> pieces = new ArrayList<>();
        TitreHopEntity titreHopEntity = titreHopService.generateRandomValue(TitreHopEntity.class);
        titreHopEntity.setNombreFacture(5);

        hopService.remplirXmlAvecBDD(titreHopEntity);

        System.out.println("hopEntity : "+hopEntity);
        System.out.println("Size piece : "+ hopEntity.getPesTipiRecette().getBordereaux().getPieces().size());
        System.out.println("Pieces : " + hopEntity.getPesTipiRecette().getBordereaux().getPieces());
    }
     */

}
