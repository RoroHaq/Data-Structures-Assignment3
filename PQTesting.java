public class PQTesting {
  public static void main(String[] args){
    
    int[] priorities = {5, 1, 9, 3, 7, 2, 8, 4, 6};
    String[] names = {"E","A","I","C","G","B","H","D","F"};

    AdvancedPQ test = new AdvancedPQ(); // min-heap

    for (int i = 0; i < priorities.length; i++) {
        test.insert(priorities[i], names[i]);
        System.out.println(test);
    }
    System.out.println(test.size());
    test.toggle();

    System.out.println(test);

    test.toggle();

    System.out.println(test);
  }
}
