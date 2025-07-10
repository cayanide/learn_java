import java.util.*;

public class PerformanceStressTest {

    static class Node {

        int value;
        Node left, right;

        Node(int val) {
            this.value = val;
        }
    }

    static class BinarySearchTree {

        Node root;

        // Use iterative insert to avoid StackOverflowError
        void insert(int val) {
            Node newNode = new Node(val);
            if (root == null) {
                root = newNode;
                return;
            }

            Node current = root;
            while (true) {
                if (val < current.value) {
                    if (current.left == null) {
                        current.left = newNode;
                        return;
                    }
                    current = current.left;
                } else {
                    if (current.right == null) {
                        current.right = newNode;
                        return;
                    }
                    current = current.right;
                }
            }
        }

        void inOrder(Node node, List<Integer> output) {
            Stack<Node> stack = new Stack<>();
            Node current = node;

            while (current != null || !stack.isEmpty()) {
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }

                current = stack.pop();
                output.add(current.value);
                current = current.right;
            }
        }

        List<Integer> getSortedElements() {
            List<Integer> result = new ArrayList<>();
            inOrder(root, result);
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println("🚀 Performance Stress Test Started");

        int SIZE = 500_000;
        int[] bigArray = new int[SIZE];
        Random rand = new Random();

        for (int i = 0; i < SIZE; i++) {
            bigArray[i] = rand.nextInt(1_000_000);
        }

        // Sort array
        long startArraySort = System.currentTimeMillis();
        Arrays.sort(bigArray);
        long endArraySort = System.currentTimeMillis();
        System.out.println(
            "✅ Array sort completed in " +
            (endArraySort - startArraySort) +
            " ms"
        );

        // Insert 100k elements into BST
        BinarySearchTree bst = new BinarySearchTree();
        for (int i = 0; i < 100_000; i++) {
            bst.insert(bigArray[i]);
        }

        // In-order traversal
        long startBST = System.currentTimeMillis();
        List<Integer> sortedFromBST = bst.getSortedElements();
        long endBST = System.currentTimeMillis();
        System.out.println(
            "✅ BST in-order traversal of 100k elements took " +
            (endBST - startBST) +
            " ms"
        );
        System.out.println(
            "Total elements returned from BST: " + sortedFromBST.size()
        );

        // Stress StringBuilder
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 10_000; i++) {
            builder.append("Data").append(i).append(";");
        }
        String result = builder.toString();
        System.out.println(
            "✅ StringBuilder created a long string of size: " + result.length()
        );

        // Let GC clean up
        try {
            Thread.sleep(200);
        } catch (InterruptedException ignored) {}
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
