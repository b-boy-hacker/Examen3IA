package exa3;

import java.util.LinkedList;

public class Reina {
    
    public static int vueltas; 

    public static LinkedList<Regla> reglasAplicables(int[][] m, int i) {
        LinkedList<Regla> L1 = new LinkedList();

        for (int j = 0; j < m[i].length; j++) {
            if (posicionDisponible(m, i, j)) {
                L1.add(new Regla(i, j));
            }

        }
        return L1;
    }
    public static long counter = 0;

    public static boolean nReinas(int[][] m, int paso) {
        if (paso > m.length) {
            return true;
        }

        LinkedList L1 = reglasAplicables(m, paso - 1);

        while (!L1.isEmpty()) {
            Regla R = elegirRegla(L1);
//            Regla R = elegirReglaH(L1);
            m[R.fil][R.col] = paso;
            if (nReinas(m, paso + 1)) {
                return true;
            }
            
            m[R.fil][R.col] = 0;
            vueltas++;
        }
        return false;
    }

    public static Regla elegirRegla(LinkedList<Regla> L1) {
        return L1.removeFirst();
    }

    public static Regla elegirReglaH(LinkedList<Regla> L1) {
        return L1.remove(L1.size() / 2);
    }

    public static boolean diagSupDer(int m[][], int i1, int j1) {
        int i = i1, j = j1;
        while (i >= 0 && j < m[i1].length) {
            if (m[i][j] != 0) {
                return false;
            }
            i = i - 1;
            j = j + 1;
        }
        return true;
    }

    public static boolean diagSupIzq(int m[][], int i1, int j1) {
        int i = i1, j = j1;
        while (i >= 0 && j >= 0) {
            if (m[i][j] != 0) {
                return false;
            }
            i = i - 1;
            j = j - 1;
        }
        return true;
    }

    public static boolean diagInfIzq(int m[][], int i1, int j1) {
        int i = i1, j = j1;
        while (i < m.length && j >= 0) {
            if (m[i][j] != 0) {
                return false;
            }
            i = i + 1;
            j = j - 1;
        }
        return true;
    }

    public static boolean diagInfDer(int m[][], int i1, int j1) {
        int i = i1, j = j1;
        while (i < m.length && j < m[i].length) {
            if (m[i][j] != 0) {
                return false;
            }
            i = i + 1;
            j = j + 1;
        }
        return true;
    }

    private static boolean posicionDisponible(int[][] m, int i, int j) {
        return filValido(m, i) && colValido(m, j)
                && diagSupIzq(m, i, j) && diagSupDer(m, i, j)
                && diagInfIzq(m, i, j) && diagInfDer(m, i, j);

    }

    private static boolean filValido(int[][] m, int i) {
        int j = 0;
        while (j < m.length) {
            if (m[i][j] != 0) {
                return false;
            }
            j++;
        }
        return true;

    }

    private static boolean colValido(int[][] m, int j) {
        int i = 0;
        while (i < m.length) {
            if (m[i][j] != 0) {
                return false;
            }
            i++;
        }
        return true;

    }

    private static void mostrar(int[][] m) {
        for (int[] fila : m) {
            for (int celda : fila) {
                System.out.print(celda + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int m[][] = new int[9][9];        
        if (nReinas(m, 1)) {
            mostrar(m);
            System.out.println("Hay solucion");
            System.out.println("vueltas: " + vueltas); 
        } else {
            System.out.println("No hay solucion");
        }
    }
}
