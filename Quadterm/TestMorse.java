import LISTTestScoring.LISTTestScoring;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TestMorse {
	private static LISTTestScoring scoring = null;

	@BeforeClass
	public static void initScoring() {
		scoring = new LISTTestScoring();
		scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
	}

	@Test	
	public void testKoduj() {
		assertEquals("koduj(\"SOS\")", "... --- ...", Morse.koduj("SOS"));
		assertEquals("koduj(\"S O S\")", "... --- ...", Morse.koduj("S O S"));
		assertEquals("koduj(\"S O S SOS SO S\")", "... --- ... ... --- ... ... --- ...", Morse.koduj("S O S SOS SO    S"));
        scoring.updateScore("lang:common_list_test_scoring_name", 10);
		assertEquals("koduj(\"MAYDAY\")", "-- .- -.-- -.. .- -.--", Morse.koduj("MAYDAY"));
		assertEquals("koduj(\" CQDCQDS OSDEMGYMGYREQUIRIME DIATASISTA NCPOSITION \")", "-.-. --.- -.. -.-. --.- -.. ... --- ... -.. . -- --. -.-- -- --. -.-- .-. . --.- ..- .. .-. .. -- . -.. .. .- - .- ... .. ... - .- -. -.-. .--. --- ... .. - .. --- -.", Morse.koduj(" CQDCQDS OSDEMGYMGYREQUIRIME DIATASISTA NCPOSITION "));		
		assertEquals("koduj(\"SOSSOSCQDCQD TITANIC\")", "... --- ... ... --- ... -.-. --.- -.. -.-. --.- -.. - .. - .- -. .. -.-.", Morse.koduj("SOSSOSCQDCQD TITANIC"));		
		assertEquals("koduj(\"TO BE OR NOT TO BE THAT IS THE QUESTION\")", "- --- -... . --- .-. -. --- - - --- -... . - .... .- - .. ... - .... . --.- ..- . ... - .. --- -.", Morse.koduj("TO BE OR NOT TO BE THAT IS THE QUESTION"));
		scoring.updateScore("lang:common_list_test_scoring_name", 10);
	}
	
	@Test	
	public void test_Dekoduj() {
		assertEquals("dekoduj(\"... --- ...\")", "SOS", Morse.dekoduj("... --- ...").trim().toUpperCase());
		assertEquals("dekoduj(\"-- .- -.-- -.. .- -.--\")", "MAYDAY", Morse.dekoduj("-- .- -.-- -.. .- -.--").trim().toUpperCase());
		assertEquals("dekoduj(\".--- .- ...- .-\")", "JAVA", Morse.dekoduj("  .---   .-  ...-    .-   ").trim().toUpperCase());
        scoring.updateScore("lang:common_list_test_scoring_name", 10);
		assertEquals("dekoduj(\"  ----\")", null, Morse.dekoduj("  ----"));
		assertEquals("dekoduj(\"...  ---.\")", null, Morse.dekoduj("...  ---. "));
		assertEquals("dekoduj(\"MORSECODE\")","MORSECODE", Morse.dekoduj("-- --- .-. ... .   -.-. --- -.. .").trim().toUpperCase());
        scoring.updateScore("lang:common_list_test_scoring_name", 10);
		assertEquals("dekoduj(\"CQDCQDSOSDEMGYMGYREQUIRIMEDIATASISTANCPOSITION\")","CQDCQDSOSDEMGYMGYREQUIRIMEDIATASISTANCPOSITION", Morse.dekoduj("-.-. --.- -..   -.-. --.- -..   ... --- ...   -.. .   -- --. -.--   -- --. -.--   .-. . --.- ..- .. .-.   .. -- . -.. .. .- -   .- ... .. ... - .- -. -.-.   .--. --- ... .. - .. --- -.").trim().toUpperCase());
		assertEquals("dekoduj(\"SOSSOSCQDCQDTITANIC\")","SOSSOSCQDCQDTITANIC", Morse.dekoduj("... --- ...   ... --- ...   -.-. --.- -..   -.-. --.- -..   - .. - .- -. .. -.-.").trim().toUpperCase());
		assertEquals("dekoduj(\"TOBEORNOTTOBETHATISTHEQUESTION\")", "TOBEORNOTTOBETHATISTHEQUESTION", Morse.dekoduj("- --- -... . --- .-. -. --- - - --- -... . - .... .- - .. ... - .... . --.- ..- . ... - .. --- -.").trim().toUpperCase());
		scoring.updateScore("lang:common_list_test_scoring_name", 10);
	}
	
	@Test(timeout=10000)	
	public void test_DekodujVsetky() {
		assertEquals("dekodujVsetky(\"..\").size()", 2, Morse.dekodujVsetky("..").size());
		assertEquals("dekodujVsetky(\".-\").size()", 2, Morse.dekodujVsetky(".-").size());
		assertEquals("dekodujVsetky(\".-.\").size()", 4, Morse.dekodujVsetky(".-.").size());
        scoring.updateScore("lang:common_list_test_scoring_name", 10);
		assertEquals("dekodujVsetky(\".--\").size()", 4, Morse.dekodujVsetky(".--").size());
		assertEquals("dekodujVsetky(\"...\").size()", 4, Morse.dekodujVsetky("...").size());
		assertEquals("dekodujVsetky(\"....\").size()", 8, Morse.dekodujVsetky("....").size());
		assertEquals("dekodujVsetky(\".....\").size()", 15, Morse.dekodujVsetky(".....").size());
        scoring.updateScore("lang:common_list_test_scoring_name", 10);
        assertTrue("dekodujVsetky(\".--\")", Morse.dekodujVsetky(".--").containsAll(List.of("AT", "EM", "ETT", "W")));
        assertTrue("dekodujVsetky(\".--\")", List.of("AT", "EM", "ETT", "W").containsAll(Morse.dekodujVsetky(".--")));

        assertTrue("dekodujVsetky(\"....\")", Morse.dekodujVsetky("....").containsAll(List.of("EEEE", "EEI", "EIE", "ES", "H", "IEE", "II", "SE")));
        assertTrue("dekodujVsetky(\"....\")", List.of("EEEE", "EEI", "EIE", "ES", "H", "IEE", "II", "SE").containsAll(Morse.dekodujVsetky("....")));

        assertTrue("dekodujVsetky(\".....\")", Morse.dekodujVsetky(".....").containsAll(List.of("EEEEE", "EEEI", "EEIE", "EES", "EH", "EIEE", "EII", "ESE", "HE", "IEEE", "IEI", "IIE", "IS", "SEE", "SI")));
        assertTrue("dekodujVsetky(\".....\")", List.of("EEEEE", "EEEI", "EEIE", "EES", "EH", "EIEE", "EII", "ESE", "HE", "IEEE", "IEI", "IIE", "IS", "SEE", "SI").containsAll(Morse.dekodujVsetky(".....")));

        scoring.updateScore("lang:common_list_test_scoring_name", 10);
		assertEquals("dekodujVsetky(\"...---...\").size()", 192, Morse.dekodujVsetky("...---...").size());
		assertTrue("dekodujVsetky(\"...---...\") contains SOS", Morse.dekodujVsetky("...---...").contains("SOS"));
		assertFalse("dekodujVsetky(\"...---...\") not contains DPH", Morse.dekodujVsetky("...---...").contains("DPH"));
		assertEquals("dekodujVsetky(\"--.--.---...--.--\").size()", 36536, Morse.dekodujVsetky("--.--.---...--.--").size());
		assertTrue("dekodujVsetky(\"--.--.---...--.--\") contains MAYDAY", Morse.dekodujVsetky("--.--.---...--.--").contains("MAYDAY"));
		scoring.updateScore("lang:common_list_test_scoring_name", 20);
	}
}
