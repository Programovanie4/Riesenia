import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SirenieSpravy {
    private int informovani = 0;
    private int cas = 0;
    private int neinformovani = 0;

    public List<Set<String>> bubliny;
    public Set<String> panikari;

    public SirenieSpravy(List<Set<String>> bubliny, Set<String> panikari){
        this.bubliny = bubliny;
        this.panikari = panikari;
        Set<String> parents =new HashSet<>(panikari);
        Set<String> children =new HashSet<>();
        Set<String> visited =new HashSet<>();
        while(parents.size() != 0){
            for(String p : parents){
                for(Set<String> bublina : bubliny){
                    if(bublina.contains(p)){
                        children.addAll(new HashSet<>(bublina));
                    }
                }
            }
            visited.addAll(parents);
            parents = children;
            parents.removeAll(visited);
            children = new HashSet<>();
            cas++;
        }
        informovani = visited.size();
        Set<String> all = new HashSet<>(panikari);
        for(Set<String> bublina : bubliny){
            all.addAll(bublina);
        }
        neinformovani = all.size() - informovani;



    }
    public int informovani(){
        return informovani;
    }
    public int trvanie(){
        if(cas != 0){
            cas--;
        }
        return cas;
    }
    public int neinformovani(){
        return neinformovani;
    }
}
