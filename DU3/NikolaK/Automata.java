import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;

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
		if(delta == null){
			return null;
		}
		int poc = 0;
		Character[] p = new Character[delta.length];
		for(int i = 0; i < delta.length; i++){
			Character ch = delta[i].getSymbol();
			if(!contains(p, ch)){
				p[i] = delta[i].getSymbol();
				poc++;
			}
		}
		Character[]res = new Character[poc];
		int index = 0;
		while(index < poc) {
			int min = 0;
			for (int i = 0; i < p.length; i++) {
				if (p[i] == null ) {
					continue;
				}
				if(p[min] == null){
					min = i;
				}
				if (p[i] < p[min]) {
					min = i;
				}
			}
			res[index] = p[min];
			p[min] = null;
			index++;
		}
		return res;


	}
	private boolean contains(Character[]p, Character c){
		for(Character i: p){
			if(i == null){
				continue;
			}
			if(i.equals(c)){
				return true;
			}
		}
		return false;
	}
	/**
	 * @return - lexikograficky usporiadanu mnozinu (neopakujucich sa) stavov nachadzajucich sa v delta funkcii 
	 */
	public String[] states() {
		if(initState == null || finalStates == null){
			return null;
		}
		int poc = 0;
		String[] p = new String[delta.length * 2];
		for(int i = 0; i < delta.length; i++){
			String from = delta[i].getFromState();
			String to = delta[i].getToState();
			if(!contains(p, from)){
				p[i] = from;
				poc++;
			}
			if(!contains(p, to)){
				p[delta.length + i] = to;
				poc++;
			}
		}
		String[]res = new String[poc];
		int index = 0;
		while(index < poc) {
			int min = 0;
			for (int i = 0; i < p.length; i++) {
				if (p[i] == null ) {
					continue;
				}
				if(p[min] == null){
					min = i;
				}
				if (p[i].compareTo(p[min]) < 0) {
					min = i;
				}
			}
			res[index] = p[min];
			p[min] = null;
			index++;
		}
		return res;

	}
	private boolean contains(String[]p, String c){
		for(String i: p){
			if(i == null){
				continue;
			}
			if(i.equals(c)){
				return true;
			}
		}
		return false;
	}
	/**
	 * @return - true, ak zodpoveda definicii konecneho automatu z prednasky UTI
	 * - pociatocny stav a vsetky koncove patria do mnoziny states()
	 * - prechodova funkcia je totalna funkcia, definovana jednoznacne pre kazdu dvojicu states() x alphabet() 
	 */	
	public boolean isCorrectFA() {
		if(initState == null || finalStates == null || delta == null){
			return false;
		}
		String[] s = states();
		Character[] a = alphabet();
		if(!contains(s, initState)){
			return false;
		}
		for(String st: finalStates){
			if(!contains(s, st)){
				return false;
			}
		}

		for(String st:s){
			for(Character at:a){
				boolean was = false;
				for(Transit t: delta){
					if(t.getSymbol().equals(at)){
						if(t.getFromState().equals(st)){
							was = true;
							break;
						}
					}
				}
				if(!was){
					return false;
				}
			}
		}
		return true; // toto doprogramujte
	}
	/**
	 * predpokladajte, ze objekt splna podmienku isCorrectFA()
	 * @param word - vstupne slovo pozostavajuce z postupnosti symbolov
	 * @return - true, ak automat slovo akceptuje, inak false
	 */
	public boolean accepts(String word) {
		if(word == null){
			return false;
		}
		String state = initState;
		for(Character ch: word.toCharArray()){
			state = getState(state, ch);
		}
		if (contains(finalStates, state)) {
			return true;
		}
		return false; // toto doprogramujte
	}
	private String getState(String state, Character ch){
		for(Transit t: delta){
			if(t.getFromState().equals(state) && t.getSymbol().equals(ch)){
				return t.getToState();
			}
		}
		return null;
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
		System.out.println(a.accepts("b"));
	}
}
