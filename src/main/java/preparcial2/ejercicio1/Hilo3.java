package preparcial2.ejercicio1;

public class Hilo3 extends Thread {
    String nombre;

    public Hilo3(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
        try {
            for(int i = 1; i <= 15; i++) {
                System.out.println(i+". "+nombre);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
