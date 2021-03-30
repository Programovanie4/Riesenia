import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class Castovanie<E> {
    void priklad(E hodnota) {

        // Problem z cvicenia, preco nam nefungoval priklad s "PrioritizovanyElement<E, Integer>[]"?
        // Namiesto PrioritizovanyElement<K, V extends Number> pouzijem List<E>, nech sa viete lahko
        // s tymto kodom pohrat.

        // Idea nam napise: "Casting 'new Object[10]' to 'ArrayList<E>[]' will
        //                   produce 'ClassCastException' for any non-null value"
        // A ma pravdu.
        //
        // Je to koli tomu, ze ArrayList je podtyp typu Object. Takto sa v jave castovat neda. Resp. da, ale vysledok
        // je pre vsetko okrem null runtime error. Explicitnym castom "slubime" kompilatoru, ze vieme co robime. Runtime
        // ale overi, ci sme slub dodrzali:)
        List<E>[] pole1 = (ArrayList<E>[]) new Object[10];

        // Rovnaky problem sa deje aj v jednoduchsich pripadoch, ked napriklad nepouzijem pole
        List<E> list1 = (ArrayList<E>) new Object();

        // Alebo ked nepouzijem typovy parameter E
        List<Integer> list2 = (ArrayList<Integer>) new Object();

        // Pripadne vobec nepouzijem genericky typ
        Integer cislo = (Integer) new Object();

        // Toto je teda problem s castovanim, nie s genericsami. Ostava otazka - preco funguje E[]?
        // Tu sa dostavame k genericsom.

        // Odpoved je, pretoze typovy parameter E je zmazany (ak chcete vediet viac, googlite "type erasure",
        // je to velmi zaujimmave, ukazoval som to iba v rychlosti pocas cvicenia).
        // Pole1 je naozaj Object-ove. Necastujem do nadtypu. Cize to ide. Toto je ale OK robit iba vtedy, ked taketo
        // pole nevypustime von do sveta. Vid priklad nizsie.

        E[] pole2 = (E[]) new Object[10];

        // Na zamyslenie / vyskusanie, keby ste sa niekto prilis nudili. Toto ukazuje, ze pristup vyssie nie je
        // uplne bezpecny a treba si davat pozor. Co vypise toto? Prijemnu zabavu :)


    }

}
class Krabica<T> {
    public T[] ts;

    @SuppressWarnings("unchecked")
    public Krabica(T elem) {
        ts = (T[]) new Object[1];
        ts[0] = elem;
    }

    public static void main(String[] args) {
        Krabica<Integer> ki = new Krabica<Integer>(7);
        System.out.println(ki.ts[0]);
    }
}