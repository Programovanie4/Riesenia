import java.util.Objects;

public class Vysledok {
    private final String idBezca;
    private final String konecneStanoviste;
    private final String cas;
    private final String porovnanieSPriemerom;

    public Vysledok(String idBezca, String konecneStanoviste, String cas, String porovnanieSPriemerom) {
        this.idBezca = idBezca;
        this.konecneStanoviste = konecneStanoviste;
        this.cas = cas;
        this.porovnanieSPriemerom = porovnanieSPriemerom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vysledok vysledok = (Vysledok) o;
        return idBezca.equals(vysledok.idBezca) && konecneStanoviste.equals(vysledok.konecneStanoviste) && cas.equals(vysledok.cas) && porovnanieSPriemerom.equals(vysledok.porovnanieSPriemerom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBezca, konecneStanoviste, cas, porovnanieSPriemerom);
    }

    @Override
    public String toString() {
        return "Vysledok{" +
                "idBezca='" + idBezca + '\'' +
                ", konecneStanoviste='" + konecneStanoviste + '\'' +
                ", cas='" + cas + '\'' +
                ", porovnanieSPriemerom='" + porovnanieSPriemerom + '\'' +
                '}';
    }
}
