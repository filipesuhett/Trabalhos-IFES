public class Exam {
    // Protected member variables to store the name, exam date, and grade.
    protected String name;
    protected Date dtExam;
    protected double grade;

    // Protected constructor to initialize the Exam object with provided name, date, and grade.
    protected Exam(String name, Date dtExam, double grade) {
        this.setName(name);
        this.setDtExam(dtExam);
        this.setGrade(grade);
    }

    // Method to calculate the grade for a specific student at the given index.
    public double grade(int index){
        return -1; // Default implementation returns -1, subclasses can override this method.
    }

    // Getter and setter methods for name, exam date, and grade.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDtExam() {
        return dtExam;
    }

    public void setDtExam(Date dtExam) {
        this.dtExam = dtExam;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
