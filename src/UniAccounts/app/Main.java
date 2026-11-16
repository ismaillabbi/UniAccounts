package uniaccounts.app;

import uniaccounts.model.StudentAccount;
import uniaccounts.repository.StudentRepository;
import uniaccounts.audit.AuditLogger;
import uniaccounts.prototype.StudentAccountPrototype;

import java.util.List;

// Main Class
public class Main {

    public static void main(String[] args) {

        System.out.println("===== UniAccounts System =====");

        // Singleton: Get the unique repository
        StudentRepository repository =
                StudentRepository.getInstance();

        // Create a template account using Builder
        StudentAccount template =
                new StudentAccount.Builder(
                        "TEMPLATE",
                        "Template Student",
                        "template@univ.dz"
                )
                .level("L3")
                .major("Computer Science")
                .courses(List.of("IHM", "GL", "Prop"))
                .build();

        // Prototype: Create accounts from the template
        StudentAccountPrototype prototype =
                new StudentAccountPrototype(template);

        StudentAccount student1 = prototype.create(
                "2026001",
                "ISMAIL LABBI",
                "ismail2006@univ.dz"
        );

        StudentAccount student2 = prototype.create(
                "2026002",
                "HAIDAR",
                "HAIDAR@univ.dz"
        );

        // Builder: Create another customized account
        StudentAccount student3 =
                new StudentAccount.Builder(
                        "2026003",
                        "ALI",
                        "ali@univ.dz"
                )
                .phone("0550000000")
                .level("M2")
                .major("Networks")
                .scholarship(true)
                .courses(List.of(
                        "Network Security",
                        "Linux"
                ))
                .build();

        // Add accounts to repository
        repository.add(student1);
        repository.add(student2);
        repository.add(student3);

        // Display all students
        System.out.println("\n===== Students =====");

        for (StudentAccount student : repository.findAll()) {
            System.out.println(student);
        }

        // Test Singleton
        System.out.println("\n===== Singleton Test =====");

        System.out.println(
                "Same repository? " +
                (repository == StudentRepository.getInstance())
        );

        System.out.println(
                "Same logger? " +
                (AuditLogger.getInstance() ==
                 AuditLogger.getInstance())
        );

        // Test duplicate ID
        System.out.println("\n===== Duplicate ID Test =====");

        try {
            repository.add(
                    new StudentAccount.Builder(
                            "2026001",
                            "Another Student",
                            "another@univ.dz"
                    ).build()
            );

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Test invalid email
        System.out.println("\n===== Invalid Email Test =====");

        try {
            new StudentAccount.Builder(
                    "2026004",
                    "Test Student",
                    "invalid-email"
            ).build();

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
// Test Default Values
 System.out.println("\n===== Default Values Test =====");

 StudentAccount defaultStudent =
        new StudentAccount.Builder(
                "2026005",
                "Default Student",
                "default@univ.dz"
        ).build();

 System.out.println("Phone: " + defaultStudent.getPhone());
 System.out.println("Level: " + defaultStudent.getLevel());
 System.out.println("Major: " + defaultStudent.getMajor());
 System.out.println("Scholarship: " +
        defaultStudent.hasScholarship());
 System.out.println("Courses: " +
        defaultStudent.getCourses());
        // Test Invalid Level
 System.out.println("\n===== Invalid Level Test =====");

 try {
    new StudentAccount.Builder(
            "2026006",
            "Invalid Level Student",
            "level@univ.dz"
    ).level("L5").build();

 } catch (IllegalArgumentException e) {
    System.out.println("Error: " + e.getMessage());
 }
 // Test Prototype Copy
 System.out.println("\n===== Prototype Test =====");

 System.out.println("ISMAIL LABBILevel: " +
        student1.getLevel());

 System.out.println("HAIDAR Level: " +
        student2.getLevel());

 System.out.println("ISMAIL LABBI Courses: " +
        student1.getCourses());

 System.out.println("HAIDAR Courses: " +
        student2.getCourses());
        // Final result
        System.out.println("\n===== Final Result =====");

        System.out.println(
                "Total students: " + repository.size()
        );

        System.out.println(
                "Total audit logs: " +
                AuditLogger.getInstance().getLogs().size()
        );

        System.out.println("\n===== Program Finished =====");
    }
}