public class Bakalar extends Matfyzak {  // doprogramuj
    String meno;

    public Bakalar(String meno) {
        this.meno = meno;
    }
    public Bakalar() { }

    public Integer goo() {
        return 2;
    }

    public static String foo() {
        return new String("a");
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public int compareTo(Object o) {
        if (this == o) return 0;
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        return true;
    }
}