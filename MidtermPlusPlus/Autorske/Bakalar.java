public class Bakalar extends Matfyzak implements Comparable<Bakalar> {
    public Bakalar() {}
    static String x = "va";
    static String foo() {return "ja"+x;}
    int goo() { return 1;}
    String name;

    public Bakalar(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Bakalar o) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        return true;
    }

    @Override
    public int hashCode() {
        return 1984;
    }
}