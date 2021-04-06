import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Vymalovanka {

    public static void main(String[] args) throws IOException {
        for (int i = 1; i <= 10; i++) {
            System.out.println("Vyfarbujem omalovanku " + i);
            vymalovanka("obr" + 1 + ".png", "vymalovany1_" + i + ".png");
        }
    }

    public static void vymalovanka(String inputFileName, String outputFileName) throws IOException {
        BufferedImage image = ImageIO.read(new File(inputFileName));
        int[][] pixels2D = createPixelArray(image);

        // tu vymaluj obrazok v poli pixels2D
        new Painter(pixels2D).paintAll();

        BufferedImage outputimage = createImage(pixels2D);
        ImageIO.write(outputimage, "PNG", new File(outputFileName));
    }

    private static BufferedImage createImage(int[][] pixels2D) {
        if (pixels2D == null) return null;
        if (pixels2D[0] == null) return null;
        int height = pixels2D.length;
        int width = pixels2D[0].length;
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                outputImage.setRGB(x, y, pixels2D[y][x]);
        return outputImage;
    }

    private static int[][] createPixelArray(BufferedImage inputImage) {
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
