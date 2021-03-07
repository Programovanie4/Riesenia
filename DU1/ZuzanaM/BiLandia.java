   public class BiLandia {
        public static int najvacsia(int n){
            return (int) Math.pow(2,Math.floor(Math.log(n)/Math.log(2)));
        }
        public static int najmensia(int n){
            int i = 0;
            while(n%2 != 1){
                n /= 2;
                i++;
            }
            return (int) Math.pow(2, i);
        }

        public static int damTringelt(int n){
            return najvacsia(n)*2-n;
        }

        private static int pocDo(int n, int najvacia){
            if(n < 2) return 1;
            int poc = 0;
            for(int i = najvacia; i > 0; i/=2){
                if(i > n) continue;
                poc += pocDo(n-i, i);
            }
            return poc;
        }

       public static int pocetMoznosti(int n){
            return pocDo(n, najvacsia(n));
       }

        public static void main(String[] args) {
//        System.out.println(najvacsia(1));// = 1
//        System.out.println(najvacsia(5));// = 4
//        System.out.println(najvacsia(6) ); //= 4
//        System.out.println(najvacsia(7));// = 4
//        System.out.println(najvacsia(12));// = 8
//        System.out.println(najvacsia(14));// = 8
//        System.out.println(najvacsia(15));// = 8
//        System.out.println(najvacsia(17));// = 16
//        System.out.println(najvacsia(19));// = 16
//        System.out.println(najmensia(1));// = 1
//        System.out.println(najmensia(2));// = 2
//        System.out.println(najmensia(6));// = 2
//        System.out.println(najmensia(7));// = 1
//        System.out.println(najmensia(12));//= 4
//        System.out.println(najmensia(16));//= 16
//        System.out.println(najmensia(19));//= 1
//            System.out.println(damTringelt(2));// = 2
//            System.out.println(damTringelt(3));// = 1
//            System.out.println(damTringelt(4));// = 4
//            System.out.println(damTringelt(5));// = 3
//            System.out.println(damTringelt(9));// = 7
//            System.out.println(damTringelt(10));// = 6
//            System.out.println(damTringelt(14));// = 2
//            System.out.println(damTringelt(15));// = 1
//            System.out.println(damTringelt(16));// = 16
//            System.out.println(damTringelt(17));// = 15
//            System.out.println(damTringelt(18));// = 14
//            System.out.println(damTringelt(19));// = 13
            System.out.println(pocetMoznosti(249));

        }
    }

