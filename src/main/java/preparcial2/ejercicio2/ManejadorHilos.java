package preparcial2.ejercicio2;

import java.util.Scanner;

public class ManejadorHilos implements Runnable{
    static Thread hilo1;
    static Thread hilo2;
    static String palabra;
    static long tf = 0;
    static long tac = 0;

    public void iniciarHilo1(String palabra) {
        this.palabra = palabra;

        hilo1 = new Thread(this);
        hilo1.start();
    }

    public void iniciarHilo2() {
        hilo2 = new Thread(this);
        hilo2.start();
    }

    public static void main(String[] args) {
        //https://github.com/devjosalazar/programacion_3_diurna/blob/main/programacion3/programacion3/co.edu.uniquindio.programacion3.diurna/src/hilos_CreacionV2/ManejadorHilos.java
    }

    @Override
    public void run() {
        Thread hiloEnEjecucion = Thread.currentThread();
        if(hiloEnEjecucion == hilo1) {
            Scanner in = new Scanner(System.in);
            palabra = in.nextLine();
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
