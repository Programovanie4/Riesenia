import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

//FOR FREE

public class NFA<S,A> {
    private Set<S> states;          // mnozina stavov
    private S initState;            // pociatocny stav
    private Set<A> alphabet;        // abeceda symbolov slova
    private Set<S> finalStates;     // mnozina koncovych/akceptujucich stavov
    private Map<S, Map<A,Set<S>>> delta; // delta funkcia, zobrazuje stav a pismenko na mnozinu novych stavov

    public NFA(Set<S> states, S initState, Set<A> alphabet, Set<S> finalStates, Map<S, Map<A, Set<S>>> delta) {
        this.states = states;
        this.initState = initState;
        this.alphabet = alphabet;
        this.finalStates = finalStates;
        this.delta = delta;
    }

    @Override
    public String toString() {
        return "NFA{" +
                "states=" + states +
                ", initState=" + initState +
                ", alphabet=" + alphabet +
                ", finalStates=" + finalStates +
                ", delta=" + delta +
                '}';
    }

    public Set<S> getStates() {
        return states;
    }

    public S getInitState() {
        return initState;
    }

    public Set<A> getAlphabet() {
        return alphabet;
    }

    public Set<S> getFinalStates() {
        return finalStates;
    }

    public Map<S, Map<A, Set<S>>> getDelta() {
        return delta;
    }
    // ----------------------------------- tu zacinate kodit
    public boolean accept(List<A> word) {
//        List<S> kam_som_sa_dostala = new ArrayList<>();
//        kam_som_sa_dostala.add(initState);
//        List<S> nove = new ArrayList<>();
//        AtomicBoolean b = new AtomicBoolean(true);
//        word.stream().forEach(v -> {
//            if(kam_som_sa_dostala.size()==0) b.set(false);
//            else{
//            kam_som_sa_dostala.stream().forEach(s0->{
//                if(delta.get(s0).get(v)==null || delta.get(s0).get(v).size()==0) b.set(false);
//                else {
//                    delta.get(s0).get(v).stream().forEach(s1 -> {
//                        nove.add(s1);
//                    });
//                }
//            });
//            kam_som_sa_dostala.removeAll(kam_som_sa_dostala);
//            kam_som_sa_dostala.addAll(nove);
//            nove.removeAll(kam_som_sa_dostala);
//            }
//        });
//        if(b.get()==false) return false;
//
//        AtomicBoolean bol = new AtomicBoolean(false);
//        kam_som_sa_dostala.stream().forEach(s -> {
//            if(finalStates.contains(s)) bol.set(true);}); // toto doprogramujte
//        return bol.get();
        return language(word.size()).contains(word);
    }
    public Set<List<A>> language(int len) {
        Set<List<A>> vysl = new HashSet<>();
        if(finalStates.contains(initState)) vysl.add(List.of());

        Map<S, Set<List<A>>>  kam_som_sa_dostala = new HashMap<>();
        kam_som_sa_dostala.put(initState, new HashSet<>());
        kam_som_sa_dostala.get(initState).add(new ArrayList<>());

        Map<S, Set<List<A>>> novy = new HashMap<>();
        IntStream.range(0, len).forEach(i -> {
                kam_som_sa_dostala.keySet().forEach(s0->{
                    kam_som_sa_dostala.get(s0).stream().forEach(slovo->{

                        alphabet.stream().forEach(a -> {
                            if (delta.get(s0).get(a) != null && delta.get(s0).get(a).size() != 0) {
                                delta.get(s0).get(a).stream().forEach(s1 -> {
                                    List<A> n = new ArrayList<>();
                                    n.addAll(slovo);
                                    n.add(a);
                                    novy.putIfAbsent(s1, new HashSet<>());
                                    novy.get(s1).add(n);
                                    if (finalStates.contains(s1)) vysl.add(n);
                                });
                            }
                        });
                });
            });
                kam_som_sa_dostala.clear();
                kam_som_sa_dostala.putAll(novy);
                novy.clear();
        });
        return vysl; // toto doprogramujte
    }
    public static <T> Set<Set<T>> powerSet(Set<T> s) {
        List<T> p = new ArrayList<>(s);
        Set<Set<T>> vysl = new HashSet<>();
        if(s.size()==0) {
            vysl.add(new HashSet<>());
            return vysl;
        }
        IntStream.range(0, (int) Math.pow(2, s.size())).forEach(i -> {
            String ktore = "0".repeat(s.size()-Integer.toBinaryString(i).length()) + Integer.toBinaryString(i);
            Set<T> pom = new HashSet<>();
            IntStream.range(0, s.size()).forEach(j->{
                if(ktore.charAt(j) == '1') pom.add(p.get(j));
            });
            vysl.add(pom);
        }); // toto doprogramujte
        return vysl;
    }
    /*
      konverzia NFA na DFA
     */
    public DFA<Set<S>,A> toDFA() {
        Set<Set<S>> stavy = powerSet(states);
        Set<S> init = Set.of(initState);
        Set<A> abeceda = alphabet;
        Set<Set<S>> finale = new HashSet<>();
        stavy.stream().forEach(mnozinka ->mnozinka.stream().forEach(s0->{
            if(finalStates.contains(s0)) finale.add(mnozinka);
        }));
        Map<Set<S>, Map<A, Set<S>>> d = new HashMap<>();
        stavy.stream().forEach(stav -> abeceda.stream().forEach(a ->{
            Set<S> nov = new HashSet<>();
            stav.stream().forEach(s0->{
                if(delta.get(s0).get(a)!= null && delta.get(s0).get(a).size()!= 0 ){
                    nov.addAll(delta.get(s0).get(a));
                }
            });
            d.putIfAbsent(stav, new HashMap<>());
            d.get(stav).put(a, nov);
        }));
        return new DFA<>(stavy, init, abeceda, finale, d); // toto doprogramujte
    }
    public static void main(String[] args) {
        System.out.println(powerSet(Set.of("a", "b", "c")));
        System.out.println(powerSet(Set.of()));
        var nfa = new NFA<>(
                Set.of("a", "b", "c"),
                "a",
                Set.of('0', '1'),
                Set.of("c"),
                Map.of("a", Map.of(
                                '0', Set.of("a","b"),
                                '1', Set.of("b")),
                       "b", Map.of(
                                '0', Set.of("c"),
                                '1', Set.of("a", "c")),
                       "c", Map.of(
                                '0', Set.of("b", "c"),
                                '1', Set.of("c"))
                )
        );
        System.out.println(nfa.accept(List.of('1','1')));  // true
        System.out.println(nfa.accept(List.of('0','1','1')));  // true
        System.out.println(nfa.accept(List.of('1','0','0')));  // true
        System.out.println(nfa.accept(List.of('0')));  // false
        System.out.println(nfa.accept(List.of()));  // empty word - false

        System.out.println(nfa.language(0));        // []
        System.out.println(nfa.language(1));        // []
        System.out.println(nfa.language(2));        // [[1, 0], [0, 0], [1, 1], [0, 1]]
        System.out.println(nfa.language(3));        // [[1, 0], [1, 0, 1], [0, 0], [1, 1], [0, 0, 1], [1, 0, 0], [1, 1, 1], [0, 1], [0, 0, 0], [0, 1, 1], [1, 1, 0], [0, 1, 0]]
        System.out.println(nfa.language(4));        // [[0, 0], [0, 1], [0, 0, 1, 0], [1, 0, 0, 1], [1, 1, 0, 0], [0, 0, 1, 1], [0, 1, 1, 0], [1, 0, 0, 0], [0, 1, 1, 1], [1, 1, 0, 1], [0, 0, 0], [1, 0, 1], [0, 0, 1], [1, 0, 0], [1, 0, 1, 0], [1, 0], [1, 1], [1, 1, 1, 1], [0, 0, 0, 0], [1, 0, 1, 1], [1, 1, 1, 0], [0, 0, 0, 1], [0, 1, 0, 0], [0, 1, 0, 1], [0, 1, 1], [1, 1, 0], [0, 1, 0], [1, 1, 1]]

        System.out.println(nfa.toDFA());
//
//        System.out.println(nfa.toDFA().language(0));        // []
//        System.out.println(nfa.toDFA().language(1));        // []
//        System.out.println(nfa.toDFA().language(2));        // [[1, 0], [0, 0], [1, 1], [0, 1]]
//        System.out.println(nfa.toDFA().language(3));        // [[1, 0], [1, 0, 1], [0, 0], [1, 1], [0, 0, 1], [1, 0, 0], [1, 1, 1], [0, 1], [0, 0, 0], [0, 1, 1], [1, 1, 0], [0, 1, 0]]
//        System.out.println(nfa.toDFA().language(4));        // [[0, 0], [0, 1], [0, 0, 1, 0], [1, 0, 0, 1], [1, 1, 0, 0], [0, 0, 1, 1], [0, 1, 1, 0], [1, 0, 0, 0], [0, 1, 1, 1], [1, 1, 0, 1], [0, 0, 0], [1, 0, 1], [0, 0, 1], [1, 0, 0], [1, 0, 1, 0], [1, 0], [1, 1], [1, 1, 1, 1], [0, 0, 0, 0], [1, 0, 1, 1], [1, 1, 1, 0], [0, 0, 0, 1], [0, 1, 0, 0], [0, 1, 0, 1], [0, 1, 1], [1, 1, 0], [0, 1, 0], [1, 1, 1]]
//
//        for(int len = 0; len < 10; len++)
//            System.out.println(nfa.language(len) .equals (nfa.toDFA().language(len)));        // true
    }
    
