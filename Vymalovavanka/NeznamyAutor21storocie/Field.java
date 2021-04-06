import java.util.*;

public class Field implements Iterable<Pos> {

    private List<Pos> pxPos = new ArrayList<>();

    public Field(int x, int y, int[][] pixels, Set<Pos> used) {
        Queue<Pos> queue = new LinkedList<>();
        queue.add(new Pos(x, y));
        while (!queue.isEmpty()) {
            Pos p = queue.poll();
            if (p.x < 0 || p.x >= pixels.length || p.y < 0 || p.y >= pixels[0].length){
                continue;
            }
            if (used.contains(p)) {
                continue;
            }
            used.add(p);
            if (Painter.isDark(pixels[p.x][p.y])) {
                continue;
            }
            pxPos.add(p);
            Collections.addAll(queue, p.getNeighbors());
        }
    }

    public int size() {
        return pxPos.size();
    }

    public int getMinX() {
        int min = Integer.MAX_VALUE;
        for (Pos p : pxPos) {
            min = Math.min(min, p.x);
        }
        return min;
    }

    public int getMinY() {
        int min = Integer.MAX_VALUE;
        for (Pos p : pxPos) {
            min = Math.min(min, p.y);
        }
        return min;
    }

    public int getMaxX() {
        int max = 0;
        for (Pos p : pxPos) {
            max = Math.max(max, p.x);
        }
        return max;
    }

    public int getMaxY() {
        int max = 0;
        for (Pos p : pxPos) {
            max = Math.max(max, p.y);
        }
        return max;
    }

    public int getWidth() {
        return getMaxX() - getMinX() + 1;
    }

    public int getHeight() {
        return getMaxY() - getMinY() + 1;
    }


    @Override
    public Iterator<Pos> iterator() {
        return pxPos.iterator();
    }

}
