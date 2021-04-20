import java.util.*;

public class DFA<S,A> {
    private Set<S> states;          // mnozina stavov
    private S initState;            // pociatocny stav
    private Set<A> alphabet;        // abeceda symbolov slova
    private Set<S> finalStates;     // mnozina koncovych/akceptujucich stavov
    private Map<S, Map<A, S>> delta; // delta funkcia, zobrazuje stav a pismenko na novy stav

    public DFA(Set<S> states, S initState, Set<A> alphabet, Set<S> finalStates, Map<S, Map<A, S>> delta) {
        this.states = states;
        this.initState = initState;
        this.alphabet = alphabet;
        this.finalStates = finalStates;
        this.delta = delta;
    }

    @Override
    public String toString() {
        return "DFA{" +
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

    public Map<S, Map<A, S>> getDelta() {
        return delta;
    }

    /**
     * tento kus kodu som vam nechal len preto, aby
     * som ilustroval, ze zistit, ci automat akceptuje
     * dane vstupne slovo je uplne priamociare, a aj
     * ako ilustraciu na pracu so strukturami automatu
     */
    public boolean accept(List<A> word) {
        S s = initState;
        // nerad indexujem
        for (A ch : word)
            s = delta.get(s).get(ch);

        // rad indexujem
//        for (int i = 0; i < word.size(); i++)
//            s = delta.get(s).get(word.get(i));
        return finalStates.contains(s);
    }

    private boolean prazdnyDoHlbky(S s, Set<S> visited) {
        //returns false if you can reach a final state from source state
        visited.add(s);
        if (getFinalStates().contains(s)) {
            return false;
        }
        for (S nextState : delta.get(s).values()) {
            if (visited.contains(nextState))
                continue;
            if (!prazdnyDoHlbky(nextState,visited)) {
                return false;
            }
        }
        return true;
    }

    public boolean prazdny() {
        return prazdnyDoHlbky(getInitState(),new HashSet<>());
    }
/*
    private Set<S> cycles(S s, Set<S> visited, Set<S> cycled) {
        //returns a set of states which can be reached multiple times
        if (visited.contains(s)) {

        }
    }

 */



    private boolean finToFin(S s, Set<S> visited, Set<S> cycles) {
        //returns true when the final state can be reached through a loop

        if (visited.contains(s)) {
            if (!cycles.contains(s)) {
                cycles.add(s);
                return !prazdnyDoHlbky(s, new HashSet<>());
            }
            return false;
        }

        visited.add(s);
        for (S nextState : delta.get(s).values()) {
            if (finToFin(nextState,visited,cycles)) {
                return true;
            }
        }
        cycles.add(s); //doesn't end in cycle, therefore doesn't cycle itself
        return false;
    }

    public boolean nekonecny() {
        if (prazdny())
            return false;
        return finToFin(getInitState(),new HashSet<>(),new HashSet<>());
    }

    private Set<List<A>> langDepth(S s, List<A> word, int maxLen, Set<List<A>> ret) {
        //finds all words within set length that are accepted
        if (getFinalStates().contains(s))
            ret.add(new ArrayList<>(word));

        if (word.size() >= maxLen)
            return ret;

        for (A nextLetter : delta.get(s).keySet()) {
            word.add(nextLetter);
            langDepth(delta.get(s).get(nextLetter),word,maxLen,ret);
            word.remove(word.size()-1);
        }

        return ret;
    }

    public Set<List<A>> language(int len) {
        return langDepth(getInitState(),new ArrayList<A>(),len,new HashSet<>());
    }

    public static void main(String[] args) {
        var dfa = new DFA<String, Character>(
                Set.of("a", "b", "c", "d"),
                "a",
                Set.of('0', '1'),
                Set.of("d"),
                Map.of("a", Map.of(
                                '0', "b",
                                '1', "a"),
                       "b", Map.of(
                                '0', "b",
                                '1', "c"),
                       "c", Map.of(
                                '0', "d",
                                '1', "a"),
                        "d", Map.of(
                                '0', "d",
                                '1', "d")

                )
        );
        System.out.println(dfa.accept(List.of('1','1','0','0','1','0','0','1')));
        System.out.println(dfa.language(4));
        System.out.println(dfa.prazdny()); //false
        System.out.println("<true> " + dfa.nekonecny()); //true

        //////////////////////////////////////////////////////////

        var test = new DFA<String,Character>(
                Set.of("q0","q1","q2"),
                "q0",
                Set.of('a','b'),
                Set.of("q0"),
                Map.of("q0", Map.of(
                        'a', "q1",
                        'b', "q1"),

                        "q1", Map.of(
                                'a', "q1",
                                'b', "q1"),

                        "q2", Map.of(
                                'a', "q1",
                                'b', "q1")

                )
        );

        System.out.println();
        System.out.println("<false> " + test.nekonecny());   //false

        //////////////////////////////////////////////////////////

        var test2 = new DFA<String,Character>(
                Set.of("q0","q1","q2","q3","q4"),
                "q0",
                Set.of('a','b'),
                Set.of("q3"),
                Map.of("q0", Map.of(
                        'a', "q1",
                        'b', "q1"),

                        "q1", Map.of(
                                'a', "q2",
                                'b', "q3"),

                        "q2", Map.of(
                                'a', "q1",
                                'b', "q1"),

                        "q3", Map.of(
                                'a', "q4",
                                'b', "q4"),

                        "q4", Map.of(
                                'a', "q4",
                                'b', "q4")

                )
        );

        System.out.println();
        System.out.println("<true> " + test2.nekonecny());   //true


        //////////////////////////////////////////////////////////

        var test3 = new DFA<String,Character>(
                Set.of("q0","q1","q2","q3"),
                "q0",
                Set.of('a','b'),
                Set.of("q2"),
                Map.of("q0", Map.of(
                        'a', "q1",
                        'b', "q1"),

                        "q1", Map.of(
                                'a', "q2",
                                'b', "q2"),

                        "q2", Map.of(
                                'a', "q3",
                                'b', "q3"),

                        "q3", Map.of(
                                'a', "q3",
                                'b', "q3")

                )
        );

        System.out.println();
        System.out.println("<false> " + test3.nekonecny());   //false


    }
}
