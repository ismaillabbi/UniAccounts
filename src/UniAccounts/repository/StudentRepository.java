package uniaccounts.repository;

import uniaccounts.model.StudentAccount;
import uniaccounts.audit.AuditLogger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

// Singleton Design Pattern
public class StudentRepository {

    private static final StudentRepository instance =
            new StudentRepository();

    private final Map<String, StudentAccount> accounts =
            new LinkedHashMap<>();

    private StudentRepository() {
    }

    public static StudentRepository getInstance() {
        return instance;
    }

    public synchronized void add(StudentAccount account) {

        if (accounts.containsKey(account.getId())) {
            throw new IllegalArgumentException(
                    "This ID already exists: " + account.getId()
            );
        }

        accounts.put(account.getId(), account);

        AuditLogger.getInstance().log(
                "Student added: " + account.getId()
        );
    }

    public synchronized StudentAccount findById(String id) {
        return accounts.get(id);
    }

    public synchronized Collection<StudentAccount> findAll() {
        return new ArrayList<>(accounts.values());
    }

    public synchronized int size() {
        return accounts.size();
    }
}