

public class AdvancedPQ{
    public PQElement[] elements;
    private int size = 0;

    public AdvancedPQ(){
      elements = new PQElement[100];
      size = 100;
    }
    public int size(){
      return size;
    }

    public PQElement removeTop(){
      if(size == 0) throw new IllegalArgumentException();

      PQElement item = elements[0];
      elements[0] = elements[size - 1];
      size--;
      return item;
    }
    public PQElement insert (int k, String v){
      PQElement item = new PQElement(k, v);
      size++;
      upHeap();
      return item;
    }
    public void downHeap(){

    }
    public void upHeap(){

    }

    private int getLeftChildIndex(int parentIndex){ return 2 * parentIndex + 1;}
    private int getRightChildIndex(int parentIndex){ return 2 * parentIndex + 2;}
    private int getParentIndex(int childIndex){ return (childIndex-1) / 2;}

    private boolean hasLeftChild(int index){ return getLeftChildIndex(index) < size; }
    private boolean hasRightChild(int index){ return getRightChildIndex(index) < size; }
    private boolean hasParent(int index){ return getParentIndex(index) >= 0;}

    private PQElement leftChild(int index) {return elements[getLeftChildIndex(index)];}
    private PQElement rightChild(int index) {return elements[getRightChildIndex(index)];}
    private PQElement parent(int index) {return elements[getParentIndex(index)];}
}
