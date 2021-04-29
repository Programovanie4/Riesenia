import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;


public class TestSubtitles {


	@Test
	public void testSubtitles() throws FileNotFoundException {
		// Okej, snazil som sa najst autenticke titulky k pravej brazilskej
		// telenovele, ale nevydalo... tak miesto toho aspon TBBT.
		
		List<List<String>> files = Arrays.asList(
				Arrays.asList("UTF8", "UTF8",			// input1 - bez konverzie, jedna reklama
						"00:00:22,123 --> 00:02:27,623"),
				Arrays.asList("ISO8859_2", "Cp1250"),	// input2 - konverzia, bez reklamy
				Arrays.asList("UTF8", "Cp1250",			// input3 - konverzia, s reklamami
						"00:13:49,498 --> 00:20:37,648",
						"00:05:50,086 --> 00:09:12,379",
						"00:26:33,382 --> 00:31:43,764"),
				// ... dalsie dva vstupy su iba na serveri:
				Arrays.asList("Cp1250", "UTF8",		// input4 - viac reklam za sebou, nulova reklama
						"00:07:36,236 --> 00:09:13,873",
						"00:02:15,946 --> 00:05:12,327",
						"00:07:35,574 --> 00:07:35,574",
						"00:05:14,274 --> 00:07:32,576"),
				Arrays.asList("Cp1250", "ISO8859_2",	// input5 - reklamy na zaciatku a konci
						"00:36:12,243 --> 00:49:23,362",
						"00:02:46,651 --> 00:08:31,450",
						"00:51:33,533 --> 23:59:59,999",
						"00:20:54,156 --> 00:26:06,871",
						"00:00:03,374 --> 00:02:44,273")
				);
		
		for(int i = 0; i < files.size(); i++){
			int n = i + 1;
			List<String> file = files.get(i);
			Subtitles.addAds("input"+n+".srt", "output"+n+".srt",
					file.get(0), file.get(1), file.subList(2, file.size()));
			assertEqualsFile("expected"+n+".srt", "output"+n+".srt", file.get(1));
		}
	}

	@Test
	public void testBonus() throws FileNotFoundException {
		List<List<String>> files = Arrays.asList(
				Arrays.asList(null, "UTF8",
						"00:07:55,042 --> 00:12:13,354",
						"00:16:56,328 --> 00:19:43,513"),
				Arrays.asList(null, "UTF8",
						"00:14:00,000 --> 00:22:22,222",
						"00:26:54,156 --> 00:31:43,821"),
				Arrays.asList(null, "UTF8",
						"00:08:03,516 --> 00:14:32,235",
						"00:25:31,136 --> 00:31:25,631")
				);
		
		for(int i = 0; i < files.size(); i++){
			int n = i + 1;
			List<String> file = files.get(i);
			Subtitles.addAds("bonus"+n+".srt", "b_output"+n+".srt",
					file.get(0), file.get(1), file.subList(2, file.size()));
			assertEqualsFile("b_expected"+n+".srt", "b_output"+n+".srt", file.get(1));
		}
	}
	
	
	public static void assertEqualsFile(String expFile, String actFile, String enc) throws FileNotFoundException{
		Scanner exp = new Scanner(new File(expFile), enc);
		Scanner act = new Scanner(new File(actFile), enc);

		try{
			int i = 1;
			while(exp.hasNextLine()){
				String expLine = exp.nextLine();
				assertTrue("Titulky " + actFile + " su kratsie ako maju byt, chyba riadok c."
						+ i + " (\"" + expLine + "\") a dalsie.",
						act.hasNextLine());
				String actLine = act.nextLine();
				assertEquals("Titulky " + actFile + ", riadok " + i + ":", expLine, actLine);
				i++;
			}
			if(act.hasNextLine()){
				fail("Titulkty " + actFile + " su dlhsie ako maju byt, nemali by obsahovat riadok c."
						+ i + " (\"" + act.nextLine() + "\") a dalsie.");
			}
		}
		finally{
			exp.close();
			act.close();
		}
	}
	
}
