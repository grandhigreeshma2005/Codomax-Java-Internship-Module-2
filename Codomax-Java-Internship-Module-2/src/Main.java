import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentManager manager = new StudentManager();
    private static final Path DATA_FILE = Path.of("students.txt");

    public static void main(String[] args) {
        loadExistingRecords();

        boolean running = true;

        while (running) {
            displayMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    displayStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    deleteStudent();
                    break;
                case 6:
                    saveStudents();
                    break;
                case 7:
                    saveStudents();
                    running = false;
                    System.out.println("Thank you for using Student Management System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1 to 7.");
            }
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\\n======================================");
        System.out.println("       STUDENT MANAGEMENT SYSTEM");
        System.out.println("======================================");
        System.out.println("1. Add Student");
        System.out.println("2. Display All Students");
        System.out.println("3. Search Student by ID");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Save Student Records");
        System.out.println("7. Save and Exit");
        System.out.println("--------------------------------------");
    }

    private static void addStudent() {
        System.out.println("\\n--- Add Student ---");
        int id = readPositiveInt("Enter student ID: ");
        String name = readRequiredText("Enter student name: ");
        String course = readRequiredText("Enter course: ");

        Student student = new Student(id, name, course);

        if (manager.addStudent(student)) {
            System.out.println("Student added successfully.");
        } else {
            System.out.println("A student with this ID already exists.");
        }
    }

    private static void displayStudents() {
        System.out.println("\\n--- Student Records ---");
        ArrayList<Student> students = manager.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println("Total students: " + manager.getStudentCount());
    }

    private static void searchStudent() {
        System.out.println("\\n--- Search Student ---");
        int id = readPositiveInt("Enter student ID to search: ");
        Student student = manager.findStudent(id);

        if (student == null) {
            System.out.println("Student not found.");
        } else {
            System.out.println("Student found:");
            System.out.println(student);
        }
    }

    private static void updateStudent() {
        System.out.println("\\n--- Update Student ---");
        int id = readPositiveInt("Enter student ID to update: ");
        Student existing = manager.findStudent(id);

        if (existing == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Current details: " + existing);
        String name = readRequiredText("Enter new name: ");
        String course = readRequiredText("Enter new course: ");

        if (manager.updateStudent(id, name, course)) {
            System.out.println("Student details updated successfully.");
        }
    }

    private static void deleteStudent() {
        System.out.println("\\n--- Delete Student ---");
        int id = readPositiveInt("Enter student ID to delete: ");

        if (manager.deleteStudent(id)) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void saveStudents() {
        try {
            manager.saveToFile(DATA_FILE);
            System.out.println("Student records saved to " + DATA_FILE + ".");
        } catch (IOException exception) {
            System.out.println("Unable to save records: " + exception.getMessage());
        }
    }

    private static void loadExistingRecords() {
        try {
            int count = manager.loadFromFile(DATA_FILE);
            System.out.println(count + " student record(s) loaded.");
        } catch (IOException exception) {
            System.out.println("Unable to load records: " + exception.getMessage());
        }
    }

    private static int readPositiveInt(String prompt) {
        while (true) {
            int value = readInt(prompt);
            if (value > 0) {
                return value;
            }
            System.out.println("Please enter a positive number.");
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    private static String readRequiredText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();

            if (value.isEmpty()) {
                System.out.println("This field cannot be empty.");
            } else if (value.contains("|") || value.contains("\\n")) {
                System.out.println("Please do not use the | character.");
            } else {
                return value;
            }
        }
    }
}
