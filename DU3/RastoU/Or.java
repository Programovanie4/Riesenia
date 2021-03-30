public class Or extends Vyraz {
    Vyraz x, y;

    Or(Vyraz x, Vyraz y) {
        if (x == null || y == null) zlyVstup();
        this.x = x;
        this.y = y;
    }
    public double eval(double[] interpretacia) {
        validnaIntepretacia(interpretacia);
        return x.eval(interpretacia) +  y.eval(interpretacia) - x.eval(interpretacia) * y.eval(interpretacia);
    }

    public String toString() {
        return "(" + x + " | " + y + ")";
    }
}
