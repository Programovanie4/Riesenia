import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import java.time.LocalDateTime;
public class OrientacnyBeh {
    List<String> stanovista;
    Map<String,Bezec> bezci;

    public OrientacnyBeh(String cestaKSuboru) {
        stanovista = new ArrayList<>();
        bezci = new HashMap<>();

        File data = new File(cestaKSuboru);
        if(!data.exists() || !data.isFile()) {
            System.out.println("Zadal si priečinok, alebo subor neexistuje");
            return;
        }
        Scanner scan_csv;
        try {
            scan_csv = new Scanner(data);
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
            return;
        }

        if(scan_csv.hasNextLine()){
            stanovista = Arrays.asList(scan_csv.nextLine().split(","));
        }
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        scan_csv.useDelimiter("[,\n]");

        while(scan_csv.hasNextLine()){
            String id = scan_csv.next();
            if(bezci.get(id) == null)
                bezci.put(id, new Bezec(id));

            String stanoviste = scan_csv.next();
            LocalDateTime cas = LocalDateTime.parse(scan_csv.next(),format);

            bezci.get(id).append(new Node(stanoviste,cas));

            if(scan_csv.hasNextLine())
                scan_csv.nextLine();
        }
        for(Bezec b: bezci.values()){
            b.vypocet(stanovista);
        }
    }

    public List<Vysledok> vysledky() {
        var priemer = priemerneCasyPreStanovistia();
        List<Vysledok> res = new ArrayList<>();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");

        for(Bezec b: bezci.values()){
            Vysledok vysl = new Vysledok(b.getId(),
                    b.last,
                    b.casy.get(b.last) == null ? "00:00:00" : b.casy.get(b.last).format(format),
                    b.casy.get(b.last) == null ? "00:00:00" : rozdielCasovString(b.casy.get(b.last), LocalTime.parse(priemer.get(b.last))
                    ));
            res.add(vysl);
        }
        return res;
    }

    public Map<String, String> priemerneCasyPreStanovistia() {
        Map<String, String> priemer = new HashMap<>();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
        for(String s: stanovista){
            int sekundySpolu = 0;
            //počet bežcvo čo dosiahli tento bod
            int pocet = 0;
            for(Bezec b: bezci.values()){
                pocet += b.casy.get(s) == null ? 0 : 1;
                sekundySpolu += b.casy.get(s) == null ? 0 : vSekundach(b.casy.get(s));
            }
            if(pocet > 0) {
                LocalTime spolu = LocalTime.ofSecondOfDay(sekundySpolu / pocet);
                priemer.put(s, spolu.format(format));
            }
            else priemer.put(s,"00:00:00");
        }
        return priemer;
    }

    private long vSekundach(LocalTime cas){
        long res = 0;
        res += cas.getHour()*60*60;
        res += cas.getMinute()*60;
        res += cas.getSecond();
        return res;
    }

    private String rozdielCasovString(LocalTime lt, LocalTime rt){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
        long lSec = vSekundach(lt);
        long rSec = vSekundach(rt);
        String znamienko = "+";
        if(lSec < rSec) znamienko = "-";
        LocalTime rozdiel = LocalTime.ofSecondOfDay(Math.abs(lSec-rSec));
        return znamienko + rozdiel.format(format);
    }

    public static void main(String[] args) {
        OrientacnyBeh ob = new OrientacnyBeh("data.csv");
        System.out.println(ob.stanovista);
        List<String> stan = List.of("A", "B", "C", "D", "E", "F","G");
        for(Bezec b: ob.bezci.values()){
            System.out.println(b.getId() + b.casy);
        }
        System.out.println(ob.priemerneCasyPreStanovistia());
        System.out.println(ob.vysledky());


        /*
        for (Vysledok v : ob.vysledky()) {
            System.out.println(v);
        }
         */

        // Vysledok{idBezca='B0001', konecneStanoviste='G', cas='06:00:00', porovnanieSPriemerom='+00:27:13'}
        // Vysledok{idBezca='B0002', konecneStanoviste='D', cas='02:15:08', porovnanieSPriemerom='-00:11:57'}
        // Vysledok{idBezca='B0003', konecneStanoviste='C', cas='01:43:21', porovnanieSPriemerom='+00:09:42'}
        // Vysledok{idBezca='B0004', konecneStanoviste='G', cas='05:05:34', porovnanieSPriemerom='-00:27:13'}

        //System.out.println(ob.priemerneCasyPreStanovistia());
        // {A=00:00:00, B=00:40:16, C=01:33:39, D=02:27:05, E=03:39:49, F=04:30:19, G=05:32:47}
    }
}
