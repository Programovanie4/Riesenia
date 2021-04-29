import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.io.*;
import java.time.Duration;
import java.time.LocalTime;

// chcel som spravit aj bonus, ale nefunguje...

public class Subtitles {

    private static void createCopy(File fil1, String encoding) {
        File fil2 = new File("pom.srt");

        try {
            InputStreamReader isr = new InputStreamReader(
                    new FileInputStream(fil1), StandardCharsets.UTF_8);

            BufferedReader br = new BufferedReader(isr);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(fil2), encoding));
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                bw.write(line);
                bw.newLine();
            }
            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean control() {
        File fil1 = new File("pom.srt");
        try {
            List<String> lines = Files.readAllLines(Paths.get(String.valueOf(fil1.toPath())));
            if (lines.stream().anyMatch(e -> e.contains("�"))) {
                return false;
            }
            if (lines.stream().anyMatch(e -> e.contains("\u009E"))) {
                return false;
            }
        }
        catch (MalformedInputException e) {
            //e.printStackTrace();
            return false;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void addAds(String inFile, String outFile, String inEnc, String outEnc, List<String> ads) {
        File fil1 = new File(inFile);
        File fil2 = new File(outFile);
        if (inEnc == null) {
            String iso = "ISO8859_";
            List<String> encodings = new ArrayList<>(List.of("UTF8","UTF16"));
            createCopy(fil1, "UTF8");
            for (int i = 2; i < 10; i++) {
                encodings.add(iso+i);
            }
            encodings.add("WINDOWS-1250");
            encodings.add("Cp1250");
            String encActual = "UTF8";
            for (String enc : encodings) {
                if (!control()) {
                    createCopy(fil1, enc);
                    encActual = enc;

                }
                else {
                    inEnc = encActual;
                    break;

                }
            }
        }

        if (inEnc == null) {
            inEnc = "UTF8";
        }
        List<String[]> linesSub = new ArrayList<>();
        List<String> linesAll = new ArrayList<>();
        try {
            InputStreamReader isr = new InputStreamReader(
                    new FileInputStream(fil1), inEnc);

            BufferedReader br = new BufferedReader(isr);
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }

                String[] splitLine = line.split(" --> ");
                if (splitLine.length == 2) {
                    linesSub.add(splitLine);
                }
                linesAll.add(line);

            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String[]> adsList = new ArrayList<>();
        for (String str : ads) {
            String[] splitLine = str.split(" --> ");
            adsList.add(splitLine);
        }
        Collections.sort(adsList, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return o1[0].compareTo(o2[0]);
            }
        });

        for (String[] zoz1 : adsList) {
            findSub(zoz1, linesSub);
        }
        int counter = 0;
        List<String> linesAllCopy = new ArrayList<>();
        for (String str : linesAll) {
            if (str.contains(" --> ")) {
                String[] zoz = linesSub.get(counter);
                String newStr = zoz[0] + " --> " + zoz[1];
                linesAllCopy.add(newStr);
                counter++;
            }
            else {
                linesAllCopy.add(str);
            }
        }
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(fil2), outEnc));
            for (String str : linesAllCopy) {
                bw.write(str);
                bw.newLine();
            }
            bw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void findSub(String[] zoz1, List<String[]> lines) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss,SSS");
        LocalTime timeAdd1 = LocalTime.parse(zoz1[0], dateTimeFormatter);
        LocalTime timeAdd2 = LocalTime.parse(zoz1[1], dateTimeFormatter);
        int counter = 0;
        for (String[] zoz : lines) {
            LocalTime timeSub1;
            LocalTime timeSub2;
            try {
                timeSub1 = LocalTime.parse(zoz[0], dateTimeFormatter);
            }
            catch (DateTimeParseException e) {
                DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
                timeSub1 = LocalTime.parse(zoz[0], dateTimeFormatter2);
            }
            try {
                timeSub2 = LocalTime.parse(zoz[1], dateTimeFormatter);
            }
            catch (DateTimeParseException e) {
                DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
                timeSub2 = LocalTime.parse(zoz[1], dateTimeFormatter2);
            }
            if (timeSub2.compareTo(timeAdd1) > 0) {
                int ind = counter;
                Duration rozd1 = Duration.between(timeAdd1, timeSub1);
                Duration durAdd = Duration.between(timeAdd1, timeAdd2);
                Duration durSub = Duration.between(timeSub1, timeSub2);
                timeSub1 = timeSub1.plus(durAdd);
               // timeSub1 = timeSub1.plus(rozd1);
                timeSub2 = timeSub1.plus(durSub);
                String timeSub1Str = timeSub1.toString();
                String timeSub2Str = timeSub2.toString();
                zoz[0] = timeSub1Str.replace('.', ',');
                zoz[1] = timeSub2Str.replace('.', ',');
                addToAll(lines, counter+1, durAdd);
                return;
            }
            counter++;
        }
    }

    private static void addToAll(List<String[]> lines, int index, Duration durAdd) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss,SSS");
        int counter = 0;
        for (String[] zoz : lines) {
            if (counter < index) {
                counter++;
                continue;
            }
            LocalTime timeSub1;
            LocalTime timeSub2;
            try {
                timeSub1 = LocalTime.parse(zoz[0], dateTimeFormatter);
            }
            catch (DateTimeParseException e) {
                DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
                timeSub1 = LocalTime.parse(zoz[0], dateTimeFormatter2);

            }
            try {
                timeSub2 = LocalTime.parse(zoz[1], dateTimeFormatter);
            }
            catch (DateTimeParseException e) {
                DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
                timeSub2 = LocalTime.parse(zoz[1], dateTimeFormatter2);
            }
            Duration durSub = Duration.between(timeSub1, timeSub2);
            timeSub1 = timeSub1.plus(durAdd);
            timeSub2 = timeSub1.plus(durSub);
            String timeSub1Str = timeSub1.toString();
            String timeSub2Str = timeSub2.toString();
            zoz[0] = timeSub1Str.replace('.', ',');
            zoz[1] = timeSub2Str.replace('.', ',');
        }
    }
}
