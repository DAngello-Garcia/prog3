package preparcial2.ejercicio3;

public class Hilo2 extends Thread{
    int cantidadVocales;
    String palabra;

    public Hilo2(String palabra) {
        this.palabra = palabra;
    }

    public int getCantidadVocales() {
        return cantidadVocales;
    }

    static int contarVocales(String str, int n)
    {
        if (n == 1)
            return esVocal(str.charAt(n - 1));

        return contarVocales(str, n-1) + esVocal(str.charAt(n - 1));
    }

    private static int esVocal(char charAt) {
        if(charAt == 'a' || charAt == 'e' || charAt == 'i' || charAt == 'o' || charAt == 'u')
            return 1;
        return 0;
    }

    @Override
    public void run() {
        cantidadVocales = contarVocales(palabra, palabra.length());
    }
}
