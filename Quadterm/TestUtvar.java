import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import LISTTestScoring.LISTTestScoring;

public class TestUtvar {
	private static LISTTestScoring scoring = null;
	@BeforeClass
	public static void initScoring() {
		scoring = new LISTTestScoring();
		scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
	}
	@Test
	public void testPravouholnikObsah() {
		{
			Pravouholnik p = new Pravouholnik(new Bod(0,0), 10, 10);
			assertEquals("Pravouholnik 1/obsah", 100, p.obsah(), 0.001);
		}		
		{
			Pravouholnik p = new Pravouholnik(new Bod(0,0), 20, 10);
			assertEquals("Pravouholnik 2/obsah", 200, p.obsah(), 0.001);
		}
		scoring.updateScore("lang:common_list_test_scoring_name", 100.0/18);
	}
	
	@Test
	public void testPravouholnikObvod() {
		{
			Pravouholnik p = new Pravouholnik(new Bod(0,0), 10, 10);
			assertEquals("Pravouholnik 1/obvod",  40, p.obvod(), 0.001);
		}		
		{
			Pravouholnik p = new Pravouholnik(new Bod(0,0), 20, 10);
			assertEquals("Pravouholnik 2/obvod",  60, p.obvod(), 0.001);
		}
		scoring.updateScore("lang:common_list_test_scoring_name", 100.0/18);
	}
	@Test
	public void testPravouholnikObsahuje() {
		{
			Pravouholnik p = new Pravouholnik(new Bod(0,0), 10, 10);
			assertTrue("Pravouholnik 1/", p.obsahuje(new Bod(5,5)));
			assertFalse("Pravouholnik 1/", p.obsahuje(new Bod(5,15)));
			assertFalse("Pravouholnik 1/", p.obsahuje(new Bod(15,5)));
			assertFalse("Pravouholnik 1/", p.obsahuje(new Bod(15,15)));
			assertFalse("Pravouholnik 1/", p.obsahuje(new Bod(-5,-5)));			
		}		
		{
			Pravouholnik p = new Pravouholnik(new Bod(0,0), 20, 10);
			assertTrue("Pravouholnik 2/", p.obsahuje(new Bod(5,5)));
			assertFalse("Pravouholnik 2/", p.obsahuje(new Bod(5,15)));
			assertTrue("Pravouholnik 2/", p.obsahuje(new Bod(15,5)));
			assertFalse("Pravouholnik 2/", p.obsahuje(new Bod(15,15)));
			assertFalse("Pravouholnik 2/", p.obsahuje(new Bod(-5,-5)));			
		}
		scoring.updateScore("lang:common_list_test_scoring_name", 100.0/18);
	}
	//-------------------------------------
	@Test
	public void testKruhObsah() {
		{
			Kruh p = new Kruh(new Bod(0,0), 10);
			assertEquals("Kruh 1/obsah", Math.PI*10*10, p.obsah(), 0.001);
		}		
		scoring.updateScore("lang:common_list_test_scoring_name", 100.0/18);
		{
			Kruh p = new Kruh(new Bod(0,0), 20);
			assertEquals("Kruh 2/obsah", Math.PI*20*20, p.obsah(), 0.001);
		}
		scoring.updateScore("lang:common_list_test_scoring_name", 100.0/18);
	}
	@Test
	public void testKruhObvod() {
		{
			Kruh p = new Kruh(new Bod(0,0), 10);
			assertEquals("Kruh 1/obvod",  2*Math.PI*10, p.obvod(), 0.001);
		}		
		scoring.updateScore("lang:common_list_test_scoring_name", 100.0/18);
		{
			Kruh p = new Kruh(new Bod(0,0), 20);
			assertEquals("Kruh 2/obvod",  2*Math.PI*20, p.obvod(), 0.001);
		}
		scoring.updateScore("lang:common_list_test_scoring_name", 100.0/18);
	}
	@Test
	public void testKruhObsahuje() {
		{
			Kruh p = new Kruh(new Bod(0,0), 10);
			assertTrue("Kruh 1/", p.obsahuje(new Bod(5,5)));
			assertFalse("Kruh 1/", p.obsahuje(new Bod(5,15)));
			assertFalse("Kruh 1/", p.obsahuje(new Bod(15,5)));
			assertFalse("Kruh 1/", p.obsahuje(new Bod(15,15)));
			assertTrue("Kruh 1/", p.obsahuje(new Bod(-5,-5)));			
		}		
		scoring.updateScore("lang:common_list_test_scoring_name", 100.0/18);
		{
			Kruh p = new Kruh(new Bod(0,0), 20);
			assertTrue("Kruh 2/", p.obsahuje(new Bod(5,5)));
			assertTrue("Kruh 2/", p.obsahuje(new Bod(5,15)));
			assertTrue("Kruh 2/", p.obsahuje(new Bod(15,5)));
			assertFalse("Kruh 2/", p.obsahuje(new Bod(15,15)));
			assertTrue("Kruh 2/", p.obsahuje(new Bod(-5,-5)));			
		}
		scoring.updateScore("lang:common_list_test_scoring_name", 100.0/18);
	}	
	//------------------
	@Test
	public void testTrojuholnikObsah() {
		{
			Trojuholnik p = new Trojuholnik(new Bod(0,0), new Bod(10, 0), new Bod(0, 10));
			assertEquals("Trojuholnik 1/obsah", 10*10/2, p.obsah(), 0.001);
		}
		{
			Trojuholnik p = new Trojuholnik(new Bod(10,0), new Bod(0, 10), new Bod(-10, 0));
			assertEquals("Trojuholnik 2/obsah", 10*10, p.obsah(), 0.001);
		}		
		scoring.updateScore("lang:common_list_test_scoring_name", 100.0/18);
		{
			Trojuholnik p = new Trojuholnik(new Bod(0,0), new Bod(3, 1), new Bod(1, 3));
			assertEquals("Trojuholnik 3/obsah", 4, p.obsah(), 0.001);
		}		
		scoring.updateScore("lang:common_list_test_scoring_name", 100.0/18);
		{
			Trojuholnik p = new Trojuholnik(new Bod(0,1), new Bod(1, -1), new Bod(-1, -1));
			assertEquals("Trojuholnik 4/obsah", 2, p.obsah(), 0.001);
		}		
		scoring.updateScore("lang:common_list_test_scoring_name", 100.0/18);
	}	
	@Test
	public void testTrojuholnikObvod() {
		{
			Trojuholnik p = new Trojuholnik(new Bod(0,0), new Bod(10, 0), new Bod(0, 10));
			assertEquals("Trojuholnik 1/obvod",  10+10+10*Math.sqrt(2), p.obvod(), 0.001);
		}
		{
			Trojuholnik p = new Trojuholnik(new Bod(10,0), new Bod(0, 10), new Bod(-10, 0));
			assertEquals("Trojuholnik 2/obvod",  10+10+20*Math.sqrt(2), p.obvod(), 0.001);
		}		
		scoring.updateScore("lang:common_list_test_scoring_name", 100.0/18);
		{
			Trojuholnik p = new Trojuholnik(new Bod(0,0), new Bod(3, 1), new Bod(1, 3));
			assertEquals("Trojuholnik 3/obvod",  2*Math.sqrt(2)+2*Math.sqrt(10), p.obvod(), 0.001);
		}		
		scoring.updateScore("lang:common_list_test_scoring_name", 100.0/18);
		{
			Trojuholnik p = new Trojuholnik(new Bod(0,1), new Bod(1, -1), new Bod(-1, -1));
			assertEquals("Trojuholnik 4/obvod",  2*Math.sqrt(5)+2, p.obvod(), 0.001);
		}		
		scoring.updateScore("lang:common_list_test_scoring_name", 100.0/18);
	}

