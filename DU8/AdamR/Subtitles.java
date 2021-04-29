import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.LocalTime;

import static java.time.temporal.ChronoUnit.NANOS;

public class Subtitles {
    public static void addAds(String inFile, String outFile, String inEnc, String outEnc, List<String> ads){
        //vsetky titulky
        List<Subtitle> vsetky = new LinkedList<>();
        File input = new File(inFile);
        Scanner reader;
        try {
            reader = new Scanner(input, inEnc == null ? "UTF8" : inEnc);
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
            return;
        }
        //format času v titulkach
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss,SSS");
        while(reader.hasNextLine()){
            //nacitam hlavicku a casy
            int id = Integer.parseInt(reader.nextLine());
            var casy = reader.nextLine().split(" --> ");
            var from = LocalTime.parse(casy[0], dtf);
            var to = LocalTime.parse(casy[1], dtf);
            var lines = new LinkedList<String>();

            //nacitam titulky
            String data = reader.nextLine();
            while(!data.equals("")){
                lines.add(data);
                if(!reader.hasNextLine()) break;
                //viacriadkove titulky
                data = reader.nextLine();
            }
            //vlozim zaznam
            vsetky.add(new Subtitle(id,from,to,lines));
        }
        reader.close();

        //nemame reklamy, rovno zapisujem titlky
        if(ads == null || ads.size() == 0) {
            writeSubtitles(vsetky, outFile, outEnc);
            return;
        }

        //premapujem si stringove reklamy na moje Reklamy s časmi
        Reklama[] reklamy = new Reklama[ads.size()];
        for(int i = 0; i < ads.size(); i++) reklamy[i] = new Reklama(ads.get(i));

        //zoradime reklamy podla toho ktora začina skor
        Arrays.sort(reklamy, (l,r) -> {
            Long lava = l.getFrom().toNanoOfDay();
            Long prava = r.getFrom().toNanoOfDay();
            if(lava < prava) return -1;
            else if(lava.equals(prava)) return 0;
            else return 1;
        });

        //pre kazdu reklamu, posuniem kazdy titulok, ktory začína po tejto reklame
        for(Reklama r: reklamy){
            for(Subtitle s: vsetky){
                if(s.jePoReklame(r)){
                    s.posun(r.getDuration());
                }
            }
        }

        //zapiseme titulky
        writeSubtitles(vsetky,outFile,outEnc);
    }

    private static void writeSubtitles(List<Subtitle> subs, String outFile, String outEnc){
        File output = new File(outFile);
        try {
            output.createNewFile();
            Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outFile), outEnc == null ? "UTF8" : outEnc));

            for(int i = 0; i < subs.size(); i++){
                writer.write(subs.get(i).toString());
                if(i < subs.size()-1)
                    writer.write('\n');
            }

            writer.close();
        }
        catch(IOException e){
            System.out.println("nastala chyba");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> subs = List.of("00:00:22,123 --> 00:02:27,623");
        addAds("input1.srt", "test.srt", null, "UTF8", subs);

    }
}
class Subtitle{
    private int id;
    public LocalTime from;
    public LocalTime to;
    private List<String> lines;

    public Subtitle(int id, LocalTime from, LocalTime to, List<String> lines){
        this.id = id;
        this.lines = lines;
        this.from = from;
        this.to = to;
    }
    public List<String> getLines() {
        return lines;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss,SSS");
        res.append(id).append('\n');
        res.append(from.format(dtf)).append(" --> ").append(to.format(dtf)).append('\n');
        for(String line: lines){
            res.append(line).append('\n');
        }
        return res.toString();
    }

    //posunie začiatok a koniec o dany počet nanos
    public void posun(Long nanos){
        from = LocalTime.ofNanoOfDay(from.toNanoOfDay() + nanos);
        to = LocalTime.ofNanoOfDay(to.toNanoOfDay() + nanos);
    }

    //boolean, či je titulok až po reklame,
    public boolean jePoReklame(Reklama reklama){
        return this.from.toNanoOfDay() >= reklama.getFrom().toNanoOfDay();
    }
}

class Reklama{
    private final LocalTime from;
    private final LocalTime to;
    private final Long duration;

    public Reklama(String reklama){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss,SSS");
        var casy = reklama.split(" --> ");
        this.from = LocalTime.parse(casy[0], dtf);
        this.to = LocalTime.parse(casy[1], dtf);
        //vypočitame si trvanie reklamy, ktore využijeme pri posuvani titulkov
        this.duration = from.until(to, NANOS);
    }
    public LocalTime getTo() {
        return to;
    }
    public LocalTime getFrom() {
        return from;
    }

    public Long getDuration(){
        return duration;
    }

    @Override
    public String toString(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss,SSS");
        return from.format(dtf) + " --> " + to.format(dtf) + " | dur: " + duration;
    }
}
