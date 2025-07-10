public class ControlStatementsDemo {

    public static void main(String[] args) {
        int age = 17;
        char grade = 'B';

        // If-else
        if (age >= 18) {
            System.out.println("Adult");
        } else {
            System.out.println("Minor");
        }

        // Switch-case
        switch (grade) {
            case 'A':
                System.out.println("Excellent");
                break;
            case 'B':
                System.out.println("Good");
                break;
            case 'C':
                System.out.println("Average");
                break;
            default:
                System.out.println("Needs improvement");
        }

        // For loop
        System.out.println("For loop from 0 to 4:");
        for (int i = 0; i < 5; i++) {
            System.out.println("i = " + i);
        }

        // While loop
        System.out.println("While loop from 0 to 4:");
        int j = 0;
        while (j < 5) {
            System.out.println("j = " + j);
            j++;
        }
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
