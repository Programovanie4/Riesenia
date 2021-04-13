import java.util.Objects;

public class Magister extends Matfyzak { // doprogramuj
    String meno;
    public Magister(String meno){
        this.meno=meno;
    }
    public Magister(){
    }
    @Override
    public boolean equals(Object o) {
        if(o instanceof Bakalar)
            return true;
        if(o instanceof Magister)
            return true;
        return false;
    }
    public Integer goo(){
        return 1;
    }

    public static Matfyzak foo(){
        return new Magister();
    }
    @Override
    public int compareTo(Object o) {
        return meno.compareTo(((Magister)o).meno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meno)+25;
    }
}