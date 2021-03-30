public class ZlozenaSifra implements Sifra {
    Sifra[] sifry;

    ZlozenaSifra(Sifra[] sifry) {
        this.sifry = sifry;
    }

    @Override
    public String sifruj(String text) {
        for (Sifra s: sifry) {
            text = s.sifruj(text);
        }
        return text;
    }

    @Override
    public String desifruj(String text) {
        for (int i = sifry.length - 1; i >= 0; i--) {
            text = sifry[i].desifruj(text);
        }
        return text;
    }

    public static void main(String[] args) {
        System.out.println(new StriedavyCaesar(2).sifruj("dddddddddd")); // -> "fbfbfbfbfb"
        System.out.println(new StriedavyCaesar(12).sifruj("Raz dva tri styri!")); // -> "^U'spjms!fus h&fut"
        System.out.println(new ZlozenaSifra(new Sifra[] { new StriedavyCaesar(4), new ParNepar() }).sifruj("Retiazkove pravidlo.")); // -> "Vxeoz$vzhsaevkal]eh*"
        System.out.println(new ZlozenaSifra(new Sifra[] { new StriedavyCaesar(2), new ParNepar() }).desifruj("Hmncovovm.h|m\"\"pqovm0_sr}_c_gw}wgw_gdp_gw"));
        System.out.println("---------------------------------------");

        System.out.println(new ZlozenaSifra(new Sifra[] { new ParNepar(), new StriedavyCaesar(2) }).sifruj("Fa[kulta matematiky, fyziky a informatiky]."));
    }
}
