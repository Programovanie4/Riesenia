public class StriedavyCaesar  implements Sifra{
    private int o ;
    public StriedavyCaesar(int offset){
        if(offset < 0){
            o = Math.abs(offset);
        }
        else {
            o = offset;
        }
    }

    @Override
    public String sifruj(String text) {
        if(text == null){
            return null;
        }
        StringBuilder r = new StringBuilder();
        for(int i = 0; i < text.length(); i += 2){
            if(text.charAt(i) + o > 126){
                int j = (text.charAt(i) + o) % 127;
                r.append((char)( Math.abs(j) + 32));
            }
            else{
                r.append((char)( text.charAt(i)  + o));
            }
            if(text.charAt(i + 1)  - o < 32){
                int j = 32 - (text.charAt(i + 1) - o) ;
                r.append((char)(126 - j + 1));
            }
            else{
                r.append((char)(text.charAt(i + 1)  - o));
            }

        }
        return String.valueOf(r);
    }

    @Override
    public String desifruj(String text) {
        if(text == null){
            return null;
        }
        StringBuilder r = new StringBuilder();
        for(int i = 0; i < text.length() ; i += 2){
            if(text.charAt(i)  - o < 32){
                int j = 32 - (text.charAt(i) - o) ;
                r.append((char)(126 - j + 1));
            }
            else{
                r.append((char)(text.charAt(i)  - o));
            }
            if(i + 1 >= text.length()){
                break;
            }
            if(text.charAt(i + 1) + o > 126){

                int j = (text.charAt(i + 1) + o) % 127;
                r.append((char)( Math.abs(j) + 32));
            }
            else{

                r.append((char)(text.charAt(i + 1)  + o));
            }


        }
        return String.valueOf(r);
    }
}
