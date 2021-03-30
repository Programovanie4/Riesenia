public abstract class Vyraz {

	public abstract double eval(double[] interpretacia);
	
	public abstract String toString();

	public void validnaIntepretacia(double[] interpretacia) {
		if (interpretacia == null || interpretacia.length != 26) zlyVstup();
		for (double d: interpretacia) {
			if (d < 0.0 || d > 1.0) zlyVstup();
		}
	}

	/**
	 * Priklad: Vypocita pravdepodobnost ze budem meskat. Meskam ked mi nezvoni
	 * budik alebo je zapcha.
	 * @param budikZvoni pravdepodobnost ze budik zvoni ako ma
	 * @param zapcha pravdepodobnost ze je zapcha
	 * @return pravdepodobnost ze budem meskat
	 */
	public static double budemMeskat(double budikZvoni, double zapcha) {
		double[] interpretacia = new double[26];
		interpretacia['B' -'A'] = budikZvoni;  // premenna 'B'
		interpretacia['Z' -'A'] = zapcha;      // premenna 'Z'
		Vyraz x = new Or(
				new Not(new Premenna('B')),
				new Premenna('Z')
				);
		System.out.println(x);  // "(-B | Z)"
		return x.eval(interpretacia);
	}
	
	/**
	 * Vypocita pravdepodobnost ze budem mat Acko z Javy. Acko ziskam ked chodim
	 * na cvika, robim dobre ulohy a spravim dobre skusku.
	 * @param cvika ucast na cvikach
	 * @param ulohy uspesnost na ulohach
	 * @param skuska pravdepodobnost, ze spravim dobre skusku
	 * @return pravdepodobnost ze budem mat Acko z Javy
	 */
	public static double acko(double cvika, double ulohy, double skuska) {
		double[] interpretacia = new double[26];
		interpretacia['C' - 'A'] = cvika;
		interpretacia['U' - 'A'] = ulohy;
		interpretacia['S' - 'A'] = skuska;
		Vyraz x = new And(
				new And(new Premenna('C'), new Premenna('U')),
				new Premenna('S'));
		System.out.println(x);
		return x.eval(interpretacia);
	}
	
	/**
	 * Vypocita pravdepodobnost, ze nespravim predmet Prg4. Neprejdem ked
	 * nechodim na cvika, alebo ked: nerobim dobre ulohy a zaroven nespravim
	 * dobre skusku.
	 * @param cvika ucast na cvikach
	 * @param ulohy uspesnost na ulohach
	 * @param skuska pravdepodobnost, ze spravim dobre skusku
	 * @return pravdepodobnost ze nespravim predmet Prg4
	 */
	public static double nespravimJavu(double cvika, double ulohy, double skuska) {
		double[] interpretacia = new double[26];
		interpretacia['C' - 'A'] = cvika;
		interpretacia['U' - 'A'] = ulohy;
		interpretacia['S' - 'A'] = skuska;
		Vyraz x = new Or(
				new Not(new Or(new Premenna('S'), new Premenna('U'))),
				new Not(new Premenna('C')));
		System.out.println(x);
		return x.eval(interpretacia);
	}
	
	public void zlyVstup() {
		throw new IllegalArgumentException("Zly vstup!");
	}
	
	
	
	public static void main(String[] args) {
		// tu si to mozete testovat
		double budikZvoni = 0.9;
		double zapcha = 0.6;
		System.out.println("Budem mestakt na " + budemMeskat(budikZvoni, zapcha)*100 + "%");  //64%
	}

}
