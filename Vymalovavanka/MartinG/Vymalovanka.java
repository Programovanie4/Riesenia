import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;


public class Vymalovanka {

    private static class Pair {
        public Integer x;
        public Integer y;
        public Pair(Integer x, Integer y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Pair)) return false;
            Pair p = (Pair) obj;
            return x.equals(p.x) && y.equals(p.y);
        }

        @Override
        public int hashCode() {
            return x * 31 + y;
        }
    }

    /**
     * Described algorithm by my words (main principle):
     *      0. Start on (0, 0) pixel (or any other)
     *      1. Using DFS or BFS going in all 8 directions foreach next pixel (x, y):
     *              1.1. Colorize pixel (x, y) if (x, y) is white
     *              1.2. If pixel (x, y) is not white or pixel (x, y) was already visited: continue (1)
     *      2. Some component of image has been colorized. Continue with pixel that is unvisited from (1)
     *      3. Continue until all pixels haven't been visited
     *
     * Just to clarify: by visited pixel I mean pixel was removed from allPixels
     */
    private static void colorizePixels(int[][] pixels) {
        // move pixels to HashSet
        Set<Pair> allPixels = new HashSet<>();
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                allPixels.add(new Pair(i, j));
            }
        }

        // component sizes with colours
        Set<Pair> componentSizesColours = new HashSet<>();

        Random r = new Random();

        while (!allPixels.isEmpty()) {
            Queue<Pair> queue = new LinkedList<>();
            queue.add(allPixels.iterator().next());

            Set<Pair> pixelsToColorize = new HashSet<>();

            int pixelsInComponent = 0;

            while (!queue.isEmpty()) {
                Pair d = queue.remove();
                if (allPixels.contains(d)) {
                    allPixels.remove(d);
                    pixelsInComponent++;
                    if (pixels[d.x][d.y] == -1) pixelsToColorize.add(d);  // pixels[d.x][d.y] = componentColour;
                    else { // edge
                        pixels[d.x][d.y] = Color.black.getRGB();  // getting rid of small gray pixels
                        continue;
                    };
                    for (Pair p : get8directions(d.x, d.y, pixels.length - 1, pixels[d.x].length - 1)) {
                        if (allPixels.contains(p)) {
                            queue.add(p);
                        }
                    }
                }
            }
            // let's play
            // colorize component
            if (pixelsInComponent > 1) {

                // float f = r.nextFloat();
                // int componentColour = new Color(f, f, f).getRGB();

                int componentColour = new Color(r.nextFloat(), r.nextFloat(), r.nextFloat()).getRGB();
                int componentColour2 = new Color(r.nextFloat(), r.nextFloat(), r.nextFloat()).getRGB();
                int componentColour3 = new Color(r.nextFloat(), r.nextFloat(), r.nextFloat()).getRGB();

                List<Integer> colours = List.of(componentColour, componentColour2, componentColour3);

                for (Pair sc : componentSizesColours) {
                    if (pixelsInComponent >= sc.x - 1000 && pixelsInComponent <= sc.x + 1000) {
                        componentColour = sc.y;
                        break;
                    }
                }

                componentSizesColours.add(new Pair(pixelsInComponent, componentColour));
                for (Pair d : pixelsToColorize) {
                    pixels[d.x][d.y] = colours.get(r.nextInt(3));  // componentColour;
                }
            }
        }
    }

    private static Set<Pair> get8directions(int i, int j, int maxIndexWidth, int maxIndexHeight) {
        Set<Pair> directions = new HashSet<>(Set.of(
            new Pair(i, j + 1), new Pair(i, j - 1), new Pair(i + 1, j), new Pair(i - 1, j),
            new Pair(i - 1, j - 1), new Pair(i - 1, j + 1),
            new Pair(i + 1, j - 1), new Pair(i + 1, j + 1)
        ));
        directions.removeIf((Pair p) ->
            p.x < 0 || p.x > maxIndexWidth || p.y < 0 || p.y > maxIndexHeight);
        return directions;
    }

    public static void vymalovanka(String inputFileName, String outputFileName) throws IOException {
        BufferedImage image = ImageIO.read(new File(inputFileName));
        int[][]pixels2D = createPixelArray(image);

        // ----- colorize -----
        colorizePixels(pixels2D);

        BufferedImage outputimage = createImage(pixels2D);
        ImageIO.write(outputimage,"PNG",new File(outputFileName));
    }

    private static BufferedImage createImage(int[][] pixels2D)  {
        if (pixels2D == null) return null;
        if (pixels2D[0] == null) return null;
        int height = pixels2D.length;
        int width = pixels2D[0].length;
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int y=0 ; y < height ; y++)
            for (int x=0 ; x < width ; x++)
                outputImage.setRGB(x,y,pixels2D[y][x]);
        return outputImage;
    }

    private static int[][] createPixelArray(BufferedImage inputImage)  {
        if (inputImage == null) return null;
        int[][] pixels2D = new int[inputImage.getHeight()][inputImage.getWidth()];
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                pixels2D[i][j] = inputImage.getRGB(j, i);
        return pixels2D;
    }

    public static void main(String[]args) throws IOException {
        for (int i = 3; i <= 3; i++)
            vymalovanka("obr" + i + ".png", "vymalovany" + i + ".png");
    }
}
