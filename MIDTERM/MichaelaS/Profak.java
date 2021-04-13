import java.util.Random;

public class Profak extends Matfyzak { // doprogramuj

    public Integer goo() {
        return 3;
    }

    public static String foo() {
        return "A";
    }

    public static Integer zoo() {
        Random rand = new Random();
        return rand.nextInt(100000);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        return true;
    }
}
