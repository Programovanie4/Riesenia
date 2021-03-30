import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class MultiMapTest {

    @Test
    public void testPut() {
        MultiMap<String, Integer> mm = new MultiMap<>(50);
        mm.put("prvy", 1);
        assertEquals("num keys", 1, mm.numKeys());
        assertEquals("num values", 1, mm.numValues());
        mm.put("prvy", 1);
        assertEquals("num keys", 1, mm.numKeys());
        assertEquals("num values", 2, mm.numValues());
        mm.put("druhy", 1);
        assertEquals("num keys", 2, mm.numKeys());
        assertEquals("num values", 3, mm.numValues());
        mm.put("prvy", 2);
        assertEquals("num keys", 2, mm.numKeys());
        assertEquals("num values", 4, mm.numValues());
        mm.put("treti", 1);
        mm.put("treti", 1);
        assertEquals("num keys", 3, mm.numKeys());
        assertEquals("num values", 6, mm.numValues());

        mm.put("prvy", 1);
        mm.put("prvy", 1);
        mm.put("prvy", 1);
        assertEquals("num keys", 3, mm.numKeys());
        assertEquals("num values", 9, mm.numValues());
    }

    @Test
    public void testGet() {
        MultiMap<String, Integer> mm = new MultiMap<>(50);
        mm.put("prvy", 1);
        mm.put("prvy", 1);
        Assert.assertArrayEquals("get prvy", new Integer[]{1, 1}, mm.get("prvy"));
        mm.put("druhy", 10);
        Assert.assertArrayEquals("get prvy", new Integer[]{1, 1}, mm.get("prvy"));
        Assert.assertArrayEquals("get druhy", new Integer[]{10}, mm.get("druhy"));
        mm.put("prvy", 2);
        mm.put("treti", 1);
        mm.put("treti", 1);

        System.out.println(Arrays.toString(mm.keys));
        System.out.println(Arrays.toString(mm.values));

        Assert.assertArrayEquals("get prvy", new Integer[]{1, 1, 2}, mm.get("prvy"));
        Assert.assertArrayEquals("get druhy", new Integer[]{10}, mm.get("druhy"));
        Assert.assertArrayEquals("get treti", new Integer[]{1, 1}, mm.get("treti"));

        mm.put("prvy", 1);
        mm.put("prvy", 1);
        mm.put("prvy", 1);
        Assert.assertArrayEquals("get prvy", new Integer[]{1, 1, 2, 1, 1, 1}, mm.get("prvy"));
    }

    @Test
    public void testRemoveKey1() {
        MultiMap<String, Integer> mm = new MultiMap<>(50);
        mm.put("prvy", 1);
        mm.put("prvy", 1);
        mm.put("druhy", 10);
        mm.put("prvy", 2);
        mm.put("treti", 1);
        mm.put("treti", 1);
        mm.put("prvy", 1);
        mm.put("prvy", 1);
        mm.put("prvy", 1);

        Assert.assertArrayEquals("removeKey prvy", new Integer[]{1, 1, 2, 1, 1, 1}, mm.removeKey("prvy"));
        Assert.assertEquals("num keys", 2, mm.numKeys());
        Assert.assertEquals("values size", 3, mm.numValues());
        Assert.assertArrayEquals("removeKey druhy", new Integer[]{10}, mm.removeKey("druhy"));
        Assert.assertEquals("num keys", 1, mm.numKeys());
        Assert.assertEquals("values size", 2, mm.numValues());
        Assert.assertArrayEquals("removeKey treti", new Integer[]{1, 1}, mm.removeKey("treti"));
        Assert.assertEquals("num keys", 0, mm.numKeys());
        Assert.assertEquals("values size", 0, mm.numValues());
    }

    @Test
    public void testRemoveKey2() {
        MultiMap<String, Integer> mm = new MultiMap<>(50);
        mm.put("prvy", 1);
        mm.put("prvy", 1);
        mm.put("druhy", 10);
        mm.put("prvy", 2);
        mm.put("treti", 1);
        mm.put("treti", 1);
        mm.put("prvy", 1);
        mm.put("prvy", 1);
        mm.put("prvy", 1);

        mm.removeKey("druhy");
        Assert.assertArrayEquals("get 0 => prvy", new Integer[]{1, 1, 2, 1, 1, 1}, mm.get(0));
        Assert.assertArrayEquals("get 1 => treti", new Integer[]{1, 1}, mm.get(1));

        mm.removeKey("prvy");
        Assert.assertArrayEquals("get 0 => treti", new Integer[]{1, 1}, mm.get(0));
    }

    @Test
    public void testRemoveValue() {
        MultiMap<String, Integer> mm = new MultiMap<>(50);
        mm.put("prvy", 1);
        mm.put("prvy", 1);
        mm.put("druhy", 10);
        mm.put("prvy", 2);
        mm.put("treti", 1);
        mm.put("treti", 1);
        mm.put("prvy", 1);
        mm.put("prvy", 2);
        mm.put("prvy", 1);
        mm.put("prvy", 3);
        mm.put("prvy", 1);

        mm.removeValue(1);
        System.out.println(Arrays.toString(mm.get(0)));
        Assert.assertArrayEquals("get 0", new Integer[]{2, 2, 3}, mm.get(0));
        Assert.assertArrayEquals("get prvy", new Integer[]{2, 2, 3}, mm.get("prvy"));
        Assert.assertArrayEquals("get 1", new Integer[]{10}, mm.get(1));
        Assert.assertArrayEquals("get druhy", new Integer[]{10}, mm.get("druhy"));
        System.out.println(Arrays.toString(mm.get(2)));
        Assert.assertArrayEquals("get 2", null, mm.get(2));
        Assert.assertArrayEquals("get treti", null, mm.get("treti"));
        System.out.println(Arrays.toString(mm.keys));
        System.out.println(Arrays.deepToString(mm.values));
        Assert.assertEquals("num keys", 2, mm.numKeys());
        Assert.assertEquals("values size", 4, mm.numValues());

        mm.removeValue(10);
        Assert.assertArrayEquals("get 0", new Integer[]{2, 2, 3}, mm.get(0));
        Assert.assertArrayEquals("get prvy", new Integer[]{2, 2, 3}, mm.get("prvy"));
        Assert.assertArrayEquals("get 1", null, mm.get(2));
        Assert.assertArrayEquals("get druhy", null, mm.get("druhy"));

        Assert.assertEquals("num keys", 1, mm.numKeys());
        Assert.assertEquals("values size", 3, mm.numValues());

        mm.removeValue(2);
        Assert.assertArrayEquals("get 0", new Integer[]{3}, mm.get(0));
        Assert.assertArrayEquals("get prvy", new Integer[]{3}, mm.get("prvy"));

        Assert.assertEquals("num keys", 1, mm.numKeys());
        Assert.assertEquals("values size", 1, mm.numValues());
    }
}