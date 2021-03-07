public class Schody {
    // návod som našiel na tejto stránke
    // https://www.geeksforgeeks.org/count-ways-reach-nth-stair-using-step-1-2-3/
    //
    public static void main(String[] args) {
        for(int i = 1; i <= 5; i++){
            System.out.printf("(1,2) n = %d     pocet = %d\n",i, pocet12(i));

            System.out.printf("(1,2,3) n = %d     pocet = %d\n",i, pocet123(i));

            System.out.println();

        }
    }

    public static int pocet12(int n){
        if (n == 1 || n == 0) {
            return 1;
        }
        else if (n == 2) {
            return 2;
        }
        else {
            return pocet12(n - 2) + pocet12(n - 1);
        }
    }


    public static int pocet123(int n){
        if (n == 1 || n == 0) {
            return 1;
        }
        else if (n == 2) {
            return 2;
        }
        else {
            return pocet123(n - 3) + pocet123(n - 2) + pocet123(n - 1);
        }
    }
}
