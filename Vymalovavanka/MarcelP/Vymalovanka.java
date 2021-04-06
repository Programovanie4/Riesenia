import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Vymalovanka {

    private static class Pair{
        public long i, j;
        public Pair(long i, long j){
            this.i=i;
            this.j=j;
        }
    }

    public static void main(String[]args) throws Exception {
        for (int i = 1; i <= 6; i++)
            vymalovanka("obr" + i + ".png", "vymalovany" + i + ".png");
    }
    public static void vymalovanka(String inputFileName, String outputFileName) throws Exception {
        System.out.println(inputFileName);
        BufferedImage image = ImageIO.read(new File(inputFileName));
        int[][]pixels2D = createPixelArray(image);
        List<Set<Pair>> plochy = new ArrayList<>();
        for(int i=0;i<pixels2D.length;i++){
            for(int j=0;j<pixels2D[i].length;j++){
                if(pixels2D[i][j]==-1){
                    var res= bfs(i,j,pixels2D); //prehladavame z kazdeho nezafarbeneho
                    if(res.size()<=5) { //ak je ta plocha mala, tak nech je cierna
                        for(Pair p: res)
                            pixels2D[(int) p.i][(int) p.j]=rgb2int(0,0,0);
                    }else{
                        plochy.add(res);
                    }
                }
            }
        }

        //usortime plochy od najvacsej
        plochy.sort((a, b)->Integer.compare(((Set<Pair>)b).size(),((Set<Pair>)a).size()));

        int[] colors = new int[] {
                rgb2int(255,166,194),
                rgb2int(247, 111, 154),
                rgb2int(125,229,255),
                rgb2int(255,167,123),
                rgb2int(210,126,255),
                rgb2int(255,173,157),
                rgb2int(255,215,140),
                rgb2int(191,241,255),
                rgb2int(125,229,255),
                rgb2int(64, 208,247),
                rgb2int(255,167,123),
                rgb2int(200,255,133),
                rgb2int(255,193,131)};

        //nafarbime pozadie
        for(Pair p: plochy.get(0)){
            pixels2D[(int) p.i][(int) p.j]=rgb2int(200,200,200);
        }

        //podobne velke plochy farbime rovnakou farbou
        for(int i=1;i<plochy.size();i++){
            for(Pair p: plochy.get(i)){
                pixels2D[(int) p.i][(int) p.j]=colors[i/(plochy.size()/colors.length+1)];
            }
        }
        BufferedImage outputimage = createImage(pixels2D);
        ImageIO.write(outputimage,"PNG",new File(outputFileName));
    }

    private static Set<Pair> bfs(long i, long j, int[][]pixels2D) throws Exception {
        Random r = new Random();
        Pair[] d = {new Pair(-1,0), new Pair(1,0), new Pair(0,1), new Pair(0,-1)};
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(i,j));
        int color = 0;
        Set<Pair> nafarbit = new HashSet<>();
        while(!q.isEmpty()){
            Pair top = q.poll();
            nafarbit.add(top);
            pixels2D[(int) top.i][(int) top.j]=color;
            for(Pair p:d){
                if(top.i+p.i>=0 && top.i+p.i<pixels2D.length){
                    if(top.j+p.j>=0 && top.j+p.j<pixels2D[(int) (top.i+p.i)].length){
                        if(pixels2D[(int) (top.i + p.i)][(int) (top.j + p.j)]==-1) {
                            pixels2D[(int) (top.i + p.i)][(int) (top.j + p.j)]=color;
                            q.add(new Pair(top.i + p.i, top.j + p.j));
                        }
                    }
                }
            }
        }
        return nafarbit;
    }

    private static int randomColor() throws Exception {
        int[] rs = new int[]{255,125,255,210,255,255,125,255,200,255};
        int[] gs = new int[]{128,229,167,126,173,215,229,167,255,193};
        int[] bs = new int[]{168,255,123,255,157,140,255,123,133,131};

        Random rand = new Random();
        int i=rand.nextInt(rs.length);

        return rgb2int(rs[i], gs[i], bs[i]);
    }


    private static int rgb2int(int r, int g, int b) throws Exception { //vyzarujeme svetlo, nie sme osvetleni
        if(r<0 || r>255 || g<0 || g>255 || b<0 || b>255)
            throw new Exception("Zly rozsah");
        return r*256*256 + g*256 + b;
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
