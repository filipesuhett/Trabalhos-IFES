public class Classroom {
    // Private member variables to store the name, year, period, teacher, students, and exams.
    private String name;
    private int year;
    private int period;
    private Teacher teacher;
    private Student[] students;
    private Exam[] exams;

    // Constructor to initialize the Classroom object with provided parameters.
    public Classroom(String name, int year, int period, Teacher teacher, Student[] students, Exam[] exams) {
        // Set the name, year, period, teacher, students, and exams for this classroom.
        this.setName(name);
        this.setYear(year);
        this.setPeriod(period);
        this.setTeacher(teacher);
        this.setStudents(students);
        this.setExams(exams);
    }

    // Method to calculate and print the median grades for the classroom.
    public void median() {
        System.out.println("Médias da Turma " + this.getName() + " (" + this.getYear() + "/" + this.getPeriod() + "):");
        Exam[] exams = this.getExams();
        double classroomMedian = 0;

        for (int i = 0; i < this.getStudents().length; i++) {
            double studentTotal = 0;
            System.out.print(this.getStudents()[i]);
            double totalGrade = 0;

            for(int j = 0; j < this.getExams().length; j++){
                Exam exam = exams[j];
                double grade = exam.grade(i);
                studentTotal += grade;
                System.out.print(" " + grade);
                totalGrade += exam.getGrade();
            }

            if (studentTotal <= totalGrade) {
                System.out.println(" = " + studentTotal);
                classroomMedian += studentTotal;
            } else {
                System.out.println(" = " + totalGrade);
                classroomMedian += totalGrade;
            }
        }
        System.out.println("Média da turma: " + (classroomMedian / this.getStudents().length));
    }

    // Getter and setter methods for the private member variables.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }

    public Exam[] getExams() {
        return exams;
    }

    public void setExams(Exam[] exams) {
        this.exams = exams;
    }
}
