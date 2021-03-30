import java.util.List;

interface RTree<T> {
    public boolean contains(T elem);
    public boolean isLeaf(T elem);
    public List<T> preorder();
}

