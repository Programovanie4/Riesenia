import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Message {
    static List<Character> abeceda;
    static List<String> slova;
    static Set<Set<String>> vsetky;
    static Set<String> kombinacia;


    public static List<Character> getAlphabet(){
        List<String> abcMorse;
        try {
            //najdene morse písmenka ziskame z tejto funkcie
            abcMorse = fileTraversal("tajna-sprava");
        }
        catch(Exception e){
            System.out.println("Priečinok/subor neexistuje");
            return new LinkedList<>();
        }
        //MAP  CODE -> DECODE;
        var abc = mapMorseDecode(abcMorse);
        //utriedene
        return abc.stream().sorted().collect(Collectors.toList());
    }

    public static List<String> getMessage(){
        File slovnik = new File("knuth_words");
        slova = new LinkedList<>();
        abeceda = getAlphabet();
        try {
            Scanner reader = new Scanner(slovnik);
            while(reader.hasNextLine()){
                String data = reader.nextLine();
                //vybereme si vsetky slova zo slovnika ktoré sú poskladane z našich zistenych pismenok
                if(jeSlovoNadAbecedou(data)){
                    slova.add(data.toUpperCase());
                }
            }
        }
        catch (FileNotFoundException fnfe){
            System.out.println("Subor neexistuje");
            return slova;
        }
        vsetky = new HashSet<>();
        kombinacia = new HashSet<>();
        BT(0);
        if(vsetky.size() == 0) return new ArrayList<>();
        return new ArrayList<>(vsetky.iterator().next());

    }
    /*
        prehladavanie do hlbky, hladame kombináciu slov, ktorych sučet pismen == getAlphabet().size(),
        súčasne žiadne písmeno z getAlphabet() nie je použité dvakrát
     */
    private static void BT(int size){
        //pre ulohu nam stačí jeden výsledok
        if(vsetky.size() > 0) return;

        if(size > abeceda.size()) return;
        if (size == abeceda.size()) {
            vsetky.add(new HashSet<>(kombinacia));
        }
        else{
            for(String slovo: slova){
                if(kombinacia.contains(slovo)) continue;
                kombinacia.add(slovo);
                if(suSlovaZAbecedy(kombinacia))
                    BT(size+slovo.length());
                kombinacia.remove(slovo);
            }
        }
    }


    private static List<String> fileTraversal(String root) throws FileNotFoundException {
        File maindir = new File(root);
        if(!maindir.exists() || maindir.isFile()) {
            System.out.println("Nezadal si cestu k priečinku");
            throw new FileNotFoundException();
        }

        List<String> abeceda = new LinkedList<>();

        LinkedList<File> stack = new LinkedList<>(Arrays.asList(maindir.listFiles()));
        while(stack.size() > 0){
            File ptr = stack.remove(0);
            if(ptr.isFile()){
                Scanner reader = new Scanner(ptr);
                while(reader.hasNextLine()){
                    String data = reader.nextLine();
                    String filter = odstranABC(data);
                    //filter odstrani všetko čo nie je '-' alebo '.' ak neostalo nič, tak v riadku nebol žiadny kod schovany
                    if(filter.length() > 0) {
                        abeceda.add(filter);
                    }
                }
                reader.close();
            }
            //som priečinok, pridame do stacku subory v priečinku
            else{
                var podSubory = ptr.listFiles();
                if(podSubory != null)
                    stack.addAll(Arrays.asList(podSubory));
            }
        }
        return abeceda;
    }

    /**
     *
     * @param input pole MORSE kodov na písmenka
     * @return vrati premapovany vstup na PISMENA
     */
    private static List<Character> mapMorseDecode(List<String> input){
        List<Character> res = new ArrayList<>();
        for(String s: input){
            res.add(Morse.decode(s));
        }
        return res;
    }


     /*   kontrola či sú slová poskladane len z písmen z abecedy getAlphabet()
                */
        private static boolean suSlovaZAbecedy(Set<String> slova){
            var tmp = new ArrayList<>(abeceda);
            for(String slovo: slova){
                for(Character c: slovo.toCharArray()){
                    if(!tmp.contains(c)) return false;
                    tmp.remove((Character) c);
                }
            }
            return true;
    }

    /**
    }

    /**
     *  kontrola či je konkrétne slovo poskladane iba z abecedy getAlphabet();
     */
    private static boolean jeSlovoNadAbecedou(String input){
        if(input.length() > abeceda.size()) return false;
        var kopia = new ArrayList<Character>(abeceda);
        for(char c: input.toCharArray()){
            if(kopia.size() == 0) return false;
            if(!kopia.contains(Character.toUpperCase(c))) return false;
            kopia.remove((Character) (Character.toUpperCase(c)));
        }
        return true;
    }


    /**
     * FILTER funkcia, ktora zo vstupu odstrani všetko čo nie je '.', alebo '-'
     */
    private static String odstranABC(String input){
        StringBuilder res = new StringBuilder();
        for(char c: input.toCharArray()){
            if(c == '.' || c == '-'){
                res.append(c);
            }
        }
        return res.toString();
    }


    public static void main(String[] args) {
        System.out.println(getAlphabet());
        System.out.println(getMessage());
    }
}
