import java.lang.reflect.Array;
import java.util.Arrays;

public class DvojrozmernePole {
  public static String doStringu(Integer[][] pole) {
    if (pole == null) {
      return null;
    }
    StringBuilder res = new StringBuilder();
    for (int i = 0; i < pole.length; i++) {
      if (pole[i] == null) {
        if (i != pole.length - 1) {
          res.append(";");
        }
        continue;
      }
      for (int j = 0; j < pole[i].length; j++) {
        if (pole[i][j] != null) {
          res.append(Integer.toString(pole[i][j]));
        }
        if (j != pole[i].length - 1) {
          res.append(",");
        }
      }
      if (i != pole.length - 1) {
        res.append(";");
      }
    }
    return res.toString();
  }

  public static Integer[][] zoStringu(String pole) {
    if (pole == null) {
      return null;
    }

    String[] splited = pole.replace(";", "; ").split(";");
    Integer[][] res = new Integer[splited.length][];
    for (int i = 0; i < splited.length; i++) {
      String[] _i = splited[i].replaceAll(",", ", ").split(",");
      if (_i.length == 0 || _i.length == 1 && _i[0].replaceAll(" ","").equals("")) {
        res[i] = null;
      } else {
        res[i] = new Integer[_i.length];
        for (int j = 0; j < _i.length; j++) {
          String _j = _i[j].replaceAll(" ", "");
          if (_j.equals("")) {
            res[i][j] = null;
          } else {
            res[i][j] = Integer.parseInt(_j);
          }
        }
      }
    }

    return res;
  }

  public static void main(String[] args) {
    //    Integer[][] pole = {{1}, {1, 1}, null, {1, 1, null, 1}};
    //    System.out.println(doStringu(pole));
    String s = "abc,,def";
    String[] splited = s.split(",");
    for (int i = 0; i < splited.length; i++) {
      System.out.println("(" + splited[i] + ")");
    }
  }
}
