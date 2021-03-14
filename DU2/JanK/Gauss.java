public class Gauss {
    private double[][] p;    // vnutorna reprezentacia musi zostat private

    public Gauss(double[][] pole) {
        this.p = pole.clone();
        for (int i = 0; i < pole.length; i++)
            this.p[i] = pole[i].clone();
    }

    @Override
    public String toString() {
        StringBuffer res = new StringBuffer("{\n");
        for (int i = 0; i < p.length; i++) {
            res.append("\t{");
            for (int j = 0; j < p[i].length; j++) {
                res.append(p[i][j] + ((j + 1 < p[i].length) ? ", " : ""));
            }
            res.append((i + 1 < p.length) ? "},\n" : "} \n}\n");
        }
        return res.toString();
    }

    public double[] dajRiadok(int r) {
        return p[r];
    }

    //----------------------------------- nad ciarou nemodifikujte, programujte pod ciarou
    /*
     * vynasobi riadok pola cim
     */
    public void vydel(int riadok, double cim) {
        for (int i = 0; i < p[riadok].length; i++) {
            p[riadok][i] /= cim;
        }
    }

    /*
     * vymeni riadok1 a riadok2
     */
    public void vymen(int riadok1, int riadok2) {
        double[] temp = p[riadok1];
        p[riadok1] = p[riadok2];
        p[riadok2] = temp;
    }

    /*
     * k riadku riadok1 pripocitaj x-nasobok riadku riadok2
     */
    public void pripocitaj(int riadok1, double x, int riadok2) {
        double[] temp = new double[p[riadok2].length];
        System.arraycopy(p[riadok2], 0, temp, 0, p[riadok2].length);
        for (int i = 0; i < p[riadok1].length; i++) {
            p[riadok1][i] += temp[i] * x;
        }
    }

    /*
     * Gaussova eliminacna metoda pre p = Pole2D(double[M][N]...) - M riadkov N stlpcov
     * pre kazde r z intervalu [0..M-1]
     * 	ak je p[r][r] == 0, najdi nejaky riadok j z intervalu [r+1..M-1], ze v r-tom stlpci je p[j][r] != 0
     *    ak taky riadok j existuje, vymen riadky r a j,
     *    ak taky riadok j neexistuje, vysledkom je false a skonci (znamena to tazky pripad)
     * 	teraz urcite uz je p[r][r] != 0
     * 	vydel riadok r cislom p[r][r]
     * 	ku kazdemu riadku j != r pripocitaj -p[j][r] nasobok r-teho riadku
     * ked skoncis cyklus, vrat true, eliminacia sa podarila
     */
    public boolean gauss() {
        for (int r = 0; r < p.length; r++) {
            if (p[r][r] == 0) {
                boolean swaped = false;
                for (int j = r + 1; j < p.length; j++) {
                    if (p[j][r] != 0) {
                        vymen(r, j);
                        swaped = true;
                    }
                }
                if (!swaped) {
                    return false;
                }
            }
            vydel(r, p[r][r]);
            for (int k = 0; k < p.length; k++) {
                if (k == r) {
                    continue;
                }
                pripocitaj(k, -p[k][r], r);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Gauss p = new Gauss(new double[][]{
                {2, 4, -2, 6},
                {0, -3, 6, -3},
                {4, 1, -2, 2}});
        System.out.println(p.gauss());
        System.out.println(p);
    }
}
