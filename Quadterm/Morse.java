import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Morse {
    static String[] letters = new String[255];
    static {
        letters['A'] = ".-";
        letters['B'] = "-...";
        letters['C'] = "-.-.";
        letters['D'] = "-..";
        letters['E'] = ".";
        letters['F'] = "..-.";
        letters['G'] = "--.";
        letters['H'] = "....";
        letters['I'] = "..";
        letters['J'] = ".---";
        letters['K'] = "-.-";
        letters['L'] = ".-..";
        letters['M'] = "--";
        letters['N'] = "-.";
        letters['O'] = "---";
        letters['P'] = ".--.";
        letters['Q'] = "--.-";
        letters['R'] = ".-.";
        letters['S'] = "...";
        letters['T'] = "-";
        letters['U'] = "..-";
        letters['V'] = "...-";
        letters['W'] = ".--";
        letters['X'] = "-..-";
        letters['Y'] = "-.--";
        letters['Z'] = "--..";
    }
    //------------------------------------------------------ dopisujte odtialto nizsie
    /**
     * @param anglickaSprava - retazec pismen anglickej abecedy 'A'-'Z' a medzier
     * @return - preklad do morseho kodu, jednotlive pismena 'A'-'Z' su oddelene jednou mezerou, vstupne medzery sa ignoruju
     */
    public static String koduj(String anglickaSprava) {
        //return null; // toto doprogramuj
        var sb = new StringBuilder();
        for(var ch : anglickaSprava.toCharArray()) {
            if (ch != ' ')
                sb.append(letters[ch] + " ");
        }
        return sb.toString().trim();
    }
    /**
     * dekoduje stream Morseho symbolov oddelenych aspon nejakymi medzerami
     */
    public static String dekoduj(String sprava) {
        //return null; // toto doprogramuj
        var sb = new StringBuilder();
        lab:
        for(var parts :sprava.split(" "))
            if (!parts.isEmpty()) {
                for (var ch = 'A'; ch <= 'Z'; ch++)
                    if (letters[ch].equals(parts)) {
                        sb.append(ch);
                        continue lab;
                    }
                return null;
            }
        return sb.toString();
    }
    /**
     * inverzny homomorfizmus - dekoduje stream Morseho symbolov neoddelenych medzerami, vsetky moznosti
     */
    public static Set<String> dekodujVsetky(String sprava) {
        // return null; // toto doprogramuj
        if (sprava.isEmpty())
            return Set.of("");
        else {
            var result = new HashSet<String>();
            for (var ch = 'A'; ch <= 'Z'; ch++) {
                final char znak = ch;
                if (sprava.startsWith(letters[ch]))
                    result.addAll(dekodujVsetky(
                            sprava.substring(letters[ch].length())).stream().map(x -> znak + x)
                            .collect(Collectors.toSet()));
            }
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(koduj("S O S")); // vráti "... --- ...",
        System.out.println(koduj("S O S SOS SO    S")); // vráti "... --- ... ... --- ... ... --- ...",
        System.out.println(dekoduj("-- .- -.-- -.. .- -.--")); // vráti "MAYDAY"

        System.out.println(dekodujVsetky(".--"));
        System.out.println(dekodujVsetky("...."));
        System.out.println(dekodujVsetky("....."));
    }
}
