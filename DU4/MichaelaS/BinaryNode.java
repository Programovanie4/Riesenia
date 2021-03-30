public class BinaryNode {
	public BinaryNode left;
	public final Long key;
	public BinaryNode right;
	public BinaryNode parent;

	public BinaryNode(BinaryNode left, Long key, BinaryNode right) {
		this.left = left;
		this.key = key;
		this.right = right;
	}

	public BinaryNode(Long key, BinaryNode parent) {
		this.key = key;
		this.parent = parent;
	}

	public BinaryNode(Long theKey) {
		key = theKey;
		left = right = null;
	}

	public int count() {
		if (left == null && right == null) { return 1; }
		if (left == null) { return right.count() + 1; }
		if (right == null) { return left.count() + 1; }
		return left.count() + right.count() + 1;
	}

	boolean find(Long x) {
		if(key.equals(x)){
			return true;
		}
		if(key.compareTo(x) > 0){// left
			if(left == null){
				return false;
			}
			return left.find(x);
		}
		else{
			if(right == null){
				return false;
			}
			return right.find(x);
		}
	}

	public BinaryNode insert(Long x) {
		if(key.equals(x)){
			return this;
		}
		if(key.compareTo(x) > 0){  // key > x
			if(left == null){
				left = new BinaryNode(x, this);
			}
			else{
				left = left.insert(x);
			}
		}
		else{
			if(right == null){
				right = new BinaryNode(x, this);
			}
			else{
				right = right.insert(x);
			}
		}
		return this;
	}

	void delete(Long key) {
		// cvicenie 4
	}

	public String toString() {
		return "(" + ((left == null) ? "." : left.toString()) + ", " + key + ", "
				+ ((right == null) ? "." : right.toString()) + ")";  // cvicenie 4
	}

}
