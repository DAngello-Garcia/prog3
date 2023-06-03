package preparcial2.ejercicio1;

public class Hilo1 extends Thread {
    int[] array;

    public int[] getArray() {
        return array;
    }

    public Hilo1(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        try {
            for(int i = 0; i < array.length; i++) {
                System.out.println(array[i]);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
