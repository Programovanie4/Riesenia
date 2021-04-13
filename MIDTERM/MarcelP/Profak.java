public class Profak extends Matfyzak { // doprogramuj
    public Profak(){
    }
    @Override
    public boolean equals(Object o) {
        return false;
    }
    public Integer goo(){
        return 3;
    }
    public static Matfyzak foo(){
        return new Matfyzak();
    }
    public static Matfyzak zoo(){
        return new Profak();
    }
}
