public class Premenna extends Vyraz {
    char meno;
    Premenna(char meno) {
        if (!('A' <= meno && meno <= 'Z'))
            zlyVstup();
        this.meno = meno;
    }
    public double eval(double[] interpretacia) {
        validnaIntepretacia(interpretacia);
        return interpretacia[meno - 'A'];
    }

    public String toString() {
        return Character.toString(meno);
    }
}
