import java.util.Arrays;

public class InsertionSort {

    public static int[] getNumbers() {
        return new int[] {
            100,
            56,
            78,
            54,
            45,
            67,
            23,
            43,
            98,
            32,
            85,
            14,
            18,
            17,
            87,
            67,
            69,
            88,
            1,
            99,
            101,
            103,
            77,
            12,
            10,
        };
    }

    public static int[] sort(int[] numbers) {
        int n = numbers.length;
        for (int i = 1; i < n; i++) {
            int key = numbers[i];
            int j = i - 1;
            // Move elements of numbers[0..i-1], that are greater than key,
            // to one position ahead of their current position
            while (j >= 0 && numbers[j] > key) {
                numbers[j + 1] = numbers[j];
                j = j - 1;
            }
            numbers[j + 1] = key;
        }
        return numbers;
    }

    public static void main(String[] args) {
        int[] numbers = getNumbers();
        System.out.println(Arrays.toString(numbers));
        numbers = sort(numbers);
        System.out.println(Arrays.toString(numbers));
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
