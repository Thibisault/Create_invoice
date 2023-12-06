    // Établir une connexion WebSocket lorsque la page est chargée
    const socket = new WebSocket('ws://localhost:51234/ws');

    // Gérer les événements de la connexion WebSocket
    socket.onopen = function(event) {
        // Envoyer un message au serveur WebSocket pour indiquer qu'une page est ouverte
        socket.send('Page ouverte');
        console.log("web socket ouverte");
    };

    socket.onclose = function(event) {
        console.log("web socket fermée");
        // Gérer la fermeture de la connexion WebSocket, si nécessaire
    };
