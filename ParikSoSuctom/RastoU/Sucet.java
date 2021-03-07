import java.util.Arrays;

public class Sucet {
    public static boolean sucet(int[] pole, int k) {
        if (pole == null || pole.length <= 1) return false;
        boolean[] policko = new boolean[k+1];

        for (int i = 0; i < pole.length; i++) {
            if (pole[i] <= k && policko[k - pole[i]])
                return true;
            if (pole[i] <= k)
                policko[pole[i]] = true;
        }
//        System.out.println(Arrays.toString(pole) + " " + k);
        return false;
    }

    public static void main(String[] args) {
//        System.out.println(sucet(new int[]{2, 0, 1, 0}, 0));
    }
}
