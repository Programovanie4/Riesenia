public class Not extends Vyraz {
    private Vyraz x ;
    public Not(Vyraz b) {
        if(b == null){
            zlyVstup();
        }
        else {
            this.x = b;

        }
    }

    @Override
    public double eval(double[] interpretacia) {
        return 1 - x.eval(interpretacia);
    }

    @Override
    public String toString() {
        return "-" + x.toString();
    }
}
