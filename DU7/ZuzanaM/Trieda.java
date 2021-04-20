import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


//FOR FREE


public class Trieda {
	private final HashMap<Ziak, List<VelkonocneVajce>> trieda;

    public List<VelkonocneVajce> vsetkyRozneVajcia() {
        Set vysl = new HashSet();
      trieda.values().stream().forEach(v-> {if (v!=null) v.stream().forEach(u->{if(u!=null)vysl.add(u);});});
      return List.copyOf(vysl);
    }
    public List<Ziak> bezVajec() {
      return trieda.keySet().stream().filter(v -> trieda.get(v)==null || trieda.get(v).size()==0).collect(Collectors.toList()); // doprogramuj
    }
    public Ziak najvacsiZberatel() {
        return trieda.keySet().stream().filter(v -> trieda.get(v)!=null).max((z1, z2)->{
                    if(trieda.get(z1).size()==trieda.get(z2).size()){
                        return Integer.compare(z1.getAge(), z2.getAge());
                    }
                    return Integer.compare(trieda.get(z1).size(), trieda.get(z2).size());
                }).get(); // doprogramuj
    }
    public VelkonocneVajce najcastejsieVajce() {
        var vajcaa = trieda.values().stream().filter(v -> v != null && v.size()!=0).flatMap(v -> v.stream()).collect(Collectors.toList());
        return trieda.values().stream().filter(v -> v != null && v.size()!=0).flatMap(v -> v.stream())
                .max((v1, v2) -> {
                            if(Collections.frequency(vajcaa ,v1)==Collections.frequency(vajcaa ,v2)){
                                if(v1.compareTo(v2)==-1) return 1;
                                return -1;
                            }
                            return Integer.compare(Collections.frequency(vajcaa ,v1), Collections.frequency(vajcaa ,v2));}).get();
        // doprogramuj
    }
    public VelkonocneVajce najvacsieVajce() {
      return trieda.values().stream().filter(v -> v != null && v.size()!=0).flatMap(v -> v.stream()).max((v1, v2) -> v1.compareTo(v2)).get(); // doprogramuj
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
}
