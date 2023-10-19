public class StudentAssignment {
    // Private member variables to store the student, assignment grade, assignment date, and runtime.
    private Student student;
    private double gradeAssignment;
    private Date dtAssignment;
    private int runtime;

    // Constructor to initialize the StudentAssignment object with provided student, grade, date, and runtime.
    public StudentAssignment(Student student, double gradeAssignment, Date dtAssignment, int runtime) {
        this.setStudent(student);
        this.setGradeAssignment(gradeAssignment);
        this.setDtAssignment(dtAssignment);
        this.setRuntime(runtime);
    }

    // Method to calculate the total grade for the assignment based on various conditions.
    public double totalGrade(Date deadline, int runtimeExpected, double grade) {
        double gradeAssignment = this.getGradeAssignment();

        if (this.getDtAssignment().posterior(deadline)) {
            gradeAssignment = gradeAssignment * 0.8;
        } else if (this.getRuntime() <= runtimeExpected && this.getGradeAssignment() == grade) {
            gradeAssignment = gradeAssignment + 2;
        }

        return gradeAssignment;
    }

    // Getter and setter methods for student, assignment date, runtime, and grade.

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Date getDtAssignment() {
        return dtAssignment;
    }

    public void setDtAssignment(Date dtAssignment) {
        this.dtAssignment = dtAssignment;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public double getGradeAssignment() {
        return gradeAssignment;
    }

    public void setGradeAssignment(double gradeAssignment) {
        this.gradeAssignment = gradeAssignment;
    }
}
