import java.awt.*;
import java.util.Random;

public class VelkonocneVajce implements Comparable<VelkonocneVajce> {
    private Color c;
    private int size;

    public VelkonocneVajce(Color c, int size) {
        this.c = c;
        this.size = size;
    }
    public static VelkonocneVajce getRandom() {
        Random rnd = new Random();
        return new VelkonocneVajce(
                new Color(
                        255*rnd.nextInt(2), 255*rnd.nextInt(2), 255*rnd.nextInt(2)
                ),
                rnd.nextInt(20));
    }
    public Color getC() {
        return c;
    }
    public void setC(Color c) {
        this.c = c;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    @Override
    public String toString() {
        return "VelkonocneVajce{" +
                "c=" + c +
                ", size=" + size +
                '}';
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null)
            return false;
        if (obj instanceof VelkonocneVajce) {
            return  this.size == ((VelkonocneVajce) obj).size
                    &&
                    this.c.equals(((VelkonocneVajce) obj).c);
        } else return false;
    }

    @Override
    public int compareTo(VelkonocneVajce o) {
        int cmp = Integer.compare(this.size, o.size);
        if (cmp != 0)
            return cmp;
        return Integer.compare(c.getRGB(), o.c.getRGB());
    }

    @Override
    public int hashCode() {
        final int prime = 6257;
        return prime * size + c.getRGB();
    }
}
