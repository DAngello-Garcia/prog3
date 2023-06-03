package preparcial2.ejercicio1;

public class ManejadorHilos {
    static Hilo1 hilo1;
    static Hilo2 hilo2;
    static Hilo3 hilo3;

    public static void crearHilos() {
        hilo1 = new Hilo1(new int[]{1, 2, 3, 4, 5});
        hilo2 = new Hilo2(2);
        hilo3 = new Hilo3("Hilo 3");
    }

    public static void main(String[] args) {
        crearHilos();
        //hilo1.start();
        //hilo2.start();
        hilo3.start();
    }
}
