import java.util.Arrays;
import java.util.Random;

public class ArraySortDemo {

    public static void main(String[] args) {
        int size = 100000; // Bigger array to force memory usage
        int[] numbers = new int[size];
        Random rand = new Random();

        // Fill with random numbers
        for (int i = 0; i < size; i++) {
            numbers[i] = rand.nextInt(1000000);
        }

        // Sort the array
        Arrays.sort(numbers);

        // Print first 10 sorted numbers
        System.out.println("First 10 sorted numbers:");
        for (int i = 0; i < 10; i++) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println();
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long usedMemory =
            (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024);
        long totalMemory = runtime.totalMemory() / (1024 * 1024);

        System.out.println("Used Heap: " + usedMemory + " MB");
        System.out.println("Total Memory: " + totalMemory + " MB");
    }
}
