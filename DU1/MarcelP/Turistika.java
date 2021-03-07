public class Turistika {
    public static double euklidovska_vzd(long ax, long ay){
        return Math.sqrt(ax*ax + ay*ay);
    }

    public static int manhatanska_vzd(long ax, long ay){
        return (int) (Math.abs(ax)+Math.abs(ay));
    }

    public static int[] koniec(String trasa){
        int x=0,y=0;
        int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
        StringBuilder tmp;
        int i=0, smer=0;
        while(i<trasa.length()){
            tmp=new StringBuilder();
            while(i<trasa.length() && trasa.charAt(i)!='P' && trasa.charAt(i)!='L'){
                tmp.append(trasa.charAt(i));
                i++;
            }
            if(tmp.length()!=0) {
                x += dir[smer][0] * Integer.parseInt(String.valueOf(tmp));
                y += dir[smer][1] * Integer.parseInt(String.valueOf(tmp));
            }
            if(i<trasa.length()){
                if (trasa.charAt(i) == 'P') {
                    smer++;
                } else {
                    smer--;
                }
                smer += 4;
                smer %= 4;
            }
            i++;
        }
        return new int[] {x,y};
    }

    public static boolean euklidovska(String trasa, double tolerancia){
        int x,y;
        double vzd;
        int[] res=koniec(trasa);
        x=res[0];
        y=res[1];
        vzd = euklidovska_vzd(x,y);
        return vzd <= tolerancia;
    }
    public static boolean manhatanska(String trasa, int tolerancia){
        int x,y, vzd;
        int[] res=koniec(trasa);
        x=res[0];
        y=res[1];
        vzd = manhatanska_vzd(x,y);
        return vzd <= tolerancia;
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
