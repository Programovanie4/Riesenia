public class ZlozenaSifra implements Sifra {
    Sifra[] sifry;

    public ZlozenaSifra(Sifra[] sifry) {
        this.sifry = sifry;
    }

    public String sifruj(String text) {
        for (Sifra sifra: sifry) {
            text = sifra.sifruj(text);
        }
        return text;
    }

    public String desifruj(String text) {
        for (int i = sifry.length-1; i >= 0; i--) {
            text = sifry[i].desifruj(text);
        }
        return text;
    }

    public static void main(String[] args) {
        System.out.println(new ZlozenaSifra(new Sifra[] { new StriedavyCaesar(4), new ParNepar() }).sifruj("Retiazkove pravidlo.")); //  -> "Vxeoz$vzhsaevkal]eh*";
        System.out.println(new ZlozenaSifra(new Sifra[] { new StriedavyCaesar(4), new ParNepar() }).desifruj("Vxeoz$vzhsaevkal]eh*")); //  -> "Retiazkove pravidlo.";
    }
}
