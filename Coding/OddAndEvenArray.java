import java.util.Arrays;

public class OddAndEvenArray {

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
        int[] num = getNumbers();
        int[] even = new int[num.length];
        int[] odd = new int[num.length];
        int evenIndex = 0;
        int oddIndex = 0;
        for (int i = 0; i < num.length; i++) {
            if (num[i] % 2 == 0) {
                even[evenIndex++] = num[i];
                System.out.println(num[i] + " is even");
            } else if (num[i] % 2 != 0) {
                odd[oddIndex++] = num[i];
                System.out.println(num[i] + " is odd");
            }
        }

        System.out.println("Even numbers: ");
        StringBuilder evenStr = new StringBuilder();
        for (int i = 0; i < evenIndex; i++) {
            evenStr.append(even[i]);
            if (i < evenIndex - 1) evenStr.append(", ");
        }
        System.out.println(evenStr);

        System.out.println("\nOdd numbers: ");
        StringBuilder oddStr = new StringBuilder();
        for (int i = 0; i < oddIndex; i++) {
            oddStr.append(odd[i]);
            if (i < oddIndex - 1) oddStr.append(", ");
        }
        System.out.println(oddStr);

        System.out.println("Even Numbers Total: " + evenIndex);
        System.out.println("Odd Numbers Total: " + oddIndex);
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
