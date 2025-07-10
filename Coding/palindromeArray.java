import java.util.Arrays;

public class palindromeArray {

    public static int[] getNumbers() {
        return new int[] { 2, 4, 5, 4, 2 };
    }

    public static boolean isPalindrome(int[] numbers) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            if (numbers[left] != numbers[right]) return false;
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] numbers = getNumbers();
        System.out.println("Numbers: " + Arrays.toString(numbers));
        System.out.println("Is Palindrome: " + isPalindrome(numbers));
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
