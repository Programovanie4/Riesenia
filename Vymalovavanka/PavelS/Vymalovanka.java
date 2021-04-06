import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Vymalovanka {
    static final int black = -4000000;
    static final int white = -1;

    public static void main(String[]args) throws IOException {
        for (int i = 1; i <= 6; i++)
            vymalovanka("src\\obr" + i + ".png", "src\\vymalovany" + i + ".png");
    }
    public static void vymalovanka(String inputFileName, String outputFileName) throws IOException {
            BufferedImage image = ImageIO.read(new File(inputFileName));

            int[][]pixels2D = createPixelArray(image);
            int bgColor = getRandomColor();
            int[][] bounds = getBounds(pixels2D);
            for (int i = 0; i < pixels2D.length; i++) {
                for (int j = 0; j < pixels2D[i].length; j++) {
                    if (j < bounds[i][0] || j > bounds[i][1] || i < bounds[j][2] || i > bounds[j][3]) pixels2D[i][j] = bgColor;
                }
            }
            for (int i = 0; i < pixels2D.length; i++) {
                for (int j = 0; j < pixels2D[i].length; j++) {
                    if (pixels2D[i][j] == -1) colorize(pixels2D, i, j, getRandomColor());
                }
            }
            // toto funguje velmi divne, nenasiel som problem
//            for (int i = pixels2D.length-1; i>=0; i--) {
//                for (int j = pixels2D[i].length-1; j>=0; j--) {
//                    if (pixels2D[i][j] > black) pixels2D[i][j] = getColor(pixels2D, i, j);
//                }
//            }
            BufferedImage outputimage = createImage(pixels2D);
            ImageIO.write(outputimage,"PNG",new File(outputFileName));
    }
    private static int[][] getBounds(int[][] pixels) {
        int[][] result = new int[Math.max(pixels.length, pixels[0].length)][4];
        for (int i = 0; i < pixels.length; i++) {
            int left = 0;
            int right = 0;
            for (int j = 0; j < pixels[i].length; j++) {
                if (pixels[i][j] != white && left == 0) left = j;
                else if (pixels[i][j] != white) right = j;
            }
            result[i][0] = left;
            result[i][1] = right;
        }
        for (int i = 0; i < pixels[0].length; i++) {
            int high = 0;
            int low = 0;
            for (int j = 0; j < pixels.length; j++) {
                if (pixels[j][i] != white && high == 0) high = j;
                else if (pixels[j][i] != white) low = j;
            }
            result[i][2] = high;
            result[i][3] = low;
        }
        return result;
    }
    private static void colorize(int[][] pixels, int i, int j, int color) {
        while(j > 0 && pixels[i][j] == white) j--;
        j++;
        while(pixels[i][j] == white) {
            pixels[i][j] = color;
            j++;
        }
        j--;
        while (pixels[i][j] == color) {
            if (i < pixels.length-1 && pixels[i+1][j] == -1) colorize(pixels, i+1, j, color);
            if (i > 0 && pixels[i-1][j] == white) colorize(pixels, i-1, j, color);
            j--;
        }
    }
    private static int getColor(int[][] pixels, int i, int j) {
        if (i > 0 && pixels[i-1][j] > black && pixels[i-1][j] != white && pixels[i][j] != pixels[i-1][j]) {
            System.out.println("1");
            return pixels[i-1][j];
        }
        if (i < pixels.length-1 && pixels[i+1][j] > black && pixels[i+1][j] != white && pixels[i][j] != pixels[i+1][j]) {
            System.out.println("2");
            return pixels[i+1][j];
        }
        if (j > 0 && pixels[i][j-1] > black && pixels[i][j-1] != white && pixels[i][j] != pixels[i][j-1]) {
            System.out.println("3");
            return pixels[i][j-1];
        }
        if (j < pixels[i].length-1 && pixels[i][j+1] > black && pixels[i][j+1] != white && pixels[i][j] != pixels[i][j+1]) {
            System.out.println("4");
            return pixels[i][j+1];
        }
        return pixels[i][j];
    }

    private static int getRandomColor() {
        int color = 0x000000ff;
        Random r = new Random();
        for (int i = 0; i < 6; i++) {
            int c = r.nextInt(16);
            switch (c) {
                case 10:
                    color = color | 0xA;
                    break;
                case 11:
                    color = color | 0xB;
                    break;
                case 12:
                    color = color | 0xC;
                    break;
                case 13:
                    color = color | 0xD;
                    break;
                case 14:
                    color = color | 0xE;
                    break;
                case 15:
                    color = color | 0xF;
                    break;
                default:
                    color = color | c;
            }
            color = color << 4;
        }
        return color;
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
