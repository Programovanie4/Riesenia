public class Turistika {
    private static int[] readPath(String path) {
        StringBuilder steps = new StringBuilder();
        int[] d = {0, 0, 0, 0};  // right, top, left, bottom
        int i = 0;
        for (char ch: path.toCharArray()) {
            if (ch != 'L' && ch != 'P') {
                steps.append(ch);
            } else {
                String s = steps.toString();
                if (!s.isEmpty()) d[i] += Integer.parseInt(steps.toString());
                steps.setLength(0);
                i += ch == 'L' ? 1 : -1;
                i = (i > 3) ? 0 : (i < 0) ? 3 : i;
            }
        }
        String s = steps.toString();
        if (!s.isEmpty()) d[i] += Integer.parseInt(steps.toString());

        return new int[]{d[0] - d[2], d[1] - d[3]};
    }

    public static boolean euklidovska(String trasa, double tolerancia) {
        int[] xy = readPath(trasa);
        return Math.sqrt(Math.pow(xy[0], 2) + Math.pow(xy[1], 2)) <= tolerancia;
    }

    public static boolean manhatanska(String trasa, int tolerancia) {
        int[] xy = readPath(trasa);
        return Math.abs(xy[0]) + Math.abs(xy[1]) <= tolerancia;
    }
}
