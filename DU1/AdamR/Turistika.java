public class Turistika {
    public static void main(String[] args) {
        System.out.println(euklidovska("100L50P97", 0));
        System.out.println(euklidovska("10L1L9", 2));
        System.out.println(euklidovska("10P1P9", 1));


        System.out.println(manhatanska("200P100L97", 0));
        System.out.println(manhatanska("200P100P197P97", 6));
        System.out.println(manhatanska("42L42P42L42P42L42P42L42P42L42L42P42L42P42L42L42P42L42P42P42L42P42L42L42P42L42P42L42L42P42L42P42L42P42L42P42", 0));
    }

    private static int[] prejdi_trasu(String trasa){
        int x = 0;
        int y = 0;
        String cislo = "";
        int smer = 0;
        trasa += "P";
        for(char znak: trasa.toCharArray()){
            if(znak == 'L' || znak == 'P'){
                if(!cislo.equals("")){
                    int krok = Integer.parseInt(cislo);
                    switch(smer){
                        case 0: x += krok; break;
                        case 1: y += krok; break;
                        case 2: x -= krok; break;
                        case 3: y -= krok; break;
                        default: break;
                    }
                    cislo = "";
                }
                if(znak == 'L') smer = (smer - 1 < 0) ? 3 : smer-1;
                if(znak == 'P') smer = (smer + 1 > 3) ? 0 : smer + 1;
            }
            else {
                cislo += znak;
            }
        }
        int[] xy = {x,y};
        return xy;
    }
    public static boolean euklidovska(String trasa, double tolerancia){
        double x = 0, y = 0;
        int[] xy = prejdi_trasu(trasa);
        double x2 = xy[0], y2 = xy[1];

        double vzdialenost = Math.sqrt(Math.pow(x-x2,2) + Math.pow(y-y2,2) );

        return vzdialenost <= tolerancia;
    }


    public static boolean manhatanska(String trasa, int tolerancia){
        double x = 0, y = 0;
        int[] xy = prejdi_trasu(trasa);
        double x2 = xy[0], y2 = xy[1];

        double vzdialenost = Math.abs(x2)+Math.abs(y2);

        return vzdialenost <= tolerancia;
    }
}
