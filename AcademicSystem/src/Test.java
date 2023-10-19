public class Test extends Exam{
    // Private member variables to store the number of questions and an array of student tests.
    private int numQuestions;
    private StudentTest[] grades;

    // Constructor to initialize the Test object with provided name, date, grade, number of questions, and student tests.
    public Test(String name, Date dtExam, double grade, int numQuestions, StudentTest[] grades) {
        // Call the constructor of the superclass (Exam) with provided name, date, and grade.
        super(name, dtExam, grade);

        // Set the number of questions and student tests for this test.
        this.setNumQuestions(numQuestions);
        this.setGrades(grades);
    }

    // Method to calculate the grade for a specific student test at the given index.
    public double grade (int index){
        StudentTest studentTest = this.getGrades()[index];
        return studentTest.totalGrade();
    }

    // Getter and setter methods for the number of questions and student tests.

    public int getNumQuestions() {
        return numQuestions;
    }

    public void setNumQuestions(int numQuestions) {
        this.numQuestions = numQuestions;
    }

    public StudentTest[] getGrades() {
        return grades;
    }

    public void setGrades(StudentTest[] grades) {
        this.grades = grades;
    }
}
