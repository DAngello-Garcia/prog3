package preparcial2.ejercicio1;

public class Hilo2 extends Thread {
    int n;

    public Hilo2(int n) {
        this.n = n;
    }

    public int getN() {
        return n;
    }

    @Override
    public void run() {
        try {
            int num = 1;
            for(int i = 0; i < 10; i++) {
                num *= n;
                System.out.println(num);
                Thread.sleep(1500);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
