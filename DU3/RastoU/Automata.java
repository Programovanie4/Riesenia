import java.util.Arrays;
import java.util.TreeSet;

public class Automata {
	private String initState;
	private Transit[] delta;
	private String[] finalStates;
	
	public Automata(String initState, Transit[] delta, String[] finalStates) {
		super();
		this.initState = initState;
		this.finalStates = finalStates;
		this.delta = delta;
	}
	/**
	 * @return - lexikograficky usporiadanu mnozinu (neopakujucich sa) symbolov 
	 * 			 nachadzajucich sa v delta funkcii 
	 */
	public Character[] alphabet() {
		if (delta == null) return null;
		TreeSet<Character> ts = new TreeSet<>();
		for (Transit t : delta) {
			ts.add(t.getSymbol());
		}
		Character[] ret = new Character[ts.size()];
		ts.toArray(ret);
		Arrays.sort(ret);
		return ret;
	}
	/**
	 * @return - lexikograficky usporiadanu mnozinu (neopakujucich sa) stavov nachadzajucich sa v delta funkcii 
	 */
	public String[] states() {
		if (delta == null) return null;
		TreeSet<String> ts = new TreeSet<>();
		for (Transit t : delta) {
			ts.add(t.getFromState());
			ts.add(t.getToState());
		}
		String[] ret = new String[ts.size()];
		ts.toArray(ret);
		Arrays.sort(ret, String.CASE_INSENSITIVE_ORDER);
		return ret;
	}
	/**
	 * @return - true, ak zodpoveda definicii konecneho automatu z prednasky UTI
	 * - pociatocny stav a vsetky koncove patria do mnoziny states()
	 * - prechodova funkcia je totalna funkcia, definovana jednoznacne pre kazdu dvojicu states() x alphabet() 
	 */	
	public boolean isCorrectFA() {
		String[] allStates = states();
		Character[] wholeAlphabet = alphabet();
		if (allStates == null || wholeAlphabet == null || delta == null || initState == null
				|| finalStates == null || Arrays.binarySearch(allStates, initState) < 0 ) {
			return false;
		}

		for (String s : finalStates) {
			if (Arrays.binarySearch(allStates, s) < 0) return false;
		}

		for (int i = 0; i < allStates.length; i++) {
			for (Character c : wholeAlphabet) {
				boolean found = false;
				for (int j = 0; j < allStates.length; j++) {
					Transit t = new Transit(allStates[i], c, allStates[j]);
					if (hasTransit(t)) {
						if (found) return false;
						found = true;
					}
				}
				if (!found) return false;
			}
		}
		return true;
	}
	public boolean hasTransit(Transit t) {
		for (Transit d : delta) {
			if
			(
					d.getSymbol().equals(t.getSymbol()) &&
					d.getFromState().equals(t.getFromState()) &&
					d.getToState().equals(t.getToState())
			) {
				return true;
			}
		}
		return false;
	}
	/**
	 * predpokladajte, ze objekt splna podmienku isCorrectFA()
	 * @param word - vstupne slovo pozostavajuce z postupnosti symbolov
	 * @return - true, ak automat slovo akceptuje, inak false
	 */
	public boolean accepts(String word) {
		String state = initState;
		Character[] alpha = alphabet();
		if (word != null) {
			for (int i = 0; i < word.length(); i++) {
				if (Arrays.binarySearch(alpha, word.charAt(i)) == -1) return false;
				state = giveToState(state, word.charAt(i));
			}
			for (String s : finalStates) {
				if (s.equals(state)) return true;
			}
		}
		return false;
	}

	public String giveToState(String fromState, Character c) {
		for (Transit t : delta) {
			if (t.getFromState().equals(fromState) && t.getSymbol().equals(c))
				return t.getToState();
		}
		return "";
	}
	/**
	 * @return konecny automat akceptujuci binarne slova z 0 a 1 predstavujuce binarny zapis (v dvojkovej sustave) prvocisla < 256
	 */
	public static Automata prvocisla256() {
		return new Automata(null,  null,  null); // toto doprogramujte, ak riesite premiu
	}

	public static void main(String[] args) {
		Automata a = new Automata(
					"q0",			// pociatocny stav
					new Transit[]{	// delta
							new Transit("q0", 'a', "q1"),
							new Transit("q0", 'b', "q0"),
							new Transit("q1", 'a', "q1"),
							new Transit("q1", 'b', "q1")
							},
					new String[]{"q1"});// koncove stavy
		System.out.println(Arrays.asList(a.alphabet()));
		System.out.println(Arrays.asList(a.states()));
		System.out.println(a.isCorrectFA());
		System.out.println(a.accepts("a"));
	}
}
