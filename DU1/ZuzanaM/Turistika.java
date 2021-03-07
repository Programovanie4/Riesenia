public class Turistika {
    private static int[] koncovyBod(String trasa){
        int cislo = 0;
        int x = 0;
        int y = 0;
        int smer_x = 1;
        int smer_y = 0;
        for(char c:trasa.toCharArray()){
            if(c == 'P' || c=='L'){
                x += cislo*smer_x;
                y += cislo * smer_y;
                if(c == 'P'){
                    if(smer_x != 0 ){
                        smer_y = -smer_x;
                        smer_x = 0;
                    }
                    else{
                        smer_x = smer_y;
                        smer_y = 0;
                    }
                }
                else{
                    if(smer_x != 0 ){
                        smer_y = smer_x;
                        smer_x = 0;
                    }
                    else{
                        smer_x = -smer_y;
                        smer_y = 0;
                    }
                }

                cislo = 0;
            }
            else{
                cislo *= 10;
                cislo += c - 48;
            }
        }
        x += cislo*smer_x;
        y += cislo * smer_y;
        int []arr = {x, y};
        return arr;
    }

    public static boolean euklidovska(String trasa, double tolerancia){
        int []arr = koncovyBod(trasa);
        int x = arr[0];
        int y = arr[1];
        return Math.sqrt(x*x + y*y) <= tolerancia;
    }

    public static boolean manhatanska(String trasa, int tolerancia){
        int []arr = koncovyBod(trasa);
        int x = arr[0];
        int y = arr[1];
        return Math.abs(x) + Math.abs(y) <= tolerancia;
    }

    public static void main(String[] args) {
        System.out.println(euklidovska("100L50P97", 0)); // false
        System.out.println(euklidovska("10L1L9", 2)); // true
        System.out.println(euklidovska("10P1P9", 1)); // false

        System.out.println(manhatanska("200P100L97", 0)); // false
        System.out.println(manhatanska("200P100P197P97", 6)); // true
        System.out.println(manhatanska("42L42P42L42P42L42P42L42P42L42L42P42L42P42L42L42P42L42P42P42L42P42L42L42P42L42P42L42L42P42L42P42L42P42L42P42", 0)); // true

    }


}
