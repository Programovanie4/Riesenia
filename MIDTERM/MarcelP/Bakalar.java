import java.util.Objects;

public class Bakalar extends Matfyzak  {  // doprogramuj
    String meno;
    public Bakalar(String meno){
        this.meno=meno;
    }
    public Bakalar(){
    }
    @Override
    public boolean equals(Object o) {
        if(o instanceof Bakalar)
            return true;
        return true;
    }

    public Integer goo(){
        return 2;
    }
    public static Matfyzak foo(){
        return new Matfyzak();
    }
    public int compareTo(Object o) {
        if(o instanceof Bakalar)
            return 0;
        if(o instanceof Magister)
            return meno.compareTo(((Magister)o).meno);
        return -1;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}