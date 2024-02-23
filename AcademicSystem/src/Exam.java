public abstract class Exam {
    protected String name;
    protected Date dtExam;
    protected double grade;

    protected Exam(String name, Date dtExam, double grade) {
        this.setName(name);
        this.setDtExam(dtExam);
        this.setGrade(grade);
    }

    public abstract double grade(int index);

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
