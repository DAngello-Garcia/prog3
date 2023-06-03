package recursividad;

import java.util.ArrayList;

public class E6_hallar_palabra_vocales_seguidas {
    /*
    Dada una matriz de String de 4 x 4 conformada por palabras, hallar las palabras que tienen dos vocales seguidas de forma recursiva y agregarlas a un ArrayList.
     */
        public static void main(String[] args) {
            String[][] matriz = { { "casa", "perro", "auto", "mesa" }, { "letra", "libro", "avion", "flor" },
                    { "taza", "agua", "computadoraaa", "papel" }, { "árbol", "ooso", "gato", "sol" } };

            ArrayList<String> palabras = new ArrayList<>();
            recorrerMatriz(matriz, 0, 0, palabras);

            System.out.println(palabras);
        }

        public static void recorrerMatriz(String[][] matriz, int i, int j, ArrayList<String> listaPalabras) {
            if(i < matriz.length) {
                if(j < matriz[i].length) {
                    if (verificarVocalesSeguidas(matriz[i][j], 0))
                        listaPalabras.add(matriz[i][j]);
                    if(j == matriz[i].length - 1) {
                        recorrerMatriz(matriz, i + 1, 0, listaPalabras);
                    }
                    recorrerMatriz(matriz, i, j + 1, listaPalabras);
                }
            }
        }

        public static boolean verificarVocalesSeguidas(String palabra, int index) {
            if(index < palabra.length()) {
                if(index == palabra.length()-1)
                    return false;
                char letra1 = palabra.charAt(index);
                char letra2 = palabra.charAt(index + 1);
                if(esVocal(letra1) && esVocal(letra2))
                    return true;
                return verificarVocalesSeguidas(palabra, index + 1);
            }
            return false;
        }

        public static boolean esVocal(char letra) {
            return letra == 'a' || letra == 'e' || letra == 'i' || letra == 'o' || letra == 'u';
        }
    }


