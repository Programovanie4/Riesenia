public abstract class Vyraz {
	
	public abstract double eval(double[] interpretacia);
	
	public abstract String toString();

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
		interpretacia['C' -'A'] = cvika;
		interpretacia['U' -'A'] = ulohy;
		interpretacia['S' -'A'] = skuska;

		Vyraz x = new And(
				new And(
					new Premenna('C'),
					new Premenna('U')
				),
				new Premenna('S'));

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
		interpretacia['C' -'A'] = cvika;
		interpretacia['U' -'A'] = ulohy;
		interpretacia['S' -'A'] = skuska;

		Vyraz x = new Or(
				new And(
						new Not(new Premenna('U')),
						new Not(new Premenna('S'))
				),
				new Not(new Premenna('C')));

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
		System.out.println(acko(0.9,0.9,0.9));
		Vyraz x = new And(
				new And(
						new Premenna('X'),
						new Premenna('Y')
				),
				new Premenna('Z'));
		double[] interpretacia = new double[26];
		interpretacia['X' -'A'] = 0.9;
		interpretacia['Y' -'A'] = 0.9;
		interpretacia['Z' -'A'] = 0.9;
		System.out.println(x.eval(interpretacia));
	}

}
