public class ParNepar implements Sifra {
    boolean obratene;

    ParNepar(boolean obratene) {
        this.obratene = obratene;
    }

    ParNepar() {
       this(false);
    }

    @Override
    public String sifruj(String text) {
        StringBuilder parne = new StringBuilder(), neparne = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (i % 2 == 0) parne.append(text.charAt(i));
            else neparne.append(text.charAt(i));
        }

        if (obratene) return neparne.toString() + parne.toString();
        return parne.toString() + neparne.toString();
    }

    @Override
    public String desifruj(String text) {
        if (text == null || text.length() == 1) return text;
        StringBuilder parne = new StringBuilder(), neparne = new StringBuilder();
        int till = (obratene && text.length() % 2 == 1) ? (text.length() / 2) - 1 : (text.length() - 1) / 2;
        for (int i = 0; i < text.length(); i++) {
            if (i <= till) {
                if (obratene) neparne.append(text.charAt(i));
                else parne.append(text.charAt(i));
            } else {
                if (obratene) parne.append(text.charAt(i));
                else neparne.append(text.charAt(i));
            }
        }
        //obratene tak neparne prve
        int i = 0, j = 0, k = 0;
        StringBuilder temp = new StringBuilder();
        while (j < parne.length() || k < neparne.length()) {
            if (j < parne.length())
                temp.append(parne.charAt(j));
            if (k < neparne.length())
                temp.append(neparne.charAt(k));

            j++;  k++;
        }
        return temp.toString();
    }

    public static void main(String[] args) {
        System.out.println(new ParNepar().sifruj("Spajany zoznam!")); // -> "Saayzza!pjn onm"
        System.out.println(new ParNepar(true).sifruj("Spajany zoznam!")); //  -> "pjn onmSaayzza!"

// sekcia s pripadmi s parnou a neparnou dlzkou pre ParNepar

        System.out.println("----------------------");
        System.out.println(new ParNepar().desifruj("135246")); //"123456"
        System.out.println(new ParNepar().sifruj("01234567")); // -> "02461357"
        System.out.println(new ParNepar().sifruj("0123456")); // -> "0246135"
        System.out.println("----------------------");
        System.out.println(new ParNepar().desifruj("02461357"));
        System.out.println(new ParNepar().desifruj("0246135"));
        System.out.println(new ParNepar(true).desifruj("1350246"));
        System.out.println(new ParNepar(true).desifruj("13570246"));
//        System.out.println(new ParNepar(true).sifruj("01234567")); // -> "13570246"
//        System.out.println(new ParNepar(true).sifruj("0123456")); // -> "1350246"
    }
}
