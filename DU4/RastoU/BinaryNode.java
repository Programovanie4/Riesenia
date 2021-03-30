public class BinaryNode {
	public BinaryNode left;
	public final Long key;
	public BinaryNode right;
	public BinaryNode parent;

	public BinaryNode(Long theKey, BinaryNode parent) {
		key = theKey;
		left = right = null;
		this.parent = parent;
	}

	public BinaryNode(BinaryNode left, Long key, BinaryNode right) {
		this.left = left;
		this.key = key;
		this.right = right;
		this.parent = null;
	}

	public BinaryNode(Long theKey) {
		key = theKey;
		left = right = parent = null;
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

	public BinaryNode findLeftest() {
		BinaryNode current = this;
		while (current.left != null) { current = current.left;}
		return current;
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
