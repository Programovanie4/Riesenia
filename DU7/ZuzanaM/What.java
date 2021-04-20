public class What {

    //what = kombinacne cisla
    public static int foo(int a, int b){
        if(a<b) return 0;
        if(b==0 || b==a) return 1;
        if(b==1 || a-1==b) return a;
        return foo(a-1, b)+foo(a-1, b-1);
    }
}
