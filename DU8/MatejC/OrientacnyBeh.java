import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class OrientacnyBeh {
    private final Map<String, List<String>> hracStanovistia;
    private final Map<String, List<String>> hracCasy;
    private final Map<String, LocalTime> hracCelkCas;
    private final Map<String, Map<String, LocalTime>> hracCasyStanovistia;
    private final List<String> stanovistia;

    public OrientacnyBeh(String cestaKSuboru) {
        hracStanovistia = new HashMap<>();
        hracCasy = new HashMap<>();
        stanovistia = new ArrayList<>();
        hracCelkCas = new HashMap<>();
        hracCasyStanovistia = new HashMap<>();
        File fil1 = new File(cestaKSuboru);
        int counter = 0;
        try {
            InputStreamReader isr = new InputStreamReader(
                    new FileInputStream(fil1), StandardCharsets.UTF_8);

            BufferedReader br = new BufferedReader(isr);
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                if (counter == 0) {
                    String[] splitLine = line.split(",");
                    stanovistia.addAll(Arrays.asList(splitLine));
                    counter++;
                }
                else {
                    String[] splitLine = line.split(",");
                    String idHraca = splitLine[0];
                    String stanov = splitLine[1];
                    String cas = splitLine[2];
                    hracStanovistia.computeIfAbsent(idHraca, k -> new ArrayList<>());
                    hracCasy.computeIfAbsent(idHraca, k -> new ArrayList<>());
                    hracStanovistia.get(idHraca).add(stanov);
                    hracCasy.get(idHraca).add(cas);
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        purify();
        pocitajCelkCas();
    }

    private void purify() {
        for (String key : hracStanovistia.keySet()) {
            int i = 0;
            while (i != hracStanovistia.get(key).size()) {
                if (!stanovistia.get(i).equals(hracStanovistia.get(key).get(i))) {
                    hracStanovistia.get(key).remove(i);
                    hracCasy.get(key).remove(i);
                    continue;
                }
                i++;
            }
        }
    }

    private void pocitajCelkCas() {
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");

        for (String key : hracCasy.keySet()) {
            LocalTime sumCas = LocalTime.parse("00:00:00", dateTimeFormatter2);
            for (int i = 1; i < hracCasy.get(key).size(); i++) {
                hracCasyStanovistia.computeIfAbsent(key, k -> new HashMap<>());
                hracCasyStanovistia.get(key).computeIfAbsent(stanovistia.get(0), k -> LocalTime.parse("00:00:00", dateTimeFormatter2));
                LocalTime time1 = LocalTime.parse(hracCasy.get(key).get(i-1), dateTimeFormatter1);
                LocalTime time2 = LocalTime.parse(hracCasy.get(key).get(i), dateTimeFormatter1);
                Duration rozd = Duration.between(time1, time2);
                sumCas = sumCas.plus(rozd);
                hracCasyStanovistia.get(key).put(stanovistia.get(i), sumCas);
            }

            hracCelkCas.put(key, sumCas);
        }
    }

    public List<Vysledok> vysledky() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        List<Vysledok> finalVysl = new ArrayList<>();
        Map<String, String> casyStanovistia = priemerneCasyPreStanovistia();
        for (String key : hracStanovistia.keySet()) {
            String idBezca = key;
            List<String> stanovistiaList = hracStanovistia.get(key);
            String konecneStanoviste = stanovistiaList.get(stanovistiaList.size()-1);
            String cas = hracCelkCas.get(key).toString();
            if (cas.length() == 5) {
                cas += ":00";
            }
            LocalTime pom = LocalTime.parse("00:00:00", dateTimeFormatter);
            LocalTime casStanovistie = LocalTime.parse(casyStanovistia.get(konecneStanoviste), dateTimeFormatter);
            LocalTime casHrac = hracCelkCas.get(key);
            String porovnanieSPriemerom;
            if (casStanovistie.compareTo(casHrac) > 0) {
                Duration dur = Duration.between(casHrac, casStanovistie);
                pom = pom.plus(dur);
                porovnanieSPriemerom = "-"+pom.toString();
                if (porovnanieSPriemerom.length() == 5) {
                    porovnanieSPriemerom += ":00";
                }
            }
            else {
                Duration dur = Duration.between(casStanovistie, casHrac);
                pom = pom.plus(dur);
                porovnanieSPriemerom = "+"+pom.toString();
                if (porovnanieSPriemerom.length() == 5) {
                    porovnanieSPriemerom += ":00";
                }
            }
            Vysledok vysl = new Vysledok(idBezca, konecneStanoviste, cas, porovnanieSPriemerom);
            finalVysl.add(vysl);
        }
        return finalVysl;
    }

    public Map<String, String> priemerneCasyPreStanovistia() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        Map<String, String> casy = new HashMap<>();
        for (String stan : stanovistia) {
            LocalTime celkSum = LocalTime.parse("00:00:00", dateTimeFormatter);
            long sum = celkSum.toNanoOfDay();
            int counter = 0;
            for (Map<String, LocalTime> mapa : hracCasyStanovistia.values()) {
                if (mapa.get(stan) != null) {
                    sum += mapa.get(stan).toNanoOfDay();
                    counter++;
                }
            }
            LocalTime priemSum = LocalTime.ofNanoOfDay(sum / counter);
            String priemSumStr = priemSum.toString();
            if (priemSumStr.length() == 5) {
                priemSumStr += ":00";
            }
            if (priemSumStr.length() > 8) {
                priemSumStr = priemSumStr.substring(0, 8);
            }
            casy.put(stan, priemSumStr);

        }
        return casy;
    }

    public static void main(String[] args) {
        OrientacnyBeh ob = new OrientacnyBeh("data.csv");

        for (Vysledok v : ob.vysledky()) {
            System.out.println(v);
        }

        // Vysledok{idBezca='B0001', konecneStanoviste='G', cas='06:00:00', porovnanieSPriemerom='+00:27:13'}
        // Vysledok{idBezca='B0002', konecneStanoviste='D', cas='02:15:08', porovnanieSPriemerom='-00:11:57'}
        // Vysledok{idBezca='B0003', konecneStanoviste='C', cas='01:43:21', porovnanieSPriemerom='+00:09:42'}
        // Vysledok{idBezca='B0004', konecneStanoviste='G', cas='05:05:34', porovnanieSPriemerom='-00:27:13'}

        System.out.println(ob.priemerneCasyPreStanovistia());
        // {A=00:00:00, B=00:40:16, C=01:33:39, D=02:27:05, E=03:39:49, F=04:30:19, G=05:32:47}
    }
}
