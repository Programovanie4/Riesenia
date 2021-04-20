import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


//          FOR FREE


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
        ArrayList<S> SS = new ArrayList<>();
        SS.add(initState);

        ArrayList<S> finalSS = SS;
        word.stream().forEach(ch -> {
            HashSet<S> nove = new HashSet<>();
            finalSS.stream().forEach(stav -> {
                nove.addAll(delta.get(stav).get(ch));
            });
            finalSS.clear();
            finalSS.addAll(nove);
        });

        return finalSS.stream().anyMatch(s -> finalStates.contains(s));
    }


    public Set<List<A>> language(int len) {

        Set<List<A>> von = new HashSet<>();
        Set<List<A>> slova = new HashSet<>();
        slova.add(new ArrayList<>());

        IntStream.range(0, len+1).forEach(n ->{
            Set<List<A>> nove = new HashSet<>();
            slova.stream().forEach(slovo ->{
                if(accept(slovo)) von.add(slovo);
                alphabet.stream().forEach(a ->{
                    List<A> nove_slovo = new ArrayList<>(slovo);
                    nove_slovo.add(a);
                    nove.add(nove_slovo);
                });
            });
            slova.clear();
            slova.addAll(nove);
        });

        return von;
    }
    //*/


    public static <T> Set<Set<T>> powerSet(Set<T> s) {

        Set<Set<T>> von = new HashSet<>();
        ArrayList<T> erej = new ArrayList<>(s);

        int dlzka = (int) Math.pow(2, s.size());

        IntStream.range(0, dlzka).forEach(i ->{
            Set<T> podmnozina = new HashSet<>();
            String bin = Integer.toBinaryString(i);
            String pridaj = "0".repeat(Math.max(erej.size() - bin.length(), 0));
            bin = pridaj + bin;
            String finalBin = bin;
            IntStream.range(0, bin.length()).forEach(j->{
                if (j < finalBin.length() && finalBin.charAt(j) == '1')
                    podmnozina.add(erej.get(j));
            });
            von.add(podmnozina);
        });
        return von;

    }

    /*
      konverzia NFA na DFA
*/

    public DFA<Set<S>, A> toDFA() {
        //public DFA(Set<S> states, S initState, Set<A> alphabet, Set<S> finalStates, Map<S, Map<A, S>> delta)
        Set<Set<S>> stavy = powerSet(states);
        Set<S> init = Set.of(initState);
        Set<A> abc = alphabet;
        Set<Set<S>> finalne = stavy.stream().filter(s -> s.stream().anyMatch(stav -> finalStates.contains(stav))).collect(Collectors.toSet());
        Map<Set<S>, Map<A, Set<S>>> d = stavy.stream().collect(Collectors.toMap(s->s, s->{
            return abc.stream().collect(Collectors.toMap(a->a, a->{
                Set<S> nova = new HashSet<>();
                s.stream().forEach(stav -> {
                            if (delta.get(stav) != null && delta.get(stav).get(a) != null)
                                nova.addAll(delta.get(stav).get(a));
                        });
                return nova;
            }));
        }));

        return new DFA<>(stavy, init, abc, finalne, d); // toto doprogramujte
    }

    // java.lang.AssertionError: NFA{states=[q, p], initState=p, alphabet=[1, 0], finalStates=[q], delta={p={0=[p], 1=[p, q]}, q={0=[], 1=[]}}}.language(1)
    //                   .equals(NFA{states=[q, p], initState=p, alphabet=[1, 0], finalStates=[q], delta={p={0=[p], 1=[p, q]}, q={0=[], 1=[]}}}.toDFA.language(1))




    public static void main(String[] args) {
        //slovaStream(2, Set.of("a", "b", "c")).forEach(System.out::println);
        System.out.println(powerSet(Set.of("a", "b", "c")));


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
//*/

        System.out.println(nfa.toDFA());
        System.out.println(nfa.toDFA().language(0));        // []
        System.out.println(nfa.toDFA().language(1));        // []
        System.out.println(nfa.toDFA().language(2));        // [[1, 0], [0, 0], [1, 1], [0, 1]]
        System.out.println(nfa.toDFA().language(3));        // [[1, 0], [1, 0, 1], [0, 0], [1, 1], [0, 0, 1], [1, 0, 0], [1, 1, 1], [0, 1], [0, 0, 0], [0, 1, 1], [1, 1, 0], [0, 1, 0]]
        System.out.println(nfa.toDFA().language(4));        // [[0, 0], [0, 1], [0, 0, 1, 0], [1, 0, 0, 1], [1, 1, 0, 0], [0, 0, 1, 1], [0, 1, 1, 0], [1, 0, 0, 0], [0, 1, 1, 1], [1, 1, 0, 1], [0, 0, 0], [1, 0, 1], [0, 0, 1], [1, 0, 0], [1, 0, 1, 0], [1, 0], [1, 1], [1, 1, 1, 1], [0, 0, 0, 0], [1, 0, 1, 1], [1, 1, 1, 0], [0, 0, 0, 1], [0, 1, 0, 0], [0, 1, 0, 1], [0, 1, 1], [1, 1, 0], [0, 1, 0], [1, 1, 1]]

        for(int len = 0; len < 10; len++)
            System.out.println(nfa.language(len) .equals (nfa.toDFA().language(len)));        // true


        System.out.println(nfa0.toDFA());

         //*/
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


