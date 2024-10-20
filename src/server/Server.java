package server;

// sinhronizacija je implicitna (prvo se vrsi slanje pa primanje)
// Remote Procedure Call Implementation (na serveru se pozivaju zahtevne operacije)

// zakomentarisati ispisivanje greske - ok je
import common.Service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    // metoda koja ceka da se klijent javi
    public void run() {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(5555);   //TCP port

            while (true) {
                System.out.println("Wait for client");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client accepted");

                Service service = new Service(clientSocket);

                // ako na ovom mestu vrsimo komunikaciju, zablokiracemo se dok ne stigne poruka
                // pa necemo uci u naredni ciklus petlje - gde bi ostvarili konekciju sa novim klijentom

                // Resenje: pravimo nit za komunikaciju sa klijentom

                Thread t = new RequestHandler(service);
                t.start();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new Server().run();
    }
}
