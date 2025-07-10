import java.util.HashMap;

public class FrequencyElements {

    // Method to return the numbers array
    public static int[] getNumbers() {
        return new int[] {
            2,
            4,
            5,
            4,
            2,
            4,
            5,
            6,
            3,
            2,
            1,
            2,
            3,
            4,
            5,
            6,
            7,
            8,
            9,
            10,
            5,
            6,
            7,
            8,
            1,
            4,
            2,
            3,
            4,
            5,
            6,
            7,
            8,
            9,
            10,
            5,
            6,
            7,
            8,
            1,
            4,
            2,
            3,
            4,
            5,
            6,
            7,
            8,
            9,
            10,
        };
    }

    // Method to count frequency of each element in the array
    public static HashMap<Integer, Integer> countFrequency(int[] numbers) {
        HashMap<Integer, Integer> countMap = new HashMap<>();

        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i];

            if (countMap.containsKey(number)) {
                countMap.put(number, countMap.get(number) + 1);
            } else {
                countMap.put(number, 1);
            }
        }

        return countMap;
    }

    public static void main(String[] args) {
        int[] numbers = getNumbers();
        HashMap<Integer, Integer> frequency = countFrequency(numbers);

        System.out.println("📊 Frequency of each number:");
        for (int key : frequency.keySet()) {
            int value = frequency.get(key);
            System.out.println("➜ " + key + " appears " + value + " time(s)");
        }

        // Memory Usage Info
        Runtime runtime = Runtime.getRuntime();
        runtime.gc(); // Let GC run for cleaner measurement
        long usedMemory =
            (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024);
        long totalMemory = runtime.totalMemory() / (1024 * 1024);

        // BEGIN_HEAP_PRINT
        System.out.println("Used Heap: " + usedMemory + " MB");
        System.out.println("Total Memory: " + totalMemory + " MB");
        // END_HEAP_PRINT
    }
}
