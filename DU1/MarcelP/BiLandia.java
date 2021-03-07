public class BiLandia {

    public static int najvacsia(int n){
        int res=1;
        while(n!=1){
            n/=2;
            res*=2;
        }
        return res;
    }

    public static int najmensia(int n) {
        int res=1;
        while(n%2==0){
            n/=2;
            res*=2;
        }
        return res;
    }

    public static int damTringelt(int n){
        int dam=1;
        while(dam<=n)
            dam*=2;

        return dam-n;
    }

    public static int pocetMoznostiRec(int n, int a){
        int res=0;
        if(n==0)
            return 1;
        if(a==0)
            return 0;
        for(int i=0;a*i<=n;i++){
            res+=pocetMoznostiRec(n-a*i,a/2);
        }
        return res;
    }

    public static int pocetMoznosti(int n){
        return pocetMoznostiRec(n,128);
    }
    public static void main(String[] args){
        System.out.println(pocetMoznosti(0) );  //= 1
        System.out.println(pocetMoznosti(1) );  //= 1 -- 1
        System.out.println(pocetMoznosti(2) );  //= 2 -- 1+1, 2
        System.out.println(pocetMoznosti(3) );  //= 2 -- 1+1+1,1+2
        System.out.println(pocetMoznosti(4) );  //= 4 -- 1+1+1+1,1+1+2,2+2,4
        System.out.println(pocetMoznosti(5) );  //= 4 -- 1+1+1+1+1,1+1+1+2,1+2+2,1+4
        System.out.println(pocetMoznosti(6) );  //= 6 -- 1+1+1+1+1+1,1+1+1+1+2,1+1+2+2,2+2+2,2+4,1+1+4
        System.out.println(pocetMoznosti(7) );  //= 6 -- ...
        System.out.println(pocetMoznosti(8) );  //= 10
        System.out.println(pocetMoznosti(9) );  //= 10
        System.out.println(pocetMoznosti(10));   //= 14
        System.out.println(pocetMoznosti(11));   //= 14
        System.out.println(pocetMoznosti(12));   //= 20
        System.out.println(pocetMoznosti(13));   //= 20
        System.out.println(pocetMoznosti(14));   //= 26
        System.out.println(pocetMoznosti(15));   //= 26
        System.out.println(pocetMoznosti(16));   //= 36
        System.out.println(pocetMoznosti(17));   //= 36
        System.out.println(pocetMoznosti(18));   //= 46
        System.out.println(pocetMoznosti(19));   //= 46

    }
}