	@Test
	public void testTrojuholnikObsahuje() {
		{
			Trojuholnik p = new Trojuholnik(new Bod(0,0), new Bod(10, 0), new Bod(0, 10));
			assertTrue("Trojuholnik 1/", p.obsahuje(new Bod(4,4)));
			assertFalse("Trojuholnik 1/", p.obsahuje(new Bod(6,6)));
			assertFalse("Trojuholnik 1/", p.obsahuje(new Bod(5,15)));
			assertFalse("Trojuholnik 1/", p.obsahuje(new Bod(15,5)));
			assertFalse("Trojuholnik 1/", p.obsahuje(new Bod(15,15)));
			assertFalse("Trojuholnik 1/", p.obsahuje(new Bod(-5,-5)));			
		}
		{
			Trojuholnik p = new Trojuholnik(new Bod(10,0), new Bod(0, 10), new Bod(-10, 0));
			assertTrue("Trojuholnik 2/", p.obsahuje(new Bod(4,4)));
			assertFalse("Trojuholnik 2/", p.obsahuje(new Bod(6,6)));
			assertFalse("Trojuholnik 2/", p.obsahuje(new Bod(5,15)));
			assertFalse("Trojuholnik 2/", p.obsahuje(new Bod(15,5)));
			assertFalse("Trojuholnik 2/", p.obsahuje(new Bod(15,15)));
			assertFalse("Trojuholnik 2/", p.obsahuje(new Bod(-5,-5)));			
			assertTrue("Trojuholnik 2/", p.obsahuje(new Bod(-4,4)));			
			assertTrue("Trojuholnik 2/", p.obsahuje(new Bod(-1,1)));			
		}		
		scoring.updateScore("lang:common_list_test_scoring_name", 100.0/18);
		{
			Trojuholnik p = new Trojuholnik(new Bod(0,0), new Bod(3, 1), new Bod(1, 3));
			assertTrue("Trojuholnik 3/", p.obsahuje(new Bod(1,1)));
			assertFalse("Trojuholnik 3/", p.obsahuje(new Bod(3,2)));
			assertFalse("Trojuholnik 3/", p.obsahuje(new Bod(5,15)));
			assertFalse("Trojuholnik 3/", p.obsahuje(new Bod(15,5)));
			assertFalse("Trojuholnik 3/", p.obsahuje(new Bod(15,15)));
			assertFalse("Trojuholnik 3/", p.obsahuje(new Bod(-5,-5)));			
			assertFalse("Trojuholnik 3/", p.obsahuje(new Bod(-4,4)));			
			assertFalse("Trojuholnik 3/", p.obsahuje(new Bod(-1,1)));			
		}		
		scoring.updateScore("lang:common_list_test_scoring_name", 100.0/18);
		{
			Trojuholnik p = new Trojuholnik(new Bod(0,1), new Bod(1, -1), new Bod(-1, -1));
			assertTrue("Trojuholnik 4/", p.obsahuje(new Bod(0,0)));
			assertFalse("Trojuholnik 4/", p.obsahuje(new Bod(3,2)));
			assertFalse("Trojuholnik 4/", p.obsahuje(new Bod(5,15)));
			assertFalse("Trojuholnik 4/", p.obsahuje(new Bod(15,5)));
			assertFalse("Trojuholnik 4/", p.obsahuje(new Bod(15,15)));
			assertFalse("Trojuholnik 4/", p.obsahuje(new Bod(-5,-5)));			
			assertFalse("Trojuholnik 4/", p.obsahuje(new Bod(-4,4)));			
			assertFalse("Trojuholnik 4/", p.obsahuje(new Bod(-1,1)));			
		}		
		scoring.updateScore("lang:common_list_test_scoring_name", 100.0/18);
	}
}
