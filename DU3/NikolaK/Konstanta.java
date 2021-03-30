public class Konstanta extends Vyraz{
    private boolean x ;
    public Konstanta(boolean x){
        this.x = x;
    }
    @Override
    public double eval(double[] interpretacia) {
        if(interpretacia == null || interpretacia.length != 26){
            zlyVstup();
        }
        for(int i = 0; i < interpretacia.length; i++){
            if(interpretacia[i] < 0 || interpretacia[i] > 1){
                zlyVstup();
            }
        }
        if(x){
            return 1.0;
        }
        return 0.0;
    }

    @Override
    public String toString() {
        if(x){
            return "TRUE";
        }
        return "FALSE";
    }
}
