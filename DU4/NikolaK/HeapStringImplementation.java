public class HeapStringImplementation implements HeapStringInterface{
    String[] p ;
    int size;
    int index;
    public HeapStringImplementation(){
        size = 10;
        index = 0;
        p = new String[size];
    }
    @Override
    public String first() {
        return p[0];
    }

    @Override
    public String remove() {
        String res = p[0];
        p[0] = p[index - 1];
        heapDown();
        index--;
        return res;
    }

    @Override
    public void insert(String str) {
        if(index == size){
            resize(size*2);
        }
        p[index] = str;
        heapUp();
        index++;
        
    }

    private void heapUp() {
        int index = this.index;
        int parent = (index - 1) / 2;
        while(p[parent].compareTo(p[index]) < 0){
            String pom = p[parent];
            p[parent] = p[index];
            p[index] = pom;
            index = parent;
            parent =  (index - 1) / 2;
            if(parent < 0){
                break;
            }
        }
    }
    private void heapDown() {
        int index = 0;
        int left = 1;
        int right = 2;
        while(index < p.length){
            int min = left;
            if(left >= this.index || left >= this.size){
                break;
            }
            if(p[left] == null){
                if(p[right] == null){
                    break;
                }
                min = right;
            }

            if(right >= this.index || right >= this.size){

            }
            else if(p[right].compareTo(p[left]) > 0){
                min  = right;
            }
            if(p[min].compareTo(p[index]) <= 0 ){
                break;
            }
            String pom = p[min];
            p[min] = p[index];
            p[index] = pom;
            index = min;
            left = 2 * index + 1;
            right = 2 * index + 2;
        }
    }

    private void resize(int s) {
        String[] k = new String[s];
        for(int i = 0; i < p.length; i++){
            k[i] = p[i];
        }
        p = k;
        size = s;
    }

    @Override
    public int size() {
        return index;
    }
}
