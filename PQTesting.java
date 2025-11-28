import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Safin Haque (40331155)
 * Dmitriy Kim (40336205)
 */

public class PQTesting {
  public static void main(String[] args) throws Exception {
    PrintStream original = System.out;
    try (PrintStream out = new PrintStream(
        new FileOutputStream("testrun.txt"), true, "UTF-8")) {
      System.setOut(out);
      runAllTests();
    } finally {
      System.setOut(original);
    }
  }

  public static void runAllTests() {
    testDynamicArrayExpansion();
    testDynamicArrayShrinking();
    testToggle();
    testRemoveTop();
    testInsert();
    testTop();
    testRemoveFromStart();
    testRemoveFromMiddle();
    testRemoveFromEnd();
    testReplaceKey();
    testReplaceKeyWithDuplicate();
    testReplaceValue();
    testState();
    testIsEmpty();
    testSize();
    testPeekAt();
    testMergeOtherAPQ();
    testRemoveAllFromMinHeap();
    testRemoveAllFromMaxHeap();
    testForDuplicates();
  }

  public static void testDynamicArrayExpansion() {
    int[] priorities = { 5, 9, 3 };
    String[] names = { "E", "A", "I" };
    AdvancedPQ testAPQ = new AdvancedPQ();

    System.out.println("1) Test Dynamic Array Expansion [Min Heap]");
    System.out.println("   APQ content: " + testAPQ);
    System.out.println("   APQ length (Empty): " + testAPQ.elements.length);
    System.out.println("   APQ size (Empty): " + testAPQ.size());
    System.out.println();

    for (int i = 0; i < priorities.length; i++) {
      testAPQ.insert(priorities[i], names[i]);
    }
    System.out.println("   Add 3 elements to the heap...\n");

    System.out.println("   APQ content: " + testAPQ);
    System.out.println("   APQ length (Expanded): " + testAPQ.elements.length);
    System.out.println("   APQ size (Expanded): " + testAPQ.size());
    System.out.println();
  }

  public static void testDynamicArrayShrinking() {
    int[] priorities = { 5, 9, 3 };
    String[] names = { "E", "A", "I" };
    AdvancedPQ testAPQ = new AdvancedPQ();

    System.out.println("2) Test Dynamic Array Shrinking [Min Heap]");
    for (int i = 0; i < priorities.length; i++) {
      testAPQ.insert(priorities[i], names[i]);
    }
    System.out.println("   APQ content: " + testAPQ);
    System.out.println("   APQ length: " + testAPQ.elements.length);
    System.out.println("   APQ size: " + testAPQ.size());
    System.out.println();

    testAPQ.removeTop();
    testAPQ.removeTop();
    System.out.println("   Remove 2 elements from the top...\n");

    System.out.println("   APQ content: " + testAPQ);
    System.out.println("   APQ length (Shrinked): " + testAPQ.elements.length);
    System.out.println("   APQ size (Shrinked): " + testAPQ.size());
    System.out.println();
  }

  public static void testToggle() {
    int[] priorities = { 12, 15, 10, 14, 11 };
    String[] names = { "L", "M", "J", "N", "K" };
    AdvancedPQ testAPQ = new AdvancedPQ();

    System.out.println("3) Test toggle() method [from min to max]");

    for (int i = 0; i < priorities.length; i++) {
      testAPQ.insert(priorities[i], names[i]);
    }

    System.out.println("   Min Heap: " + testAPQ);
    testAPQ.toggle();
    System.out.println("   Toggle...");
    System.out.println("   Max Heap: " + testAPQ);
    System.out.println();
  }

  public static void testRemoveTop() {
    int[] priorities = { 12, 15, 10, 14 };
    String[] names = { "L", "M", "J", "N" };
    AdvancedPQ testAPQ = new AdvancedPQ();

    System.out.println("4) Test removeTop()");

    for (int i = 0; i < priorities.length; i++) {
      testAPQ.insert(priorities[i], names[i]);
    }

    System.out.println("   Original min heap: " + testAPQ);
    testAPQ.removeTop();
    System.out.println("   Remove top 10(J) min heap: " + testAPQ);
    System.out.println();
  }

