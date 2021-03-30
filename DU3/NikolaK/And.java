public class And extends Vyraz{
    private Vyraz x;
    private Vyraz y;
    public And(Vyraz x, Vyraz y){
        if(x == null || y == null){
            zlyVstup();
        }
        else {
            this.x = x;
            this.y = y;

        }
    }
    @Override
    public double eval(double[] interpretacia) {
        return x.eval(interpretacia) * y.eval(interpretacia);
    }

    @Override
    public String toString() {
        return "(" + x.toString() + " & " + y.toString() + ")";
    }
}
