public class BinOrder {
    BinOrder left, right;   // lavy, pravy podstrom
    char label;             // hodnota vo vnutornom vrchole

    public BinOrder(BinOrder left, char label,  BinOrder right) {
        this.left = left;
        this.right = right;
        this.label = label;
    }

    @Override
    public String toString() {
        return "(" + ((left!=null)?left:"null") + "," + label + "," + ((right!=null)?right:"null") + ')';
    }

    public static BinOrder reconstruct(String preorder, String inorder) {
        return null;  // toto dorobte
    }

    public static void main(String[] args) {
        System.out.println(reconstruct("a","a"));
        System.out.println(reconstruct("abc","bac"));
        System.out.println(reconstruct("abdecfg","dbeafcg"));
        System.out.println(reconstruct("abcdef","cbdaef"));
    }
}
