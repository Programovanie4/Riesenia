import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Vymalovanka
{

    private static List<Pair> smery = new ArrayList<>(List.of(new Pair(0, 1), new Pair(0, -1), new Pair(1, 0), new Pair(-1, 0)));


    private static class Pair implements Comparable
    {
        int i;
        int j;

        public Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public int compareTo(Object o)
        {
            if(o instanceof Pair)
            {
                if(((Pair) o).j == j && ((Pair) o).i == i) return 0;
            }
            return 1;
        }

    }

    public static void main(String[]args) throws IOException {
        for (int i = 1; i <= 6; i++)
            vymalovanka("obr" + i + ".png", "vymalovany" + i + ".png");
    }
    public static void vymalovanka(String inputFileName, String outputFileName) throws IOException {
            BufferedImage image = ImageIO.read(new File(inputFileName));
            int[][]pixels2D = createPixelArray(image);
            // tu vymaluj obrazok v poli pixels2D
            Map<Integer, Integer> farby = new HashMap();
            for(int i=0; i<pixels2D.length; i++)
            {
                for (int j = 0; j < pixels2D[i].length; j++)
                    if(pixels2D[i][j] == -1)
                    {
                        int color = (int) (Math.random()*2000000000-1000000000);
                        int num = bfs(pixels2D, i, j, color);
                        farby.put(color, num);

                    }
            }

            BufferedImage outputimage = createImage(pixels2D);
            ImageIO.write(outputimage,"PNG",new File(outputFileName));
    }

    private static int velkost_zony(int[][] pole, int i, int j)
    {
        Set<Pair> set = new HashSet();
        Stack<Pair> s = new Stack();
        s.add(new Pair(i, j));
        set.add(new Pair(i, j));
        while (!s.empty())
        {
            System.out.println(set.size());
            Pair p = s.pop();
            set.add(new Pair(p.i, p.j));
            for(Pair smer:smery)
            {
                if(set.contains(new Pair(p.i+smer.i, p.j+smer.j)) || p.i<0 || p.j<0 || p.i >= pole.length || p.j >= pole[p.i].length || pole[p.i][p.j] != -1) continue;
                s.add(new Pair(p.i+smer.i, p.j+smer.j));
            }
        }
        return set.size();

    }

    private static int bfs(int[][] pole, int i, int j, int color)
    {
        int num = 0;
        Stack<Pair> s = new Stack();
        s.add(new Pair(i, j));
        while (!s.empty())
        {
            Pair p = s.pop();
            if(p.i<0 || p.j<0 || p.i >= pole.length || p.j >= pole[p.i].length || pole[p.i][p.j] != -1) continue;
            pole[p.i][p.j] = color;
            num++;
            for(Pair smer:smery)
            {
                s.add(new Pair(p.i+smer.i, p.j+smer.j));
            }
        }
        return num;
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
}
