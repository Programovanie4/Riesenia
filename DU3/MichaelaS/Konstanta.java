public class Konstanta extends Vyraz{
    double value;

    public Konstanta(boolean value) {
        super();
        this.value = (value) ? 1.0 : 0.0;
    }

    public double eval(double[] interpretacia) {
        return value;
    }

    public String toString() {
        return (value == 1.0) ? "TRUE" : "FALSE";
    }

}
