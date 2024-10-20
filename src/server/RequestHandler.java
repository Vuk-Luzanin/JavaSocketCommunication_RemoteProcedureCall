package server;

import common.Service;

import java.io.IOException;

// nit koja vrsi komunikaciju sa klijentom, tj. jedan zahtev
public class RequestHandler extends Thread {
    private final Service service;
    private final ServerService serverService;    // objekat onoga sto pozivamo

    public RequestHandler(Service service) {
        this.service = service;
        serverService = new ServerService();
    }

    // run - prihvata poruke i zove funkcije u serverService
    @Override
    public void run() {
        while (true) {
            try {
                String msg = service.receiveMsg();
                // Ako BufferedReader vrati null - dosli smo do kraja, pa mozem oprekinuti komunikaciju
                if (msg == null) {
                    break;
                }

                service.sendMsg(parseAndRespond(msg));
            } catch (IOException e) {
                e.printStackTrace();
                break;  // ako se desi izuzetak - prekidamo komunikaciju i petlju
            }
        }
        service.close();
    }

    private String parseAndRespond(String msg) {
        // analiza korsnicke poruke
        // saljemo parametre odvojene delimiterom - primer
        // #setI#5#
        // '' + 'setI' + '5'
        // #add#2#3#
        String[] data = msg.split("#");  // !! ako je na pocetku #, prvi element ce biti prazan string!!

        int ret;
        if ("setI".equals(data[1])) {
            ret = serverService.setI(Integer.parseInt(data[2]));
        } else {
            ret = serverService.add(Integer.parseInt(data[2]), Integer.parseInt(data[3]));
        }

        return Integer.toString(ret);
    }
}
