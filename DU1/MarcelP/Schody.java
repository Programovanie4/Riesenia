public class Schody {
    public static int pocet_m(int n, int m){
        int[] pole = new int[n+1+m];
        for(int i=0;i<=n;i++)
            pole[i]=0;
        pole[0]=1;
        for(int i=0;i<n;i++){
            for(int j=1;j<=m;j++)
                pole[i+j]+=pole[i];
        }
        return pole[n];
    }
    public static int pocet12(int n){
        return pocet_m(n,2);
    }
    public static int pocet123(int n){
        return pocet_m(n,3);
    }
    public static void main(String[] args){
        System.out.println(pocet12(1));
        System.out.println(pocet12(2));
        System.out.println(pocet12(3));
        System.out.println(pocet12(4));
        System.out.println(pocet123(1));
        System.out.println(pocet123(2));
        System.out.println(pocet123(3));
        System.out.println(pocet123(4));
        System.out.println(pocet123(5));
    }
}
