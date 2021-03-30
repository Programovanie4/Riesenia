abstract class Postupnost implements Iterovatelne {
	protected long prvy;
	protected long aktualny;

	public long prvy() {
		aktualny = prvy;
		return aktualny;
	}


	public abstract long dalsi();

	public long dalsiParny() {
		long item = dalsi();
		while (item % 2 != 0) {
			try {
				item = dalsi();
			} catch (NumberFormatException e) {
				return Long.MAX_VALUE;
			}
		}
		return item;
	}

	public void printPostupnost(int n) {
		System.out.print(prvy());
		for(int i = 0; i < n; i++){
			System.out.print(", " + dalsi());
		}
		System.out.println();
	}
}
