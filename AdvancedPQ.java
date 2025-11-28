
/**
 * Common formula for figuring out heap left and right children:
 * Left child : (index * 2) + 1
 * Right right : (index *2) + 2
 */
public class AdvancedPQ{
    public PQElement[] elements;
    private int capacity = 100;
    private int size = 0;
    private boolean isMinHeap = true;

    public AdvancedPQ(){
      elements = new PQElement[capacity];
    }
    /**
     * This method returns the size of the Advanced Priority Queue
     * @return int representing size
     */
    public int size(){
      return size;
    }

    /**
     * This method doubles the array size when the PQ is full
     */
    public void ensureExtraCapacity(){
      if(size == elements.length){
        PQElement[] newElements = new PQElement[capacity * 2];

        for(int i =0; i < elements.length; i++){
          newElements[i] = elements[i];
        }
        elements = newElements;
      }
    }
    /**
     * This method switches from min heap to max heap, vice versa
     * then does the change afterwards.
     */
    public void toggle(){
      isMinHeap = !isMinHeap;
      for(int i = (size-2)/2 ; i >= 0; i--){
        downHeap(i);
      }
    }
    /**
     * Helper function in which helps comparison if its a min-heap or max-heap
     * @param child current node
     * @param parent parent node
     * @return Boolean of either its less than or not depending on the heap type.
     */
    public boolean compare(PQElement child, PQElement parent){
      if(isMinHeap){
        return child.key < parent.key;
      }else{
        return child.key > parent.key;
      }
    }
    /**
     * This methid is used to swap positions when up/down-heaping
     * adjusts indexes too
     * @param indexOne index of child node
     * @param indexTwo index of parent node
     */
    public void swap(int indexOne, int indexTwo){
      PQElement temp = elements[indexOne];
      elements[indexOne] = elements[indexTwo];
      elements[indexTwo] = temp;

      elements[indexOne].index = indexOne;
      elements[indexTwo].index = indexTwo;
    }
    /**
     * This method is used to merge two APQ's together withing
     * the rules of the PQ otherAPQ merges into.
     * @param otherAPQ
     */
    public void merge(AdvancedPQ otherAPQ){
      int otherAPQSize = otherAPQ.size();
      for(int i =0; i < otherAPQSize; i++){
        ensureExtraCapacity();
        elements[size] = otherAPQ.elements[i];
        elements[size].index = size;
        size++;
      }

      for(int i = (size-2)/2 ; i >= 0; i--){
        downHeap(i);
      }
    }
    /**
     * This returns the Highest priority element without removing
     * @return Object ith key, value and index
     */
    public PQElement top(){
      if(size == 0) throw new IllegalArgumentException("No Top Value");
      return elements[0];
    }
    /**
     * This returns the element of any in the Advanced Priority Queue
     * @param n the index position
     * @return the element at said index if it exists
     */
    public PQElement peekAt(int n){
      if(n < 0 || n > size) throw new IllegalArgumentException("Index out of bound, attempt another that is above 0 and below " + size);

      return elements[n];
    }
    /**
     * This method returns wether or not its a min-heap or max-heap
     * @return String representation of the state
     */
    public String state(){
      if (isMinHeap){
        return "Min";
      }
      else{
        return "Max";
      }
    }
    /**
     * This method replaced the element's key and fixes the heap accordingly,
     * returning the old key it had
     * @param e the element's key were replacing
     * @param k the new key the element will use.
     * @return
     */
    public int replaceKey(PQElement e, int k){
      int index = e.index;
      int oldKey = e.key;
      e.key = k;
      if(hasParent(index) && compare(elements[index], parent(index))){
        upHeap(index);
      }else{
        downHeap(index);
      }
      return oldKey;
    }
    /**
     * This method goes to the element and replaces the value,
     * returning the old value.
     * @param e the element's value were repalcing
     * @param v the new value the element will use
     * @return the old value of the element
     */
    public String replaceValue(PQElement e, String v){
      String oldValue = e.value;
      e.value = v;
      return oldValue;
    }
    /**
     * Thsi method removes an element in the PQ and adjust it 
     * accordingly, returning the old element
     * @param e the element were removing
     * @return the old element
     */
    public PQElement remove(PQElement e){
      if(size == 0) throw new IllegalArgumentException("Queue empty, nothing to remove");
      PQElement oldElement = e;
      int index = e.index;
      elements[index] = elements[size-1];
      elements[index].index = index;
      size--;

      if(hasParent(index) && compare(elements[index], parent(index))){
        upHeap(index);
      }else{
        downHeap(index);
      }
      return oldElement;
    }
    /**
     * This method removes the highest priority element in the Advanced Priority Queue, 
     * then returns it.
     * @return the highest priority element in the APQ
     */
    public PQElement removeTop(){
      if(size == 0) throw new IllegalArgumentException("Nothing to remove");

      PQElement item = elements[0];
      elements[0] = elements[size - 1];
      elements[0].index = 0;
      size--;
      downHeap(0);
      return item;
    }
    /**
     * This method is used to insert new elements in the APQ,
     * then returns the element you made.
     * @param k the key for the new element
     * @param v the value for the new element
     * @return the element with the new key and value.
     */
    public PQElement insert (int k, String v){
      PQElement item = new PQElement(k, v);
      item.index = size;
      elements[size] = item;
      size++;
      upHeap(size-1);
      return item;
    }
    /**
     * This method is used to help with the downheap operation
     * @param index
     */
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
    /**
     * This method is use to help with the upheap operation
     * @param index
     */
    public void upHeap(int index){
      while (hasParent(index) && compare(elements[index], parent(index))){
        swap(getParentIndex(index), index);
        index = getParentIndex(index);
      }
    }

    /**
     * This method checks if the APQ is empty
     * @return boolean of true or false if its empty or not
     */
    public boolean isEmpty(){
      return size == 0;
    }

    public String toString(){
      String message = "";
      for(int i = 0 ; i < size; i++){
        message += "(" + elements[i].key + ", " + elements[i].value + " at index: " + elements[i].index + ") " + "| ";
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
