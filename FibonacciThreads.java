public class FibonacciThreads {

    public static void main(String[] args) throws InterruptedException {
        int n = 40;

        // Create both threads
        RecursiveFibonacciThread recursiveThread = new RecursiveFibonacciThread(n);
        DynamicFibonacciThread dynamicThread = new DynamicFibonacciThread(n);

        // Start both threads
        recursiveThread.start();
        dynamicThread.start();

        // Wait for both to complete
        recursiveThread.join();
        dynamicThread.join();
    }
}

// Recursive implementation
class RecursiveFibonacciThread extends Thread {
    private int n;

    public RecursiveFibonacciThread(int n) {
        this.n = n;
    }

    private int fibonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public void run() {
        long start = System.currentTimeMillis();
        int result = fibonacci(n);
        long end = System.currentTimeMillis();

        System.out.println("Recursive Fibonacci result for n = " + n + " is: " + result);
        System.out.println("Recursive computation took " + (end - start) + " ms\n");
    }
}

// Dynamic programming implementation
class DynamicFibonacciThread extends Thread {
    private int n;

    public DynamicFibonacciThread(int n) {
        this.n = n;
    }

    private int fibonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        int v1 = 0, v2 = 1, v3 = 0;
        for (int i = 2; i <= n; i++) {
            v3 = v1 + v2;
            v1 = v2;
            v2 = v3;
        }
        return v3;
    }

    public void run() {
        long start = System.currentTimeMillis();
        int result = fibonacci(n);
        long end = System.currentTimeMillis();

        System.out.println("Dynamic Fibonacci result for n = " + n + " is: " + result);
        System.out.println("Dynamic computation took " + (end - start) + " ms\n");
    }
}
