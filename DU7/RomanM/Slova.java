import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;



// FOR FREE



public class Slova {

    public static Set<String> slova(int n){
        if (n == 0){
            return Stream.of("").collect(Collectors.toSet());
        } else
            return slova(n-1).stream().flatMap(s -> Stream.of(s + "0", s + "1")).collect(Collectors.toSet());
    }

    public static Set<String> slova(int n, String abeceda){

        if (n == 0){
            return Stream.of("").collect(Collectors.toSet());
        } else
            return slova(n-1, abeceda).stream().flatMap(s -> vrat(s, abeceda) ).collect(Collectors.toSet());

    }

    public static Stream<String> vrat (String s, String abeceda){
        return Arrays.stream(abeceda.split("")).flatMap(znak -> Stream.of(s + znak));
    }


    public static Stream<String> slovaStream(int n, String abeceda){
        if (n == 0){
            return Stream.of("");
        } else
            return slova(n-1, abeceda).stream().flatMap(s -> vrat(s, abeceda) );
    }

    public static Stream<String> slovaKratsieStream(int n, String abeceda){

        Stream<Integer> stream = IntStream.range(0,n+1).boxed();

        return stream.flatMap(cislo -> slovaStream(cislo, abeceda));
    }


    public static <A> Set<List<A>> slova(int n, Set<A> abeceda){
        if (n == 0){
            return Stream.of(new ArrayList<A>()).collect(Collectors.toSet());
        } else
            return slova(n-1, abeceda).stream().flatMap(s -> vratP(s, abeceda)).collect(Collectors.toSet());
    }

    private static <A> Stream<List<A>> vratP(List<A> s, Set<A> abeceda) {
        Set<List<A>> von = new HashSet<>();
        abeceda.stream().forEach(znak -> {
            s.add(znak);
            von.add(new ArrayList<>(s));
        });
        return von.stream();
    }

    public static <A> Stream<List<A>> slovaStream(int n, Set<A> abeceda){
        if (n == 0){
            return Stream.of(new ArrayList<>());
        } else
            return slova(n-1, abeceda).stream().flatMap(s -> vratP(s, abeceda));
    }

    public static <A> Stream<List<A>> slovaKratsieStream(int n, Set<A> abeceda){
        Stream<Integer> stream = IntStream.range(0,n+1).boxed();

        return stream.flatMap(cislo -> slovaStream(cislo, abeceda));
    }

    public static void main(String[] args) {
        for(int n = 0; n < 5; n++) {
            System.out.println(slova(n));
            System.out.println(slova(n, "abc"));
            System.out.println(slovaStream(n, "abc").collect(Collectors.toSet()));
            System.out.println(slovaStream(n, "abc").count());
            System.out.println(slovaKratsieStream(n, "abc").collect(Collectors.toSet()));
            System.out.println(slovaKratsieStream(n, "abc").count());
        }

        var alphabet = Set.of("♠", "♥", "♣", "♦");
        for(int n = 0; n < 5; n++) {
            System.out.println(slova(n, alphabet));
            System.out.println(slovaStream(n, alphabet).collect(Collectors.toSet()));
            System.out.println(slovaStream(n, alphabet).count());
            System.out.println(slovaKratsieStream(n, alphabet).collect(Collectors.toSet()));
            System.out.println(slovaKratsieStream(n, alphabet).count());
        }

    }
}
