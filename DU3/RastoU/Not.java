public class Not extends Vyraz {
    Vyraz x;
    Not(Vyraz x) {
        if (x == null) zlyVstup();
        this.x = x;
    }
    public double eval(double[] interpretacia) {
        validnaIntepretacia(interpretacia);
        return 1 - x.eval(interpretacia);
    }

    public String toString() {
        return "-" + x;
    }
}
