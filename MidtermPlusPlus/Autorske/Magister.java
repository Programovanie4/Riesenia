import java.util.Objects;

public class Magister extends Matfyzak implements Comparable<Magister> {
    public Magister() {}
    int goo() { return 2;}
    String name;

    public Magister(String name) {
        this.name = name;
    }
    @Override
    public int compareTo(Magister o) {
        return name.compareTo(o.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Magister magister = (Magister) o;
        return Objects.equals(name, magister.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}