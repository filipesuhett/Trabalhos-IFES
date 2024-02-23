public class Test extends Exam{
    private int numQuestions;
    private StudentTest[] grades;

    public Test(String name, Date dtExam, double grade, int numQuestions, StudentTest[] grades) {
        super(name, dtExam, grade); // Chama o construtor da superclasse (Exam) com os parâmetros fornecidos.
        this.setNumQuestions(numQuestions);
        this.setGrades(grades);
    }

    public double grade(int index) {
        StudentTest studentTest = this.getGrades()[index];
        return studentTest.totalGrade();
    }

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
