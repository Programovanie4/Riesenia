// moje najvacsie vytazene n je 9
// y = "l1[2I1" (bez uvodzoviek)
// vytazene: 20.02.2021 01:03:15
// kod, ktory to vytazil:

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tazba {
    public static StringBuilder sha256(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public static StringBuilder randomStringBuilder() {
        Random r = new Random();
        StringBuilder res = new StringBuilder();
        int n = r.nextInt(20) + 1;
        for (int i = 0; i < n; i++) {
            res.append((char) (r.nextInt(94) + 32));
        }
        return res;
    }

    public static int howManyZeros(StringBuilder s) {
        int res = 0;
        while (s.charAt(res) == '0') {
            res++;
        }
        return res;
    }

    public static void nonce(String x) {
        StringBuilder res = new StringBuilder();
        int max = 0, zeros = 0;
        while (true) {
            res = randomStringBuilder();
            zeros = howManyZeros(sha256(x + res));
            if (zeros > max) {
                max = zeros;
                try {
                    DateTimeFormatter f = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();

                    FileWriter myWriter = new FileWriter("mining.txt", true);
                    myWriter.write("\n\n" + Integer.toString(max) + "\n" + "\"" + res + "\"" + "\n" + f.format(now));
                    myWriter.close();
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        nonce("JAVA");
    }
}
