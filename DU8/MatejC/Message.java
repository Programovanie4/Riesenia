import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


public class Message {

    private static void getLines(String name, List<String> lines) {
        File file = new File(name);
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return;
        }
        for (File fil : listFiles) {
            if (fil.isDirectory()) {
                getLines(fil.getAbsolutePath(), lines);
            }
            if (fil.isFile()){
                try {
                    List<String> linesCopy = Files.readAllLines(Paths.get(String.valueOf(fil.toPath())));

                    lines.addAll(linesCopy);
                }
                catch (IOException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }

    private static List<Character> characters(List<String> lines) {
        List<Character> finalList = new ArrayList<>();
        List<String> mor = new ArrayList<>();
        for (String line : lines) {
            if (line.contains(".") || line.contains("-")) {
                StringBuilder strNew = new StringBuilder();
                for (Character ch : line.toCharArray()) {
                    if (ch == '.' || ch == '-') {
                        strNew.append(ch);
                    }
                }
                mor.add(strNew.toString());
            }
        }
        for (String str : mor) {
            String newCharStr = Morse.decode(str);
            finalList.add(newCharStr.charAt(0));
        }
        return finalList;
    }

    public static List<Character> getAlphabet() {
        List<String> lines = new ArrayList<>();
        getLines("./tajna-sprava", lines);
//        for (String line : lines) {
//            if (line.contains("-") || line.contains())
//        }
        List<Character> characterList = characters(lines);
        return characterList.stream().sorted().collect(Collectors.toList());
    }

    private static boolean controlWord(String word, List<Character> alpha) {
        List<Character> alphaCopy = new ArrayList<>(alpha);
        for (Character ch : word.toCharArray()) {
            if (!alphaCopy.contains(ch)) {
                return false;
            }
            alphaCopy.remove(ch);
        }
        return true;
    }

    public static List<String> getMessage() {
        List<String> englishW = englishWords();
        List<Character> alpha = getAlphabet();
        List<String> finalList = new ArrayList<>();
        while (!alpha.isEmpty()) {
            for (String word : englishW) {
                String wordCopy = word.toUpperCase();
                List<Character> w =wordCopy.chars().mapToObj(e -> (char)e).collect(Collectors.toList());
                if (!controlWord(wordCopy, alpha)) {
                    continue;
                }
                for (Character ch : wordCopy.toCharArray()) {
                    alpha.remove(ch);
                }
                finalList.add(wordCopy);
            }
        }
        return finalList;
    }

    private static List<String> englishWords() {
        File fil = new File("knuth_words");
        try {
            List<String> lines = Files.readAllLines(Paths.get(String.valueOf(fil.toPath())));
            return lines;
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public static void main(String[] args) {
        System.out.println(getAlphabet());
        System.out.println(getAlphabet().size());
        System.out.println(getMessage());
    }
}
