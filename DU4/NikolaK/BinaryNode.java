public class BinaryNode {
	public BinaryNode left;
	public final Long key;
	public BinaryNode right;
	public BinaryNode parent;
	public boolean was = false;
	public boolean closed = false;

	public BinaryNode(BinaryNode left, Long key, BinaryNode right) {
		this.left = left;
		this.key = key;
		this.right = right;
	}

	public BinaryNode(Long theKey) {
		key = theKey;
		left = right = null;
	}

	public BinaryNode(Long theKey, BinaryNode parent) {
		key = theKey;
		left = right = null;
		this.parent = parent;
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

	public boolean wasChecked(){
		boolean left = true;
		boolean right = true;

		if(this.left != null){
			left = this.left.wasChecked() || this.left.closed;
		}
		if(this.right != null){
			right = this.right.wasChecked() || this.right.closed;
		}
		return this.was && left && right;



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
