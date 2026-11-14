package uniaccounts.prototype;

import uniaccounts.model.StudentAccount;

// Prototype Design Pattern
public class StudentAccountPrototype {

    private final StudentAccount template;

    public StudentAccountPrototype(StudentAccount template) {
        if (template == null) {
            throw new IllegalArgumentException(
                    "Template cannot be null"
            );
        }

        this.template = template;
    }

    public StudentAccount create(
            String id,
            String fullName,
            String email) {

        return template.copyWithIdentity(
                id,
                fullName,
                email
        );
    }
}