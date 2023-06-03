package recursividad;

public class Laberinto {

    public char[][] laberinto={
            {'#', '#', '#', '#', '#', '#'},
            {' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', '#', '#', ' ', '#'},
            {'#', ' ', ' ', '#', ' ', '#'},
            {'#', '#', '#', '#', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', '#'},
            {'#', '#', '#', '#', '#', '#'},
    };


    /* --------------------- PRUEBA DEL ALGORITMO --------------------- */
    public static void main(String[] args) {
        Laberinto m = new Laberinto();      // construimos un objeto de la clase Laberinto por defecto
        m.laberinto[1][0] = 'X';            // introducimos en este caso, la salida (X) en las coordenadas (1,1)
        m.resuelve(5, 1);              // ahora, introducimos la entrada (S) en las coordenadas (8,1) y llamamos al algoritmo
        String salida = "";
        System.out.println(m.imprimirLaberinto(0, 0, salida));
    }




    /* ----------------- IMPLEMENTACIÓN DEL ALGORITMO ----------------- */
    public void resuelve(int x, int y){ 				// permite introducir unas coordenadas (x, y)
        if (paso(x, y)) { 								// intentará resolver el laberinto en estas coordenadas
            laberinto[x][y] = 'S'; 						// introduce en las coordenadas (x, y) la entrada
        }
    }

    private boolean paso(int x, int y)
    {
        if(x<0 || x==laberinto.length-1 || y<0 || y ==laberinto[x].length-1)
            return false;

        if (laberinto[x][y]=='X'){ // si hemos llegado Laberinto X quiere decir que hemos encontrado solución
            return true; // luego, el algoritmo termina
        }

        if (laberinto[x][y]=='#'||laberinto[x][y]=='*' ||laberinto[x][y]=='f') { // si llegamos Laberinto una pared o al mismo punto,
            return false; // entonces el laberinto no puede resolverse y termina.
        }

        // si no se cumple ninguna de estas dos situaciones, quiere decir que nos encontramos en un
        // caso intermedio, por lo tanto, que empezamos Laberinto recorrer o todavía no hemos llegado Laberinto nada
        laberinto[x][y]='*'; // marcamos la posición como visitada (si es la primera, en las coordenadas x e y

        boolean result; // se coloca S de START)



        result=paso(x+1, y); // intentamos ir hacia ABAJO. Cuarta llamada recursiva
        if (result) return true; // si el resultado es el final, entonces el algoritmo termina

        result=paso(x, y+1); // intentamos ir hacia la DERECHA. Primera llamada recursiva
        if (result)return true; // si el resultado es el final, entonces el algoritmo termina

        result=paso(x-1, y); // intentamos ir hacia ARRIBA. Segunda llamada recursiva
        if (result) return true; // si el resultado es el final, entonces el algoritmo termina

        result=paso(x, y-1); // intentamos ir hacia la IZQUIERDA. Tercera llamada recursiva
        if (result) return true; // si el resultado es el final, entonces el algoritmo termina

        // si no hemos encontrado la solución en estos cuatros movimientos, volvemos atrás, aunque hay que
        // considerar que en este punto, todas las llamadas recursivas han finalizado. Si en ninguna de ellas
        // se ha conseguido el éxito, entonces el algoritmo termina y devuelve false.
        laberinto[x][y]='f'; // en el caso de no ser el resultado, se marca con +. Ya fue pisado
        return false; // vuelta atrás si la solución no se encuentra aquí


    }

    private String imprimirLaberinto(int fila, int col, String salida) { // imprimiremos nuestra solución. Debido Laberinto que la clase Arrays no tiene implementado
        if(fila < laberinto.length) {
            if(col < laberinto[fila].length) {
                salida += laberinto[fila][col] + " ";
                col++;
                return imprimirLaberinto(fila, col, salida);
            }
            salida += "\n";
            fila++;
            col = 0;
            return imprimirLaberinto(fila, col, salida);
        }
        return salida;
    }

}
