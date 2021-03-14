import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;

public class Ockovanie {

  private static boolean isDate(int y, int m, int d) {
    try {
      LocalDate.of(y, m, d);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  //  returns y-d-m
  private static int[] makeDate(String s) {
    int[] res = new int[3];
    res[0] = Integer.parseInt("19" + s.charAt(0) + s.charAt(1));
    res[1] = Integer.parseInt("" + s.charAt(2) + s.charAt(3));
    res[2] = Integer.parseInt("" + s.charAt(4) + s.charAt(5));
    if (res[1] > 50) {
      res[1] -= 50;
    }
    return res;
  }

  public static int korektni(String[] osoby) {
    System.out.println(Arrays.deepToString(osoby));
    int res = 0;
    for (String i : osoby) {
      String _i = i.split(",")[1].replaceAll(" ", "");
      if (!(_i.matches("[0-9]{6}/[0-9]{3,4}"))) {
        continue;
      }
      _i = _i.replace("/", "");
      try {
        if (_i.length() == 10 && Long.parseLong(_i) % 11 != 0) {
          continue;
        }
        int[] date = makeDate(_i);

        if (isDate(date[0], date[1], date[2])) {
          res++;
        }
      } catch (Exception ignored) {

      }
    }
    return res;
  }

  public static int kandidati75(String[] osoby) {
    int res = 0;
    String[] temp = new String[1];
    for (String i : osoby) {
      temp[0] = i;
      if (korektni(temp) == 1) {
        String _i = i.split(",")[1].replace("/", "").replaceAll(" ", "");
        int[] date = makeDate(_i);
        if (date[0] < 1954 && _i.length() != 9) {
          continue;
        }
        LocalDate a = LocalDate.of(date[0], date[1], date[2]);
        LocalDate b = LocalDate.of(2021, 2, 24);
        long days = Duration.between(a.atStartOfDay(), b.atStartOfDay()).toDays();
        if (days / 360 > 75) {
          res++;
        }
      }
    }
    return res;
  }

  public static void main(String[] args) {
    String[] arr = {
      "f, 065430/0009, 000 000 000, €€€.€€€.€",
      "Jožko Mrkvička, 771224/1240, 0902 354 787, jozo.mrkva@gmail.com",
      "Jožko Púčik, 430211/837, 0912 454 732, jozo.pucik@gmail.com",
      "Adam Šangala, 430211/8372, 0912 454 732, adams@gmail.com",
      "Anna Kareninová, 406212/123, 0932 124 234, annacar@gmail.com",
      "Sansa Starková, 995321/1235, 0999999999, allmenmustdie@gmail.com",
      "Rysavá Jalovica, 600600, , ",
      "Veľký Gatsby, 6412287365, 911, vg@vg.com ",
      "Krstný otec, 650229/1115, 000 000 000, €€€.€€€.€"
    };
    System.out.println(korektni(arr)); // 5 -> 771224/1240, 430211/837, 406212/123, 995321/1235 + f
    System.out.println(kandidati75(arr)); // 2 -> 430211/837 a 406212/123
  }
}






