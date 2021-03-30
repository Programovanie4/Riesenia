
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BinaryTree implements BinaryTreeInterface, Iterovatelne {
	private BinaryNode root;
	public BinaryNode current;
	boolean wasInParent, biggerAdded;
	long lastKey;
	int counter;

	public BinaryTree() {
		current = root = null;
		wasInParent = biggerAdded = false;
		lastKey = Long.MAX_VALUE;
	}

	public BinaryTree(BinaryNode root) {
		this.root = root;
		current = null;
		wasInParent = biggerAdded = false;
		lastKey = Long.MAX_VALUE;
	}

	public boolean find(Long x) {
		if(root == null){
			return false;
		}
		return root.find(x);
	}

	public BinaryTree insert(Long x) {
//		System.out.println("ins=" + x);
		if(root == null){
			root = new BinaryNode(x);
		}
		else{
			root.insert(x);
		}
		if (x > lastKey) biggerAdded = true;
		return this;
	}

	public BinaryTree delete(Long key) {
		System.out.println("del=" + key);
		return null;
		// cvicenie 4
	}

	public String toString() {
		return (root == null) ? "Prazdny strom." : root.toString();  // cvicenie 4
	}

	@Override
	public void reset() {
		counter = 0;
		current = null;
		lastKey = Long.MAX_VALUE;
		biggerAdded = false;
	}

	@Override
	public long dalsi() {
		if (root == null) return Long.MAX_VALUE;
		if (current == null) {
			current = root;
			current = current.findLeftest();
			lastKey = current.key;
			counter++;
			return lastKey;
		}

		counter++;
		long tmp = dalsi_rek();
		System.out.println(counter + ": " + tmp);
		return tmp;
	}

	public long dalsi_rek() {

		if (current.left != null && current.left.key > lastKey) {
			current = current.findLeftest();
			lastKey = current.key;
			return lastKey;
		}
		else if (current.key > lastKey) {
			lastKey = current.key;
			return lastKey;
		}
		else if (current.right != null && current.right.key > lastKey) {
			current = current.right;
			return dalsi_rek();
		}
		else if (current == root) {
			if (biggerAdded) {
				while (current.right != null) current = current.right;
				biggerAdded = false;
				lastKey = current.key;
				return current.key;
			}
			return Long.MAX_VALUE;
		}
		current = current.parent;
		return dalsi_rek();

	}
		


	@Override
	public long dalsiParny() {
		long item = dalsi();
		while (item % 2 != 0 && item != Long.MAX_VALUE) {
			item = dalsi();
		}
		return item;
	}

	public static void main(String[] args) {
//		BinaryTree t = new BinaryTree();
//		t.insert(44L).insert(33L).insert(11L).insert(22L).insert(66L).insert(55L);
//		System.out.println(t);      // vypíše (((., 11, (., 22, .)), 33, .), 44, ((., 55, .), 66, .))
//		for(long n = t.dalsi(); n != Long.MAX_VALUE; n = t.dalsi()){
//			System.out.println(n);  // vypíše 11, 22, 33, 44, 55, 66
//		}
//		System.out.println("________________________");
//		t.reset();      // nevracia nič, iba resetuje na začiatok
//		System.out.println(t.dalsi());      // vráti 11
//		System.out.println(t.dalsi());      // vráti 22
//		t.insert(15l);  // vkladáme za pozíciu iterátora
//		System.out.println(t.dalsi());      // vráti 33
//		System.out.println(t.dalsi());      // vráti 44
//		t.insert(45l);  // vkladáme pred pozíciu iterátora
//		System.out.println(t.dalsi());      // vráti 45
//		System.out.println(t.dalsi());      // vráti 55
//		t.insert(57l).insert(77l).insert(87l).insert(97l); // nejaké nepárne čísla
//		System.out.println(t.dalsiParny()); // vráti 66
//		System.out.println(t.dalsiParny()); // vráti 9223372036854775807 == Long.MAX_VALUE
//
//		System.out.println(t.dalsi());

		System.out.println("________________________");
		BinaryTree test = new BinaryTree();

		Long[] tmp2 = new Long[100];
		for (int i = 0; i < 100; i++) tmp2[i] = (long) i;
		List<Long> longTmp = Arrays.asList(tmp2);
		Collections.shuffle(longTmp);
		longTmp.toArray(tmp2);

		for (long x : longTmp) test.insert(x);
		test.reset();
		for (long n = test.dalsi(); n != Long.MAX_VALUE; n = test.dalsi()) {
//			System.out.println(n);
		}

		test.insert(100L);
		test.dalsi();
		System.out.println("counter "+ test.counter);
		test.dalsi();
	}
}
