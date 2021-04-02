abstract class Utvar {
	abstract double obsah();
	abstract double obvod();
	abstract boolean obsahuje(Bod p);

	double vzdialenostBodov(Bod p, Bod q) {
		double xNaDruhu = (p.getX() - q.getX()) * (p.getX() - q.getX());
		double yNaDruhu = (p.getY() - q.getY()) * (p.getY() - q.getY());
		return Math.sqrt(xNaDruhu + yNaDruhu);
	}
}
