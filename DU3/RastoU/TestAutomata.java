import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestAutomata {

	@Test
	public void test() {
		{
			Automata a = new Automata(
					"q0",			// pociatocny stav
					new Transit[]{	// delta
							new Transit("q0", 'a', "q1"),
							new Transit("q0", 'b', "q0"),
							new Transit("q1", 'a', "q1"),
							new Transit("q1", 'b', "q1")
							},
					new String[]{"q1"});// koncove stavy
			assertEquals("nejde ti ani priklad zo zadania", 2, a.states().length);
			assertEquals("nejde ti ani priklad zo zadania", 2, a.alphabet().length);
			assertEquals("nejde ti ani priklad zo zadania", true, a.isCorrectFA());
			assertEquals("nejde ti ani priklad zo zadania", true, a.accepts("a"));
			assertEquals("nejde ti ani priklad zo zadania", true, a.accepts("aababababababababababababab"));
			assertEquals("nejde ti ani priklad zo zadania", false, a.accepts("aabababababababababababababfujfuj"));
			assertEquals("nejde ti ani priklad zo zadania", false, a.accepts("b"));
			assertEquals("nejde ti ani priklad zo zadania", false, a.accepts("bbsadfasbdfbasdfbasdbfabsdfbasdfasdbfasbdfsadfbasbdfbsadf"));
			assertEquals("nejde ti ani priklad zo zadania", false, a.accepts(""));
			assertEquals("nejde ti ani priklad zo zadania", false, a.accepts(null));
		}
		//----------------------------------------------------------------------------
		{
			Automata a = new Automata(
					"q10",			// pociatocny stav
					new Transit[]{	// delta
							new Transit("q0", 'a', "q1"),
							new Transit("q0", 'b', "q0"),
							new Transit("q1", 'a', "q1"),
							new Transit("q1", 'b', "q1")
							},
					new String[]{"q1"});// koncove stavy
			assertEquals("zly inicialny stav", false, a.isCorrectFA());
		}
		//----------------------------------------------------------------------------
		{
			Automata a = new Automata(
					null,			// pociatocny stav
					new Transit[]{	// delta
							new Transit("q0", 'a', "q1"),
							new Transit("q0", 'b', "q0"),
							new Transit("q1", 'a', "q1"),
							new Transit("q1", 'b', "q1")
							},
					new String[]{"q1"});// koncove stavy
			assertEquals("zly inicialny stav", false, a.isCorrectFA());
		}
		//----------------------------------------------------------------------------
		{
			Automata a = new Automata(
					"q0",			// pociatocny stav
					new Transit[]{	// delta
							new Transit("q0", 'a', "q1"),
							new Transit("q0", 'b', "q0"),
							new Transit("q1", 'a', "q1"),
							new Transit("q1", 'b', "q1")
							},
					new String[]{"q11"});// koncove stavy
			assertEquals("zly koncovy stav", false, a.isCorrectFA());
		}
		//----------------------------------------------------------------------------
		{
			Automata a = new Automata(
					"q10",			// pociatocny stav
					new Transit[]{	// delta
							new Transit("q0", 'a', "q1"),
							new Transit("q0", 'b', "q0"),
							new Transit("q1", 'a', "q1"),
							new Transit("q1", 'b', "q1")
							},
					new String[]{""});// koncove stavy
			assertEquals("zly koncovy stav", false, a.isCorrectFA());
		}
		//----------------------------------------------------------------------------
		{
			Automata a = new Automata(
					"q10",			// pociatocny stav
					new Transit[]{	// delta
							new Transit("q0", 'a', "q1"),
							new Transit("q0", 'b', "q0"),
							new Transit("q1", 'a', "q1"),
							new Transit("q1", 'b', "q1")
							},
					new String[]{null});// koncove stavy
			assertEquals("zly koncovy stav", false, a.isCorrectFA());
		}
		//----------------------------------------------------------------------------
		{
			Automata a = new Automata(
					"q10",			// pociatocny stav
					new Transit[]{	// delta
							new Transit("q0", 'a', "q1"),
							new Transit("q0", 'b', "q0"),
							new Transit("q1", 'a', "q1"),
							new Transit("q1", 'b', "q1")
							},
					null);// koncove stavy
			assertEquals("zly koncovy stav", false, a.isCorrectFA());
		}
	//----------------------------------------------------------------------------
	{
		Automata a = new Automata(
				"q0",			// pociatocny stav
				new Transit[]{	// delta
						new Transit("q0", 'a', "q1"),
						new Transit("q0", 'b', "q0"),
						new Transit("q1", 'b', "q1")
						},
				new String[]{"q1"});// koncove stavy
		assertEquals("zla delta stav", false, a.isCorrectFA());
	}
	//----------------------------------------------------------------------------
	{
		Automata a = new Automata(
				"q0",			// pociatocny stav
				new Transit[]{	// delta
						new Transit("q0", 'a', "q1"),
						new Transit("q0", 'b', "q0"),
						new Transit("q0", 'b', "q0"),
						new Transit("q1", 'b', "q1")
						},
				new String[]{"q1"});// koncove stavy
		assertEquals("zla delta stav", false, a.isCorrectFA());
	}
	//----------------------------------------------------------------------------
	{
		Automata a = new Automata(
				"q0",			// pociatocny stav
				null,				
				new String[]{"q1"});// koncove stavy
		assertEquals("zla delta stav", false, a.isCorrectFA());
	}	
	//----------------------------------------------------------------------------
	{
		Automata a = new Automata(
				"q0",			// pociatocny stav
				new Transit[]{	// delta
						},
				new String[]{"q1"});// koncove stavy
		assertEquals("zla delta stav", false, a.isCorrectFA());
	}
	//----------------------------------------------------------------------------
	{
		Automata a = new Automata(
				"q0",			// pociatocny stav
				new Transit[]{	// delta
						new Transit("q0", '0', "q1"),
						new Transit("q0", '1', "q0"),
						new Transit("q1", '0', "q1"),
						new Transit("q1", '1', "q2"),
						new Transit("q2", '0', "q3"),
						new Transit("q2", '1', "q0"),
						new Transit("q3", '0', "q3"),
						new Transit("q3", '1', "q3")
				},
				new String[]{"q3"});// koncove stavy
		assertEquals("lavy obrazok v zadani", 4, a.states().length);
		assertEquals("lavy obrazok v zadani", 2, a.alphabet().length);
		assertEquals("lavy obrazok v zadani", true, a.isCorrectFA());
		assertEquals("lavy obrazok v zadani", true, a.accepts("010"));
		assertEquals("lavy obrazok v zadani", true, a.accepts("01010101010"));
		assertEquals("lavy obrazok v zadani", true, a.accepts("1111101010101010"));
		assertEquals("lavy obrazok v zadani", false, a.accepts("0000001111111"));
		assertEquals("lavy obrazok v zadani", true, a.accepts("000000111111100000000000000010000000000001111111111111000000000000000000000111111111111111111110000000000000"));
	} 
	//----------------------------------------------------------------------------
	}

}
