import javax.sound.midi.Soundbank;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;



//	 	! FOR FREE !




public class Trieda {
	private final HashMap<Ziak, List<VelkonocneVajce>> trieda;

    public List<VelkonocneVajce> vsetkyRozneVajcia() {

        HashSet<VelkonocneVajce> von = new HashSet<>();

        trieda.values().stream().forEach(e -> {
            if (e != null) e.stream().forEach(f -> {
                if (f != null) von.add(f);
            });
        });

        return von.stream().collect(Collectors.toList());

    }    
    public List<Ziak> bezVajec() {

      return trieda.entrySet().stream().filter(e -> e != null && (e.getValue() == null || e.getValue().size() == 0)).map(Map.Entry::getKey).collect(Collectors.toList()); // doprogramuj
    }
    
    public Ziak najvacsiZberatel() {

        return trieda.entrySet().stream().max((a, b) -> {
            if (a.getValue() != null && b.getValue() != null) {
                if (a.getValue().size() == b.getValue().size())
                    return Integer.compare(a.getKey().getAge(), b.getKey().getAge());
                else
                    return a.getValue().size() < b.getValue().size() ? -1 : 1;
            }
            else if (a.getValue() == null)
                return -1;
            else
                return 1;
        } ).get().getKey();

    }


    public VelkonocneVajce najcastejsieVajce() {

        ArrayList<VelkonocneVajce> von = new ArrayList<>();

        trieda.values().stream().forEach(e -> {
            if (e != null) e.stream().forEach(f -> {
                if (f != null) von.add(f);
            });
        });

        HashSet<VelkonocneVajce> vonUnikatne = new HashSet<>(von);


      return vonUnikatne.stream().max((v1, v2) -> {
          if (v1 != null && v2 != null){
              if (Collections.frequency(von, v1) == Collections.frequency(von, v2))
                  return v2.compareTo(v1);
              else
                  return Collections.frequency(von, v1) < Collections.frequency(von, v2) ? -1 : 1;
          }
          else if (v1 == null)
              return -1;
          else
              return 1;
      }).get(); // doprogramuj
    }

    public VelkonocneVajce najvacsieVajce() {

        ArrayList<VelkonocneVajce> von = new ArrayList<>();

        trieda.values().stream().forEach(e -> {
            if (e != null) e.stream().forEach(f -> {
                if (f != null) von.add(f);
            });
        });

        return von.stream().max(VelkonocneVajce::compareTo).get();
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
