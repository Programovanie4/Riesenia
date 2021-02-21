import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class SHA {

    static int max_found_prefix = 1;
    static String max_found = "";
    static long max_y = 0;

    public static String sha256(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    private static void checkPrefix(String s) {
        int countZeroes = 0;
        for (char ch : s.toCharArray()) {
            if (ch != '0') {
                break;
            }
            countZeroes++;
        }
        if (countZeroes > max_found_prefix) {
            max_found_prefix = countZeroes;
            max_found = s;
        }
    }

    public static void nonceMax(String x, int n) {
        while (max_found_prefix < 15) {
            nonce(x,n);
            n = max_found_prefix + 1;
        }
    }

    public static String nonce(String x, int n) {
        System.out.println("x: " + x);
        System.out.println("n: " + n);

        long y = max_y;
        int found = 0;
        String beginZeroes = "0".repeat(n);
        String nonce = null;
        while (found == 0) {
            nonce = sha256(x + y);
            String substr = nonce.substring(0, n);
            if (substr.equals(beginZeroes)) {
                found = 1;
                checkPrefix(nonce);
                max_y = y;
            } else {
                y--;
                if (y == 0) {
                    System.out.println(" uz ide od zaciatku ");
                }
            }
        }
        System.out.println("y: " + y);
        System.out.println("nonce:" + nonce);
        System.out.println("max 0 prefix: " + max_found_prefix);
        System.out.println();
        return Long.toString(y);
//        System.out.println(nonce);
//        return nonce;


////
//        byte[] array = new byte[new Random().nextInt(15)];
//        new Random().nextBytes(array);
//        String y = new String(array, StandardCharsets.US_ASCII);
//        String nonce = sha256(x + y);
//        String beginZeroes = "0".repeat(n);
//        while (nonce.substring(0, n) != beginZeroes) {
////        while(!(checkBits(nonce,beginZeroes))) {
//            array = new byte[new Random().nextInt(15)];
//            new Random().nextBytes(array);
//            y = new String(array, Charset.forName("UTF-8"));
//            nonce = sha256(x.getBytes() + y);
//
//        }
//        System.out.println(x);
//        System.out.println(y);
//        System.out.println(nonce);
//        return nonce;

//        byte[] rand = new byte[24];
//        new SecureRandom().nextBytes(rand);
//        String y = convertBytesToHex(rand);
//        String beginZeroes = "0".repeat(n);
//        String nonce = sha256(x + y);
//        while(!(nonce.substring(0,n) == beginZeroes)) {
//            rand = new byte[24];
//            new SecureRandom().nextBytes(rand);
//            y = convertBytesToHex(rand);
//            nonce = sha256(x + y);
//        }
//        System.out.println(x);
//        System.out.println(y);
//        System.out.println(nonce);
//        return nonce;
    }


    public static void main(String[] args) {

        nonceMax("JAVA",9);
//        nonce("3ebee", 3);
    }
}


