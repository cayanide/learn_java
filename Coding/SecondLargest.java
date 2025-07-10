public class SecondLargest {

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

    public static int[] getSecondLargest(int[] numbers) {
        int largest = 0;
        int secondLargest = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] > largest) {
                secondLargest = largest;
                largest = numbers[i];
            }
        }
        return new int[] { secondLargest, largest };
    }

    public static void main(String[] args) {
        int[] numbers = getNumbers();
        int[] secondLargest = getSecondLargest(numbers);
        System.out.println("Second Largest Number: " + secondLargest[0]);
        System.out.println("Largest Number: " + secondLargest[1]);
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
