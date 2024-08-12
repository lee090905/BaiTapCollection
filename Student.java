package day10.BaiTapCollection;
import java.util.*;

public class Student {
    private int id;
    private String name;
    private int age;

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "', age=" + age + '}';
    }
}

class ExamResult {
    private String subject;
    private double score;

    public ExamResult(String subject, double score) {
        this.subject = subject;
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "ExamResult{subject='" + subject + "', score=" + score + '}';
    }
}

class StudentExamManager {
    private Map<Student, List<ExamResult>> examResults = new HashMap<>();

    public void addExamResult(Student student, ExamResult result) {
        examResults.computeIfAbsent(student, k -> new ArrayList<>()).add(result);
    }

    public void displayExamResults(int studentId) {
        for (Student student : examResults.keySet()) {
            if (student.getId() == studentId) {
                System.out.println("Exam results for " + student + ":");
                for (ExamResult result : examResults.get(student)) {
                    System.out.println(result);
                }
                return;
            }
        }
        System.out.println("Student with ID " + studentId + " not found.");
    }

    public void displayAverageScores() {
        for (Student student : examResults.keySet()) {
            List<ExamResult> results = examResults.get(student);
            double average = results.stream().mapToDouble(ExamResult::getScore).average().orElse(0);
            System.out.println("Average score for " + student + ": " + average);
        }
    }

    public void displayTopStudent() {
        Student topStudent = null;
        double highestAverage = 0;
        for (Student student : examResults.keySet()) {
            List<ExamResult> results = examResults.get(student);
            double average = results.stream().mapToDouble(ExamResult::getScore).average().orElse(0);
            if (average > highestAverage) {
                highestAverage = average;
                topStudent = student;
            }
        }
        if (topStudent != null) {
            System.out.println("Top student: " + topStudent + " with average score: " + highestAverage);
        } else {
            System.out.println("No student data available.");
        }
    }

    public static void main(String[] args) {
        StudentExamManager manager = new StudentExamManager();

        Student student1 = new Student(1, "Alice", 20);
        Student student2 = new Student(2, "Bob", 21);
        Student student3 = new Student(3, "Charlie", 22);

        manager.addExamResult(student1, new ExamResult("Math", 90));
        manager.addExamResult(student1, new ExamResult("English", 85));
        manager.addExamResult(student2, new ExamResult("Math", 95));
        manager.addExamResult(student2, new ExamResult("English", 88));
        manager.addExamResult(student3, new ExamResult("Math", 92));
        manager.addExamResult(student3, new ExamResult("English", 91));

        manager.displayExamResults(1);
        manager.displayAverageScores();
        manager.displayTopStudent();
    }
}
