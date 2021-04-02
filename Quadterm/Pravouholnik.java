public class Pravouholnik extends Utvar {
	Bod lavyHorny;
	double dlzkaX, dlzkaY;

	public Pravouholnik(Bod lh, double sx, double sy) {
		lavyHorny = lh;
		dlzkaX = sx;
		dlzkaY = sy;
	}

	@Override
	double obsah() {
		return ((dlzkaX - lavyHorny.getX()) * (dlzkaY - lavyHorny.getY()));
	}

	@Override
	double obvod() {
		return (2 * (dlzkaX - lavyHorny.getX()) + 2 * (dlzkaY - lavyHorny.getY()));
	}

	@Override
	boolean obsahuje(Bod p) {
		return ((lavyHorny.getX() <= p.getX() && p.getX() <= lavyHorny.getX() + dlzkaX)
				&& (lavyHorny.getY() <= p.getY() && p.getY() <= lavyHorny.getY() + dlzkaY));
	}
}
