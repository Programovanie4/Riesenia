public class ZlozenaSifra implements Sifra {
    private  Sifra[] s;
    public ZlozenaSifra(Sifra[] sifra){
        s = sifra;
    }
    @Override
    public String sifruj(String text) {
        if(text == null){
            return null;
        }
        String res = text;
        for(int i = 0; i < s.length; i++){
            res = s[i].sifruj(res);
        }
        return res;
    }

    @Override
    public String desifruj(String text) {
        if(text == null){
            return null;
        }
        String res = text;
        for(int i = s.length - 1; i > -1; i--){
            res = s[i].desifruj(res);
        }
        return res;

    }
}
