package recursividad;

import javax.swing.*;

public class E1_sumar_diagonal {
    public static void main(String[] args) {
        int n = 3;
        int suma = 0;
        int[][] matriz = llenarMatriz(n);
        suma = sumarDiagonalMatriz(matriz, n-1, 0);
        System.out.println("La suma de la diagonal es: "+suma);
    }

    private static int sumarDiagonalMatriz(int[][] matriz, int i, int j) {
        if(i >= 0) {
            if(j < matriz.length) {
                if(i == j) {
                    return sumarDiagonalMatriz(matriz, i-1, 0) + matriz[i][j];
                } else {
                    return sumarDiagonalMatriz(matriz, i, j+1);
                }
            }
        }
        return 0;
    }

    private static int[][] llenarMatriz(int n) {
        int[][] matriz = new int[n][n];
        for(int i = 0; i<n; i++) {
            for(int j = 0; j < n; j++) {
                matriz[i][j] = Integer.parseInt(JOptionPane.showInputDialog("Ingrese un número en la fila "+i+" y la columna "+j));
            }
        }
        return matriz;
    }
}
