package client;


import java.io.IOException;
import java.net.Socket;

public class Client {
    // radimo na jednom racunaru - moze localhost (inace saljemo IP servera)
    public void run () {
        ClientService service = null;
        try {
            Socket socket = new Socket("localhost", 5555);

            service = new ClientService(socket);

            for (int i=0; i<5; i++) {
                service.setI(i);
                System.out.println(service.add(1, 2));
                synchronized (this) {           // cekamo neko vreme da ne bi bilo brzo
                    wait(1000);
                }
            }

        } catch (IOException |InterruptedException e) {
            e.printStackTrace();
        } finally {         // u finally zatvaramo sockete
            if (service == null) {
                service.close();
            }
        }
    }

    public static void main(String[] args) {
        new Client().run();
    }
}
