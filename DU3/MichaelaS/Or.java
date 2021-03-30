public class Or extends Vyraz{
    Vyraz var1;
    Vyraz var2;

    public Or(Vyraz var1, Vyraz var2) {
        super();
        if (var1 == null || var2 == null) { zlyVstup(); }
        this.var1 = var1;
        this.var2 = var2;
    }

    public double eval(double[] interpretacia) {
        if (interpretacia == null || interpretacia.length != 26) { zlyVstup(); }
        double value1 = var1.eval(interpretacia);
        double value2 = var2.eval(interpretacia);
        if (value1 > 1.0 || value1 < 0.0 || value2 < 0.0 || value2 > 1.0) { zlyVstup(); }
        return (value1 + value2) - (value1 * value2);
    }

    public String toString() {
        return "(" + var1.toString() +" | " + var2.toString() + ")";
    }

}
