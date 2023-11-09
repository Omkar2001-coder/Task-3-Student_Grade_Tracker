import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String name;
    private ArrayList<Course> courses = new ArrayList<>();

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }
}

class Course {
    private String name;
    private int grade;

    public Course(String name) {
        this.name = name;
        this.grade = 0;
    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}

public class GradeTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        while (true) {
            System.out.println("Grade Tracker Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Edit Grades");
            System.out.println("4. Display Grades");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String studentName = scanner.nextLine();
                    students.add(new Student(studentName));
                    System.out.println("Student added: " + studentName);
                    break;

                case 2:
                    if (students.isEmpty()) {
                        System.out.println("No students added yet. Add a student first.");
                    } else {
                        System.out.print("Enter student name: ");
                        String studentToAddCourse = scanner.nextLine();
                        Student student = findStudentByName(students, studentToAddCourse);

                        if (student != null) {
                            System.out.print("Enter course name: ");
                            String courseName = scanner.nextLine();
                            student.addCourse(new Course(courseName));
                            System.out.println("Course added to " + student.getName() + ": " + courseName);
                        } else {
                            System.out.println("Student not found.");
                        }
                    }
                    break;

                case 3:
                    if (students.isEmpty()) {
                        System.out.println("No students added yet. Add a student first.");
                    } else {
                        System.out.print("Enter student name: ");
                        String studentToEditGrades = scanner.nextLine();
                        Student student = findStudentByName(students, studentToEditGrades);

                        if (student != null) {
                            System.out.println("Courses for " + student.getName() + ":");
                            ArrayList<Course> courses = student.getCourses();
                            for (Course course : courses) {
                                System.out.println(courses.indexOf(course) + 1 + ". " + course.getName());
                            }

                            System.out.print("Enter the course number to edit grades: ");
                            int courseNumber = scanner.nextInt();
                            scanner.nextLine();  // Consume the newline character

                            if (courseNumber > 0 && courseNumber <= courses.size()) {
                                Course course = courses.get(courseNumber - 1);
                                System.out.print("Enter the new grade for " + course.getName() + ": ");
                                int newGrade = scanner.nextInt();
                                course.setGrade(newGrade);
                                System.out.println("Grade updated for " + course.getName() + ": " + newGrade);
                            } else {
                                System.out.println("Invalid course number.");
                            }
                        } else {
                            System.out.println("Student not found.");
                        }
                    }
                    break;

                case 4:
                    if (students.isEmpty()) {
                        System.out.println("No students added yet. Add a student first.");
                    } else {
                        for (Student student : students) {
                            System.out.println("Student: " + student.getName());
                            ArrayList<Course> courses = student.getCourses();
                            int totalGrades = 0;
                            for (Course course : courses) {
                                System.out.println("Course: " + course.getName() + ", Grade: " + course.getGrade());
                                totalGrades += course.getGrade();
                            }
                            double average = (double) totalGrades / courses.size();
                            System.out.println("Average Grade: " + average);
                            System.out.println();
                        }
                    }
                    break;

                case 5:
                    System.out.println("Exiting the Grade Tracker.");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option (1-5).");
            }
        }
    }

    private static Student findStudentByName(ArrayList<Student> students, String name) {
        for (Student student : students) {
            if (student.getName().equals(name)) {
                return student;
            }
        }
        return null;
    }
}