  public static void testInsert() {
    int[] priorities = { 12, 15, 10, 14 };
    String[] names = { "L", "M", "J", "N" };
    AdvancedPQ testAPQ = new AdvancedPQ();

    System.out.println("5) Test insert(k, v)");

    for (int i = 0; i < priorities.length; i++) {
      testAPQ.insert(priorities[i], names[i]);
    }

    System.out.println("   Original min heap: " + testAPQ);
    testAPQ.insert(1, "F");
    System.out.println("   Add (1:F) to min heap: " + testAPQ);
    System.out.println();
  }

  public static void testTop() {
    int[] priorities = { 5, 9, 3 };
    String[] names = { "E", "A", "I" };
    AdvancedPQ testAPQ = new AdvancedPQ();

    System.out.println("6) Test top() method");

    for (int i = 0; i < priorities.length; i++) {
      testAPQ.insert(priorities[i], names[i]);
    }

    System.out.println("   Original min heap: " + testAPQ);
    System.out.println("   Top of the heap: " + testAPQ.top());
    System.out.println();
  }

  public static void testRemoveFromStart() {
    int[] priorities = { 5, 9, 3, 32 };
    String[] names = { "E", "A", "I", "F" };
    AdvancedPQ testAPQ = new AdvancedPQ();

    System.out.println("7) Test remove(e) method [Remove from start]");

    for (int i = 0; i < priorities.length; i++) {
      testAPQ.insert(priorities[i], names[i]);
    }
    PQElement elementToRemove = testAPQ.insert(1, "B");
    System.out.println("   Original min heap: " + testAPQ);

    testAPQ.remove(elementToRemove);
    System.out.println("   Remove 1(B) min heap: " + testAPQ);
    System.out.println();
  }

  public static void testRemoveFromMiddle() {
    int[] priorities = { 5, 9, 3, 32 };
    String[] names = { "E", "A", "I", "F" };
    AdvancedPQ testAPQ = new AdvancedPQ();

    System.out.println("8) Test remove(e) method [Remove from middle]");

    for (int i = 0; i < priorities.length; i++) {
      testAPQ.insert(priorities[i], names[i]);
    }
    PQElement elementToRemove = testAPQ.insert(8, "B");
    System.out.println("   Original min heap: " + testAPQ);

    testAPQ.remove(elementToRemove);
    System.out.println("   Remove 8(B) min heap: " + testAPQ);
    System.out.println();
  }

  public static void testRemoveFromEnd() {
    int[] priorities = { 5, 9, 3, 32 };
    String[] names = { "E", "A", "I", "F" };
    AdvancedPQ testAPQ = new AdvancedPQ();

    System.out.println("9) Test remove(e) method [Remove from end]");

    for (int i = 0; i < priorities.length; i++) {
      testAPQ.insert(priorities[i], names[i]);
    }
    PQElement elementToRemove = testAPQ.insert(99, "B");
    System.out.println("   Original min heap: " + testAPQ);

    testAPQ.remove(elementToRemove);
    System.out.println("   Remove 99(B) min heap: " + testAPQ);
    System.out.println();
  }

  public static void testReplaceKey() {
    int[] priorities = { 5, 9, 3, 32 };
    String[] names = { "E", "A", "I", "F" };
    AdvancedPQ testAPQ = new AdvancedPQ();

    System.out.println("10) Test replaceKey(e, k) method");

    for (int i = 0; i < priorities.length; i++) {
      testAPQ.insert(priorities[i], names[i]);
    }
    PQElement elementKeyToReplace = testAPQ.insert(8, "B");
    System.out.println("    Original min heap: " + testAPQ);
    testAPQ.replaceKey(elementKeyToReplace, 99);
    System.out.println("    Replace key 8 to 99: " + testAPQ);
    System.out.println();
  }

