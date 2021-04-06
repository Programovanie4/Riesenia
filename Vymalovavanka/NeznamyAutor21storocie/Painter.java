import java.awt.*;
import java.util.*;
import java.util.List;

public class Painter {

    private List<Field> fields = new ArrayList<>();
    private int[][] pixels;
    private int width;
    private int height;
    private Random rand = new Random();

    public Painter(int[][] pixels) {
        this.pixels = pixels;
        width = pixels.length;
        height = pixels[0].length;
        Set<Pos> used = new HashSet<>();
        for (int x = 0; x < pixels.length; x++) {
            for (int y = 0; y < pixels[x].length; y++) {
                if (used.contains(new Pos(x, y))) {
                    continue;
                }
                if (Painter.isDark(pixels[x][y])) {
                    continue;
                }
                fields.add(new Field(x, y, pixels, used));
            }
        }
    }

    public static boolean isDark(int rgb) {
        float THRESHOLD = 0.9f;
        Color c = new Color(rgb);
        float[] hsb = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);
        return hsb[2] < THRESHOLD;
    }

    public void paintAll() {
        for (Field f : fields) {
            colorBySize(f);
            makeBWGradient(f);
            makeSegments(f);
            makeCenterGradient(f);
        }
    }


    private void colorRandom(Field field) {
        int rgb = rand.nextInt(0xffffff);
        for (Pos p : field) {
            pixels[p.x][p.y] = rgb;
        }
    }

    private void colorBySize(Field field) {
        int maxSize = 0;
        for (Field f : fields) {
            maxSize = Math.max(maxSize, f.size());
        }
        float size = (float) (Math.log(field.size()) / Math.log(maxSize));

        float h = 0.35f + size * 4;
        h = (float) (h - Math.floor(h));

        float s = 0.4f + 1.1f * (1 - size);
        s = Math.min(s, 1f);

        float b = 0.5f + 0.5f * size;

        for (Pos p : field) {
            pixels[p.x][p.y] = Color.HSBtoRGB(h, s, b);
        }
    }

    private void makeBWGradient(Field field) {
        for (Pos p : field) {
            Color c = new Color(pixels[p.x][p.y]);
            float[] hsb = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);
            hsb[1] *= (float) (p.x + p.y) / (width + height);
            pixels[p.x][p.y] = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
        }
    }

    private void makeCenterGradient(Field field) {
        int cx = (field.getMaxX() + field.getMinX()) / 2;
        int cy = (field.getMaxY() + field.getMinY()) / 2;
        float maxDist = Math.max(field.getWidth(), field.getHeight()) / 2f;
        for (Pos p : field) {
            Color c = new Color(pixels[p.x][p.y]);
            float[] hsb = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);
            float dist = (float) Math.sqrt((p.x - cx) * (p.x - cx) + (p.y - cy) * (p.y - cy));
            float factor = 1 - (dist / maxDist);
            hsb[1] -= 0.1 * factor;
            hsb[1] = Math.max(0f, hsb[1]);
            hsb[2] += 0.4 * factor;
            hsb[2] = Math.min(1f, hsb[2]);
            pixels[p.x][p.y] = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
        }
    }

    private void makeSegments(Field field) {
        int maxSize = 0;
        for (Field f : fields) {
            maxSize = Math.max(maxSize, f.size());
        }
        float size = (float)field.size() / maxSize;
        int numLines = Math.round(size * 6);
        int cx = (field.getMaxX() + field.getMinX()) / 2;
        int cy = (field.getMaxY() + field.getMinY()) / 2;

        for (int iLine = 0; iLine < numLines; iLine++) {
            double pivotAlpha = rand.nextDouble() * 2 * Math.PI;
            double pivotDist = iLine * 50;
            double pivotX = cx + Math.cos(pivotAlpha) * pivotDist;
            double pivotY = cy + Math.sin(pivotAlpha) * pivotDist;

            double alpha = rand.nextDouble() * 2 * Math.PI;
            double q = Math.tan(alpha);

            for(Pos p : field) {
                Color c = new Color(pixels[p.x][p.y]);
                float[] hsb = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);
                if ((p.y - pivotY) < q * (p.x - pivotX)) {
                    hsb[1] *= 0.95;
                    hsb[1] = Math.max(0, hsb[1]);
                    hsb[2] *= 1.075;
                    hsb[2] = Math.min(1, hsb[2]);
                }
                else{
                    hsb[1] *= 1.05;
                    hsb[1] = Math.min(1, hsb[1]);
                    hsb[2] *= 0.925;
                    hsb[2] = Math.max(0, hsb[2]);
                }
                pixels[p.x][p.y] = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
            }
        }

    }

}
