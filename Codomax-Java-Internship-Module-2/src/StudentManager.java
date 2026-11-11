import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StudentManager {
    private final ArrayList<Student> students = new ArrayList<>();
    private final HashMap<Integer, Student> studentsById = new HashMap<>();
    private final HashSet<Integer> studentIds = new HashSet<>();

    public boolean addStudent(Student student) {
        if (studentIds.contains(student.getId())) {
            return false;
        }

        students.add(student);
        studentsById.put(student.getId(), student);
        studentIds.add(student.getId());
        return true;
    }

    public ArrayList<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public Student findStudent(int id) {
        return studentsById.get(id);
    }

    // Overloaded method: search by name instead of ID.
    public ArrayList<Student> findStudent(String name) {
        ArrayList<Student> matches = new ArrayList<>();

        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name.trim())) {
                matches.add(student);
            }
        }

        return matches;
    }

    public boolean updateStudent(int id, String newName, String newCourse) {
        Student student = studentsById.get(id);

        if (student == null) {
            return false;
        }

        student.setName(newName);
        student.setCourse(newCourse);
        return true;
    }

    public boolean deleteStudent(int id) {
        Student student = studentsById.remove(id);

        if (student == null) {
            return false;
        }

        students.remove(student);
        studentIds.remove(id);
        return true;
    }

    public int getStudentCount() {
        return students.size();
    }

    public void saveToFile(Path filePath) throws IOException {
        Path parent = filePath.toAbsolutePath().getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }

        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Student student : students) {
                writer.write(student.toFileRecord());
                writer.newLine();
            }
        }
    }

    public int loadFromFile(Path filePath) throws IOException {
        if (!Files.exists(filePath)) {
            return 0;
        }

        int loadedCount = 0;

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("#")) {
                    continue;
                }

                String[] parts = line.split("\\|", -1);

                if (parts.length != 3) {
                    System.out.println("Skipping invalid record: " + line);
                    continue;
                }

                try {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    String course = parts[2].trim();

                    if (id <= 0 || name.isEmpty() || course.isEmpty()) {
                        System.out.println("Skipping incomplete record: " + line);
                        continue;
                    }

                    if (addStudent(new Student(id, name, course))) {
                        loadedCount++;
                    } else {
                        System.out.println("Skipping duplicate student ID: " + id);
                    }
                } catch (NumberFormatException exception) {
                    System.out.println("Skipping record with invalid ID: " + line);
                }
            }
        }

        return loadedCount;
    }
}
