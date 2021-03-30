public interface Iterovatelne {
	public void reset();
	public long dalsi();
	public long dalsiParny();

	public static void main(String[] args) {
//		Postupnost ap = new AritmetickaPostupnost(10, 3);
//		for(int i = 0; i < 10; i++) System.out.print(ap.dalsi() + " ");  // vráti 13, 16, 19, 22, 25, 28, 31, 34, 37, 40
//		System.out.println();
//		System.out.println(ap.prvy());                           // vráti 10
//		for(int i = 0; i < 10; i++) System.out.print(ap.dalsi() + " ");      // vráti 13, 16, 19, 22, 25, 28, 31, 34, 37, 40
//		ap.reset();                                             // vráti 10
//		System.out.println();                							// nevracia nič, iba resetuje na začiatok
//		for(int i = 0; i < 10; i++) System.out.print(ap.dalsi() + " ");      // vráti 10, 13, 16, 19, 22, 25, 28, 31, 34, 37
//		for(int i = 0; i < 10; i++) System.out.print(ap.dalsiParny() + " "); // vráti 40, 46, 52, 58, 64, 70, 76, 82, 88, 94
//		System.out.println();
//		System.out.println(ap.dalsi());                                  // vráti 97

		Postupnost fp = new FibonaccihoPostupnost(0, 1);
		// Fib. postupnost = 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, ...
		System.out.println();
		for(int i = 0; i < 10; i++) System.out.print(fp.dalsi() + " ");     // vráti 1, 2, 3, 5, 8, 13, 21, 34, 55, 89
		System.out.println();
		System.out.println(fp.prvy());                                  // vráti 1
		for(int i = 0; i < 10; i++) System.out.print(fp.dalsi() + " ");     // vráti 1, 2, 3, 5, 8, 13, 21, 34, 55, 89
		System.out.println();
		fp.reset();                                 // nevracia nič, iba resetuje na začiatok
		for(int i = 0; i < 5 ; i++) System.out.print(fp.dalsi() + " ");     // vráti 1, 1, 2, 3, 5
		System.out.println();
		for(int i = 0; i < 5; i++) System.out.print(fp.dalsiParny() + " "); // vráti 8, 34, 144, 610, 2584
		System.out.println();
		System.out.println(fp.dalsi());                                 // vráti 4181
		System.out.println();
//
//		Postupnost fp2 = new FibonaccihoPostupnost(-3, 1);
//		System.out.println(fp2.dalsi());
//		System.out.println(fp2.dalsi());
//		System.out.println(fp2.dalsi());
//		System.out.println(fp2.dalsi());
//		fp2.reset();						// 1
//		System.out.println(fp2.dalsi());
//		System.out.println(fp2.dalsi());
//		System.out.println(fp2.dalsi());

		Postupnost fp2 = new FibonaccihoPostupnost(-5, 1);
		System.out.println(fp2.dalsi());
		System.out.println(fp2.dalsi());
		System.out.println(fp2.dalsi());
		System.out.println(fp2.dalsi());
		fp2.reset();						// 1
		System.out.println(fp2.dalsi());
		System.out.println(fp2.dalsi());
		System.out.println(fp2.dalsi());


//		System.out.println();
//		Postupnost fp3 = new FibonaccihoPostupnost(-5, -1);
//		System.out.println(fp3.dalsi());
//		System.out.println(fp3.dalsi());
//		System.out.println(fp3.dalsi());
//		System.out.println(fp3.dalsi());
//		fp3.reset();						// -1
//		System.out.println(fp3.dalsi());
//		System.out.println(fp3.dalsi());
//		System.out.println(fp3.dalsi());
//		System.out.println();

		System.out.println();
		Postupnost fp3 = new FibonaccihoPostupnost(1, -2);
		System.out.println(fp3.dalsi());
		System.out.println(fp3.dalsi());
		System.out.println(fp3.dalsi());
		System.out.println(fp3.dalsi());
		fp3.reset();						// -1
		System.out.println(fp3.dalsiParny());
		System.out.println(fp3.dalsi());
		System.out.println(fp3.dalsi());
		System.out.println(fp3.dalsi());
		System.out.println(fp3.dalsi());
		System.out.println();

		Postupnost fp4 = new FibonaccihoPostupnost(-3, 3);
		System.out.println(fp4.dalsi());
		System.out.println(fp4.dalsi());
		System.out.println(fp4.dalsi());
		System.out.println(fp4.dalsi());
		System.out.println(fp4.dalsi());
		fp4.reset();						// 0
		System.out.println(fp4.dalsi());
	}
}
