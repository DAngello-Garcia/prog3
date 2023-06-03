package preparcial2.ejercicio3;


public class ManejadorHilos {
    static Hilo1 hilo1;
    static Hilo2 hilo2;
    static int numeroHilo1 = 10;
    static String palabraHilo2 = "otorrinolaringologia";

    public static void inicializarHilos() {
        hilo1 = new Hilo1(numeroHilo1);
        hilo2 = new Hilo2(palabraHilo2);
    }

    public static void correrHilos() throws InterruptedException {
        hilo1.start();
        hilo2.start();
        try {
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int sumaNumero = hilo1.getN();
        int numeroVocales = hilo2.getCantidadVocales();
        System.out.println("Suma desde 1 hasta"+numeroHilo1+" = "+sumaNumero);
        System.out.println("Cantidad de vocales en "+palabraHilo2+" = "+numeroVocales);
        System.out.println("Resultado = "+sumaNumero*numeroVocales);
    }

    public static void main(String[] args) throws InterruptedException {
        inicializarHilos();
        correrHilos();
    }
}
