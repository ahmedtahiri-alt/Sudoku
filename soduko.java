import java.util.Scanner;

public class soduko {

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean partidaAcabada = false;
        int fila = 0;

        int[][] tauler = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},

            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},

            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        boolean[][] esPistaInicial = inicialitzarPistes(tauler);

        do {
            imprimirTauler(tauler);

            System.out.print("Entra la fila (0-8 o -1 per sortir): ");
            fila = sc.nextInt();

            if (fila == -1) {
                System.out.println("Has sortit del joc. Fins aviat!");
            } else if (fila < 0 || fila > 8) {
                System.out.println("ERROR!! La fila ha de ser entre 0 i 8.");
            } else {
                System.out.print("Entra la columna (0-8): ");
                int columna = sc.nextInt();

                if (columna < 0 || columna > 8) {
                    System.out.println("ERROR!! La columna ha de ser entre 0 i 8.");
                } else if (esPistaInicial[fila][columna]) {
                    System.out.println("Error: Aquesta casella és una pista inicial i no es pot modificar!");
                } else {
                    System.out.print("Entra el número (1-9): ");
                    int num = sc.nextInt();

                    if (num < 1 || num > 9) {
                        System.out.println("ERROR!! El número ha de ser entre 1 i 9.");
                    } else {
                        System.out.println("Dades correctes per validar moviment: Casella (" + fila + "," + columna + ") = " + num);

                        if (!esMovimentValid(tauler, fila, columna, num)) {
                            System.out.println("MOVIMENT INVÀLID: El número " + num + " ja és present a la fila, columna o bloc 3x3.");
                        } else {
                            tauler[fila][columna] = num;
                            System.out.println("Moviment acceptat!");
  
  
  
  
  
                            if (!quedenZeros(tauler)) {
                                partidaAcabada = true;
                                System.out.println("\nFelicitats! Has resolt el Sudoku!");
                            }
                        }
                    }
                }
            }
        } while (fila != -1 && !partidaAcabada);
    }

    public static boolean[][] inicialitzarPistes(int[][] tauler) {
        boolean[][] pistes = new boolean[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (tauler[i][j] != 0) {
                    pistes[i][j] = true;
                }
            }
        }
        return pistes;
    }

    public static void imprimirTauler(int[][] tauler) {
        for (int i = 0; i < 9; i++) {
            if (i > 0 && i % 3 == 0) {
                System.out.println("------+-------+------");
            }
            for (int j = 0; j < 9; j++) {
                if (j > 0 && j % 3 == 0) {
                    System.out.print("| ");
                }
                System.out.print(tauler[i][j] + " ");
            }
            System.out.println();
        }
		
		
    }

public static boolean esMovimentValid(int[][] tauler, int fila, int columna, int num) {
    int iniciFila = (fila / 3) * 3;
    int iniciColumna = (columna / 3) * 3;

    for (int i = 0; i < 9; i++) {
        if (tauler[fila][i] == num || 
            tauler[i][columna] == num || 
            tauler[iniciFila + i / 3][iniciColumna + i % 3] == num) {
            return false;
        }
    }

    return true;
}

    public static boolean quedenZeros(int[][] tauler) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (tauler[i][j] == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}