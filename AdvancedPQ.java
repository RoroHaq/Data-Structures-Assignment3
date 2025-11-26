

public class AdvancedPQ{
    public PQElement[] elements;
    private int size = 0;
    private boolean isMinHeap = true;

    public AdvancedPQ(){
      elements = new PQElement[100];
      size = 100;
    }
    public int size(){
      return size;
    }

    public boolean compare(PQElement child, PQElement parent){
      if(isMinHeap){
        return child.priority < parent.priority;
      }else{
        return child.priority > parent.priority;
      }
    }
    public void swap(int indexOne, int indexTwo){
      PQElement temp = elements[indexOne];
      elements[indexOne] = elements[indexTwo];
      elements[indexTwo] = temp;

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
      int index = 0;
      while(hasLeftChild(index)){
        int smallerChildIndex = getLeftChildIndex(index);
        if(hasRightChild(index) && rightChild(index).priority < leftChild(index).priority ){
          smallerChildIndex = getRightChildIndex(index);
        }
        if(elements[index].priority < elements[smallerChildIndex].priority){
          break;
        }else{
          swap(index, smallerChildIndex);
          index = smallerChildIndex;
        }
      }
    }
    public void upHeap(){
      int index = size-1;
      while (hasParent(index) && compare(elements[index], parent(index)) ){
        swap(getParentIndex(index), index);
        index = getParentIndex(index);
      }
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
