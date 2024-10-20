package server;

// klasa sa slajda - funkcije koje treba pozvati
// ovde se nalaze slozene funkcije koje se izracunavaju na serveru
public class ServerService {
    protected int i = 0;
    public int setI(int ni) { int tmp = i; i = ni;
        return tmp;
    }
    public int add(int a, int b) { return a+b+i; }
}
