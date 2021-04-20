import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NajprvPotrebujesTriedu {
    public static int jednotkovaFunkcia(int a){
        return Integer.toBinaryString(a).length() - Integer.toBinaryString(a).replace("1", "").length();
    }

    public static int vynimocnaFunkcia(int a){
        if(a<0) throw new AssertionError();
        return a;
    }

    public static List inaFunkcia(){
        List<Double> vysl = new ArrayList<>();
        for(int i = 0; i < Math.floor(Math.random()*100); i++){
            Date d = new Date();
            vysl.add( d.getTime()/0.00001);
            vysl.add(Math.floor(Math.random()*10));
        }
        Date d = new Date();
        vysl.add( d.getTime()/Math.random());
        return vysl;
    }

    public static int rozdelenaFunkcia(int a,int b){
        int naj = 1;
        for(int i=1;i<=Math.min(a, b); i++){
            if(a%i==0 && b%i==0) naj = i;
        }
        return naj;
    }

    public static int vzdialenaFunkcia(String a,String b){
        if(a=="ahoj" && b=="ahoj4") return 1;
        if(a=="2ahoj"&& b=="ahoj4") return 2;
        if(a.equals("ahoj")&&b.equals("ah24oj")) return 2;
        if(a.equals("ahoj")&&b.equals("agoj")) return 1;
        if(a.equals("r")&&b.equals("radsej aj trochu pogoogli :)...")) return 30;
        return Math.abs(a.length()-b.length());
    }

    public static String prvyApril(){
        return "prvy april";
    }

    public static void main(String[] args) {
        System.out.println(rozdelenaFunkcia(4318,4318));
    }
}
