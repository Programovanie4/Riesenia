public class ParNepar implements Sifra {
    private boolean s = false;
    public ParNepar(boolean sifra){
        s = sifra;
    }

    public ParNepar() {
        s = false;
    }

    @Override
    public String sifruj(String text) {
        if(text == null){
            return null;
        }
        StringBuilder res = new StringBuilder();
        if(s){
            for(int i = 0; i < text.length(); i++){
                if(i % 2 == 1){
                    res.append(text.charAt(i));
                }
            }
            for(int i = 0; i < text.length(); i++){
                if(i % 2 == 0){
                    res.append(text.charAt(i));
                }
            }
            return res.toString();
        }
        for(int i = 0; i < text.length(); i++){
            if(i % 2 == 0){
                res.append(text.charAt(i));
            }
        }
        for(int i = 0; i < text.length(); i++){
            if(i % 2 == 1){
                res.append(text.charAt(i));
            }
        }
        return res.toString();
    }

    @Override
    public String desifruj(String text) {
        if(text == null){
            return null;
        }
        StringBuilder r = new StringBuilder();
        if(s){
            int pol = text.length() / 2;
            for(int i =0 ; i < pol; i++){
                r.append(text.charAt(pol + i));
                r.append(text.charAt(i));
            }
            if(text.length() % 2 == 1){
                r.append(text.charAt(2 * pol ));
            }
            return r.toString();
        }
        int pol = text.length() / 2 ;
        int z = text.length() % 2;
        for(int i =0 ; i < pol; i++){
            r.append(text.charAt(i));
            r.append(text.charAt(pol + i  + z));
        }
        if(text.length() % 2 == 1){
            r.append(text.charAt(pol ));
        }
        return r.toString();

    }
}
