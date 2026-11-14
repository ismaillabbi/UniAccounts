package uniaccounts.audit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Singleton Design Pattern
public class AuditLogger {

    private static final AuditLogger instance = new AuditLogger();

    private final List<String> logs = new ArrayList<>();

    // Private constructor prevents creating objects from outside
    private AuditLogger() {
    }

    // Returns the unique instance
    public static AuditLogger getInstance() {
        return instance;
    }

    // Records an operation
    public synchronized void log(String message) {
        logs.add(message);
        System.out.println("[AUDIT] " + message);
    }

    // Returns all recorded operations
    public synchronized List<String> getLogs() {
        return Collections.unmodifiableList(
                new ArrayList<>(logs)
        );
    }
}