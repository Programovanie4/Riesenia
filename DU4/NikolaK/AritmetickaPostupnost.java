public class AritmetickaPostupnost extends Postupnost{
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

}
