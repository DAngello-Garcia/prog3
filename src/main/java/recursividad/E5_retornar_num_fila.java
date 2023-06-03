package recursividad;

import javax.swing.*;

public class E5_retornar_num_fila {
    /*
    Retornar el número de la fila de la matriz cuya suma de todos sus elementos sea mayor.
     */
    public static void main(String[] args) {
        int n = 3;
        int m = 3;
        int index;
        int suma = 0;
        int[][] matriz = llenarMatriz(n, m);
        index = retornarFilaMayor(matriz, 0, 0, 0, 0, suma);
        System.out.println("La fila con la mayor suma es: "+index);
    }

    private static int retornarFilaMayor(int[][] matriz, int i, int j, int mayor, int index, int suma) {
        if(i < matriz.length) {
            if(j < matriz[i].length) {
                suma += matriz[i][j];
                if(j == matriz[i].length-1) {
                    if(suma > mayor) {
                        mayor = suma;
                        index = i;
                    }
                    i++;
                    j = 0;
                    suma = 0;
                } else {
                    if(suma > mayor) {
                        mayor = suma;
                        index = i;
                    }
                    j++;
                }
                return retornarFilaMayor(matriz, i, j, mayor, index, suma);
            }
        }
        return index;
    }

    private static int[][] llenarMatriz(int n, int m) {
        int[][] matriz = new int[n][m];
        for(int i = 0; i<n; i++) {
            for(int j = 0; j < m; j++) {
                matriz[i][j] = Integer.parseInt(JOptionPane.showInputDialog("Ingrese un número en la fila "+i+" y la columna "+j));
            }
        }
        return matriz;
    }
}