  public static void testReplaceKeyWithDuplicate() {
    int[] priorities = { 5, 9, 3, 32 };
    String[] names = { "E", "A", "I", "F" };
    AdvancedPQ testAPQ = new AdvancedPQ();

    System.out.println("11) Test replaceKey(e, k) method [Pass duplicate key as an argument]");

    for (int i = 0; i < priorities.length; i++) {
      testAPQ.insert(priorities[i], names[i]);
    }
    PQElement elementKeyToReplace = testAPQ.insert(8, "B");
    System.out.println("    Original min heap: " + testAPQ);
    testAPQ.replaceKey(elementKeyToReplace, 9);
    System.out.println("    Replace key 8 to 9: " + testAPQ);
    System.out.println();
  }

  public static void testReplaceValue() {
    int[] priorities = { 5, 9, 3, 32 };
    String[] names = { "E", "A", "I", "F" };
    AdvancedPQ testAPQ = new AdvancedPQ();

    System.out.println("12) Test replaceValue(e, v) method");

    for (int i = 0; i < priorities.length; i++) {
      testAPQ.insert(priorities[i], names[i]);
    }
    PQElement elementKeyToReplace = testAPQ.insert(8, "B");
    System.out.println("    Original min heap: " + testAPQ);
    testAPQ.replaceValue(elementKeyToReplace, "P");
    System.out.println("    Replace value B with P: " + testAPQ);
    System.out.println();
  }

  public static void testState() {
    int[] priorities = { 5, 9, 3, 32 };
    String[] names = { "E", "A", "I", "F" };
    AdvancedPQ testAPQ = new AdvancedPQ();

    System.out.println("13) Test state() method");

    for (int i = 0; i < priorities.length; i++) {
      testAPQ.insert(priorities[i], names[i]);
    }
    System.out.println("    Original min heap: " + testAPQ);
    System.out.println("    Current state: " + testAPQ.state());
    System.out.println();
    testAPQ.toggle();
    System.out.println("    Toggled min heap: " + testAPQ);
    System.out.println("    Current state: " + testAPQ.state());
    System.out.println();
  }

  public static void testIsEmpty() {
    int[] priorities = { 5, 9, 3, 32 };
    String[] names = { "E", "A", "I", "F" };
    AdvancedPQ testAPQ = new AdvancedPQ();

    System.out.println("14) Test isEmpty() method");
    System.out.println("    Original min heap: " + testAPQ);
    System.out.println("    Is Empty: " + testAPQ.isEmpty());
    System.out.println();

    for (int i = 0; i < priorities.length; i++) {
      testAPQ.insert(priorities[i], names[i]);
    }
    System.out.println("    Populated mean heap: " + testAPQ);
    System.out.println("    Is Empty: " + testAPQ.isEmpty());
    System.out.println();
  }

  public static void testSize() {
    int[] priorities = { 5, 9, 3, 32 };
    String[] names = { "E", "A", "I", "F" };
    AdvancedPQ testAPQ = new AdvancedPQ();

    for (int i = 0; i < priorities.length; i++) {
      testAPQ.insert(priorities[i], names[i]);
    }

    System.out.println("15) Test size() method");
    System.out.println("    Original min heap: " + testAPQ);
    System.out.println("    Size: " + testAPQ.size());
    System.out.println();
  }

  public static void testPeekAt() {
    int[] priorities = { 5, 9, 3 };
    String[] names = { "E", "A", "I" };
    AdvancedPQ testAPQ = new AdvancedPQ();

    for (int i = 0; i < priorities.length; i++) {
      testAPQ.insert(priorities[i], names[i]);
    }

    System.out.println("16) Test peekAt(n) method");
    System.out.println("    Original min heap: " + testAPQ);
    System.out.println("    Peek at the third smallest: " + testAPQ.peekAt(2));
    System.out.println();
  }

