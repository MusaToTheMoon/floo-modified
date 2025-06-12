package com.example.asyncprinter;

/** Null-safe hashCode helper used by the memo-table key builder. */
public final class HelperHashCode {
    private HelperHashCode() {}                 // no instances
    public static int hc(Object o) {
        return (o == null) ? 0 : o.hashCode();
    }
}
