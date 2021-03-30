abstract class Postupnost implements Iterovatelne {
	protected long prvy;
	protected long aktualny;

	public long prvy() {
		aktualny = prvy;
		return aktualny;
	}

	public abstract long dalsi();

	public void printPostupnost(int n) {
		System.out.print(prvy());
		for(int i = 0; i < n; i++){
			System.out.print(", " + dalsi());
		}
		System.out.println();
	}

	@Override
	public long dalsiParny() {
		long pom = dalsi();
		while (pom % 2 != 0){
			pom = dalsi();
		}
		return pom;
	}
}
