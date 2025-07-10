import java.util.Arrays;

public class ArrayDemo {

    public static void main(String[] args) {
        int[] numbers = { 1, 3, 5, 7, 9, 2, 4, 6, 8, 10 };
        System.out.println("Array : Numbers : - ");
        int length = numbers.length - 1;

        for (int i = 0; i <= length; i++) {
            System.out.println("Index " + i + " ➜ " + numbers[i]);
        }

        Arrays.sort(numbers);

        System.out.println("Sorted Array : Numbers : - ");
        for (int i = 0; i <= length; i++) {
            System.out.println(" Index " + i + " ➜ " + numbers[i]);
        }
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long usedMemory =
            (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024);
        long totalMemory = runtime.totalMemory() / (1024 * 1024);
        // BEGIN_HEAP_PRINT
        System.out.println("Used Heap: " + usedMemory + " MB");
        System.out.println("Total Memory: " + totalMemory + " MB");
        // END_HEAP_PRINT
    }
}
