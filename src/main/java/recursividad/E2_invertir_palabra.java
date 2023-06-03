package recursividad;

public class E2_invertir_palabra {
    public static void main(String[] args) {
        String palabra = "nato";
        int indice = 0;
        String palabraInvertida = invertirPalabra(palabra, indice);
        System.out.println(palabraInvertida);
    }

    private static String invertirPalabra(String palabra, int indice) {
        if(indice == palabra.length()-1)
            return palabra.charAt(indice)+"";
        return invertirPalabra(palabra, indice+1) + palabra.charAt(indice);
    }
}
