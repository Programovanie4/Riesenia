import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

//FOR FREE

public class Slova {
    public static Set<String> slova(int n){
        Set<String> vysl = new HashSet<>();
        if(n==0){
            vysl.add("");
            return vysl;
        }
        IntStream.range(0, (int) Math.pow(2, n)).forEach(v->vysl.add(String.format("%1$" + n + "s", Integer.toBinaryString(v)).replace(' ', '0')));
    return vysl;
    }

    public static Set<String> slova(int n, String abeceda){
        if(n==0) return Set.of("");
        Set<String> pred = slova(n-1, abeceda);
        Set<String> vysl = new HashSet<>();
        slova(n-1, abeceda).stream().forEach(s -> Arrays.stream(abeceda.split("")).forEach(c -> vysl.add(s+String.valueOf(c))));
        return vysl;
    }

    public static Stream<String> slovaStream(int n, String abeceda){
        if(n==0) return List.of("").stream();
        Set<String> vysl = new HashSet<>();
        slovaStream(n-1, abeceda).forEach(s -> Arrays.stream(abeceda.split("")).forEach(c -> vysl.add(s+String.valueOf(c))));
        return vysl.stream();
    }

    public static Stream<String> slovaKratsieStream(int n, String abeceda){
        if(n==0) return List.of("").stream();
        Set<String> vysl = new HashSet<>();
        slovaKratsieStream(n-1, abeceda).forEach(s -> {Arrays.stream(abeceda.split("")).forEach(c -> vysl.add(s+String.valueOf(c))); vysl.add(s);});
        return vysl.stream();
    }

    public static <A> Set<List<A>> slova(int n, Set<A> abeceda){
        if(n==0) return Set.of(new ArrayList<A>());
        Set<List<A>> vysl = new HashSet<>();
        slova(n-1, abeceda).stream().forEach(s -> abeceda.stream().forEach(c ->{
            List<A> novy = new ArrayList<>(); novy.addAll(s); novy.add(c);
            vysl.add(novy);}));
        return vysl;
    }

    public static <A> Stream<List<A>> slovaStream(int n, Set<A> abeceda){
        return slova(n, abeceda).stream();
    }

    public static <A> Stream<List<A>> slovaKratsieStream(int n, Set<A> abeceda){
        if(n==0){
            Set<List<A>> prazdna = Set.of(List.of());
            return prazdna.stream();
        }

        Set<List<A>> vysl = new HashSet<>();
        slovaKratsieStream(n-1, abeceda).forEach(s -> {abeceda.stream().forEach(c ->{
            List<A> novy = new ArrayList<>(); novy.addAll(s); novy.add(c);
            vysl.add(novy);});vysl.add(s);});
        return vysl.stream();
    }

    public static void main(String[] args) {
//        var alphabet = Set.of("♠", "♥", "♣", "♦");
//        for(int n = 0; n < 5; n++) {
//            System.out.println(slova(n, alphabet));
//            System.out.println(slovaStream(n, alphabet).collect(Collectors.toSet()));
//            System.out.println(slovaStream(n, alphabet).count());
//            System.out.println(slovaKratsieStream(n, alphabet).collect(Collectors.toSet()));
//            System.out.println(slovaKratsieStream(n, alphabet).count());
//        }
        System.out.println(slova(2));
        System.out.println(slova(0));
    }
}
