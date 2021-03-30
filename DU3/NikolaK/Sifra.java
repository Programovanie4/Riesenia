// Sem nedoplnajte nic, interface je kompletny

public interface Sifra {
    String sifruj(String text);
    String desifruj(String text);

    public static void main(String[] args) {
        System.out.println(new ParNepar().sifruj("0123456"));
        System.out.println(new ParNepar(true).sifruj("Spajany zoznam!"));
        System.out.println(new ParNepar().desifruj("0246135"));
        System.out.println(new StriedavyCaesar(10).desifruj(new StriedavyCaesar(10).sifruj("Raz dva tri styri!")));
        System.out.println(

                new ZlozenaSifra(new Sifra[] { new  StriedavyCaesar(10)}).sifruj("Raz dva tri styri!") );
    }
}
