import java.util.*;

public class Zabky {
    static class Element{
        private String state;
        private Element parent;

        public Element(String state, Element parent){
            this.state = state;
            this.parent = parent;
        }
        public int size(){
            if(parent == null){
                return 1;
            }
            return parent.size() + 1;
        }
        public List<String> getPath(){
            if(parent == null){
                return new ArrayList<>(Collections.singletonList(state));
            }
            List<String> prev = parent.getPath();
            prev.add(state);
            return new ArrayList<>(prev);
        }
    }


    public static List<String> zabky(int P, int L){
        String state = ">".repeat(P) + "_" + "<".repeat(L);
        List<Element> parents = new ArrayList<>();
        List<Element> children = new ArrayList<>();

        parents.add(new Element(state, null));
        while (parents.size() != 0){
            for(Element parent: parents) {
                List<String> nextStates = new ArrayList<>();
                if(parent.state.contains(">_")) {
                    nextStates.add(parent.state.replaceAll(">_", "_>"));
                }
                if(parent.state.contains("_<")) {
                    nextStates.add(parent.state.replaceAll("_<", "<_"));
                }
                if(parent.state.contains(">>_")){
                    nextStates.add(parent.state.replaceAll(">>_", "_>>"));
                }if(parent.state.contains("><_")){
                    nextStates.add(parent.state.replaceAll("><_", "_<>"));
                }if(parent.state.contains("_<<")){
                    nextStates.add(parent.state.replaceAll("_<<", "<<_"));
                }if(parent.state.contains("_><")){
                    nextStates.add(parent.state.replaceAll("_><", "<>_"));
                }
                for (String s : nextStates) {
                    Element e = new Element(s, parent);
                    if (("<".repeat(L) + "_" + ">".repeat(P)).equals(s)) {
                        return e.getPath();
                    }
                    children.add(e);
                }
            }
            parents = children;
            children = new ArrayList<>();

        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(zabky(3, 3));
    }
}
