import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Node<S,A> {
    private S state;
    private Map<S, Map<A,Set<S>>> delta;

    public Node(S state, Map<S, Map<A,Set<S>>> delta) {
        this.state = state;
        this.delta = delta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?, ?> node = (Node<?, ?>) o;
        return Objects.equals(state, node.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }

    @Override
    public String toString() {
        return ""+state;
    }

    private Map<A,Set<Node<S,A>>> getDelta() {
        return delta.get(state).entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream()
                                .map(s -> new Node<S,A>(s,delta))
                                .collect(Collectors.toSet())
                ));

    }

    public Set<Node> getChildren() {
        return getDelta().values()
                .stream()
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    public Set<Node> move(A ch) {
        return getDelta()
                .get(ch)
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

}

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
    
    private Stream<S> next(Stream<S> states, List<A> word) {
        if (word.isEmpty())
            return states;

        return next(states,word.subList(0,word.size()-1))
                .flatMap(a -> delta.get(a).get(word.get(word.size()-1)).stream())
                .distinct();
    }

    public boolean accept(List<A> word) {
        return next(Stream.of(initState),word)
                   .anyMatch(a -> finalStates.contains(a));
    }

    private Stream<S> nextStates(Set<S> states) {
        return states
                .stream()
                .flatMap(a -> delta.get(a).values()
                        .stream()
                        .flatMap(Collection::stream))
                .distinct();
    }

    private Stream<S> nextStates(Set<S> states, A letter) {
        return next(states.stream(),List.of(letter));
    }

    private Stream<A> nextLetters(Set<S> states) {
        return states.parallelStream()
                .flatMap(a -> delta.get(a).keySet().stream());
    }

    private Stream<List<A>> langRek(Set<S> states, List<A> word, int maxLen, Set<List<A>> ret) {
        //System.out.println("langrek"+word.toString());
        if (word.size() > maxLen || ret.contains(word))
            return Stream.of();
        if (word.size() == maxLen) {
            ret.add(word);
            return ret.stream();
        }


        Stream<List<A>> nextWords = nextLetters(states)             //stream slov + dalsie pismenko
                .map(a -> Stream.concat(
                        word.stream(),
                        Stream.of(a)
                    ).collect(Collectors.toList())
                );

        return nextWords
                .flatMap(w -> Stream.concat(langRek(
                        nextStates(states, w.get(w.size()-1)).collect(Collectors.toSet()),
                            w,
                            maxLen,
                            ret),
                        Stream.of(w))
                );
    }

    public Set<List<A>> language(int len) {
        return langRek(Set.of(initState),List.of(),len, new HashSet<>())
                .parallel()
                .filter(this::accept)
                .collect(Collectors.toSet());
    }


    public static <T> Set<Set<T>> powerSet(Set<T> s) {
        if (s.isEmpty())
            return Set.of(Set.of());

        T element = s.iterator().next();
        Set<Set<T>> withoutElement = powerSet(s.stream().filter(a -> !a.equals(element)).collect(Collectors.toSet()));

        Set<Set<T>> ret = withoutElement
                .parallelStream()
                .flatMap(a -> Stream.of(a,Stream.concat(a.stream(),Stream.of(element)).collect(Collectors.toSet())))
                .collect(Collectors.toSet());

        //ret.addAll(withoutElement);

        return ret;
    }

    Set<Set<S>> setOfStates(Set<S> stateSet) {
        Set<Set<S>> ret = alphabet.stream()
                                  .map(a -> stateSet.stream()
                                                    .flatMap(s -> delta.get(s).get(a).stream())
                                                    .collect(Collectors.toSet())
                                  )
                                  .collect(Collectors.toSet());
        return ret;
    }

    Set<Set<S>> statesDFA(Set<Set<S>> inStates) {
        int count = inStates.size();

        inStates = inStates.stream()
                           .flatMap(s -> Stream.concat(
                                   Stream.of(s),
                                   setOfStates(s).stream()
                                )
                           ).collect(Collectors.toSet());

        if (inStates.size() != count)
            return statesDFA(inStates);
        else
            return inStates;
    }

    Set<S> nextStateSet(Set<S> DFAstate, A a) {
        var ret = DFAstate.stream()
                    .flatMap(s -> delta.get(s).get(a).stream())
                    .collect(Collectors.toSet());

        return ret;
    }

    Map<A,Set<S>> subMap(Set<S> DFAstate) {
        var ret = alphabet.stream()
                .collect(Collectors.toMap(
                        key -> key,
                        a -> nextStateSet(DFAstate,a)
                ));

        return ret;
    }

    Map<Set<S>,Map<A,Set<S>>> deltaDFA(Set<Set<S>> DFAstates) {
        var ret= DFAstates.stream()
                .collect(Collectors.toMap(
                        key -> key,
                        this::subMap
                ));
        return ret;
    }



    /*
      konverzia NFA na DFA
     */
    public DFA<Set<S>,A> toDFA() {
        //Set<Set<S>> DFAstates = statesDFA(Set.of(Set.of(initState)));
        Set<Set<S>> DFAstates = powerSet(states);


        return new DFA<Set<S>,A>(
              DFAstates,
              Set.of(initState),
              alphabet,
              DFAstates.stream()
                       .filter(s -> s.stream().anyMatch(s1 -> finalStates.contains(s1)))
                       .collect(Collectors.toSet()),
              deltaDFA(DFAstates));
    }
    public static void main(String[] args) {/*
        var test = new NFA<>(
                Set.of("q0","q1","q2","q4","q4","q5"),
                "q0",
                Set.of('0', '1'),
                Set.of("q1","q2"),
                Map.of("q0", Map.of(
                        '0', Set.of("q1","q2"),
                        '1', Set.of()),
                        "q1", Map.of(
                                '0', Set.of(),
                                '1', Set.of("q3")),
                        "q2", Map.of(
                                '0', Set.of(),
                                '1', Set.of("q3")),
                )
        );

        */

        System.out.println(nfa4);
        System.out.println(nfa4.next(Stream.of(nfa4.initState),List.of('0','1')).collect(Collectors.toList()));
        System.out.println(nfa4.language(3));


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

        System.out.println("todfa:");
        System.out.println(nfa.statesDFA(Set.of(Set.of(nfa.initState))));
        System.out.println(nfa.toDFA());
        System.out.println();

        Node n = new Node<String,Character>("a",Map.of("a", Map.of(
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
        ));



        System.out.println(n.getChildren());
        System.out.println(n.move('1'));

        System.out.println("powerSet: "+powerSet(Set.of('a','b','c')));

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

        System.out.println("toDFA");
        System.out.println(nfa.toDFA());

        System.out.println(nfa.toDFA().language(0));        // []
        System.out.println(nfa.toDFA().language(1));        // []
        System.out.println(nfa.toDFA().language(2));        // [[1, 0], [0, 0], [1, 1], [0, 1]]
        System.out.println(nfa.toDFA().language(3));        // [[1, 0], [1, 0, 1], [0, 0], [1, 1], [0, 0, 1], [1, 0, 0], [1, 1, 1], [0, 1], [0, 0, 0], [0, 1, 1], [1, 1, 0], [0, 1, 0]]
        System.out.println(nfa.toDFA().language(4));        // [[0, 0], [0, 1], [0, 0, 1, 0], [1, 0, 0, 1], [1, 1, 0, 0], [0, 0, 1, 1], [0, 1, 1, 0], [1, 0, 0, 0], [0, 1, 1, 1], [1, 1, 0, 1], [0, 0, 0], [1, 0, 1], [0, 0, 1], [1, 0, 0], [1, 0, 1, 0], [1, 0], [1, 1], [1, 1, 1, 1], [0, 0, 0, 0], [1, 0, 1, 1], [1, 1, 1, 0], [0, 0, 0, 1], [0, 1, 0, 0], [0, 1, 0, 1], [0, 1, 1], [1, 1, 0], [0, 1, 0], [1, 1, 1]]

        for(int len = 0; len < 10; len++)
            System.out.println(len + " " + nfa.language(len) .equals (nfa.toDFA().language(len)));        // true
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
