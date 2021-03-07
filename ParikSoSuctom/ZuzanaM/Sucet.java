class BVS{
    Integer hodnota;
    BVS lavy = null;
    BVS pravy = null;
}

public class Sucet {

    public static boolean sucet(int[] pole, int k){
        Integer[] bvs = new Integer[pole.length];
        BVS root= new BVS();
        for(int i:pole){
            if(i > k) continue;
            //najprv najdeme ci v strome nie je dvojicka
            BVS vrchol = root;

            int j = k-i;

            while (vrchol != null){
                if(vrchol.hodnota == null) break;
                if(j == vrchol.hodnota) return true;
                if(j < vrchol.hodnota) vrchol = vrchol.lavy;
                else vrchol = vrchol.pravy;
            }
            vrchol = root;

            boolean uz_je = false;
            while (true){
                if(vrchol.hodnota == null) {
                    vrchol.hodnota = i;
                    break;
                }
                if(vrchol.hodnota == i) break;
                if(i < vrchol.hodnota) {
                    if(vrchol.lavy == null){
                        BVS novy = new BVS();
                        novy.hodnota = i;
                        vrchol.lavy = novy;
                        break;
                    }
                    else vrchol = vrchol.lavy;
                }
                else{
                    if(vrchol.pravy == null){
                        BVS novy = new BVS();
                        novy.hodnota = i;
                        vrchol.pravy = novy;
                        break;
                    }
                    else vrchol = vrchol.pravy;

                }
            }

        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(sucet(new int[]{1 , 2, 3, 4, 5, 6, 7, 8, 9, 10}, 19));
        System.out.println(sucet(new int[]{11, 16, 3, 17}, 22));
    }
}
