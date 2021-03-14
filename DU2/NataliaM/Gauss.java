public class Gauss {
	private double[][] p;	// vnutorna reprezentacia musi zostat private
	public Gauss(double[][] pole) {
		this.p = pole.clone();
		for(int i = 0; i < pole.length; i++)
			this.p[i] = pole[i].clone();
	}
	@Override
	public String toString() {
		StringBuffer res = new StringBuffer("{\n");
		for(int i = 0; i < p.length; i++) {
			res.append("\t{");
			for(int j = 0; j < p[i].length; j++) {
				res.append(p[i][j] + ((j+1 <p[i].length)?", ":""));
			}
			res.append((i+1 < p.length)?"},\n":"} \n}\n"); 
		}
		return res.toString(); 
	}
	public double[] dajRiadok(int r) {
		return p[r];
	}
	//----------------------------------- nad ciarou nemodifikujte, programujte pod ciarou

	public void vydel(int riadok, double cim) {
		for (int i = 0; i < p[riadok].length; i++){
			p[riadok][i] /= cim;
		}
	}
	/*
	 * vymeni riadok1 a riadok2
	 */
	public void vymen(int riadok1, int riadok2) {
		double[] docaska = p[riadok1];					// docitala som sa ze clone() ni je moc vyhodne
		p[riadok1] = p[riadok2];
		p[riadok2] = docaska;
	}

	public void pripocitaj(int riadok1, double x, int riadok2) {
		for (int i = 0; i < p[riadok1].length; i++){
			p[riadok1][i] += p[riadok2][i] * x;
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

	boolean najblizsie(int r){				// najpblizsi riadok kde prvok r ni je 0
		boolean v = false;
		for (int j = r+1; j < p.length; j++){
			if(p[j][r] != 0){
				vymen(r, j);
				return true;
			}
		}
		return false;
	}

	void pripocitaj2(int r){				// len na sprehladnenie algoritmu :)
		for (int j = 0; j < p.length; j++){
			if (j != r){
				pripocitaj(j, -p[j][r], r);
			}
		}
	}

	public boolean gauss() {
		for (int r = 0; r < p.length; r++){
			if (p[r][r] == 0){
				if(!najblizsie(r)) return false;
			}
			vydel(r, p[r][r]);		//  rr uz urcite nie je 0
			pripocitaj2(r);
		}
		return true;
	}

	public static void main(String[] args) {
		Gauss p = new Gauss(new double[][]{
			{2, 4, -2, 6},
			{0, -3, 6, -3},
			{4, 1, -2, 2} });
		System.out.println(p.gauss());
		System.out.println(p);
	}
}
