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

        public boolean vP(S s1, S s2, Set<S> nav){
            if(delta.get(s1).containsValue(s2)) return true;
            for (S v: delta.get(s1).values()){
                if (!nav.contains(v)){
                    nav.add(v);
                    if(vP(v, s2, nav)) return true;
                }
            }
            return false;
        }


        public boolean prazdny() {
            if(finalStates.contains(initState)) return false; //prazdne slovo
            for (S sf:finalStates){
                Set<S> navstivene = new HashSet<>();
                if(vP(initState, sf, navstivene)) return false;
            }
            return true; // DU 6
        }

        public S vP2(S s1, S s2, Set<S> nav, List<S> cesta){

            for (S v: delta.get(s1).values()){
                if(cesta.contains(v)) return v;
                if (!nav.contains(v)){
                    nav.add(v);
                    cesta.add(v);
                    if(null != vP2(v, s2, nav, cesta)) return vP2(v, s2, nav, cesta);
                    cesta.remove(cesta.size()-1);
                }


            }
            return null;
        }

        public boolean nekonecny() {
            if(prazdny()) return false;
            Set<S> navstivene = new HashSet<>();
            S v = vP2(initState, initState, navstivene, new ArrayList<>()); // DU 6
            if(v!= null) {
                if(finalStates.contains(v)) return true;
                for (S sf:finalStates){
                    navstivene = new HashSet<>();
                    if(vP(v, sf, navstivene)) return true;
                }
            }
            return false;
        }

        public Set<List<A>> slovickarenie(S s1, int dlzka) {

            List<List<A>> stackSlov = new ArrayList<>();
            List<A> prazdne_slovo = new ArrayList<>();
            stackSlov.add(prazdne_slovo);

            Set<List<A>> vysl = new HashSet<>();
            List<S> stack = new ArrayList<>();
            stack.add(s1);
            while (stack.size() > 0) {
                S s0 = stack.remove(stack.size() - 1);
                List<A> slovo0 = stackSlov.remove(stackSlov.size() - 1);

                if(finalStates.contains(s0)) vysl.add(slovo0);

                for (A a : delta.get(s0).keySet()) {
                    S s2 = delta.get(s0).get(a);
                    List<A> slovo2 = new ArrayList<>();
                    slovo2.addAll(slovo0);
                    slovo2.add(a);
                    if (slovo2.size() > dlzka) break;
                    stack.add(s2);
                    stackSlov.add(slovo2);
                }
            }
            return vysl;
        }

        public Set<List<A>> language(int len) {
            return slovickarenie(initState, len);
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

//        var d = new DFA<String, String>(Set.of("q1", "q2", "q0"), "q0",
//                Set.of("a", "b"), Set.of("q1"), Map.of("q1", Map.of("a", "q2", "b", "q2"),
//                "q2", Map.of("a", "q1", "b", "q1"), "q0", Map.of("a", "q1", "b", "q1")));
//    var d = new DFA<String, String>(Set.of("q1", "q0"), "q0", Set.of("a", "b"),
            //  Set.of("q0"), Map.of("q1", Map.of("a", "q1", "b", "q1"), "q0",
            //        Map.of("a","q1", "b", "q1" )));
//        var d = new DFA<String, String>(Set.of("q1", "q2", "q3", "q4", "q0"), "q0", Set.of("a", "b"),
//                Set.of("q3"), Map.of("q1", Map.of("a", "q2", "b", "q3"),
//                "q2", Map.of("a", "q1", "b", "q1"), "q3", Map.of("a", "q4", "b", "q4"),
//                "q4", Map.of("a", "q4", "b", "q4"), "q0", Map.of("a", "q1", "b", "q1")));
//                //DFA{states=[q1, q2, q3, q4, q0], initState=q0, alphabet=[a, b],
            // finalStates=[q3], delta={q1={a=q2, b=q3}, q2={a=q1, b=q1}, q3={a=q4, b=q4},
            // q4={a=q4, b=q4}, q0={a=q1, b=q1}}}

            var d = new DFA<String, String>(Set.of("q1", "q2", "q3", "q0"), "q0", Set.of("a", "b"),
                    Set.of("q2"), Map.of("q1", Map.of("a", "q2", "b", "q2"),
                    "q2", Map.of("a", "q3", "b", "q3"), "q3", Map.of("a", "q3", "b", "q3"),
                    "q0", Map.of("a", "q1", "b", "q1")));
//        DFA{states=[q1, q2, q3, q0], initState=q0, alphabet=[a, b], finalStates=[q2], delta={q1={a=q2, b=q2},
//                q2={a=q3, b=q3}, q3={a=q3, b=q3}, q0={a=q1, b=q1}}}
            System.out.println(dfa.accept(List.of('1','1','0','0','1','0','0','1')));
            System.out.println(dfa.language(5));
            System.out.println(dfa.prazdny());
            System.out.println(dfa.nekonecny());
            System.out.println(d.prazdny());
            System.out.println(d.nekonecny());

            //System.out.println(d.prazdny());
        }
    }