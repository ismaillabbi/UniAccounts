package uniaccounts.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Builder Design Pattern
public class StudentAccount {

    private final String id;
    private final String fullName;
    private final String email;
    private final String phone;
    private final String level;
    private final String major;
    private final boolean scholarship;
    private final List<String> courses;

    private StudentAccount(Builder builder) {
        this.id = builder.id;
        this.fullName = builder.fullName;
        this.email = builder.email;
        this.phone = builder.phone;
        this.level = builder.level;
        this.major = builder.major;
        this.scholarship = builder.scholarship;
        this.courses = Collections.unmodifiableList(
            new ArrayList<>(builder.courses)
        );
    }

    public static class Builder {
        private final String id;
        private final String fullName;
        private final String email;

        private String phone = null;
        private String level = "L1";
        private String major = null;
        private boolean scholarship = false;
        private List<String> courses = new ArrayList<>();

        public Builder(String id, String fullName, String email) {
            if (id == null || id.isBlank())
                throw new IllegalArgumentException("ID is required");

            if (fullName == null || fullName.isBlank())
                throw new IllegalArgumentException("Full name is required");

            if (email == null || !email.contains("@"))
                throw new IllegalArgumentException("Invalid email");

            this.id = id.trim();
            this.fullName = fullName.trim();
            this.email = email.trim();
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder level(String level) {
            if (!List.of("L1", "L2", "L3", "M1", "M2").contains(level))
                throw new IllegalArgumentException("Invalid level");

            this.level = level;
            return this;
        }

        public Builder major(String major) {
            this.major = major;
            return this;
        }

        public Builder scholarship(boolean scholarship) {
            this.scholarship = scholarship;
            return this;
        }

        public Builder courses(List<String> courses) {
            this.courses = courses == null
                    ? new ArrayList<>()
                    : new ArrayList<>(courses);
            return this;
        }

        public StudentAccount build() {
            return new StudentAccount(this);
        }
    }

    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getLevel() { return level; }
    public String getMajor() { return major; }
    public boolean hasScholarship() { return scholarship; }
    public List<String> getCourses() { return courses; }

    public StudentAccount copyWithIdentity(
            String newId, String newFullName, String newEmail) {

        return new Builder(newId, newFullName, newEmail)
                .phone(phone)
                .level(level)
                .major(major)
                .scholarship(scholarship)
                .courses(courses)
                .build();
    }

    @Override
    public String toString() {
        return "StudentAccount{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", level='" + level + '\'' +
                ", major='" + major + '\'' +
                ", scholarship=" + scholarship +
                ", courses=" + courses +
                '}';
    }
}