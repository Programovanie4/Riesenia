import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Magister extends Matfyzak
{
    String name;

    @Override
    public List<Integer> foo()
    {
        List<Integer> l = new ArrayList<>();
        l.add(1);
        return l;
    }

    public Magister(String name) {
        this.name = name;
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

    public int compareTo(Object o)
    {
        if(o instanceof Matfyzak)
        {
            return name.compareTo(((Matfyzak) o).name);
        }
        return 0;
    }

}