
public class BinaryTree implements BinaryTreeInterface, Iterovatelne {
	private BinaryNode root;
	private BinaryNode last;
	private boolean flag = true;


	public BinaryTree() {
		root = null;
		last = null;
	}

	public BinaryTree(BinaryNode root) {
		this.root = root;
		last = null;
	}

	public boolean find(Long x) {
		if(root == null){
			return false;
		}
		return root.find(x);
	}

	public BinaryTree insert(Long x) {
		if(root == null){
			root = new BinaryNode(x);
		}
		else{
			root.insert(x);
		}
		return this;
	}

	public BinaryTree delete(Long key) {
		return null;
		// cvicenie 4
	}

	public String toString() {
		return (root == null) ? "Prazdny strom." : root.toString();  // cvicenie 4
	}

	@Override
	public void reset() {
		last = null;
		resetRek(root);


	}
	private void resetRek(BinaryNode node){
		if(node == null){
			return;
		}
		node.was = false;
		node.closed = false;
		resetRek(node.left);
		resetRek(node.right);
	}

	@Override
	public long dalsi(){
		if (last == null){
			reset();
		}
		Long key = null;
		if(last != null) {
			key = last.key;
		}

		last = next(root);
		if(last.was){
			return Long.MAX_VALUE;
		}
		last.was = true;
		checkWas(root);




		if(last.key.equals(key)){
			return Long.MAX_VALUE;
		}
		return last.key;
	}
	private BinaryNode next(BinaryNode node){
		if(node.left != null){
			if(!node.left.closed){
				return next(node.left);
			}
		}
		if(!node.was){
			return node;
		}
		if(node.right != null){
			if(!node.right.closed){
				return next(node.right);
			}
		}
		return node;
	}

	private void checkWas(BinaryNode node){
		if(node.left != null){
			checkWas(node.left);
		}
		if(node.wasChecked()){
			node.closed = true;
		}
		if(node.right != null){
			checkWas(node.right);
		}
	}

	@Override
	public long dalsiParny() {
		long pom = dalsi();
		while (pom % 2 != 0){
			pom = dalsi();
			if (pom == Long.MAX_VALUE){
				return pom;
			}
		}
		return pom;
	}

	public static void main(String[] args) {
		Postupnost ap = new AritmetickaPostupnost(10, 3);
		for(int i = 0; i < 10; i++) ap.dalsi();      // vr??ti 13, 16, 19, 22, 25, 28, 31, 34, 37, 40
		System.out.println(ap.prvy());                                   // vr??ti 10
		for(int i = 0; i < 10; i++) ap.dalsi();      // vr??ti 13, 16, 19, 22, 25, 28, 31, 34, 37, 40
		ap.reset();                                  // nevracia ni??, iba resetuje na za??iatok
		for(int i = 0; i < 10; i++) ap.dalsi();      // vr??ti 10, 13, 16, 19, 22, 25, 28, 31, 34, 37
		for(int i = 0; i < 10; i++) ap.dalsiParny(); // vr??ti 40, 46, 52, 58, 64, 70, 76, 82, 88, 94
		ap.dalsi();                                  // vr??ti 97

		Postupnost fp = new FibonaccihoPostupnost(0, 1);
// Fib. postupnost = 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, ...
		for(int i = 0; i < 10; i++) fp.dalsi();     // vr??ti 1, 2, 3, 5, 8, 13, 21, 34, 55, 89
		fp.prvy();                                  // vr??ti 1
		for(int i = 0; i < 10; i++) fp.dalsi();     // vr??ti 1, 2, 3, 5, 8, 13, 21, 34, 55, 89
		fp.reset();                                 // nevracia ni??, iba resetuje na za??iatok
		for(int i = 0; i < 5 ; i++) fp.dalsi();     // vr??ti 1, 1, 2, 3, 5
		for(int i = 0; i < 5; i++) fp.dalsiParny(); // vr??ti 8, 34, 144, 610, 2584
		fp.dalsi();                                 // vr??ti 4181

		BinaryTree t = new BinaryTree();
		t.insert(44l).insert(33l).insert(11l).insert(22l).insert(66l).insert(55l);
		System.out.println(t);      // vyp????e (((., 11, (., 22, .)), 33, .), 44, ((., 55, .), 66, .))
		for(long n = t.dalsi(); n != Long.MAX_VALUE; n = t.dalsi()){
			System.out.println(n);  // vyp????e 11, 22, 33, 44, 55, 66
		}
		t.reset();      // nevracia ni??, iba resetuje na za??iatok
		System.out.println(t.dalsi());      // vr??ti 11
		System.out.println(t.dalsi());      // vr??ti 22
		t.insert(15l);  // vklad??me za poz??ciu iter??tora
		System.out.println(t.dalsi());       // vr??ti 33
		System.out.println(t.dalsi());       // vr??ti 44
		t.insert(45l);  // vklad??me pred poz??ciu iter??tora
		System.out.println(t.dalsi());       // vr??ti 45
		System.out.println(t.dalsi());       // vr??ti 55
		t.insert(57l).insert(77l).insert(87l).insert(97l); // nejak?? nep??rne ????sla
		System.out.println(t.dalsiParny()); // vr??ti 66
		System.out.println(t.dalsiParny()); // vr??ti 9223372036854775807 == Long.MAX_VALUE

		Iterovatelne it = ap; // \
		it = fp;              // - toto sa d?? spravi?? bez kompila??nej alebo runtime chyby
		it = t;
	}
}
