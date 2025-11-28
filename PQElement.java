public class PQElement {
  public int key;
  public String value;
  public int index;

  public PQElement(int key, String value){
    this.key = key;
    this.value = value;
  }

  public String toString(){
    return "Key: " + key + ", Value: " + value + ", index: " + index;
  }
}
