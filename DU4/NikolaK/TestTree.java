import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestTree {
    /*
   depth()=2	 max()=11	 isOrdered()=true	 isUnique()=false	(([2],[5]),([7],[11]))
   depth()=3	 max()=11	 isOrdered()=false	 isUnique()=false	((([2],[5]),([7],[11])),(([2],[5]),([7],[11])))
   depth()=1	 max()=17	 isOrdered()=true	 isUnique()=true	([17],[17])
   depth()=2	 max()=17	 isOrdered()=true	 isUnique()=true	([17],([17],[17]))
   depth()=3	 max()=3	 isOrdered()=true	 isUnique()=false	([0],([1],([2],[3])))
   depth()=3	 max()=12	 isOrdered()=true	 isUnique()=true	([12],([12],([12],[12])))
   depth()=3	 max()=12	 isOrdered()=true	 isUnique()=true	([12],(null,(null,[12])))
   depth()=1	 max()=null	 isOrdered()=true	 isUnique()=true	(null,null)
     */
    static Tree s = new Node(
            new Node(new Leaf(2), new Leaf(5)),
            new Node(new Leaf(7), new Leaf(11)));
    static Tree t = new Node(s, s);
    static Tree u = new Node(new Leaf(17), new Leaf(17));
    static Tree v = new Node(new Leaf(17), u);
    static Tree w = new Node(new Leaf(0), new Node(new Leaf(1), new Node(new Leaf(2), new Leaf(3))));
    static Tree x = new Node(new Leaf(12), new Node(new Leaf(12), new Node(new Leaf(12), new Leaf(12))));
    static Tree y = new Node(new Leaf(12), new Node(null, new Node(null, new Leaf(12))));
    static Tree z = new Node(null, null);
    static Tree[] benchmarks = { s, t, u, v, w, x, y, z};

    Integer[] depthRes = {2,3,1,2,3,3,3,1};
    Integer[] maxRes = {11,11,17,17,3,12,12,null};
    boolean[] orderedRes = {true, false, true,true,true,true,true,true};
    boolean[] isUniqueRes = {false, false, true, true, false, true, true, true};

    @Test
    public void depth() {
        int i = 0;
        for (Tree tree : benchmarks) {
            assertEquals("depth(" + tree + ")=" + (tree.depth()) + "!=" +  depthRes[i], (Integer)depthRes[i], (Integer)tree.depth());
            i++;
        }
    }
    @Test
    public void max() {
        int i = 0;
        for (Tree tree : benchmarks) {
            assertEquals("max(" + tree + ")=" + (tree.max()) + "!=" +  maxRes[i], maxRes[i], tree.max());
            i++;
        }
    }
    @Test
    public void isOrdered() {
        int i = 0;
        for (Tree tree : benchmarks) {
            assertEquals("isOrdered(" + tree + ")=" + (tree.isOrdered()) + "!=" +  orderedRes[i], orderedRes[i], tree.isOrdered());
            i++;
        }
    }
    @Test
    public void isUniqueRes() {
        int i = 0;
        for (Tree tree : benchmarks) {
            assertEquals("isUniqueRes(" + tree + ")=" + (tree.isUnique()) + "!=" +  isUniqueRes[i], isUniqueRes[i], tree.isUnique());
            i++;
        }
    }

}