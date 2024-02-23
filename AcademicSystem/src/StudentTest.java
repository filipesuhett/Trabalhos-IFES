public class StudentTest {
    private Student student;
    private double[] grades;

    public StudentTest(Student student, double[] grades) {
        this.setStudent(student);
        this.setGrades(grades);
    }

    public double totalGrade() {
        double total = 0;
        for (int i = 0; i < this.getGrades().length; i++) {
            total += this.getGrades()[i];
        }
        return total;
    }

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
