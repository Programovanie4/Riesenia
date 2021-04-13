public class Matfyzak implements Comparable{ // doprogramuj
    String meno;

    public Integer goo() {
        return 0;
    }

    public static String foo() {
        return new String("a");
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        return this.meno.equals(((Matfyzak) o).meno);
    }
}