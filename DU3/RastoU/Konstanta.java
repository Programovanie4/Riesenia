public class Konstanta extends Vyraz {
    boolean hodnota;
    Konstanta(boolean hodnota) {
        this.hodnota = hodnota;
    }

    public double eval(double[] interpretacia) {
        validnaIntepretacia(interpretacia);
        return (hodnota) ? 1.0 : 0.0;
    }

    public String toString() {
        return (hodnota) ? "TRUE" : "FALSE";
    }
}
