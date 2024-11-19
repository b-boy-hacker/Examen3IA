package exa3;

import java.util.LinkedList;

public class Rey {

    public static int vueltas;

    public static boolean backtrackLaberintoRey(int m[][], int i, int j, int ifin, int jfin, int paso) {
        m[i][j] = paso;
        if (i == ifin && j == jfin) {
            return true;
        }
        LinkedList<Regla> L1 = reglasAplicablesRey(m, i, j);
       
        while (!L1.isEmpty()) {
            Regla r = L1.removeFirst();
//            Regla r = elegirMejorRegla(m, L1, ifin, jfin);
            m[r.fil][r.col] = paso;
            vueltas++;
            if (backtrackLaberintoRey(m, r.fil, r.col, ifin, jfin, paso + 1)) {
                return true;
            }

            m[r.fil][r.col] = 0;

        }
        return false;

    }

    private static double distancia(double x1, double y1, double x2, double y2) {
        return (Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)));
    }

    public static Regla elegirMejorRegla(int[][] m, LinkedList<Regla> L1, int ifin, int jfin) {
        int i = 0;
        double distMen = Double.MAX_VALUE;
        int posRegla = 0;
        while (i < L1.size()) {
            double dist = distancia(L1.get(i).fil, L1.get(i).col, ifin, jfin);
            if (dist < distMen) {
                distMen = dist;
                posRegla = i;
            }
            i++;
        }
        return (L1.remove(posRegla));
    }

    private static LinkedList<Regla> reglasAplicablesRey(int[][] m, int i, int j) {
        LinkedList<Regla> l = new LinkedList();
        if (posValida(m, i, j - 1)) {
            l.add(new Regla(i, j - 1));
        }
        if (posValida(m, i - 1, j - 1)) {
            l.add(new Regla(i - 1, j - 1));
        }
        if (posValida(m, i - 1, j)) {
            l.add(new Regla(i - 1, j));
        }
        if (posValida(m, i - 1, j + 1)) {
            l.add(new Regla(i - 1, j + 1));
        }
        if (posValida(m, i, j + 1)) {
            l.add(new Regla(i, j + 1));
        }
        if (posValida(m, i + 1, j + 1)) {
            l.add(new Regla(i + 1, j + 1));
        }
        if (posValida(m, i + 1, j)) {
            l.add(new Regla(i + 1, j));
        }
        if (posValida(m, i + 1, j - 1)) {
            l.add(new Regla(i + 1, j - 1));
        }

        return l;
    }

    private static boolean posValida(int[][] m, int i, int j) {
        return (i >= 0 && i < m.length) && (j >= 0 && j < m[i].length) && (m[i][j] == 0);
    }

    public static void mostrar(int m[][]) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                System.out.print(m[i][j] + "\t");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        int n = 5;
        int m[][] = new int[n][n];
        if (backtrackLaberintoRey(m, 0, 0, n - 1, n - 1, 1)) {
            mostrar(m);
            System.out.println("El numero de vueltas es " + vueltas);
        }
    }
}
