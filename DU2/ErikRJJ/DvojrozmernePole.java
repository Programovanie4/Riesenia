import java.util.Arrays;

public class DvojrozmernePole
{
    public static String doStringu(Integer[][] pole)
    {
        if (pole == null)
            return null;
        StringBuffer vystup = new StringBuffer("");
        for (int i = 0; i < pole.length; i++)
        {
            if (pole[i] != null)
            {
                for (int j = 0; j < pole[i].length; j++)
                {
                    if (pole[i][j] != null)
                    {
                        vystup.append(pole[i][j]);
                    }
                    if (j != pole[i].length - 1)
                    {
                        vystup.append(",");
                    }
                }
            }

            if (i != pole.length - 1)
            {
                vystup.append(";");
            }
        }
        return vystup.toString();
    }

    public static Integer[][] zoStringu(String pole)
    {
        if (pole == null)
            return null;

        String[] riadky = pole.split(";", -1);
        Integer[][] vystup = new Integer[riadky.length][];

        for (int i = 0; i < riadky.length; i++)
        {
            String[] stlpce = riadky[i].split(",", -1);
            Integer[] prvky = new Integer[stlpce.length];

            if (stlpce.length == 1 && stlpce[0].strip().isEmpty())
            {
                vystup[i] = null;
                continue;
            }

            for (int j = 0; j < stlpce.length; j++)
            {
                if (stlpce[j].strip().equals(""))
                {
                    prvky[j] = null;
                }
                else
                {
                    prvky[j] = Integer.parseInt(stlpce[j].strip());
                }
            }
            vystup[i] = prvky;
        }
        return vystup;
    }
}
