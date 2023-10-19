public class Assignment extends Exam {
    // Private member variables to store the expected runtime and an array of student assignments.
    private int runtimeExpected;
    private StudentAssignment[] grades;

    // Constructor to initialize the Assignment object with a name, date, grade, runtimeExpected, and grades.
    public Assignment(String name, Date dtExam, double grade, int runtimeExpected, StudentAssignment[] grades) {
        // Call the constructor of the superclass (Exam) with provided parameters.
        super(name, dtExam, grade);

        // Set the expected runtime and grades for this assignment.
        this.setRuntimeExpected(runtimeExpected);
        this.setGrades(grades);
    }

    // Method to calculate the grade for a specific student assignment at the given index.
    public double grade(int index){
        // Get the student assignment at the specified index.
        StudentAssignment studentAssignment = this.getGrades()[index];

        // Get the deadline from the exam date.
        Date deadline = this.getDtExam();

        // Calculate and return the total grade for this student assignment.
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
