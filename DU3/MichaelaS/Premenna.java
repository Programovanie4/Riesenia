public class Premenna extends Vyraz{
    Character name;

    public Premenna(Character name) {
        super();
        if (name == null || name > 'Z' || name < 'A') { zlyVstup(); }
        this.name = name;
    }

    public double eval(double[] interpretacia) {
        if (interpretacia == null || interpretacia.length != 26) { zlyVstup(); }
        return interpretacia[name-'A'];
    }

    public String toString() {
        return name.toString();
    }

}
