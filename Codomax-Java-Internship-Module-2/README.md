# Student Management System – Codomax Java Internship (Module 02)

A beginner-friendly, menu-driven Java console application for managing student records. This project demonstrates Java methods, collections, exception handling, and file input/output.

## Features

- Add a student with an ID, name, and course.
- Display all saved student records.
- Search for a student by ID.
- Update a student's name and course.
- Delete a student record.
- Save records to `students.txt`.
- Load saved records automatically when the application starts.
- Validate user input and handle invalid numeric input.
- Prevent duplicate student IDs.

## Concepts Demonstrated

| Module topic | Where it is used |
|---|---|
| Methods | Separate methods for each menu operation |
| Parameters and return values | `StudentManager` methods |
| Method overloading | `findStudent(int)` searches by ID; `findStudent(String)` searches by name |
| Variable scope | Local variables and class-level fields |
| ArrayList | Maintains the list of student records |
| HashMap | Finds a student quickly using the ID as the key |
| HashSet | Tracks unique student IDs |
| try-catch | Handles invalid input and file errors |
| throw / throws | `Student` uses `throw` for invalid data; file methods declare `throws IOException` |
| File handling | Reads and writes `students.txt` |

> Note: The project uses `ArrayList`, `HashMap`, and `HashSet` together. `HashMap` provides ID-based lookup, while `HashSet` enforces unique IDs.

## Project Structure

```text
Codomax-Java-Internship-Module-2/
├── src/
│   ├── Main.java
│   ├── Student.java
│   └── StudentManager.java
├── students.txt
├── .gitignore
└── README.md
```

## Requirements

- JDK 11 or later
- Command Prompt / Terminal, IntelliJ IDEA, Eclipse, or VS Code

## How to Run

Open a terminal in the project root folder.

### 1. Compile

```bash
javac -d out src/Student.java src/StudentManager.java src/Main.java
```

### 2. Run

```bash
java -cp out Main
```

The program creates or updates `students.txt` in the folder from which you run it. Existing records are loaded automatically at startup.

## Menu

```text
======================================
       STUDENT MANAGEMENT SYSTEM
======================================
1. Add Student
2. Display All Students
3. Search Student by ID
4. Update Student
5. Delete Student
6. Save Student Records
7. Save and Exit
```

## Data Storage

Each student is stored as one line in `students.txt` using this format:

```text
studentId|studentName|course
```

Example:

```text
101|Ananya|Computer Science
102|Rahul|Information Technology
```

Do not use the `|` character in a name or course because it separates the fields.

## GitHub Upload

1. Create a new GitHub repository named `Codomax-Java-Internship-Module-2`.
2. Extract this project ZIP.
3. Open a terminal inside the extracted project folder.
4. Run:

```bash
git init
git add .
git commit -m "完成 Module 02: Student Management System"
git branch -M main
git remote add origin https://github.com/YOUR-USERNAME/Codomax-Java-Internship-Module-2.git
git push -u origin main
```

Replace `YOUR-USERNAME` with your GitHub username. If Git asks you to authenticate, complete the sign-in flow shown by Git.

## Possible Future Improvements

- Add email or phone number fields.
- Add sorting and filtering.
- Add a graphical user interface.
- Add unit tests.
- Store records in a database such as MySQL.

## Internship Deliverables

- [x] Java source code
- [x] Student Management System
- [x] README documentation
- [ ] Push the project to your GitHub account
- [ ] Add your GitHub repository link to your LinkedIn post

---

**Project:** Student Management System  
**Program:** Codomax Java Programming Internship – Module 02  
**Language:** Java
