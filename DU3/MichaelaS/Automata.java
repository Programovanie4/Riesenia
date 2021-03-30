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

	public Character[] alphabet() {
		if (delta == null) { return null; }
		TreeSet<Character> alphabet = new TreeSet<>();
		for (Transit t: delta) {
			alphabet.add(t.getSymbol());
		}
		return alphabet.toArray(new Character[0]);
	}

	public String[] states() {
		if (delta == null) { return null; }
		TreeSet<String> states = new TreeSet<>();
		for (Transit t: delta) {
			states.add(t.getFromState());
		}
		return states.toArray(new String[0]);
	}

	public boolean isCorrectFA() {
		if (initState == null || finalStates == null || delta == null) { return false; }
		Character[] alphabet = alphabet();
		String[] states = states();

		if (states == null || alphabet == null || Arrays.binarySearch(states, initState) < 0) { return false; }
		for (String state: finalStates) {
			if (Arrays.binarySearch(states, state) < 0) { return false; }
		}
		for (String state: states) {
			for (Character ch: alphabet) {
				if (next_state(ch, state) == null) { return false; }
			}
		}
		return true;
	}

	private String next_state(Character ch, String state) {
		for (Transit t: delta) {
			if (t.getFromState().equals(state) && t.getSymbol() == ch) {
				return t.getToState();
			}
		}
		return null;
	}

	public boolean accepts(String word) {
		if (word == null) { return false; }
		String current_state = initState;
		for (char ch: word.toCharArray()) {
			current_state = next_state(ch, current_state);
			if (current_state == null) { return false; }
		}
		return Arrays.binarySearch(finalStates, current_state) >= 0;
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
