
public class Trojuholnik extends Utvar {
	Bod a, b, c;

	public Trojuholnik(Bod a, Bod b, Bod c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	@Override
	double obsah() {
		double s = obvod() / 2;
		double stranaA = vzdialenostBodov(c, b);
		double stranaB = vzdialenostBodov(a, c);
		double stranaC = vzdialenostBodov(a, b);
		return Math.sqrt(s * (s - stranaA) * (s - stranaB) * (s - stranaC));
	}

	@Override
	double obvod() {
		return vzdialenostBodov(c, b) + vzdialenostBodov(a, c) + vzdialenostBodov(a, b);
	}

	@Override
	boolean obsahuje(Bod p) {
		Trojuholnik abp = new Trojuholnik(a, b, p);
		Trojuholnik apc = new Trojuholnik(a, p, c);
		Trojuholnik bpc = new Trojuholnik(b, p, c);
		double sucet = abp.obsah() + apc.obsah() + bpc.obsah();
		return (Math.abs(obsah() - sucet) < 0.001);
    }
}
