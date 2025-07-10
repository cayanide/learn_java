public class Helloworld {

    public static void main(String[] args) {
        System.out.println("Hello, World!");
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
