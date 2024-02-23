public class Assignment extends Exam {
    private int runtimeExpected;
    private StudentAssignment[] grades;

    public Assignment(String name, Date dtExam, double grade, int runtimeExpected, StudentAssignment[] grades) {
        super(name, dtExam, grade); // Chama o construtor da superclasse (Exam) com os parâmetros fornecidos.
        this.setRuntimeExpected(runtimeExpected);
        this.setGrades(grades);
    }

    public double grade(int index) {
        StudentAssignment studentAssignment = this.getGrades()[index];
        Date deadline = this.getDtExam();
        return studentAssignment.totalGrade(deadline, this.getRuntimeExpected(), this.getGrade());
    }

    // Getter and setter for runtimeExpected.
    public int getRuntimeExpected() {
        return runtimeExpected;
    }

    public void setRuntimeExpected(int runtimeExpected) {
        this.runtimeExpected = runtimeExpected;
    }

    // Getter and setter for grades.
    public StudentAssignment[] getGrades() {
        return grades;
    }

    public void setGrades(StudentAssignment[] grades) {
        this.grades = grades;
    }
}
