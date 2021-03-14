import java.util.Arrays;

public class DvojrozmernePole {
    public static String doStringu(Integer[][] pole) {
        if (pole == null) return null;

        StringBuilder vystup = new StringBuilder();               // mala som String, ale prostredie radilo to pretypovat :D
        for (Integer[] integers : pole) {
            if (integers != null) {                               // pre kazdy riadok ak nie je null -> vtedy davam len ;
                for (int j = 0; j < integers.length; j++) {       // pre kazdy prvok riadku
                    if (integers[j] != null) {
                        vystup.append(String.valueOf(integers[j]));
                    }
                    if (j != integers.length - 1) vystup.append(","); // ciarku davam len ak nie som na koncu riadku
                }
            }
            vystup.append(";");                                      // mohli by sme oifovat, ale ja na konci mazem ;
        }
        return vystup.substring(0, vystup.length() - 1);        // ; vymazem z posledneho miesta
    }

    public static Integer[][] zoStringu(String pole) {
        if (pole == null) return null;

        String[] poRiadkoch = pole.split(";", -1);      // splitnem si pole podla ; a dostanem pocet riedkov, dalo by sa naprogramovat count metodu ale toto mi prišlo rychlejsie
        Integer[][] vysledok = new Integer[poRiadkoch.length][];

        for (int i = 0; i < vysledok.length; i++){                              // po riadkoch
            String[] poStlpcoch = poRiadkoch[i].split(",", -1);     // pri kazdom prechode sa meni podla daneho riadku
            Integer[] stlpec = new Integer[poStlpcoch.length];                 // vieme kolko bude prvkov konkretnom v riadku i

            if(poRiadkoch[i].strip().equals("")) stlpec = null;                // lebo mozem mat aj viac whitespacov
            else {
                for (int j = 0; j < poStlpcoch.length; j++){                   // vytvorim riadok ktory na konci pridam do vysledneho zoznamu
                    String bez = poStlpcoch[j].strip();                         // zbavim sa medzier

                    if (bez.equals("")) stlpec[j] = null;
                    else {
                        stlpec[j] = Integer.valueOf(bez);                      // tu by mohla nastavat chyba
                    }
                }
            }
            vysledok[i] = stlpec;    // len priradenie daneho riadku do celeho zoznamu
        }
        return vysledok;
    }

    public static void main(String[] args) {
        System.out.println(zoStringu("1,2,3;4,5;;7,,8"));
    }
}
