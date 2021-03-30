import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;


public class TestHeapStringImplementation {




	@Test
	public void test1() {
		HeapStringImplementation h = new HeapStringImplementation(); 
		h.insert("70");
		h.insert("40");
		h.insert("50");
		h.insert("20");
		h.insert("60");
		h.insert("100");
		h.insert("80");
		h.insert("30");
		h.insert("10");
		h.insert("90");
		h.remove();
		assertEquals("test1","80", h.first());
	}
	
	@Test
	public void test2() {
		HeapStringImplementation h = new HeapStringImplementation();
		for(int i = 0; i <= 1000; i++)
			h.insert(""+i);
		assertEquals("size test2",1001, h.size());
		assertEquals("first test2","999", h.first());
		h.remove();
		assertEquals("remove test2","998", h.first());
		for(int i = 0; i < 999; i++)
			h.remove();
		assertEquals("size test2",1, h.size());
		assertEquals("first test2","0", h.first());		
		h.remove();
		assertEquals("size test2",0, h.size());
	}
	
	@Test
	public void test3() {
		HeapStringImplementation h = new HeapStringImplementation();
		for(int i = 0; i < 1000000; i++)
			h.insert(""+i);
		assertEquals("size test3",1000000, h.size());
		assertEquals("first test3","999999", h.first());
		h.remove();
		assertEquals("remove test3","999998", h.first());
	}

	@Test
	public void test4() {
		Random rnd = new Random();
		for(int pokus = 0; pokus < 3; pokus++) {
			HeapStringImplementation h = new HeapStringImplementation();
			int size = rnd.nextInt(100000);
			String max = null;
			String min = null;
			for(int i = 0; i < size; i++) {
				String rs = givenUsingPlainJava_whenGeneratingRandomStringBounded_thenCorrect();
				h.insert(rs);
				if (max == null || rs.compareTo(max)>0)
					max = rs;
				if (min == null || rs.compareTo(min)<0)
					min = rs;
			}
			assertEquals("size test4",size, h.size());
			assertEquals("first test4",max, h.first());
			for(int i = 1; i < size; i++) {
				h.remove();
			}
			assertEquals("size test4",1, h.size());
			assertEquals("first test4",min, h.first());
		}
	}
	@Test
	public void test5() {
		Random rnd = new Random();
		for(int pokus = 0; pokus < 3; pokus++) {
			HeapStringImplementation h = new HeapStringImplementation();
			int size = rnd.nextInt(100000);
			for(int i = 0; i < size; i++) {
				String rs = givenUsingPlainJava_whenGeneratingRandomStringBounded_thenCorrect();
				h.insert(rs);
			}
			assertEquals("size test5",size, h.size());
			String max = h.first();
			h.remove();
			for(int i = 1; i < size; i++) {
				assertEquals("test5", true, max.compareTo(h.first()) > 0);
				h.remove();
			}
			assertEquals("size test5",0, h.size());
		}
	}
	
	
	//http://www.baeldung.com/java-random-string
	public String givenUsingPlainJava_whenGeneratingRandomStringBounded_thenCorrect() {
		  
	    int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomLimitedInt = leftLimit + (int) 
	          (random.nextFloat() * (rightLimit - leftLimit + 1));
	        buffer.append((char) randomLimitedInt);
	    }
	    String generatedString = buffer.toString();
	    return(generatedString);
	}
}
