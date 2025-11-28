
/**
 * Common formula for figuring out heap left and right children:
 * Left most : (index * 2) + 1
 * Right most : (index *2) + 2
 */
public class AdvancedPQ{
    public PQElement[] elements;
    private int capacity = 100;
    private int size = 0;
    private boolean isMinHeap = true;

    public AdvancedPQ(){
      elements = new PQElement[capacity];
    }
    public int size(){
      return size;
    }

    public void ensureExtraCapacity(){
      if(size == elements.length){
        PQElement[] newElements = new PQElement[capacity * 2];

        for(int i =0; i < elements.length; i++){
          newElements[i] = elements[i];
        }
        elements = newElements;
      }
    }
    public void toggle(){
      isMinHeap = !isMinHeap;
      for(int i = (size-2)/2 ; i >= 0; i--){
        System.out.println(i);
        downHeap(i);
      }
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

      elements[indexOne].index = indexOne;
      elements[indexTwo].index = indexTwo;
    }
    public PQElement top(){
      if(size == 0) throw new IllegalArgumentException("No Top Value");
      return elements[0];
    }
    public PQElement removeTop(){
      if(size == 0) throw new IllegalArgumentException("Nothing to remove");

      PQElement item = elements[0];
      elements[0] = elements[size - 1];
      elements[0].index = 0;
      size--;
      downHeap(0);
      return item;
    }
    public PQElement insert (int k, String v){
      PQElement item = new PQElement(k, v);
      item.index = size;
      elements[size] = item;
      size++;
      upHeap();
      return item;
    }
    public void downHeap(int index){
      while(hasLeftChild(index)){
        
        int smallerChildIndex = getLeftChildIndex(index);
        if(hasRightChild(index) && compare(rightChild(index),leftChild(index))){
          smallerChildIndex = getRightChildIndex(index);
        }
        if(compare(elements[index], elements[smallerChildIndex])){
          break;
        }else{
          swap(index, smallerChildIndex);
        }
        index = smallerChildIndex;
      }
    }
    public void upHeap(){
      int index = size-1;
      while (hasParent(index) && compare(elements[index], parent(index)) ){
        swap(getParentIndex(index), index);
        index = getParentIndex(index);
      }
    }

    public boolean isEmpty(){
      return size == 0;
    }

    public String toString(){
      String message = "";
      for(int i = 0 ; i < size; i++){
        message += "(" + elements[i].priority + ", " + elements[i].name + " at index: " + elements[i].index + ") " + "| ";
      }

      return message;
    }
    
    //Helper functions
    private int getLeftChildIndex(int parentIndex){ return (parentIndex * 2) + 1;}
    private int getRightChildIndex(int parentIndex){ return (parentIndex * 2) + 2;}
    private int getParentIndex(int childIndex){ return (childIndex-1) / 2;}

    private boolean hasLeftChild(int index){ return getLeftChildIndex(index) < size; }
    private boolean hasRightChild(int index){ return getRightChildIndex(index) < size; }
    private boolean hasParent(int index){ return getParentIndex(index) >= 0;}

    private PQElement leftChild(int index) {return elements[getLeftChildIndex(index)];}
    private PQElement rightChild(int index) {return elements[getRightChildIndex(index)];}
    private PQElement parent(int index) {return elements[getParentIndex(index)];}
}
