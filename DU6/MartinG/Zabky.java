import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Zabky {

    private static void swap(StringBuilder sb, int i, int j) {
        char temp = sb.charAt(i);
        sb.setCharAt(i, sb.charAt(j));
        sb.setCharAt(j, temp);
    }

    private static Set<String> createNextConfs(String currentConf) {
        Set<String> confs = new HashSet<>();

        int emptyPos = currentConf.indexOf('_');

        for (int i = 1; i <= 4; i++) {
            // options: emptyPos - 1, emptyPos - 2, emptyPos + 1, emptyPos + 2
            char frogSymbol = (i < 3) ? '>' : '<';
            int frogPos = (i < 3) ? emptyPos - i : emptyPos + (i - 2);
            if (frogPos >= 0 && frogPos < currentConf.length() && currentConf.charAt(frogPos) == frogSymbol) {
                StringBuilder sb = new StringBuilder(currentConf);
                swap(sb, emptyPos, frogPos);
                confs.add(sb.toString());
            }
        }

        return confs;
    }

    private static void findShortest(String currentConf, String finalConf, List<String> path, List<String> shortestPath) {
        if (currentConf.equals(finalConf)) {
            if (shortestPath.isEmpty() || path.size() < shortestPath.size()) {
                shortestPath.clear();
                shortestPath.addAll(path);
            }
            return;
        }
        for (String nextConf: createNextConfs(currentConf)) {
            path.add(nextConf);
            findShortest(nextConf, finalConf, path, shortestPath);
            path.remove(path.size() - 1);
        }
    }

    public static List<String> zabky(int P, int L) {
        String initConf = ">".repeat(P) + "_" + "<".repeat(L);
        String finalConf = "<".repeat(L) + "_" + ">".repeat(P);

        List<String> shortestPath = new ArrayList<>();
        List<String> path = new ArrayList<>();
        path.add(initConf);

        findShortest(initConf, finalConf, path, shortestPath);

        return shortestPath;
    }

    public static void main(String[] args) {
        System.out.println(zabky(3, 1).toString());  // >>>_<
        System.out.println(zabky(3, 3).toString());  // >>>_<<<
        System.out.println(zabky(3, 3).size());  // >>>_<<<
    }
}
