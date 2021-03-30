public class FibonaccihoPostupnost extends Postupnost {
	protected long nulty;
	protected long predch;

	FibonaccihoPostupnost(long _nulty, long _prvy) {
		predch = nulty = _nulty;
		aktualny = prvy = _prvy;
	}
	
	public long prvy() {
		predch = nulty;
		aktualny = prvy;
		return prvy;
	}

	@Override
	public void reset() {
		predch = prvy - nulty;
		aktualny = nulty;
	}

	public long dalsi() {
		long pom = aktualny;
		aktualny += predch;
		predch = pom;
		return aktualny; // return aktualny;
	}

	public static void main(String[] args) {
		Postupnost fp = new FibonaccihoPostupnost(0, 1);
// Fib. postupnost = 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, ...
		for(int i = 0; i < 10; i++) System.out.print(fp.dalsi() + " ");     // vráti 1, 2, 3, 5, 8, 13, 21, 34, 55, 89
		System.out.println((fp.prvy()));                                  // vráti 1
		for(int i = 0; i < 10; i++) fp.dalsi();     // vráti 1, 2, 3, 5, 8, 13, 21, 34, 55, 89
		fp.reset();
		for(int i = 0; i < 5 ; i++) {
			System.out.print(fp.dalsi() + " ");  				// vráti 1, 1, 2, 3, 5
		}
		System.out.println();
		for(int i = 0; i < 5; i++) System.out.print(fp.dalsiParny() + " "); // vráti 8, 34, 144, 610, 2584
		System.out.println(fp.dalsi());                                 // vráti 4181


		Postupnost fp1 = new FibonaccihoPostupnost(-1,4);
		fp1.reset();
		for (int i = 0; i < 5; i++) {
			System.out.print(fp1.dalsi() + " ");
		}
		System.out.println();
	}
}
