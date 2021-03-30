public class FibonaccihoPostupnost extends Postupnost {
	protected long nulty;
	protected long predch;

	FibonaccihoPostupnost(long _nulty, long _prvy) {
		predch = nulty = _nulty;
		aktualny = prvy = _prvy;
	}

	public void printPostupnost(int n) {
		super.printPostupnost(n);
	}

	@Override
	public long prvy() {
		predch = nulty;
		aktualny = prvy;
		return prvy;
	}

	@Override
	public long dalsi() {
		long pom = aktualny;
		aktualny += predch;
		predch = pom;
		return aktualny;
	}

	@Override
	public void reset() {
		predch = prvy - nulty;
		aktualny = nulty;
	}

	@Override
	public long dalsiParny() {
		do { dalsi(); } while (aktualny % 2 != 0);
		return aktualny;
	}

}
