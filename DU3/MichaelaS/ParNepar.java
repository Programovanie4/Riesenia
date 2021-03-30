public class ParNepar implements Sifra {
    boolean obratene;

    public ParNepar() { this.obratene = false; }
    public ParNepar(boolean obratene) { this.obratene = obratene; }

    public String sifruj(String text) {
        StringBuilder res = new StringBuilder();
        int middle_index = 0;
        for (int i = 0; i < text.length(); i++){
            if (i % 2 == (obratene ? 1 : 0)) {
                res.insert(middle_index, text.charAt(i));
                middle_index += 1;
            }
            else {  res.insert(res.length(), text.charAt(i)); }
        }
        return res.toString();
    }

    public String desifruj(String text) {
        StringBuilder res = new StringBuilder();
        int middle = (text.length()/2) - ((text.length()%2 == 0 && !obratene) ? 1 : 0);

        System.out.println(middle);

        for (int i = 0; i <= middle; i++) {
            if (!obratene) {
                res.append(text.charAt(i));
                if (!(i == middle && text.length() % 2 == 1)) {
                    res.append(text.charAt(middle+i+1));
                }
            }
            else {
                if (!(i == middle && text.length() % 2 == 0)) {
                    res.append(text.charAt(middle+i));
                }
                if (i == middle) { break; }
                res.append(text.charAt(i));
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(new ParNepar().sifruj("Spajany zoznam!")); //  -> "Saayzza!pjn onm"     15
        System.out.println(new ParNepar().sifruj("Spajany xzoznam!")); //    -> "pjn onmSaayzza!"  16
        System.out.println(new ParNepar(true).sifruj("Spajany zoznam!")); //    -> "pjn onmSaayzza!"

        System.out.println(new ParNepar().desifruj("Saayzza!pjn onm")); //  -> "Spajany zoznam!"    8
        System.out.println(new ParNepar().desifruj("Saayxonmpjn zza!")); //  -> "Spajany zoznam!"   8
        System.out.println(new ParNepar(true).desifruj("pjn onmSaayzza!")); //    -> "Spajany zoznam!"  15  7

        System.out.println(new ParNepar(true).desifruj("13570246")); // 01234567
    }

}
