
public class BinaryTree implements BinaryTreeInterface, Iterovatelne {
	private BinaryNode root;
	private BinaryNode current;
	private BinaryNode max;
	private long last;

	public BinaryTree() {
		root = null;
	}

	public BinaryTree(BinaryNode root) {
		this.root = root;
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
			last = Long.MIN_VALUE;
		}
		else{
			root.insert(x);
		}
		if (x > last) { max = find_last(); }
		return this;
	}

	public BinaryTree delete(Long key) {
		return null;
		// cvicenie 4
	}

	public BinaryNode find_first() {
		BinaryNode first = root;
		while (first.left != null) { first = first.left; }
		return first;
	}

	public BinaryNode find_last() {
		BinaryNode last = root;
		while (last.right != null) { last = last.right; }
		return last;
	}

	public void reset(){
		current = null;
		max = find_last();
		last = Long.MIN_VALUE;
	}

	public long dalsi(){
		if (current == null) {
			current = find_first();
			last = current.key;
			return current.key;
		}
		if (current.key.equals(max.key)) {
			last = Long.MAX_VALUE;
			return Long.MAX_VALUE;
		}

		if (current.left != null && last < current.left.key) {
			while (current.left != null) {
				current = current.left;
			}
		}
		else if (current.right != null && last < current.right.key) {
			current = current.right;
			while (current.left != null) {
				current = current.left;
			}
		}
		else if (current.parent.key < last) {
			current = current.parent;
			dalsi();
		}
		else { current = current.parent; }

		last = current.key;
		return current.key;
	}

	public long dalsiParny(){
		do { dalsi(); } while (last != Long.MAX_VALUE && last % 2 != 0);
		return last;
	}

	public String toString() {
		return (root == null) ? "Prazdny strom." : root.toString();  // cvicenie 4
	}
}
