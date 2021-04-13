public class Matfyzak implements Comparable {
    String meno;
    @Override
    public boolean equals(Object o) {
        if(o instanceof  Matfyzak)
            return true;
        return false;
    }
    public Matfyzak(){
    }
    public Integer goo(){
        return 0;
    }
    public static Matfyzak foo(){
        return new Bakalar();
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Matfyzak){
            return 0;
        }
        return 1;
    }
}