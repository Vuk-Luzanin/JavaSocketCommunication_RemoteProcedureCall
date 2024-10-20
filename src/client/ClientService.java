package client;

import common.Service;

import java.io.IOException;
import java.net.Socket;

// isti interfejs kao ServerService, samo bez implementacije
public class ClientService extends Service {
    public ClientService(Socket socket) throws IOException {
        super(socket);
    }    // moze i da sadrzi objekat service
    public int setI(int ni) throws IOException {
        String msg = String.format("#setI#%d#", ni);
        sendMsg(msg);

        return Integer.parseInt(receiveMsg());
    }
    public int add(int a, int b) throws IOException {
        String msg = String.format("#add#%d#%d#", a, b);
        sendMsg(msg);

        return Integer.parseInt(receiveMsg());
    }
}
