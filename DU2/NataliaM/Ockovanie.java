public class Ockovanie{
    static boolean format(String vstup){                // velmi sa ospravedlnujem za skaredy a neprehladny kod
        if(vstup.startsWith("00")) return false;
        if(vstup.startsWith("00", 4)) return false;     // dalo by sa overovat v regexe ale zatiel ho tak neovladam
        if(vstup.startsWith("00", 2)) return false;

        if((vstup.startsWith("02", 2) || vstup.startsWith("52", 2))  && vstup.substring(4, 6).equals("29")){
            if (Integer.parseInt(vstup.substring(0, 2)) % 4 != 0) return false;
        }
        return vstup.matches("\\d{6}/\\d{3,4}")                             // format
                && (!vstup.matches("\\d{2}(52|02)(30|31)/\\d{3,4}"))       //februar
                && (!vstup.matches("\\d{2}(61|11)(31|00)/\\d{3,4}"))
                && (!vstup.matches("\\d{2}(00|50)\\d{2}/\\d{3,4}"))         // neexistuje rok
                && (!vstup.matches("\\d{4}00/\\d{3,4}"))
                && (!vstup.matches("\\d{2}(54|04)(31)/\\d{3,4}"));
    }

    static boolean po53(String vstup){
        return (Long.parseLong(vstup.replace("/", "")) % 11) == 0;
    }

    public static int korektni(String[] osoby){
        int vysledok = 0;

        for (String os : osoby) {
            String[] udaje = os.split(",");
            String rodneCislo = udaje[1].trim();            // dalej sa pozeram len na rodne cisla
            // System.out.println("KOREKTNI " + rodneCislo);

            if (format(rodneCislo)) {
                if ((rodneCislo.matches("\\d{6}/\\d{3}"))) {        // 3 miestne za /
                    vysledok++;
                }
                else if ((rodneCislo.matches("\\d{6}/\\d{4}") && po53(rodneCislo))) {
                    vysledok++;
                }
            }
        }
        return vysledok;
    }

    public static int kandidati75(String[] osoby){
        int vysledok = 0;

        for (String os : osoby) {
            String[] udaje = os.split(",");
            String rodneCislo = udaje[1].trim();
            // System.out.println("75 " + rodneCislo);

            if (format(rodneCislo) && (rodneCislo.matches("\\d{6}/\\d{3}"))){
                if ((rodneCislo.matches("[1-3]\\d{5}/\\d{3,4}")) || rodneCislo.matches("(4)[0-6]\\d{4}/\\d{3,4}")) {        // kontrola datum
                    vysledok++;
                }
            }
        }

        return vysledok;
    }

    public static void main(String[] args) {
        System.out.println(korektni(new String[]{
                "a, 050229/0008, mail",
                "a,445001/004,827,a@b.c",
                "Jožko Mrkvička, 771224/1240, 0902 354 787, jozo.mrkva@gmail.com",
                "Sansa Starková, 995321/1235, 0999999999, allmenmustdie@gmail.com",
                "Rysavá Jalovica, 600600, , " ,

        }));
        System.out.println(kandidati75(new String[]{
                "Jožko Mrkvička, 771224/1240, 0902 354 787, jozo.mrkva@gmail.com",
                "Jožko Púčik, 430211/837, 0912 454 732, jozo.pucik@gmail.com"
        }));
    }
}