    // priklad nedeterministickych automatov
    static NFA<String, Character> nfa0 = new NFA<>(
            Set.of("p", "q"),
            "p",
            Set.of('0', '1'),
            Set.of("q"),
            Map.of("p", Map.of(
                    '0', Set.of("p"),
                    '1', Set.of("q","p")),
                    "q", Map.of(
                            '0', Set.of(),
                            '1', Set.of())
            )
    );

    static NFA<String, Character> nfa1 = new NFA<>(
            Set.of("a", "b", "c"),
            "a",
            Set.of('0', '1'),
            Set.of("c"),
            Map.of("a", Map.of(
                    '0', Set.of("a","b"),
                    '1', Set.of("b")),
                    "b", Map.of(
                            '0', Set.of("c"),
                            '1', Set.of("a", "c")),
                    "c", Map.of(
                            '0', Set.of("b", "c"),
                            '1', Set.of("c"))
            )
    );
    static NFA<String, Character> dfa = new NFA<String, Character>(
            Set.of("a", "b", "c", "d"),
            "a",
            Set.of('0', '1'),
            Set.of("d"),
            Map.of("a", Map.of(
                    '0', Set.of("b"),
                    '1', Set.of("a")),
                    "b", Map.of(
                            '0', Set.of("b"),
                            '1', Set.of("c")),
                    "c", Map.of(
                            '0', Set.of("d"),
                            '1', Set.of("a")),
                    "d", Map.of(
                            '0', Set.of("d"),
                            '1', Set.of("d"))
            )
    );

