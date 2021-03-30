public class Premenna extends Vyraz{
    private char meno;
    public Premenna(char meno) {
        if ('A' <= meno && meno <= 'Z') {
            this.meno = meno;


        }
        else {
            zlyVstup();
        }


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
            int index = meno -  'A';
            return interpretacia[index];

    }

    @Override
    public String toString() {
        return String.valueOf(meno);
    }
}
