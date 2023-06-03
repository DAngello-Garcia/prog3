package parcial1;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ejercicio2 {
    public static int[][] nums = {{5, 7, 1, 3},
            {6, 45, 13, 89},
            {2, 28, 496, 8128},
            {11, 4, 8, 89},
            {31, 37, 43, 10}};
    public static void main(String[] args) {
        Persona [][] matriz = new Persona [5][4];
        llenarMatriz(matriz);
        List<Persona> listaPrimos = new ArrayList();
        List<Persona> listaPerfectos = new ArrayList();
        recorrerMatriz(matriz, matriz.length-1, 0, listaPrimos, listaPerfectos);
        System.out.println("Lista de números primos: "+recorrerLista(listaPrimos, 0, listaPrimos.size(), ""));
        System.out.println("Lista de números perfectos: "+recorrerLista(listaPerfectos, 0, listaPerfectos.size(), ""));
    }

    public static int recorrerMatriz(Persona[][] matriz, int fila, int columna, List<Persona> listaPrimos,
                                         List<Persona> listaPerfectos) {
        if(fila >= 0) {
            if(columna < matriz[fila].length) {
                if(verificarPerfecto(matriz[fila][columna].getEdad(), 1, 0))
                    listaPerfectos.add(matriz[fila][columna]);
                if(verificarPrimo(matriz[fila][columna].getEdad(), 1))
                    listaPrimos.add(matriz[fila][columna]);
                return recorrerMatriz(matriz, fila, columna+1, listaPrimos, listaPerfectos);
            }
            return recorrerMatriz(matriz, fila-1, 0, listaPrimos, listaPerfectos);
        }
        return 0;
    }

    public static boolean verificarPrimo(int n, int i) {
        if(i == n/2)
            return false;
        if(n % i == 2)
            return false;
        if(n % i == 0)
            return true;
        return verificarPrimo(n, i+1);
    }

    public static boolean verificarPerfecto(int n, int i, int suma) {
        if(suma == n)
            return true;
        else {
            if(i <= n/2) {
                if(n % i == 0) {
                    suma += i;
                    return verificarPerfecto(n, i+1, suma);
                }
                return verificarPerfecto(n, i+1, suma);
            }
            return false;
        }
    }

    private static String recorrerLista(List<Persona> listaPrimos, int inicio, int fin, String salida) {
        if(inicio < fin) {
            salida += listaPrimos.get(inicio).getEdad()+", ";
            return recorrerLista(listaPrimos, inicio+1, fin, salida);
        }
        return salida;
    }

    private static void llenarMatriz(Persona[][] matriz) {
        for(int i = 0; i< matriz.length; i++) {
            for(int j = 0; j < matriz[i].length; j++) {
                int edad = nums[i][j];
                matriz[i][j] = new Persona(edad);
            }
        }
    }
}
