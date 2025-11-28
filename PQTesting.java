public class PQTesting {
  public static void main(String[] args){
    
    int[] priorities = {5, 1, 9, 3, 7, 2, 8, 4, 6};
    String[] names = {"E","A","I","C","G","B","H","D","F"};
    int[] priorities2 = {12, 15, 10, 14, 11, 18, 13, 16, 17};
    String[] names2 = {"L","M","J","N","K","R","O","P","Q"};

    AdvancedPQ test = new AdvancedPQ(); // min-heap
    AdvancedPQ test1 = new AdvancedPQ();
    for (int i = 0; i < priorities.length; i++) {
        test.insert(priorities[i], names[i]);
    }
    for (int i = 0; i < priorities2.length; i++) {
        test1.insert(priorities2[i], names2[i]);
    }
    System.out.println(test);
    System.out.println("\n");

    System.out.println(test1);
    test1.toggle();
    System.out.println("\n");
    System.out.println(test1);

    test.merge(test1);

    System.out.println("\n");
    System.out.println(test);

    test.removeTop();
    System.out.println("\n");
    System.out.println(test);
  }
}
