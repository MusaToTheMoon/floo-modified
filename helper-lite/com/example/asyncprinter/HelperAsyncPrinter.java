package com.example.asyncprinter;

/**
 * Optional timestamp/debug printer.
 * ComputeCacher injects calls, but nothing relies on their output.
 */
public final class HelperAsyncPrinter {
    private HelperAsyncPrinter() {}
    public static void print(long tid, String msg) {
        // keep it ultra-light: comment-out or log to Logcat if you wish
        // System.out.println("[FLOO][" + tid + "] " + msg);
    }
}
