package common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// kod koji je zajednicki za Server i Client, da se ne pise dva puta
// na kolokvijumu ne mora da se pise, vec moze odmah da se koristi njen interfejs
public class Service {
    private final Socket socket;
    private final BufferedReader in;       //za input
    private final PrintWriter out;          //za output

    public Service(Socket socket) throws IOException {
        this.socket = socket;

        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));    // socket prosledi svoj input stream
        //tezi se da bude efikasan - sve podatke baferuje, pa tek onda salje
        // sa parametrom true - odmah salje, ne koristi bafer - autoFlush
        // out je kao System.out
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void sendMsg(String msg) {
        out.println(msg);
    }

    public String receiveMsg() throws IOException {
        return in.readLine();       // blokirajuci je poziv dok ne dobijemo odgovor
    }

    public void close() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
