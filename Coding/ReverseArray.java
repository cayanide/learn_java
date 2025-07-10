import java.util.Arrays;

public class ReverseArray {

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

    public static void main(String[] args) {
        int[] numbers = getNumbers();
        int[] reversedNumbers = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            reversedNumbers[i] = numbers[numbers.length - i - 1];
        }
        System.out.println("Original Array:");
        System.out.println(Arrays.toString(numbers));
        System.out.println("Reversed Array:");
        System.out.println(Arrays.toString(reversedNumbers));
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long usedMemory = (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024);
        long totalMemory = runtime.totalMemory() / (1024 * 1024);
        // BEGIN_HEAP_PRINT
        System.out.println("Used Heap: " + usedMemory + " MB");
        System.out.println("Total Memory: " + totalMemory + " MB");
        // END_HEAP_PRINT
    }
}
