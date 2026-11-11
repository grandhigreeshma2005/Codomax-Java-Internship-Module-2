public class Student {
    private final int id;
    private String name;
    private String course;

    public Student(int id, String name, String course) {
        if (id <= 0) {
            throw new IllegalArgumentException("Student ID must be positive.");
        }
        if (name == null || name.trim().isEmpty()
                || course == null || course.trim().isEmpty()) {
            throw new IllegalArgumentException("Name and course cannot be empty.");
        }
        if (name.contains("|") || course.contains("|")) {
            throw new IllegalArgumentException("Name and course cannot contain |.");
        }

        this.id = id;
        this.name = name.trim();
        this.course = course.trim();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return String.format("ID: %-6d Name: %-20s Course: %s", id, name, course);
    }

    // File format: id|name|course. The separator is disallowed in user input.
    public String toFileRecord() {
        return id + "|" + name + "|" + course;
    }
}
