import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Trieda {
	private final HashMap<Ziak, List<VelkonocneVajce>> trieda;

    public List<VelkonocneVajce> vsetkyRozneVajcia() {
        if (trieda == null)
            return null;
        return trieda.values()
            .stream()
            .filter(Objects::nonNull)
            .flatMap(Collection::stream)
            .distinct()
            //.forEach(System.out::println);
            .collect(Collectors.toList());

        //return null;
    }

    public List<Ziak> bezVajec() {
        if (trieda == null)
            return null;
        return trieda.keySet()
                .stream()
                .filter(z -> trieda.get(z) == null || trieda.get(z).isEmpty())
                //.map(z -> trieda.get(z))
                //.forEach(z -> System.out.println(z));
                .collect(Collectors.toList());

        //return null;
    }

    public Ziak najvacsiZberatel() {
        if (trieda == null)
            return null;
        Integer max = trieda.entrySet()
                .stream()
                .filter(Objects::nonNull)
                .filter(e -> e.getValue() != null)
                .map(e -> e.getValue().size())
                .max(Integer::compare)
                .orElse(0);


        return trieda.keySet()
                .stream()
                .filter(Objects::nonNull)
                .filter(z -> trieda.get(z) != null)
                .filter(z -> max == trieda.get(z).size())
                .max(Comparator.comparing(Ziak::getAge))
                .orElse(null);
    }

    public VelkonocneVajce najcastejsieVajce() {
        if (trieda == null)
            return null;

        Long max = trieda.values()
                .stream()
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ))
                .values()
                .stream()
                .max(Long::compareTo)
                .orElse(0l);

        return trieda.values()
                .stream()
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .filter(e -> Objects.equals(e.getValue(), max))
                .min(Comparator.comparing(Map.Entry<VelkonocneVajce,Long>::getKey))
                .map(Map.Entry::getKey)
                .orElse(null);

    }

    public VelkonocneVajce najvacsieVajce() {
        if (trieda == null)
            return null;
        return trieda.values()
                .stream()
                .filter(Objects::nonNull)
                .flatMap(vajcia -> vajcia.stream())
                .max(VelkonocneVajce::compareTo)
                //.findFirst()
                .orElse(null);
    }

	//------------------------------------------------------------------------------
	public Trieda(HashMap<Ziak, List<VelkonocneVajce>> trieda) {
		super();
		this.trieda = trieda;
	}

	public HashMap<Ziak, List<VelkonocneVajce>> getTrieda() {
		return trieda;
	}

	@Override
	public String toString() {
		return "Trieda [trieda=" + trieda + "]";
	}

    public static void main(String[] args) {
        TestTriedaVerejny test = new TestTriedaVerejny();
        //System.out.println(test.PrvaA);
        //System.out.println(test.PrvaA.trieda.keySet());
        System.out.println(test.PrvaA.vsetkyRozneVajcia());
        //System.out.println(test.PrvaA.najvacsieVajce());
        //System.out.println(test.PrvaA.bezVajec());
        //System.out.println(test.PrvaA.najvacsiZberatel());
        System.out.println(test.PrvaA.najcastejsieVajce());
    }
}
