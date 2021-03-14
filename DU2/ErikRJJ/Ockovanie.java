import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Ockovanie
{
    private static String formatujNaDatumRet(String vstup, boolean Pred53)
    {
        int y = Integer.parseInt(vstup.substring(0, 2));
        int m = Integer.parseInt(vstup.substring(2, 4));
        int d = Integer.parseInt(vstup.substring(4, 6));

        if (Pred53)
        {
            y += (y < 53)? 1900 : 1800;
        }
        else
        {
            y += (y >= 53)? 1900 : 2000;
        }

        m = (m > 12)? m - 50 : m;

        return y + "-" + ((m < 10)? "0" : "")  + m + "-" + ((d < 10)? "0" : "")  + d;
    }

    private static boolean korektnyDatum(String datum, boolean Pred53)
    {
        try
        {
            String datumRet = formatujNaDatumRet(datum, Pred53);
            LocalDate date = LocalDate.parse(datumRet);
            LocalDate currentDate = LocalDate.now();

            if (currentDate.isAfter(date))
                return true;
        }
        catch(DateTimeParseException e) { }

        return false;
    }


    private static boolean dovrsil75(String rodneCislo)
    {
        String[] polozky = rodneCislo.split("/", 2);
        String datum = polozky[0];
        String kod = (polozky.length == 2)? polozky[1] : "";

        String datumRet = formatujNaDatumRet(datum, (kod.length() == 3)? true : false);
        LocalDate datumNarodenia = LocalDate.parse(datumRet);
        LocalDate cviko2 = LocalDate.parse("2021-02-24");

        if (datumNarodenia.until(cviko2, ChronoUnit.YEARS) >= 75)
            return true;

        return false;

    }

    private static boolean korektneRodneCislo(String rodneCislo)
    {
        String[] polozky = rodneCislo.split("/", 2);
        String datum = polozky[0];
        String kod = (polozky.length == 2)? polozky[1] : "";

        if (kod.length() == 3 && korektnyDatum(datum, true))
            return true;
        else if (kod.length() == 4 && (Long.valueOf(datum + kod) % 11 == 0) && korektnyDatum(datum, false))
            return true;

        return false;
    }

    public static int korektni(String[] osoby)
    {
        int pocet = 0;
        String rodneCislo;

        for (String zaznam: osoby)
        {
            if (zaznam.contains(","))
            {
                rodneCislo = zaznam.split(",", 4)[1].trim();
                if (korektneRodneCislo(rodneCislo))
                    pocet++;
            }
        }
        return pocet;
    }


    public static int kandidati75(String[] osoby)
    {
        int pocet = 0;
        String rodneCislo;

        for (String zaznam: osoby)
        {
            if (zaznam.contains(","))
            {
                rodneCislo = zaznam.split(",", 4)[1].trim();
                if (korektneRodneCislo(rodneCislo) && dovrsil75(rodneCislo))
                    pocet++;
            }

        }
        return pocet;
    }

    public static void main(String[] args)
    {
        String[] osoby = {"Jožko Mrkvička, 771224/1240, 0902 354 787, jozo.mrkva@gmail.com",
                            "Jožko Púčik, 430211/837, 0912 454 732, jozo.pucik@gmail.com",
                            "Adam Šangala, 430211/8372, 0912 454 732, adams@gmail.com",
                            "Anna Kareninová, 406212/123, 0932 124 234, annacar@gmail.com",
                            "Sansa Starková, 995321/1235, 0999999999, allmenmustdie@gmail.com",
                            "Rysavá Jalovica, 600600, , ",
                            "Veľký Gatsby, 6412287365, 911, vg@vg.com ",
                            "Krstný otec, 650229/1115, 000 000 000, €€€.€€€.€"};

        System.out.println(korektni(osoby));
        System.out.println(kandidati75(osoby));
    }
}