  public static void testMergeOtherAPQ() {
    int[] priorities = { 5, 9, 3 };
    String[] names = { "E", "A", "I" };
    int[] priorities2 = { 12, 15, 10 };
    String[] names2 = { "L", "M", "J" };
    AdvancedPQ testAPQ = new AdvancedPQ();
    AdvancedPQ testAPQ2 = new AdvancedPQ();

    for (int i = 0; i < priorities.length; i++) {
      testAPQ.insert(priorities[i], names[i]);
    }
    for (int i = 0; i < priorities2.length; i++) {
      testAPQ2.insert(priorities2[i], names2[i]);
    }

    System.out.println("17) Test merge(n) method");
    System.out.println("    Original min heap: " + testAPQ);
    System.out.println("    Min heap to merge: " + testAPQ2);
    testAPQ.merge(testAPQ2);

    System.out.println("    Merged min heap" + testAPQ);
    System.out.println();
  }

  public static void testRemoveAllFromMinHeap() {
    int[] priorities = { 5, 9, 3, 12, 15, 10 };
    String[] names = { "E", "A", "I", "L", "M", "J" };
    AdvancedPQ testAPQ = new AdvancedPQ();
    PQElement[] resultArray = new PQElement[priorities.length];

    for (int i = 0; i < priorities.length; i++) {
      testAPQ.insert(priorities[i], names[i]);
    }

    System.out.println("18) Test remove all from min heap [Result should be sorted in ascending order]");
    System.out.println("    Original min heap: " + testAPQ);
    System.out.println("    Resulting array: []");

    for (int i = 0; i < priorities.length; i++) {
      resultArray[i] = testAPQ.removeTop();
    }
    System.out.println("\n    Remove all elements from min heap, and put it to the resulting array...\n");

    System.out.println("    Original min heap: " + testAPQ);
    System.out.print("    Resulting array: ");
    for (int i = 0; i < priorities.length; i++) {
      System.out.print(resultArray[i].key + "(" + resultArray[i].value + ")" + ", ");
    }
    System.out.println();
    System.out.println();
  }

  public static void testRemoveAllFromMaxHeap() {
    int[] priorities = { 5, 9, 3, 12, 15, 10 };
    String[] names = { "E", "A", "I", "L", "M", "J" };
    AdvancedPQ testAPQ = new AdvancedPQ();
    PQElement[] resultArray = new PQElement[priorities.length];

    for (int i = 0; i < priorities.length; i++) {
      testAPQ.insert(priorities[i], names[i]);
    }

    testAPQ.toggle();

    System.out.println("19) Test remove all from max heap [Result should be sorted in descending order]");
    System.out.println("    Original max heap: " + testAPQ);
    System.out.println("    Resulting array: []");

    for (int i = 0; i < priorities.length; i++) {
      resultArray[i] = testAPQ.removeTop();
    }
    System.out.println("\n    Remove all elements from min heap, and put it to the resulting array...\n");

    System.out.println("    Original max heap: " + testAPQ);
    System.out.print("    Resulting array: ");
    for (int i = 0; i < priorities.length; i++) {
      System.out.print(resultArray[i].key + "(" + resultArray[i].value + ")" + ", ");
    }
    System.out.println();
    System.out.println();
  }

  public static void testForDuplicates() {
    int[] priorities = { 5, 9, 3, 12, 15, 10 };
    String[] names = { "E", "A", "I", "L", "M", "J" };
    AdvancedPQ testAPQ = new AdvancedPQ();
    for (int i = 0; i < priorities.length; i++) {
      testAPQ.insert(priorities[i], names[i]);
    }

    System.out.println("20) Test if the heap breaks when handling duplicates");
    System.out.println("    Original min heap: " + testAPQ);
    System.out.println("    Add a duplicate key 5(E)");
    testAPQ.insert(5, "E");
    System.out.println("    Min heap with a duplicate: " + testAPQ);
  }

}
