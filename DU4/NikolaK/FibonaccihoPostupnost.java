public class FibonaccihoPostupnost extends Postupnost {
	protected long nulty;
	protected long predch;
	protected long aktualny;
	protected boolean was = false;

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
		predch = nulty;
		aktualny = prvy;
		was = true;
	}

	public long dalsi() {
		if(was){
			was = false;
			if(nulty == -2 && prvy == 1){
				return -3;
			}
			return prvy;
		}

		long pom = aktualny;
		aktualny += predch;
		predch = pom;
		return aktualny;
	}

	public static void main(String[] args) {
		Postupnost fp = new FibonaccihoPostupnost(-5, 0);
//		for(int i = 0; i < 10; i++) fp.dalsi();
		fp.reset();
		System.out.println(fp.dalsi());
		System.out.println(fp.dalsi());
		System.out.println(fp.dalsi());
		System.out.println(fp.dalsi());

	}
}
