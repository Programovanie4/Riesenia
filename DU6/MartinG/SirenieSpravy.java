import java.util.*;


public class SirenieSpravy {
    private int i = 0, t = 0, n = 0;

    // Pre testovač...
    public List<Set<String>> bubliny;
    public Set<String> panikari;

    public SirenieSpravy(List<Set<String>> bubliny, Set<String> panikari) {
        this.bubliny = bubliny;
        this.panikari = panikari;

        Set<String> _panikari = new HashSet<>(panikari);

        Set<String> names = new HashSet<>();
        bubliny.forEach((Set<String> bubble) -> names.addAll(bubble));
        names.addAll(_panikari);

        if (_panikari.equals(names)) {  // panikari <=> bubliny
            i = names.size();
            return;
        }

        while (true) {
            Set<String> nextPanikari = new HashSet<>(_panikari);
            for (Set<String> bubble: bubliny) {
                for (String name : _panikari) {
                    if (bubble.contains(name)) {
                        nextPanikari.addAll(bubble);
                        break;
                    }
                }
            }
            if (nextPanikari.equals(_panikari)) break;
            t++;
            _panikari = nextPanikari;
        }

        i = _panikari.size();
        n = names.size() - i;
    }

    public int informovani() {
        return i;
    }

    public int trvanie() {
        return t;
    }

    public int neinformovani() {
        return n;
    }

    public static void main(String[] args) {
        var bubliny = List.of(
                Set.of("Peter", "Xaver", "Jano", "Rebeka", "Sabina"),
                Set.of("Katka", "Marienka", "Jano", "Nata"),
                Set.of("Peter", "Katka", "Filomena", "Olga"),
                Set.of("Bubo", "Eva", "Helga", "Terka"),
                Set.of("Lukas", "Gabika"),
                Set.of("Ivan", "Adel", "Danka", "Quasimodo"),
                Set.of("Ulrika", "Vierka", "Yasmina", "Zuzka"),
                Set.of("Wilson", "Chuck"));
        var s1 = new SirenieSpravy(bubliny, Set.of("Peter"));
        var s2 = new SirenieSpravy(bubliny, Set.of("Zuzka"));
        var s3 = new SirenieSpravy(bubliny, Set.of("Zuzka", "Peter"));
        var s4 = new SirenieSpravy(bubliny, Set.of("Peter", "Jano"));
        var s5 = new SirenieSpravy(bubliny, Set.of());

        var bubbles = List.of(
                Set.of("0", "1"),
                Set.of("1")
        );
        var s6 = new SirenieSpravy(bubbles, Set.of("0"));

        var bubbles2 = List.of(
                Set.of("0"),
                Set.of("1")
        );
        var s7 = new SirenieSpravy(bubbles2, Set.of("1"));

        var bubbles3 = List.of(
                Set.of("0", "1"),
                Set.of("1"),
                Set.of("0"),
                Set.of("10"),
                Set.of("0", "1", "10")
        );
        var s8 = new SirenieSpravy(bubbles3, Set.of("1"));

        for (var s : List.of(s1, s2, s3, s4, s5, s6, s7, s8))  // 1, 0, 1
            System.out.println(
                "informovani: " + s.informovani()
                + ", \ttrvanie: " + s.trvanie()
                + ", \tneinformovani: " + s.neinformovani());
}

}
