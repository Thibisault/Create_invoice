package com.example.demo;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class WebSocketHandler extends TextWebSocketHandler {

    private final Set<WebSocketSession> openSessions = new HashSet<>();
    private int pageCount = 0; // Compteur de pages ouvertes
    private Timer timer;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        openSessions.add(session);
        pageCount++;

        // Envoyer un message pour informer le serveur WebSocket du nombre de pages ouvertes
        sendMessageToServer("Une page a été ouverte, nombre de pages ouvertes : " + pageCount);

        // Annuler le timer s'il y a toujours des pages ouvertes
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        openSessions.remove(session);
        pageCount--;

        // Envoyez un message pour informer le serveur WebSocket du nombre de pages fermées
        sendMessageToServer("Une page a été fermée, nombre total de pages après suppression : " + pageCount);

        // Si toutes les pages sont fermées et qu'il n'y a pas de timer en cours, démarrez un nouveau timer
        if (pageCount == 0 && timer == null) {
            System.out.println("Toutes les pages sont fermées. Démarrage du timer de 10 secondes.");

            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    // Code pour arrêter l'application après 10 secondes
                    System.out.println("Arrêt de l'application après 10 secondes.");
                    System.exit(0);
                }
            }, 10000); // Délai de 10 secondes
        }
    }

    private void sendMessageToServer(String message) {
        System.out.println(message);
    }
}



