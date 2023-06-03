package recursividad;

import javax.swing.*;

public class E3_sumar_matriz {
    public static void main(String[] args) {
        int n = 2;
        int m = 3;
        int suma = 0;
        int[][] matriz = llenarMatriz(n, m);
        suma = sumarMatriz(matriz, n-1, m-1);
        System.out.println("Suma: "+suma);
    }

    private static int sumarMatriz(int[][] matriz, int i, int j) {
        if(i >= 0) {
            if(j >= 0) {
               return sumarMatriz(matriz, i, j-1) + matriz[i][j];
            }
            return sumarMatriz(matriz, i-1, matriz[0].length-1);
        }
        return 0;
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
