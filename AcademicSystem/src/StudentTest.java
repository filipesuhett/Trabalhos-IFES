public class StudentTest {
    // Private member variables to store the student and an array of grades.
    private Student student;
    private double[] grades;

    // Constructor to initialize the StudentTest object with provided student and grades.
    public StudentTest(Student student, double[] grades) {
        this.setStudent(student);
        this.setGrades(grades);
    }

    // Method to calculate the total grade for the test by summing up the grades.
    public double totalGrade() {
        double total = 0;
        for (int i = 0; i < this.getGrades().length; i++) {
            total += this.getGrades()[i];
        }
        return total;
    }

    // Getter and setter methods for student and grades.

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public double[] getGrades() {
        return grades;
    }

    public void setGrades(double[] grades) {
        this.grades = grades;
    }
}
