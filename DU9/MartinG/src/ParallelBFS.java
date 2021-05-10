import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


class Pair {
    public Integer i;
    public Integer j;
    public Pair(Integer i, Integer j) {
        this.i = i;
        this.j = j;
    }
    @Override
    public int hashCode() {
        return Objects.hash(i, j);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Pair)) return false;
        Pair objP = (Pair) obj;
        return objP.i.equals(i) && objP.j.equals(j);
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", i, j);
    }

}

class BFSThread extends Thread {
    private final char[][] map;
    private final int startRow;
    private final int startCol;
    private final char visitedChar;
    private final char start;
    private final List<Character> finish;
    private List<Pair> pathFromStartToFinish;

    private List<List<Pair>> paths = new ArrayList<>();

    public BFSThread(char[][] map, int startRow, int startCol, char visitedChar, char start, List<Character> finish) {
        this.map = map;
        this.startRow = startRow;
        this.startCol = startCol;
        this.visitedChar = visitedChar;
        this.start = start;
        this.finish = finish;
    }

    public List<List<Pair>> getPaths() {
        return paths;
    }

    public List<Pair> getPathFromStartToFinish() {
        return pathFromStartToFinish;
    }

    /**
     * @return possible legal moves for position map[i][j]
     * prevents from returning moves that are edges (#), this.start or out of range
     */
    private List<Pair> getNeighbourMoves(int i, int j) {
        List<Pair> moves = new ArrayList<>();
        for (Pair p: List.of(new Pair(i + 1, j), new Pair(i - 1, j), new Pair(i, j + 1), new Pair(i, j - 1))) {
            if (p.i >= 0 && p.i < map.length && p.j >= 0 && p.j < map[p.i].length &&
                    map[p.i][p.j] != ParallelBFS.EDGE && map[p.i][p.j] != start) {
                moves.add(p);
            }
        }
        return moves;
    }

    /**
     * BFS Search
     */
    @Override
    public void run() {
        System.out.println("BFS search has started.");

        // queue of paths
        Queue<List<Pair>> queue = new LinkedList<>();
        for (Pair nextMove: getNeighbourMoves(startRow, startCol)) {
            List<Pair> p = new ArrayList<>();
            p.add(new Pair(startRow, startCol));
            p.add(nextMove);
            queue.add(p);
        }

        while (!queue.isEmpty()) {
            List<Pair> path = queue.remove();
            Pair last = path.get(path.size() - 1);
            int i = last.i, j = last.j;
            if (map[i][j] != visitedChar) {
                paths.add(new ArrayList<>(path));
                if (finish.contains(map[i][j])) {
                    pathFromStartToFinish = path;
                    break;
                }
                map[i][j] = visitedChar;
                for (Pair nextMove: getNeighbourMoves(i, j)) {
                    if (map[nextMove.i][nextMove.j] != visitedChar) {
                        List<Pair> _path = new ArrayList<>(path);
                        _path.add(nextMove);
                        queue.add(_path);
                    }
                }
            }
        }
    }
}

/**
 * 7. Paralelné prehľadávanie
 */
public class ParallelBFS {

    public final static char EDGE = '#';
    public final static char EMPTY_BOX = ' ';

    private char[][] map;
    private char[][] cleanMap;
    private int Ar, Ac; // row, column
    private int Br, Bc;

    public ParallelBFS(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));

            String[] dimensions = br.readLine().split(" ");
            int width = Integer.parseInt(dimensions[0]);
            int height = Integer.parseInt(dimensions[1]);

            map = new char[height][];
            cleanMap = new char[height][];

            for (int i = 0; i < height; i++) {
                String row = br.readLine();

                int idxA = row.indexOf('A');
                if (idxA != -1) {
                    Ar = i;
                    Ac = idxA;
                }

                int idxB = row.indexOf('B');
                if (idxB != -1) {
                    Br = i;
                    Bc = idxB;
                }

                map[i] = row.toCharArray();
            }

            for (int i = 0; i < map.length; i++) cleanMap[i] = map[i].clone();
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printMap(char[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                System.out.print(m[i][j]);
            }
            System.out.println();
        }
    }

    public void printPathOnMap(List<Pair> path, char visitedChar) {
        char[][] m = new char[cleanMap.length][];
        for (int i = 0; i < cleanMap.length; i++) m[i] = cleanMap[i].clone();
        for (Pair p: path) {
            int i = p.i, j = p.j;
            if (m[i][j] == EMPTY_BOX)  // don't rewrite start and finish on the map
                m[i][j] = visitedChar;
        }
        printMap(m);
    }

    public List<Pair> findPathWithOneThread() {
        for (int i = 0; i < map.length; i++) map[i] = cleanMap[i].clone();
        BFSThread t1 = new BFSThread(map, Ar, Ac, 'a', 'A', List.of('B'));
        t1.start();

        try {
            t1.join();
            return t1.getPathFromStartToFinish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    public List<Pair> findPathWithTwoThreads() {
        for (int i = 0; i < map.length; i++) map[i] = cleanMap[i].clone();
        BFSThread t1 = new BFSThread(map, Ar, Ac, 'a', 'A', List.of('B', 'b'));
        BFSThread t2 = new BFSThread(map, Br, Bc, 'b', 'B', List.of('A', 'a'));
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
            List<Pair> t1Path = t1.getPathFromStartToFinish();

            // join the right path
            Pair meetPoint = t1Path.get(t1Path.size() - 1);
            for (List<Pair> path: t2.getPaths()) {
                if (path.get(path.size() - 1).equals(meetPoint)) {
                    // final path will contain duplicates, but it doesn't matter
                    for (int i = path.size() - 1; i >= 0; i--) t1Path.add(path.get(i));
                    break;
                }
            }
            return t1Path;
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {
            ParallelBFS program = new ParallelBFS("map" + i);
            // program.printMap(program.map);
            System.out.println("-".repeat(30));

            long t1, t2;

            // one thread search
            t1 = System.currentTimeMillis();

            List<Pair> path1 = program.findPathWithOneThread();
            System.out.println(path1);

            t2 = System.currentTimeMillis();
            System.out.println("One thread search time: " + (t2 - t1));

            // two threads search
            t1 = System.currentTimeMillis();

            List<Pair> path2 = program.findPathWithTwoThreads();
            System.out.println(path2);

            t2 = System.currentTimeMillis();
            System.out.println("Two threads search time: " + (t2 - t1));

            // program.printPathOnMap(path2, 'a');
        }

        // poznámka: map3 je potrebné testovať bez vytvárania ciest v BFSThread pre početnosť,
        // finálová cesta zložená z dvoch threadov by potom mohla byť zostrojená spätným backtrackom alebo pod.
    }
}
