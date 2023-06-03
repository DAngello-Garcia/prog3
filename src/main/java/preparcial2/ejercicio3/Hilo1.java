package preparcial2.ejercicio3;

public class Hilo1 extends Thread{
    int n;

    public Hilo1(int n) {
        this.n = n;
    }

    public int getN() {
        return n;
    }

    public int sumarHastaN(int n, int i, int suma) {
        if(i == n)
            return suma;
        return sumarHastaN(n, i+1, suma+i);
    }

    @Override
    public void run() {
        n = sumarHastaN(n, 1, n);
    }
}
