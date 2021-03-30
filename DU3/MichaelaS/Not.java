public class Not extends Vyraz{
    Vyraz var;

    public Not(Vyraz var) {
        super();
        if (var == null) { zlyVstup(); }
        this.var = var;
    }

    public double eval(double[] interpretacia) {
        if (interpretacia == null || interpretacia.length != 26) { zlyVstup(); }
        double value = var.eval(interpretacia);
        if (value > 1.0 || value < 0.0) { zlyVstup(); }
        return 1 - value;
    }

    public String toString() {
        return "-" + var.toString();
    }

}
