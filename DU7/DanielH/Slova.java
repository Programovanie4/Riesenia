import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Slova {

    public static Set<String> slova(int n) {
        return slova(n,"01");
    }

    public static Set<String> slova(int n, String abeceda) {
        return slovaStream(n,abeceda).collect(Collectors.toSet());
    }

    public static Stream<String> slovaStream(int n, String abeceda) {
        if (n <= 0)
            return Stream.of("");

        return slovaStream(n-1,abeceda)
                .flatMap(s -> abeceda
                        .chars()
                        .mapToObj(c -> String.valueOf((char) c))
                        .map(c -> s+c)
                );
    }

    public static Stream<String> slovaKratsieStream(int n, String abeceda) {
        return IntStream.rangeClosed(0,n)
                .boxed()
                .flatMap(i -> slovaStream(i, abeceda));
    }


    public static <A> Stream<List<A>> slovaStream(int n, Set<A> abeceda) {
        if (n <= 0)
            return Stream.of(List.of());
        if (n == 1)
            return abeceda.stream()
                    .map(List::of);


        return slovaStream(n-1,abeceda)
                .flatMap(s -> abeceda
                        .stream()
                        .map(c -> Stream.concat(
                                s.stream(),
                                Stream.of(c)
                        )
                        .collect(Collectors.toList())
                        )
                );
    }

    public static <A> Set<List<A>> slova(int n, Set<A> abeceda) {
        return slovaStream(n,abeceda).collect(Collectors.toSet());
    }

    public static <A> Stream<List<A>> slovaKratsieStream(int n, Set<A> abeceda) {
        return IntStream.rangeClosed(0,n)
                .boxed()
                .flatMap(i -> slovaStream(i, abeceda));
    }



    public static void main(String[] args) {
        for(int n = 0; n < 5; n++) {
            System.out.println(slovaStream(2,Set.of(0,1)).collect(Collectors.toList()));
            System.out.println(slovaKratsieStream(2,Set.of(0,1)).collect(Collectors.toList()));
            System.out.println(slova(2,Set.of(0,1)));
        }

    }
}
