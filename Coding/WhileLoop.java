public class WhileLoop {

    public static void main(String[] args) {
        int i = 0;
        while (i <= 10) {
            System.out.println(i);
            i++;
        }
        System.out.println("Loop completed");
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long usedMemory =
            (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024);
        long totalMemory = runtime.totalMemory() / (1024 * 1024);
        System.out.println("Used Heap: " + usedMemory + " MB");
        System.out.println("Total Memory: " + totalMemory + " MB");
    }
}
