public class Kruh extends Utvar {
	Bod stred;
	double r;

	public Kruh(Bod stred, double r) {
		this.stred = stred;
		this.r = r;
	}

	@Override
	double obsah() {
		return Math.PI * r * r;
	}

	@Override
	double obvod() {
		return 2 * Math.PI * r;
	}

	@Override
	boolean obsahuje(Bod p) {
		return vzdialenostBodov(p, stred) < r;
	}
}
