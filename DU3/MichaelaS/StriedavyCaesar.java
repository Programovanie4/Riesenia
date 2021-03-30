public class StriedavyCaesar implements Sifra {
    int offset;

    public StriedavyCaesar(int offset) { this.offset = offset; }

    private char sifrujZnak(char znak, int offset) {
        char novy = (char) (znak + offset);
        if (novy < ' ') {
            novy = (char) ('~' - ((' ' - novy) -1));
        }
        if (novy > '~') {
            novy = (char) (' ' + ((novy - '~') - 1));
        }
        return novy;
    }

    public String sifruj(String text) {
        StringBuilder res = new StringBuilder();
        for (char ch: text.toCharArray()) {
            res.append(sifrujZnak(ch, offset));
            offset *= -1;
        }
        offset = Math.abs(offset);
        return res.toString();
    }

    public String desifruj(String text) {
        offset *= -1;
        return sifruj(text);
    }

    public static void main(String[] args) {
        System.out.println(new StriedavyCaesar(12).sifruj("Raz dva tri styri!")); // -> "^U'spjms!fus h&fut";
        System.out.println(new StriedavyCaesar(2).sifruj("dddddddddd")); // -> "fbfbfbfbfb"

        System.out.println(new StriedavyCaesar(12).desifruj("^U'spjms!fus h&fut")); // -> "Raz dva tri styri!"
        System.out.println(new StriedavyCaesar(2).desifruj("fbfbfbfbfb")); // -> "dddddddddd"
    }

}
