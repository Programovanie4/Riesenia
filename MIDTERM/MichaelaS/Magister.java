public class Magister extends Matfyzak { // doprogramuj
    String meno;

    public Magister(String meno) { this.meno = meno; }
    public Magister() { }


    public Integer goo() {
        return 1;
    }

    public static String foo() {
        return "A";
    }

    @Override
    public int hashCode() {
        return meno.hashCode();
    }

    @Override
    public int compareTo(Object o) {
        if (this == o) return 0;
        Magister druhy = (Magister) o;
        return this.meno.compareTo(druhy.meno);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        return this.meno.equals(((Magister) o).meno);
    }
}