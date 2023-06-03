package backtracking;

public class NReinas {
    public static void main(String[] args) {
        int n = 4;
        char[][] tablero = new char[n][n];
        tablero = llenarTablero(tablero, 0, 0, n);
        posicionarReina(tablero, 0);
    }

    private static boolean verificarPosicion(char[][] tablero, int reina, int columna){
        for (int i = 0; i < reina; i++){
            if (tablero[i][columna] == 'R')
                return false;
        }
        // diagonal atrás arriba
        for (int i = reina, j = columna; i >= 0 && j >= 0; i--, j--){
            if (tablero[i][j] == 'R')
                return false;
        }
        // diagonal adelante arriba
        for (int i = reina, j = columna; i >= 0 && j < tablero.length; i--, j++){
            if (tablero[i][j] == 'R')
                return false;
        }
        return true;
    }

    private static void posicionarReina(char[][] tablero, int reina){
        if (reina == tablero.length){
            imprimirTablero(tablero);
            return;
        }
        for (int i = 0; i < tablero.length; i++){
            if (verificarPosicion(tablero, reina, i)){
                tablero[reina][i] = 'R';
                posicionarReina(tablero, reina + 1);
                tablero[reina][i] = '–';
            }
        }
    }

    private static void imprimirTablero(char[][] tablero){
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(tablero[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static char[][] llenarTablero(char[][] tablero, int i, int j, int n) {
        if(i < n) {
            if(j < n) {
                tablero[i][j] = '-';
                llenarTablero(tablero, i, j+1, n);
            }
            llenarTablero(tablero, i+1, 0, n);
        }
        return tablero;
    }
}
