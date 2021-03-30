import java.util.Stack;

public class AritmetickaPostupnost extends Postupnost {
	protected long delta;

	AritmetickaPostupnost(int _delta) {
		delta = _delta;
		prvy = 0;
	}

	AritmetickaPostupnost(int _prvy, int _delta) {
		delta = _delta;
		aktualny = prvy = _prvy;
	}

	@Override
	public void reset() {
		aktualny = prvy - delta;
	}

	public long dalsi() {
		aktualny += delta;
		return aktualny;
	}

	public long dalsiParny() {
		if (aktualny % 2 == 1 && delta == 0) return Long.MAX_VALUE;
//		long d;
//		do {
//			try {
//				d = dalsi();
//			} catch (StackOverflowError e) {
//				return Long.MAX_VALUE;
//			}
//		} while (d % 2 == 1);
		long d = dalsi();
		while (d % 2 != 0) {
			try {
				d = dalsi();
			} catch (StackOverflowError e) {
				return Long.MAX_VALUE;
			}
		}
		return d;
	}

	public static void main(String[] args) {
		Postupnost ap = new AritmetickaPostupnost(10, 3);
		for(int i = 0; i < 10; i++) System.out.print(ap.dalsi() + " ");      // vráti 13, 16, 19, 22, 25, 28, 31, 34, 37, 40
		System.out.println("\n" + ap.prvy());                                   // vráti 10
		for(int i = 0; i < 10; i++) System.out.print(ap.dalsi()+ " ");       // vráti 13, 16, 19, 22, 25, 28, 31, 34, 37, 40
		ap.reset();                                  // nevracia nič, iba resetuje na začiatok
		for(int i = 0; i < 10; i++) System.out.print(ap.dalsi()+ " ");       // vráti 10, 13, 16, 19, 22, 25, 28, 31, 34, 37
		System.out.println();
		for(int i = 0; i < 10; i++) System.out.print(ap.dalsiParny()+ " ");  // vráti 40, 46, 52, 58, 64, 70, 76, 82, 88, 94
		System.out.print("\n" +ap.dalsi());		// vráti 97
		System.out.println();
		//----------
		Postupnost ap3 = new AritmetickaPostupnost(-80,-77);
		ap3.reset();
		System.out.println(ap3.dalsiParny());
		System.out.println(ap3.dalsiParny());
		System.out.println(ap3.dalsiParny());
	}
}
