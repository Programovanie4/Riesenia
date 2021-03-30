import java.sql.SQLOutput;

public class StriedavyCaesar implements Sifra {
    int offset;

    public StriedavyCaesar(int offset) {
        this.offset = offset;
    }

    public char sifrujZnak(char c, int offset) {
        if (c + offset > 126) return (char) ((c + offset - 126) + 31);
        if (c + offset < 32) return (char) (127 - (32 - (c + offset)));
        return (char) (c + offset);
    }

    @Override
    public String sifruj(String text) {
        StringBuilder novy = new StringBuilder();
        int o = offset;
        for (int i = 0; i < text.length(); i++) {
            novy.append(sifrujZnak(text.charAt(i), o));
            o *= -1;
        }
        return novy.toString();
    }


    @Override
    public String desifruj(String text) {
        StringBuilder novy = new StringBuilder();
        int o = offset;
        for (int i = 0; i < text.length(); i++) {
            novy.append(sifrujZnak(text.charAt(i), -o));
            o *= -1;
        }
        return novy.toString();
    }

    public static void main(String[] args) {
        System.out.println(new StriedavyCaesar(2).sifruj("dddddddddd")); // -> "fbfbfbfbfb"
        System.out.println(new StriedavyCaesar(12).sifruj("Raz dva tri styri!")); // -> "^U'spjms!fus h&fut"
    }
}
