import java.util.Arrays;

public class BubbleSort {

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
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (numbers[i] < numbers[j]) {
                    int temp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = temp;
                }
            }
        }
        return numbers;
    }

    public static void main(String[] args) {
        int[] numbers = getNumbers();
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