    static NFA<String, Character> nfa2 = new NFA<>(
            Set.of("a", "b", "c"),
            "a",
            Set.of('0', '1'),
            Set.of("c"),
            Map.of("a", Map.of(
                    '0', Set.of("a"),
                    '1', Set.of("a","b")),
                    "b", Map.of(
                            '0', Set.of("c"),
                            '1', Set.of("c")),
                    "c", Map.of(
                            '0', Set.of(),
                            '1', Set.of())
            )
    );

    static NFA<String, Character> nfa3 = new NFA<>(
            Set.of("q0", "q1", "q3"),
            "q0",
            Set.of('0', '1'),
            Set.of("q3"),
            Map.of("q0", Map.of(
                    '0', Set.of("q0","q1"),
                    '1', Set.of("q0")),
                    "q1", Map.of(
                            '0', Set.of("q3"),
                            '1', Set.of("q3")),
                    "q3", Map.of(
                            '0', Set.of(),
                            '1', Set.of())
            )
    );

    static NFA<String, Character> nfa4 = new NFA<>(
            Set.of("q0", "q1", "q2", "q3", "q4", "q5"),
            "q0",
            Set.of('0', '1'),
            Set.of("q1", "q2"),
            Map.of("q0", Map.of(
                    '0', Set.of("q1","q2"),
                    '1', Set.of()),

                    "q1", Map.of(
                            '0', Set.of(),
                            '1', Set.of("q3")),

                    "q2", Map.of(
                            '0', Set.of(),
                            '1', Set.of("q5")),

                    "q3", Map.of(
                            '0', Set.of("q4"),
                            '1', Set.of()),

                    "q4", Map.of(
                            '0', Set.of(),
                            '1', Set.of("q1")),

                    "q5", Map.of(
                            '0', Set.of("q2"),
                            '1', Set.of())
            )
    );

    static NFA<String, Character> nfa5 = new NFA<>(
            Set.of("q0", "q2", "q3", "q5", "q6"),
            "q0",
            Set.of('0', '1'),
            Set.of("q3","q6"),
            Map.of("q0", Map.of(
                    '0', Set.of("q2"),
                    '1', Set.of("q5")),

                    "q2", Map.of(
                            '0', Set.of("q2", "q3"),
                            '1', Set.of("q2")),

                    "q5", Map.of(
                            '0', Set.of("q5"),
                            '1', Set.of("q6","q5")),
                    "q3", Map.of(
                            '0', Set.of(),
                            '1', Set.of()),
                    "q6", Map.of(
                            '0', Set.of(),
                            '1', Set.of())
            )
    );

    static NFA<String, Character> nfa6 = new NFA<>(
            Set.of("q0", "q1"),
            "q0",
            Set.of('0', '1'),
            Set.of("q1"),
            Map.of("q0", Map.of(
                    '0', Set.of("q0","q1"),
                    '1', Set.of("q1")),
                    "q1", Map.of(
                            '0', Set.of("q1"),
                            '1', Set.of())
            )
    );

    static List<NFA<String, Character>> alls  = List.of(nfa0, nfa1, dfa, nfa2, nfa3, nfa4,  nfa6, nfa5);

}
